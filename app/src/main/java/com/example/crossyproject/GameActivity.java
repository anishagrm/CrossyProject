package com.example.crossyproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowInsets;
import android.view.WindowInsetsController;
import android.widget.Button;

public class GameActivity extends AppCompatActivity {
    private GameSurface gameSurface;
    private Button leftButton;
    private Button rightButton;
    private Button upButton;
    private Button downButton;
    //TO-DO: move all of this to GameSurface
    private static String[] difficulties = {"Easy", "Medium", "Hard"};
    private static int difficultyId;
    private static int lives;
    private static int playerSpriteId;
    private static String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Receive and initialize information from config
        Bundle extras = this.getIntent().getExtras();
        playerSpriteId = extras.getInt("sprite");
        lives = 6 - (2 * extras.getInt("difficulty"));
        difficultyId = extras.getInt("difficulty");
        name = extras.getString("name");

        // Create gameSurface (main logic/organizing class), set content view
        setContentView(R.layout.activity_game);

        // Hides status bar for full screen (could also try to achieve this in AndroidManifest)
        WindowInsetsController controller = getWindow().getInsetsController();
        if (controller != null) {
            controller.hide(WindowInsets.Type.statusBars());
        }
        gameSurface = this.findViewById(R.id.gameSurface);
        leftButton = this.findViewById(R.id.leftButton);
        rightButton = this.findViewById(R.id.rightButton);
        upButton = this.findViewById(R.id.upButton);
        downButton = this.findViewById(R.id.downButton);

        leftButton.setOnClickListener(v -> gameSurface.getPlayer().moveLeft());
        rightButton.setOnClickListener(v -> gameSurface.getPlayer().moveRight());
        upButton.setOnClickListener(v -> gameSurface.getPlayer().moveUp());
        downButton.setOnClickListener(v -> gameSurface.getPlayer().moveDown());
    }

    public void gameOverActivity(int score) {
        Intent intent = new Intent(GameActivity.this, GameOverActivity.class);
        intent.putExtra("score", score);
        startActivity(intent);
    }

    public static String[] getDifficulties() {
        return difficulties;
    }

    public static int getDifficultyId() {
        return difficultyId;
    }

    public static int getLives() {
        return lives;
    }

    public static int getPlayerSpriteId() {
        return playerSpriteId;
    }

    public static String getName() {
        return name;
    }

}