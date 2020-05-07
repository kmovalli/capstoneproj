import functools
import numpy as np
import pandas as pd
import tensorflow as tf

TRAIN_DATA_URL = "https://skateboy.github.io/train2.csv"
TEST_DATA_URL = "https://skateboy.github.io/eval2.csv"

train_file_path = tf.keras.utils.get_file("train2.csv", TRAIN_DATA_URL)
test_file_path = tf.keras.utils.get_file("eval2.csv", TEST_DATA_URL)

np.set_printoptions(precision=3, suppress=True)

LABEL_COLUMN = 'nominal'
LABELS = [0, 1]

def get_dataset(file_path, **kwargs):
  dataset = tf.data.experimental.make_csv_dataset(
      file_path,
      batch_size=5, # Artificially small to make examples easier to show.
      label_name=LABEL_COLUMN,
      na_value="?",
      num_epochs=1,
      ignore_errors=True,
      **kwargs)
  return dataset

raw_train_data = get_dataset(train_file_path)
raw_test_data = get_dataset(test_file_path)

def show_batch(dataset):
  for batch, label in dataset.take(1):
    for key, value in batch.items():
      print("{:20s}: {}".format(key,value.numpy()))

show_batch(raw_train_data)

CSV_COLUMNS = ['p1', 'p2', 'p3', 'p4', 'p5', 'p6', 'uid', 'time', 'nominal']

temp_dataset = get_dataset(train_file_path, column_names=CSV_COLUMNS)

show_batch(temp_dataset)

SELECT_COLUMNS = ['p1', 'p2', 'p3', 'p4', 'p5', 'p6', 'uid', 'time', 'nominal']

temp_dataset = get_dataset(train_file_path, select_columns=SELECT_COLUMNS)

show_batch(temp_dataset)

SELECT_COLUMNS = ['p1', 'p2', 'p3', 'p4', 'p5', 'p6', 'uid', 'time', 'nominal']
DEFAULTS = [0, 0, 0, 0, 0, 0, 0, 0, 0]
temp_dataset = get_dataset(train_file_path,
                           select_columns=SELECT_COLUMNS,
                           column_defaults = DEFAULTS)

show_batch(temp_dataset)

example_batch, labels_batch = next(iter(temp_dataset))

def pack(features, label):
  return tf.stack(list(features.values()), axis=-1), label

packed_dataset = temp_dataset.map(pack)

for features, labels in packed_dataset.take(1):
  print(features.numpy())
  print()
  print(labels.numpy())

show_batch(raw_train_data)

example_batch, labels_batch = next(iter(temp_dataset))

class PackNumericFeatures(object):
  def __init__(self, names):
    self.names = names

  def __call__(self, features, labels):
    numeric_features = [features.pop(name) for name in self.names]
    numeric_features = [tf.cast(feat, tf.float32) for feat in numeric_features]
    numeric_features = tf.stack(numeric_features, axis=-1)
    features['numeric'] = numeric_features

    return features, labels

NUMERIC_FEATURES = ['p1', 'p2', 'p3', 'p4', 'p5', 'p6', 'time']

packed_train_data = raw_train_data.map(
    PackNumericFeatures(NUMERIC_FEATURES))

packed_test_data = raw_test_data.map(
    PackNumericFeatures(NUMERIC_FEATURES))

show_batch(packed_train_data)

example_batch, labels_batch = next(iter(packed_train_data))


desc = pd.read_csv(train_file_path)[NUMERIC_FEATURES].describe()
desc
MEAN = np.array(desc.T['mean'])
STD = np.array(desc.T['std'])

def normalize_numeric_data(data, mean, std):
  # Center the data
  return (data-mean)/std
normalizer = functools.partial(normalize_numeric_data, mean=MEAN, std=STD)

numeric_column = tf.feature_column.numeric_column('numeric', normalizer_fn=normalizer, shape=[len(NUMERIC_FEATURES)])
numeric_columns = [numeric_column]
numeric_column

example_batch['numeric']

numeric_layer = tf.keras.layers.DenseFeatures(numeric_columns)
numeric_layer(example_batch).numpy()

list_of_times = list(range(3649,8000))

CATEGORIES = {
    'p1' : ['0', '1'],
    'p2' : ['2', '3'],
    'p3' : ['2', '3'],
    'p4' : ['4', '5'],
    'p5' : ['4', '5'],
    'p6' : ['0', '6'],
    'time' : list_of_times
}

categorical_columns = []
for feature, vocab in CATEGORIES.items():
  cat_col = tf.feature_column.categorical_column_with_vocabulary_list(
        key=feature, vocabulary_list=vocab)

  categorical_columns.append(tf.feature_column.indicator_column(cat_col))


print(categorical_columns)

categorical_layer = tf.keras.layers.DenseFeatures(categorical_columns)
print(categorical_layer(example_batch).numpy()[0])
## categorical_columns+ to include both
preprocessing_layer = tf.keras.layers.DenseFeatures(categorical_columns+numeric_columns)

print(preprocessing_layer(example_batch).numpy()[0])

model = tf.keras.Sequential([
  preprocessing_layer,
  tf.keras.layers.Dense(128, activation='relu'),
  tf.keras.layers.Dense(128, activation='relu'),
  tf.keras.layers.Dense(1),
])
## from_logits=True VVV

model.compile(
    loss=tf.keras.losses.KLDivergence(),
    optimizer='adam',
    metrics=['accuracy'])

train_data = packed_train_data.shuffle(500)
test_data = packed_test_data

model.fit(train_data, epochs=20)


test_loss, test_accuracy = model.evaluate(test_data)

print('\n\nTest Loss {}, Test Accuracy {}'.format(test_loss, test_accuracy))

predictions = model.predict(test_data)

# Show some results
for prediction, nominal in zip(predictions[:10], list(test_data)[0][1][:10]):
  prediction = tf.sigmoid(prediction).numpy()
  print("Predicted for nominal performance: {:.2%}".format(prediction[0]),
        " | Actual outcome: ",
        ("NOMINAL" if bool(nominal) else "NOT NOMINAL"))

model.summary()

##model.save('NASAModel')

new_model = tf.keras.models.load_model('NASAModel')

# Check its architecture
new_model.summary()
