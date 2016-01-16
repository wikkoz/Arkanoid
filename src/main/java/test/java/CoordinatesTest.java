package test.java;

import org.junit.Test;
import sample.Model.Coordinates;

import java.util.Random;

import static org.junit.Assert.assertTrue;

public class CoordinatesTest{
    @Test
    public void testCopy(){
        Random rand = new Random();
        Coordinates first = new Coordinates(rand.nextInt(1000), rand.nextInt(1000));
        Coordinates second = new Coordinates(first);
        assertTrue(first.equals(second));
    }

    @Test
    public void testCheckingMove(){
        Random rand = new Random();
        int x = 0;
        int y = 0;
        Coordinates first = new Coordinates(rand.nextInt(1000), rand.nextInt(1000));
        Coordinates second = first.checkingMove(x,y);
        first.move(x,y);
        assertTrue(first.equals(second));
        x = -rand.nextInt(100);
        y = -rand.nextInt(100);
        second = first.checkingMove(x,y);
        first.move(x,y);
        assertTrue(first.equals(second));
        x = -rand.nextInt(100);
        y = rand.nextInt(100);
        second = first.checkingMove(x,y);
        first.move(x,y);
        assertTrue(first.equals(second));
        x = rand.nextInt(100);
        y = -rand.nextInt(100);
        second = first.checkingMove(x,y);
        first.move(x,y);
        assertTrue(first.equals(second));
    }

}
