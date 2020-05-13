import functools
import numpy as np
import pandas as pd
import tensorflow as tf

##my_data = genfromtxt('train2.csv', delimiter=',')

tst_data = tf.keras.utils.get_file('eval2.csv', 'skateboy.github.io/eval2.csv')

df2 = pd.read_csv(tst_data)
df2.head()
print(df2.dtypes)
print(df2.head())

target2 = df2.pop('nominal')
dataset2 = tf.data.Dataset.from_tensor_slices((df2.values, target2.values))

for feat2, targ2 in dataset2.take(5):
    print ('Features: {}, Target: {}'.format(feat2, targ2))

test_dataset = dataset2.shuffle(len(df2)).batch(1)

my_data = tf.keras.utils.get_file('train2.csv', 'skateboy.github.io/train2.csv')

df = pd.read_csv(my_data)
df.head()
print(df.dtypes)
print(df.head())

target = df.pop('nominal')
dataset = tf.data.Dataset.from_tensor_slices((df.values, target.values))

for feat, targ in dataset.take(5):
    print ('Features: {}, Target: {}'.format(feat, targ))

train_dataset = dataset.shuffle(len(df)).batch(1)

def get_compiled_model():
    model = tf.keras.Sequential([
      tf.keras.layers.Dense(10, activation='relu'),
      tf.keras.layers.Dense(10, activation='relu'),
      tf.keras.layers.Dense(1)
    ])

    model.compile('sgd', loss=tf.keras.losses.KLDivergence())
    return model

model = get_compiled_model()
model.fit(train_dataset, epochs=15)

evaluation = model.evaluate(test_dataset)
neweval = [[i] for i in evaluation]
print("Loss: %s" % neweval['loss'])
print("Accuracy: %f" % neweval['accuracy'])

model.predict(test_dataset)

model.summary()
model.save('NASAModelTF')
