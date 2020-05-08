package com.example.adastra;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class UserLoginActivity extends AppCompatActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userlogin);

        // Declaring items in layout
        final Button loginButton = findViewById(R.id.login);
        final Spinner userSpinner = findViewById(R.id.spinner);

        // Declare the array in the drop down
        ArrayAdapter<String> userAdapter = new ArrayAdapter<String>(UserLoginActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Users));
        userAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        userSpinner.setAdapter(userAdapter);

        // Sets the button to open MainActivity
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUserActivity();
            }
        });
    }
    public void openUserActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
