package com.example.crossyproject;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import org.mockito.Mockito;
//import org.mockito.junit.MockitoJUnitRunner;

import java.util.Random;

public class Sprint2NormalTests {

    private Logic logic;
    private Road road;

    private Vehicle vehicle;
    private GameSurface game;

    @Before
    public void setUp() {
        logic = new Logic();
        vehicle = new Vehicle(0, 0, 4, Mockito.mock(Sprite.class));
    }

    @Test
    public void checkScore() {
        logic.incrementScore(10);
        assertEquals(10, logic.getScore());
        logic.incrementScore(-10);
        assertEquals(10, logic.getScore());
        logic.incrementScore(5);
        assertEquals(15, logic.getScore());
        logic.incrementScore(2);
        assertEquals(17, logic.getScore());
        logic.incrementScore(0);
        assertEquals(17, logic.getScore());
    }

    @Test
    public void testReadyToSpawn() {
        logic.setMinUpdatesUntilNextSpawnTest(3);
        logic.setMinUpdatesPerSpawnTest(0);
        assertEquals(logic.isReadyToSpawn(0), false);
        assertEquals(logic.getMinUpdatesUntilNextSpawnTest(), 2);

        logic.setMinUpdatesUntilNextSpawnTest(-2);
        logic.setMinUpdatesPerSpawnTest(3);
        assertEquals(logic.isReadyToSpawn(0), true);
        assertEquals(logic.getMinUpdatesUntilNextSpawnTest(), 3);

        logic.setMinUpdatesUntilNextSpawnTest(-1);
        logic.setMinUpdatesPerSpawnTest(3);
        Random rand = new Random();
        int temp = rand.nextInt(90);

        boolean check = true;

        if (temp != 0) {
            check = false;
        }

        assertEquals(check, logic.isReadyToSpawn(temp));
        assertEquals(logic.isReadyToSpawn(0), true);
        assertEquals(logic.getMinUpdatesUntilNextSpawnTest(), 3);
    }

    @Test
    public void crossTest() {
        int score = 0;
        score += logic.crossTest();
        assertEquals(0, score);
        logic.setVisited(false);
        score += logic.crossTest();
        assertEquals(10, score);
        assertEquals(true, logic.isVisited());
        score += logic.crossTest();
        assertEquals(10, score);

    }

    @Test
    public void getTileSpriteTest() {
        assertEquals(null, logic.getTileSprite(4));
        assertEquals(null, logic.getTileSprite(-1));
        assertEquals("Sprite will be returned", logic.getTileSprite(3));
        assertEquals("Sprite will be returned", logic.getTileSprite(3));
        assertEquals("Sprite will be returned", logic.getTileSprite(0));
    }

    @Test
    public void getVehicleSpriteTests() {
        String check = logic.getVehicleSpriteTest(0, 1);
        assertEquals("0 not flipped", check);
        check = logic.getVehicleSpriteTest(1, 1);
        assertEquals("1 not flipped", check);
        check = logic.getVehicleSpriteTest(2, 1);
        assertEquals("2 not flipped", check);

        check = logic.getVehicleSpriteTest(0, -1);
        assertEquals("0 flipped", check);
        check = logic.getVehicleSpriteTest(1, -1);
        assertEquals("1 flipped", check);
        check = logic.getVehicleSpriteTest(2, -1);
        assertEquals("2 flipped", check);
    }

    @Test
    public void getPlayerSpriteTest() {
        String check = logic.getPlayerSpriteTest(3);
        assertEquals(null, check);
        check = logic.getPlayerSpriteTest(-1);
        assertEquals(null, check);
        check = logic.getPlayerSpriteTest(0);
        assertEquals("0 128", check);
        check = logic.getPlayerSpriteTest(1);
        assertEquals("128 256", check);
        check = logic.getPlayerSpriteTest(2);
        assertEquals("256 384", check);
    }

    @Test
    public void ifVehicleMovesLeft() {
        vehicle.update();
        assertEquals(4, vehicle.getX());
        vehicle.update();
        vehicle.update();
        vehicle.update();
        assertEquals(16, vehicle.getX());
        vehicle.update();
        vehicle.update();
        assertEquals(24, vehicle.getX());
    }

    @Test
    public void ifVehicleMovesRight() {
        vehicle = new Vehicle(0, 1,-4, Mockito.mock(Sprite.class));
        assertEquals(128, vehicle.getX());
        vehicle.update();
        assertEquals(124, vehicle.getX());
        vehicle.update();
        vehicle.update();
        vehicle.update();
        assertEquals(112, vehicle.getX());
        vehicle.update();
        vehicle.update();
        assertEquals(104, vehicle.getX());
    }

    @Test
    public void ifScoreIncrementsRight() {
//        Player temp = new Player(5,5,Mockito.mock(Sprite.class), Mockito.mock(GameSurface.class));
        Logic temp = new Logic();
        temp.setRow(5);
        temp.setCol(0);
        assertEquals(0, temp.getCol());
        assertEquals(5, temp.getRow());
        temp.moveRight();
        assertEquals(1, temp.getCol());
        assertEquals(5, temp.getRow());
        temp.moveRight();
        assertEquals(2, temp.getCol());
        assertEquals(5, temp.getRow());
        temp.moveRight();
        temp.moveRight();
        temp.moveRight();
        assertEquals(5, temp.getCol());
        assertEquals(5, temp.getRow());
        assertEquals(0, temp.getScore());
    }

    @Test
    public void ifScoreIncrementsLeft() {
//        Player temp = new Player(5,5,Mockito.mock(Sprite.class), Mockito.mock(GameSurface.class));
        Logic temp = new Logic();
        temp.setRow(5);
        temp.setCol(5);
        assertEquals(5, temp.getCol());
        assertEquals(5, temp.getRow());
        temp.moveLeft();
        assertEquals(4, temp.getCol());
        assertEquals( 5, temp.getRow());
        temp.moveLeft();
        assertEquals(3, temp.getCol());
        assertEquals( 5, temp.getRow());
        temp.moveLeft(); // 2
        temp.moveLeft(); // 1
        assertEquals(1, temp.getCol());
        assertEquals( 5, temp.getRow());
        assertEquals(0, temp.getScore());
    }

    @Test
    public void ifScoreIncrementsUp() {
//        Player temp = new Player(5,5,Mockito.mock(Sprite.class), Mockito.mock(GameSurface.class));
        Logic temp = new Logic();
        temp.setRow(5);
        temp.setCol(5);
        assertEquals(5, temp.getCol());
        assertEquals(5, temp.getRow());
        temp.moveUp();
        assertEquals(5, temp.getCol());
        assertEquals( 4, temp.getRow());
        temp.moveUp();
        assertEquals(5, temp.getCol());
        assertEquals( 3, temp.getRow());
        temp.moveUp();
        temp.moveUp();
//        temp.moveUp();
        assertEquals(5, temp.getCol());
        assertEquals( 1, temp.getRow());
        assertEquals(40, temp.getScore());
    }

    @Test
    public void ifScoreIncrementsDown() {
//        Player temp = new Player(5,5,Mockito.mock(Sprite.class), Mockito.mock(GameSurface.class));
        Logic temp = new Logic();
        temp.setRow(0);
        temp.setCol(5);
        temp.setScore(50);
        assertEquals(5, temp.getCol());
        assertEquals(0, temp.getRow());
        temp.moveDown();
        assertEquals(5, temp.getCol());
        assertEquals(1, temp.getRow());
        temp.moveDown();
        assertEquals(5, temp.getCol());
        assertEquals(2, temp.getRow());
        temp.moveDown();
        temp.moveDown();
        temp.moveDown();
        assertEquals(5, temp.getCol());
        assertEquals(5, temp.getRow());
        assertEquals(0, temp.getScore());
    }
}
