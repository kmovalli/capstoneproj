package com.example.adastra;

import androidx.appcompat.app.AppCompatActivity;
//import org.tensorflow;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    long startTime;
    int timer = 0;
    Toast toast;
    Button submit;
    TextView outputNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startTime = System.currentTimeMillis();

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

}


