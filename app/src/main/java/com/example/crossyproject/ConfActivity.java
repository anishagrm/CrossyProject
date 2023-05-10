package com.example.crossyproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;

public class ConfActivity extends AppCompatActivity {
    private EditText editName;
    private RadioGroup rg;
    private int sprite = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conf);

        editName = (EditText) findViewById(R.id.editTextPlayerName);
        rg = (RadioGroup) findViewById(R.id.difficultyGroup);
        final Button confirm = findViewById(R.id.playButton);
        final ImageButton spriteButton0 = findViewById(R.id.chooseFrog0);
        final ImageButton spriteButton1 = findViewById(R.id.chooseFrog1);
        final ImageButton spriteButton2 = findViewById(R.id.chooseFrog2);
        spriteButton0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sprite = getResources().getResourceEntryName(v.getId()).charAt(10) - 48;
                // semi-important to-do: implement highlighting/color changing when clicked
            }
        });
        spriteButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sprite = getResources().getResourceEntryName(v.getId()).charAt(10) - 48;
                // semi-important to-do: implement highlighting/color changing when clicked
            }
        });
        spriteButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sprite = getResources().getResourceEntryName(v.getId()).charAt(10) - 48;
                // semi-important to-do: implement highlighting/color changing when clicked
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editName.getText().toString();
                int selected = rg.getCheckedRadioButtonId();
                if (checkValid(name) || selected == -1 || sprite == -1) {
                    return;
                }
                int difficulty;
                switch (getResources().getResourceEntryName(selected)) {
                    case "easy":
                        difficulty = 0;
                        break;
                    case "hard":
                        difficulty = 2;
                        break;
                    default:
                        difficulty = 1;
                }
                Intent intent = new Intent(ConfActivity.this, GameActivity.class);
                intent.putExtra("name", name);
                intent.putExtra("difficulty", difficulty);
                intent.putExtra("sprite", sprite);
                startActivity(intent);
            }
        });
    }

    public boolean checkValid(String name) {
        return name == null || name.trim().length() == 0;
    }
}