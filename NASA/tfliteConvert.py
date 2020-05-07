import tensorflow as tf

converter = tf.lite.TFLiteConverter.from_saved_model('NASAModelTF')
tflite_model = converter.convert()

open("NASAModel.tflite", "wb").write(tflite_model)
