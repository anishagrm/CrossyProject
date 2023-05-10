package com.example.crossyproject;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Insets;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.view.WindowMetrics;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Manages objects, states, and rendering for the game
 */
public class GameSurface extends SurfaceView implements SurfaceHolder.Callback {
    private GameThread gameThread;
    private SurfaceHolder surfaceHolder;
    private Player player;
    private int startRow;
    private int startCol;
    private List<Vehicle> vehicleList = new ArrayList<Vehicle>();
    private Context context;
    private SpriteSheet spriteSheet;
    private int playerSpriteId;
    private TileMap tileMap;
    private int score = 0;
    private int lives;
    private Road[] roads;
    private double scorePenaltyPercentage = 0.5;

    // Keep this constructor to appease the layout magic
    public GameSurface(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.initialize(context);
    }
    // Keep this constructor to appease the layout magic
    public GameSurface(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.initialize(context);
    }

    private void initialize(Context context) {
        // Boilerplate
        this.setFocusable(true);
        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

        this.context = context; // Store context here as well (will be passed around a lot)
        spriteSheet = new SpriteSheet(context); // Load spriteSheet
        setLives(GameActivity.getLives()); // initial lives

        // Initialize (and draw) TileMap
        int[] screenDimensions = getScreenSize(context);
        tileMap = new TileMap(spriteSheet, screenDimensions[0], screenDimensions[1]);

        // Initialize player and player sprite
        startRow = TileMap.getNumRows() - 1;
        startCol = (TileMap.getNumColumns() - 1) / 2;
        player = new Player(startRow, startCol,
                spriteSheet.getPlayerSprite(GameActivity.getPlayerSpriteId()), this);

        // Initialize roads (aka vehicle spawners and score updates)
        roads = new Road[TileMap.getNumRows() - 2 - TileMap.getSafeRow()];
        for (int i = 0; i < roads.length; i++) {
            roads[i] = new Road(TileMap.getSafeRow() + (i + 1), context);
        }

        // Create main loop with GameThread
        gameThread = new GameThread(this, surfaceHolder);
    }

    private int[] getScreenSize(Context context) {
        // Get device's screen size
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        WindowMetrics metrics = wm.getCurrentWindowMetrics();
        // Get insets (to exclude from final calculation)
        WindowInsets windowInsets = metrics.getWindowInsets();
        Insets insets = windowInsets.getInsetsIgnoringVisibility(
                WindowInsets.Type.navigationBars() | WindowInsets.Type.displayCutout());
        int insetsWidth = insets.right + insets.left;
        int insetsHeight = insets.top + insets.bottom;
        // Get adjusted size
        final Rect bounds = metrics.getBounds();
        int[] screenDimensions = new int[2];
        screenDimensions[0] = bounds.width() - insetsWidth;
        screenDimensions[1] = bounds.height() - insetsHeight;
        return screenDimensions;
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        tileMap.draw(canvas);
        drawUPS(canvas);
        drawFPS(canvas);
        drawLives(canvas);
        drawScore(canvas);
        player.draw(canvas);
        
        for (Vehicle vehicle : vehicleList) {
            vehicle.draw(canvas);
        }
    }
    // Renders stats for Updates Per Second
    public void drawUPS(Canvas canvas) {
        String averageUPS = Double.toString(gameThread.getAverageUPS());
        Paint paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.black);
        paint.setColor(color);
        paint.setTextSize(32);
        canvas.drawText("UPS: " + averageUPS, 0, 40, paint);
    }
    // Renders stats for Frames Per Second below UPS
    public void drawFPS(Canvas canvas) {
        String averageFPS = Double.toString(gameThread.getAverageFPS());
        Paint paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.black);
        paint.setColor(color);
        paint.setTextSize(32);
        canvas.drawText("FPS: " + averageFPS, 0, 80, paint);
    }
    public void drawLives(Canvas canvas) {
        Paint paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.off_white);
        paint.setColor(color);
        paint.setTextSize(48);
        canvas.drawText(GameActivity.getDifficulties()[GameActivity.getDifficultyId()]
                + "  Lives: " + lives, 0, (TileMap.getNumRows() - 1) * 128 + 45, paint);
    }

    public void drawScore(Canvas canvas) {
        Paint paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.off_white);
        paint.setColor(color);
        paint.setTextSize(48);
        canvas.drawText(GameActivity.getName() + "  " + String.format("%06d", score), 0,
                (TileMap.getNumRows() - 1) * 128 + 95, paint);
    }
    public void update() {
        player.update();
        checkWaterCollision();
        checkToSpawnVehicles();
        updateVehiclePositions();
        vehicleCollisionAndOffscreen();
    }

    private void checkWaterCollision() {
        if (tileMap.getTileMap()[player.getRow()][player.getCol()] instanceof RiverTile) {
            loseLife();
        }
    }

    private void vehicleCollisionAndOffscreen() {
        Iterator<Vehicle> vehicleIterator = vehicleList.iterator();
        while (vehicleIterator.hasNext()) {
            Vehicle curr = vehicleIterator.next();
            if (GameObject.checkCollision(player, curr)) {
                loseLife();
            }

            if (curr.isOffscreen()) {
                vehicleIterator.remove();
            }
        }
    }

    private void loseLife() {
        reduceScoreBy(scorePenaltyPercentage);
        setLives(lives - 1);
        player.setPosition(startRow, startCol);
    }

    private void updateVehiclePositions() {
        for (Vehicle vehicle : vehicleList) {
            vehicle.update();
        }
    }

    private void checkToSpawnVehicles() {
        for (Road road : roads) {
            if (road.readyToSpawn()) {
                vehicleList.add(road.spawnVehicle());
            }
        }
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        gameThread.startLoop();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {

    }

    public Player getPlayer() {
        return player;
    }
    public Road[] getRoads() {
        return roads;
    }

    public void setLives(int lives) {
        this.lives = lives;
        if (lives <= 0) {
            gameThread.setRunning(false);
            GameActivity host = this.getActivity();
            host.gameOverActivity(this.score);
        }
    }

    private GameActivity getActivity() {
        if (context instanceof GameActivity) {
            return (GameActivity) context;
        }
        return null;
    }

    public void incrementScore(int increase) {
        if (increase > 0) {
            score += increase;
        }
    }

    private void reduceScoreBy(double percentage) {
        score = (int) (score * (1 - percentage));
    }
}
