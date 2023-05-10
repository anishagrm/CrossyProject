package com.example.crossyproject;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import org.mockito.Mock;
import org.mockito.Mockito;
//import org.mockito.junit.MockitoJUnitRunner;

import android.content.Context;
import android.app.Activity;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.mockito.Mockito.when;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

//    private static final int TIMEOUT = 200;
//    private Player temp;
//    private GameObject obj;
//
////    private GameActivity act = new GameActivity();
//    private GameSurface game;
////        = act.getGameSurface();
//
//    @Before
//    public void setUp() {
//        game = Mockito.mock(GameSurface.class);
//        obj = Mockito.mock(GameObject.class);
////        game = act.getGameSurface();
//        temp = new Player(5,5,7,7, game);
//
//        when(game.getCellLength()).thenReturn(30);
//        when(game.getWidth()).thenReturn(390);
//        when(game.getHeight()).thenReturn(960);
//    }
//
//
//    @Test
//    public void playerMovesProperly() {
//
//
//        temp  = new Player(5,5,7,7, game);
////        assertEquals(null, temp.gameSurface);
//        assertEquals(5, temp.getCol());
//        assertEquals(5, temp.getRow());
//        temp.moveLeft();
//        assertEquals(4, temp.getCol());
//        assertEquals( 5, temp.getRow());
//        temp.moveLeft();
//        assertEquals(3, temp.getCol());
//        assertEquals( 5, temp.getRow());
//        temp.moveUp();
//        assertEquals(3, temp.getCol());
//        assertEquals( 4, temp.getRow());
//        temp.moveRight();
//        assertEquals(4, temp.getCol());
//        assertEquals( 4, temp.getRow());
//        temp.moveUp();
//        assertEquals(4, temp.getCol());
//        assertEquals( 3, temp.getRow());
//        temp.moveLeft();
//        assertEquals(3, temp.getCol());
//        assertEquals( 3, temp.getRow());
//        temp.moveDown();
//        assertEquals(3, temp.getCol());
//        assertEquals( 4, temp.getRow());
//    }
//    @Test
//    public void stayOnBoard() {
//        temp = new Player(0,0,2,2, game);
//        assertEquals(0, temp.getCol());
//        assertEquals( 0, temp.getRow());
//        temp.moveUp();
//        assertEquals(0, temp.getCol());
//        assertEquals( 0, temp.getRow());
//        temp.moveLeft();
//        assertEquals(0, temp.getCol());
//        assertEquals( 0, temp.getRow());
//        temp = new Player(2,2,2,2, game);
//        temp.moveDown();
//        assertEquals(2, temp.getCol());
//        assertEquals( 3, temp.getRow());
//        temp.moveRight();
//        assertEquals(3, temp.getCol());
//        assertEquals( 3, temp.getRow());
//    }
//    @Test
//    public void checkUserNameValidity() {
//        String nonWhiteSpace = "hi";
//        String nullString = null;
//
//        ConfActivity activity = Mockito.mock(ConfActivity.class);
//
//        String whitespace = " ";
//        when(activity.checkValid(whitespace)).thenReturn(whitespace.trim().length() == 0 || whitespace == null);
//
//        //name.trim().length() == 0 || name == null
//        assertEquals(true, activity.checkValid(whitespace));
//        when(activity.checkValid(nullString)).thenReturn(nullString == null || nullString.trim().length() == 0);
//        assertEquals(true, activity.checkValid(nullString));
//        when(activity.checkValid(nonWhiteSpace)).thenReturn(nonWhiteSpace == null || nonWhiteSpace.trim().length() == 0);
//        assertEquals(false, activity.checkValid(nonWhiteSpace));
//    }
//
//    @Test
//    public void checkNumLives() {
////        GameActivity game = new GameActivity();
//        GameActivity thing = Mockito.mock(GameActivity.class);
//
//        int diff = 2;
//        int med = 1;
//        int easy = 0;
//
////        int diffCheck = 1;
//        when(thing.setLives(diff)).thenReturn(1);
//        assertEquals(1, thing.setLives(diff));
//        when(thing.setLives(med)).thenReturn(2);
//        assertEquals(2, thing.setLives(med));
//        when(thing.setLives(easy)).thenReturn(3);
//        assertEquals(3, thing.setLives(easy));
//
//    }
//
//    @Test
//    public void playerMovesLeft() {
//        temp = new Player(5,5,7,7, game);
//        assertEquals(5, temp.getCol());
//        assertEquals(5, temp.getRow());
//        temp.moveLeft();
//        assertEquals(4, temp.getCol());
//        assertEquals( 5, temp.getRow());
//        temp.moveLeft();
//        assertEquals(3, temp.getCol());
//        assertEquals( 5, temp.getRow());
//        temp.moveLeft(); // 2
//        temp.moveLeft(); // 1
//        assertEquals(1, temp.getCol());
//        assertEquals( 5, temp.getRow());
//    }
//
//    @Test
//    public void playerMovesUp() {
//        temp = new Player(5,5,7,7, game);
//        assertEquals(5, temp.getCol());
//        assertEquals(5, temp.getRow());
//        temp.moveUp();
//        assertEquals(5, temp.getCol());
//        assertEquals( 4, temp.getRow());
//        temp.moveUp();
//        assertEquals(5, temp.getCol());
//        assertEquals( 3, temp.getRow());
//        temp.moveUp();
//        temp.moveUp();
////        temp.moveUp();
//        assertEquals(5, temp.getCol());
//        assertEquals( 1, temp.getRow());
//    }
//
//    @Test
//    public void playerMovesRight() {
//        temp = new Player(5,0,7,7, game);
//        assertEquals(0, temp.getCol());
//        assertEquals(5, temp.getRow());
//        temp.moveRight();
//        assertEquals(1, temp.getCol());
//        assertEquals(5, temp.getRow());
//        temp.moveRight();
//        assertEquals(2, temp.getCol());
//        assertEquals(5, temp.getRow());
//        temp.moveRight();
//        temp.moveRight();
//        temp.moveRight();
//        assertEquals(5, temp.getCol());
//        assertEquals(5, temp.getRow());
//    }
//
//    @Test
//    public void PlayerMovesDown() {
//        temp = new Player(0,5,7,7, game);
//        assertEquals(5, temp.getCol());
//        assertEquals(0, temp.getRow());
//        temp.moveDown();
//        assertEquals(5, temp.getCol());
//        assertEquals(1, temp.getRow());
//        temp.moveDown();
//        assertEquals(5, temp.getCol());
//        assertEquals(2, temp.getRow());
//        temp.moveDown();
//        temp.moveDown();
//        temp.moveDown();
//        assertEquals(5, temp.getCol());
//        assertEquals(5, temp.getRow());
//    }
//
//    @Test
//    public void stayOnBoard2() {
//        temp = new Player(31,12,2,2, game);
//        assertEquals(12, temp.getCol());
//        assertEquals( 31, temp.getRow());
//        temp.moveDown();
//        assertEquals(12, temp.getCol());
//        assertEquals( 31, temp.getRow());
//        temp.moveRight();
//        assertEquals(12, temp.getCol());
//        assertEquals( 31, temp.getRow());
////        temp.moveUp();
////        assertEquals(12, temp.getCol());
////        assertEquals( 31, temp.getRow());
////        temp.moveLeft();
////        assertEquals(11, temp.getCol());
////        assertEquals( 30, temp.getRow());
//    }
//
//    @Test
//    public void stayOnBoard3() {
//        temp = new Player(31,0,2,2, game);
//        assertEquals(0, temp.getCol());
//        assertEquals( 31, temp.getRow());
//        temp.moveDown();
//        assertEquals(0, temp.getCol());
//        assertEquals( 31, temp.getRow());
//        temp.moveLeft();
//        assertEquals(0, temp.getCol());
//        assertEquals( 31, temp.getRow());
//        temp.moveRight();
//        assertEquals(1, temp.getCol());
//        assertEquals( 31, temp.getRow());
////        temp.moveUp();
////        assertEquals(1, temp.getCol());
////        assertEquals( 30, temp.getRow());
//    }
//
//    @Test
//    public void stayOnBoard4() {
//        temp = new Player(0,12,2,2, game);
//        assertEquals(12, temp.getCol());
//        assertEquals( 0, temp.getRow());
//        temp.moveUp();
//        assertEquals(12, temp.getCol());
//        assertEquals( 0, temp.getRow());
//        temp.moveRight();
//        assertEquals(12, temp.getCol());
//        assertEquals( 0, temp.getRow());
//        temp.moveDown();
//        assertEquals(12, temp.getCol());
//        assertEquals( 1, temp.getRow());
////        temp.moveLeft();
////        assertEquals(11, temp.getCol());
////        assertEquals( 1, temp.getRow());
//    }
//
//    @Test
//    public void movementAndStay() {
//        temp = new Player(0,0,2,2, game);
//        assertEquals(0, temp.getCol());
//        assertEquals( 0, temp.getRow());
//        temp.moveUp();
//        assertEquals(0, temp.getCol());
//        assertEquals( 0, temp.getRow());
//        temp.moveRight();
//        assertEquals(1, temp.getCol());
//        assertEquals( 0, temp.getRow());
//        temp.moveDown();
//        assertEquals(1, temp.getCol());
//        assertEquals( 1, temp.getRow());
//        temp.moveLeft();
//        temp.moveLeft();
//        assertEquals(1, temp.getCol());
//        assertEquals( 1, temp.getRow());
//        temp.moveUp();
//        assertEquals(1, temp.getCol());
//        assertEquals( 1, temp.getRow());
//        temp.moveUp();
//        assertEquals(1, temp.getCol());
//        assertEquals( 1, temp.getRow());
//    }

}