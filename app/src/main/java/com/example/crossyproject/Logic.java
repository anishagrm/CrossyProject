package com.example.crossyproject;

import java.util.List;

public class Logic {
    //this class is for testing purposes
    //methods from various classes will be pasted here to easily test w/o messing with ui elements

    private int score = 0;
    private int minUpdatesUntilNextSpawnTest = 0;
    private int minUpdatesPerSpawnTest = 0;

    private int scoreWhenCrossed = 10;

    private boolean visited = true;
    private boolean isWaterTile = false;
    private boolean isRoadTile = false;

    private int maxScore = 0;
    private int col;
    private int row;

    private int width = 10;

    private int gridDimension = 128;
    private int x;

    private int length;
    private int lives = 3;
    private boolean gameOverScreen = false;

    public Logic() {
        row = 0;
        col = 0;
    }

    public Logic(int row, int col) {
        this.row = row;
        this.col = col;
        x += 10;
        length = row * 10;
    }

    //from GameSurface
    public void incrementScore(int increase) {
        if (increase > 0) {
            setScore(getScore() + increase);
        }
    }

    public int getScore() {
        return this.score;
    }

    //from Road
    public boolean isReadyToSpawn(int rand) {
        if (getMinUpdatesUntilNextSpawnTest() <= 0) {
            // If it's been too long, skip the 1/90 chance to spawn
            if (getMinUpdatesUntilNextSpawnTest() > getMinUpdatesPerSpawnTest() * -1) {
                if (rand != 0) {
                    return false;
                }
            }
            setMinUpdatesUntilNextSpawnTest(getMinUpdatesPerSpawnTest());
            return true;
        } else {
            setMinUpdatesUntilNextSpawnTest(getMinUpdatesUntilNextSpawnTest() - 1);
            return false;
        }
    }

    public int crossTest() {
        if (!isVisited()) {
            setVisited(true);
            return scoreWhenCrossed;
        } else {
            return 0;
        }
    }


    //sprite sheet
    public String getTileSprite(int tileId) {
        if (tileId > 3 || tileId < 0) {
            return null;
        }
        int left = tileId * gridDimension;
        int right = (tileId + 1) * gridDimension;
        return new String("Sprite will be returned");
    }

    public String getVehicleSpriteTest(int vehicleSpriteId, int velocity) {
        String check = "";
        int length;
        boolean flipped = (velocity > 0);
        int top;
        int bottom;
        switch (vehicleSpriteId) {
        case 0:
            top = gridDimension * 2;
            bottom = gridDimension * 3;
            length = 1;
            check += "0";
            break;
        case 1:
            length = 2;
            top = gridDimension * 3;
            bottom = gridDimension * 4;
            check += "1";
            break;
        case 2:
            length = 3;
            top = gridDimension * 4;
            bottom = gridDimension * 5;
            check += "2";
            break;
        default:
            throw new IllegalArgumentException("Invalid vehicle sprite ID");
        }
        if (!flipped) {
            check += " flipped";
        } else {
            check += " not flipped";
        }
        return check;
    }

    public String getPlayerSpriteTest(int spriteId) {
        String check = "";
        if (spriteId > 2 || spriteId < 0) {
            return null;
        }
        int left = spriteId * gridDimension;
        check += left + " ";
        int right = (spriteId + 1) * gridDimension;
        check += right;
        return check;
    }

    //following methods are from Player.java
    public void moveLeft() {
        this.col = this.col - 1;
        setScore(getScore() + 0);
    }

    public void moveRight() {
        this.col = this.col + 1;
        setScore(getScore() + 0);
    }

    public void moveUp() {
        this.row = this.row - 1;
        //setScore(getScore() + 10);
        calculateScoreIncrease();
    }

    public void moveDown() {
        this.row = this.row + 1;
        //setScore(getScore() - 10);
        calculateScoreDecrease();
    }

    public void updateWater(List<Logic> arr) {
        for (Logic curr : arr) {
            if (curr.getCol() == this.col && this.getRow() == this.row) {
                score = 0;
                lives--;
                updateGameOver();
                row = 10;
                col = 10;
                break;
            }
        }
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getMinUpdatesUntilNextSpawnTest() {
        return minUpdatesUntilNextSpawnTest;
    }

    public int getScoreWhenCrossed() {
        return scoreWhenCrossed;
    }

    public boolean isVisited() {
        return visited;
    }

    public int getGridDimension() {
        return gridDimension;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setMinUpdatesUntilNextSpawnTest(int minUpdatesUntilNextSpawnTest) {
        this.minUpdatesUntilNextSpawnTest = minUpdatesUntilNextSpawnTest;
    }

    public int getMinUpdatesPerSpawnTest() {
        return minUpdatesPerSpawnTest;
    }

    public void setMinUpdatesPerSpawnTest(int minUpdatesPerSpawnTest) {
        this.minUpdatesPerSpawnTest = minUpdatesPerSpawnTest;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public boolean checkCollision(Logic o1, Logic o2) {
        if (o1.row == o2.row) {
            lives--;
            updateGameOver();
            row = 10;
            score = 0;
            col = 10;
            return (o1.x <= o2.x + o2.width && o1.x > o2.x - o1.width);
        }
        return false;
    }

    private void updateGameOver() {
        if (lives <= 0) {
            gameOverScreen = true;
        }
        maxScore = Math.max(maxScore, score);
    }

    public void calculateScoreIncrease() {
        score += 10 + length * 10;
    }

    public void calculateScoreDecrease() {
        score -= 10 + length * 10;
    }

    public int getLives() {
        return lives;
    }

    public int getLength() {
        return length;
    }

    public boolean getGameOverScreenStatus() {
        return gameOverScreen;
    }

    public boolean isWaterTile() {
        return isWaterTile;
    }

    public void setTileType(String type) {
        if (type.equalsIgnoreCase("water")) {
            isWaterTile = true;
        } else if (type.equalsIgnoreCase("road")) {
            isRoadTile = true;
        }
    }

    public boolean isRoadTile() {
        return isRoadTile;
    }

    public int getMaxScore() {
        return maxScore;
    }
}
