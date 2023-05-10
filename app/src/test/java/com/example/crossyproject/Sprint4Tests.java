package com.example.crossyproject;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.mockito.Mockito;
//import org.mockito.junit.MockitoJUnitRunner;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Sprint4Tests {
    private Logic logic;
    private Logic logic2;


    private Road road;

    private Vehicle vehicle;
    private GameSurface game;

    @Before
    public void setUp() {
        logic = new Logic(0, 0);
        logic2 = new Logic(0, 0);
        vehicle = new Vehicle(0, 0, 4, Mockito.mock(Sprite.class));
    }

    @Test
    public void testCheckCollision() {
        assertEquals(true, logic.checkCollision(logic, logic2));
        logic.setRow(0);
        logic.setCol(0);
        logic.moveDown();
        assertEquals(1, logic.getRow());
        assertEquals(0, logic.getCol());
        logic.moveRight();
        assertEquals(1, logic.getRow());
        assertEquals(1, logic.getCol());
        assertEquals(false, logic.checkCollision(logic, logic2));
    }

    @Test
    public void testCalculateScoreIncrease() {
        logic = new Logic(2, 0);
        logic.moveUp();
        assertEquals(1, logic.getRow());
        assertEquals(0, logic.getCol());
        assertEquals(20, logic.getLength());
        assertEquals(210, logic.getScore());


        logic.moveUp();
        assertEquals(0, logic.getRow());
        assertEquals(0, logic.getCol());
        assertEquals(420, logic.getScore());

        logic.moveDown();
        assertEquals(1, logic.getRow());
        assertEquals(0, logic.getCol());
        assertEquals(210, logic.getScore());
    }

    @Test
    public void testCalculateScoreDecrease() {
        logic = new Logic(2, 0);
        logic.setScore(500);
        assertEquals(2, logic.getRow());
        assertEquals(0, logic.getCol());
        assertEquals(500, logic.getScore());

        logic.moveDown();
        assertEquals(3, logic.getRow());
        assertEquals(0, logic.getCol());
        assertEquals(290, logic.getScore());

        logic.moveDown();
        assertEquals(4, logic.getRow());
        assertEquals(0, logic.getCol());
        assertEquals(80, logic.getScore());
    }

    @Test
    public void testCheckCollisionAndScore() {
        assertEquals(true, logic.checkCollision(logic, logic2));
        assertEquals(0, logic.getScore());
        logic.setScore(500);
        logic.setRow(0);
        logic.setCol(0);
        logic.moveDown();
        assertEquals(1, logic.getRow());
        assertEquals(0, logic.getCol());
        assertEquals(490, logic.getScore());

        logic.moveRight();
        assertEquals(1, logic.getRow());
        assertEquals(1, logic.getCol());
        assertEquals(false, logic.checkCollision(logic, logic2));
        assertEquals(490, logic.getScore());

        logic.moveUp();
        logic.moveLeft();
        assertEquals(true, logic.checkCollision(logic, logic2));
        assertEquals(0, logic.getScore());
    }

    @Test
    public void collisionAndLives() {
        assertEquals(true, logic.checkCollision(logic, logic2));
        logic.setRow(0);
        logic.setCol(0);
        assertEquals(2, logic.getLives());
        logic.moveDown();
        assertEquals(1, logic.getRow());
        assertEquals(0, logic.getCol());
        logic.moveRight();
        assertEquals(1, logic.getRow());
        assertEquals(1, logic.getCol());
        assertEquals(false, logic.checkCollision(logic, logic2));
        assertEquals(2, logic.getLives());
        logic.moveUp();
        logic.moveLeft();
        assertEquals(true, logic.checkCollision(logic, logic2));
        assertEquals(1, logic.getLives());
    }

    @Test
    public void respawnAndCollision() {
        assertEquals(0, logic.getRow());
        assertEquals(0, logic.getCol());
        assertEquals(true, logic.checkCollision(logic, logic2));
        assertEquals(10, logic.getRow());
        assertEquals(10, logic.getCol());
        logic.moveUp();
        assertEquals(9, logic.getRow());
        assertEquals(10, logic.getCol());
        assertEquals(10, logic.getScore());
        logic2 = new Logic(9, 10);
        assertEquals(true, logic.checkCollision(logic, logic2));
        assertEquals(10, logic.getRow());
        assertEquals(10, logic.getCol());
    }

    @Test
    public void scoreWhenRespawned() {
        logic = new Logic(2, 0);
        logic.setScore(500);
        assertEquals(2, logic.getRow());
        assertEquals(0, logic.getCol());
        assertEquals(500, logic.getScore());

        logic.moveDown();
        assertEquals(3, logic.getRow());
        assertEquals(0, logic.getCol());
        assertEquals(290, logic.getScore());

        logic.moveDown();
        assertEquals(4, logic.getRow());
        assertEquals(0, logic.getCol());
        assertEquals(80, logic.getScore());

        logic2 = new Logic(4, 0);
        assertEquals(true, logic.checkCollision(logic, logic2));
        assertEquals(10, logic.getRow());
        assertEquals(10, logic.getCol());
        assertEquals(0, logic.getScore());
    }

    @Test
    public void gameOverScreenDisplayed() {
        assertEquals(true, logic.checkCollision(logic, logic2));
        logic.setRow(0);
        logic.setCol(0);
        assertEquals(2, logic.getLives());
        logic.moveDown();
        assertEquals(1, logic.getRow());
        assertEquals(0, logic.getCol());
        logic.moveRight();
        assertEquals(1, logic.getRow());
        assertEquals(1, logic.getCol());
        assertEquals(false, logic.checkCollision(logic, logic2));
        assertEquals(2, logic.getLives());
        logic.moveUp();
        logic.moveLeft();
        assertEquals(true, logic.checkCollision(logic, logic2));
        assertEquals(1, logic.getLives());
        logic.setCol(0);
        logic.setRow(0);
        assertEquals(true, logic.checkCollision(logic, logic2));
        assertEquals(0, logic.getLives());
        assertEquals(true, logic.getGameOverScreenStatus());
    }

    @Test
    public void hitWaterTileLocationReset() {
        logic2.setTileType("water");
        logic2.setRow(2);
        logic2.setCol(2);
        List<Logic> tiles = new LinkedList<>();
        tiles.add(logic2);
        logic.setRow(2);
        logic.setCol(2);
        logic.updateWater(tiles);
        assertEquals(10, logic.getRow());
        assertEquals(10, logic.getCol());
    }

    @Test
    public void hitWaterTileScoreReset() {
        logic2.setTileType("water");
        logic2.setRow(2);
        logic2.setCol(2);
        List<Logic> tiles = new LinkedList<>();
        tiles.add(logic2);
        logic.setRow(2);
        logic.setCol(2);
        logic.updateWater(tiles);
        assertEquals(0, logic.getScore());
    }

    @Test
    public void hitWaterTileLivesAdjusted() {
        logic2.setTileType("water");
        logic2.setRow(2);
        logic2.setCol(2);
        List<Logic> tiles = new LinkedList<>();
        tiles.add(logic2);
        logic.setRow(2);
        logic.setCol(2);
        logic.updateWater(tiles);
        assertEquals(2, logic.getLives());
    }

    @Test
    public void maxScore() {
        logic.setScore(500);
        logic.setRow(0);
        logic.setCol(0);
        logic.moveDown();
        assertEquals(1, logic.getRow());
        assertEquals(0, logic.getCol());
        assertEquals(490, logic.getScore());

        logic.moveRight();
        assertEquals(1, logic.getRow());
        assertEquals(1, logic.getCol());
        logic2.setCol(1);
        logic2.setRow(1);
        logic.setScore(500);
        assertEquals(true, logic.checkCollision(logic, logic2));
        assertEquals(0, logic.getScore());
        assertEquals(500, logic.getMaxScore());


        logic.setScore(700);
        logic.setRow(0);
        logic.setCol(0);
        logic.moveDown();
        assertEquals(1, logic.getRow());
        assertEquals(0, logic.getCol());
        assertEquals(690, logic.getScore());

        logic.moveRight();
        assertEquals(1, logic.getRow());
        assertEquals(1, logic.getCol());
        logic2.setCol(1);
        logic2.setRow(1);
        logic.setScore(700);
        assertEquals(true, logic.checkCollision(logic, logic2));
        assertEquals(0, logic.getScore());
        assertEquals(700, logic.getMaxScore());

    }


}
