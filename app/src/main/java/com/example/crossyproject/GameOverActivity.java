package com.example.crossyproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GameOverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_game_over);
        Bundle extras = this.getIntent().getExtras();

        int score = extras.getInt("score");
        String scoreString = "Final Score: " + score;
        TextView scoreTextView = this.findViewById(R.id.textView3);
        scoreTextView.setText(scoreString);
        Button exitBtn = this.findViewById(R.id.button2);
        exitBtn.setOnClickListener(v -> confActivity());
        Button playAgainBtn = this.findViewById(R.id.button);
        playAgainBtn.setOnClickListener(v -> welcomeActivity());
    }

    private void welcomeActivity() {
        Intent intent = new Intent(GameOverActivity.this, WelcomeActivity.class);
        startActivity(intent);
    }
    private void confActivity() {
        Intent intent = new Intent(GameOverActivity.this, ConfActivity.class);
        startActivity(intent);
    }
}
