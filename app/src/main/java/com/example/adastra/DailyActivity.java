package com.example.adastra;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class DailyActivity extends AppCompatActivity implements View.OnClickListener {
    long startTime;
    int timer = 0;
    Toast toast;
    // Interpreter tflite;
    Button submit;
    TextView outputNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_tasks);
        startTime = System.currentTimeMillis();

        /*Interpreter tflite;
        try {
            tflite = new Interpreter(file);
        } catch (Exception ex) {
            ex.printStackTrace();
        }*/
        final ImageButton button1 = findViewById(R.id.imageButton1);
        final ImageButton button2 = findViewById(R.id.imageButton2);
        final ImageButton button3 = findViewById(R.id.imageButton3);
        final ImageButton button4 = findViewById(R.id.imageButton4);
        final ImageButton button5 = findViewById(R.id.imageButton5);
        final ImageButton button6 = findViewById(R.id.imageButton6);

        submit = (Button) findViewById(R.id.submitButton) ;

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);

        /*submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                int prediction = doInference(submit);
                outputNumber.setText(prediction);
            }
        }*///);

    }

    @Override
    public void onClick(View v) {
        String ms;
        String message;
        int duration = Toast.LENGTH_SHORT;

        if (timer >= 30) {
            message = readFile();
            toast = Toast.makeText(this, message, Toast.LENGTH_LONG);
            toast.show();
        }
        switch (v.getId()) {
            case R.id.imageButton1:
                writeToFile("B1", this);
                ms = "" + (System.currentTimeMillis() - startTime);
                writeToFile(ms, this);
                timer = Integer.parseInt(ms);
                writeToFile(ms, this);
                toast = Toast.makeText(this, ms, duration);
                toast.show();
                break;
            case R.id.imageButton2:
                writeToFile("B2", this);
                ms = "" + (System.currentTimeMillis() - startTime);
                writeToFile(ms, this);
                timer = Integer.parseInt(ms);
                writeToFile(ms, this);
                toast = Toast.makeText(this, ms, duration);
                toast.show();
                break;
            case R.id.imageButton3:
                writeToFile("B3", this);
                ms = "" + (System.currentTimeMillis() - startTime);
                writeToFile(ms, this);
                timer = Integer.parseInt(ms);
                writeToFile(ms, this);
                toast = Toast.makeText(this, ms, duration);
                toast.show();
                break;
            case R.id.imageButton4:
                writeToFile("B4", this);
                ms = "" + (System.currentTimeMillis() - startTime);
                writeToFile(ms, this);
                timer = Integer.parseInt(ms);
                writeToFile(ms, this);
                toast = Toast.makeText(this, ms, duration);
                toast.show();
                break;
            case R.id.imageButton5:
                writeToFile("B5", this);
                ms = "" + (System.currentTimeMillis() - startTime);
                writeToFile(ms, this);
                timer = Integer.parseInt(ms);
                writeToFile(ms, this);
                toast = Toast.makeText(this, ms, duration);
                toast.show();
                break;
            case R.id.imageButton6:
                writeToFile("B6", this);
                ms = "" + (System.currentTimeMillis() - startTime);
                writeToFile(ms, this);
                timer = Integer.parseInt(ms);
                writeToFile(ms, this);
                toast = Toast.makeText(this, ms, duration);
                toast.show();
                break;

        }
    }

    private void writeToFile(String data, Context context) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("config.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String readFile() {
        String line = null;
        try {
            BufferedReader bReader = new BufferedReader(new InputStreamReader(openFileInput("config.txt")));
            StringBuffer text = new StringBuffer();
            while ((line = bReader.readLine()) != null) {
                text.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return line;
    }

   /* public void doInference(int butt1, butt2, butt3, butt4, butt5, butt6){
        int p1;
        int p2;
        int p3;
        int p4;
        int p5;
        int p6;

        p1 = int.valueOf(butt1);
        p2 = int.valueOf(butt2);
        p3 = int.valueOf(butt3);
        p4 = int.valueOf(butt4);
        p5 = int.valueOf(butt5);
        p6 = int.valueOf(butt6);

    }
    private MappedByteBuffer loadModelFile() throws IOException {
        AssetFileDescriptor fileDescriptor = this.getAssets().openFD("testmodel.tflite");
        FileInputStream inputStream = new FileInputStream(fileDescriptor.getFileDescriptor());
        FileChannel fileChannel = inputStream.getChannel();
        long startOffset = fileDescriptor.getStartOffset();
        long declaredLength = fileDescriptor.getDeclaredLength();
        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength);
    }
    interpreter.close();*/
}


