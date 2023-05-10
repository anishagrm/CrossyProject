package com.example.crossyproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WelcomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button start = findViewById(R.id.startButton);
        start.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent page2  = new Intent(getApplicationContext(), ConfActivity.class);
                startActivity(page2);
            }
        }));
    }
}