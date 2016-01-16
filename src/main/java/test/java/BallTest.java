package test.java;

import org.junit.Test;
import sample.Model.Ball;
import sample.Model.Board;
import sample.Model.Coordinates;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BallTest{

    Ball ball =new Ball(new Coordinates(Board.TOTAL_WIDTH/2, Board.TOTAL_HEIGHT/2));

    @Test
    public void testInit(){
        assertFalse(ball.isAlive());
        ball.init();
        assertTrue(Math.abs(ball.getXspeed())==Math.abs(ball.getYspeed()));
        assertTrue(ball.isAlive());
        assertTrue(Math.pow(ball.getXspeed(),2)+Math.pow(ball.getYspeed(),2)<=Math.pow(Ball.TOTALSPEED,2));
        ball.flip(2,1);
        assertFalse(Math.abs(ball.getXspeed()) == Math.abs(ball.getYspeed()));
    }

    @Test
    public void testPaddleFlip(){
        ball.TOTALSPEED=10;
        ball.paddleFlip(-0.85);
        assertFalse(ball.getYspeed()==0);
        assertFalse(ball.getXspeed()==Ball.TOTALSPEED);
        assertTrue(ball.getXspeed()<0);
        assertTrue(Math.abs(ball.getXspeed())>Math.abs(ball.getYspeed()));
        ball.paddleFlip(-0.5);
        assertTrue(Math.abs(ball.getYspeed())==Math.abs(ball.getXspeed())+1);
        ball.paddleFlip(-0.3);
        assertTrue(ball.getXspeed()<0);
        assertTrue(Math.abs(ball.getXspeed())<Math.abs(ball.getYspeed()));
        ball.paddleFlip(0);
        assertTrue(ball.getXspeed()==0);
        ball.paddleFlip(0.3);
        assertTrue(ball.getXspeed()>0);
        assertTrue(Math.abs(ball.getXspeed())<Math.abs(ball.getYspeed()));
        ball.paddleFlip(0.85);
        assertFalse(ball.getYspeed()==0);
        assertFalse(ball.getXspeed()==Ball.TOTALSPEED);
        assertTrue(ball.getXspeed()>0);
        assertTrue(Math.abs(ball.getXspeed())>Math.abs(ball.getYspeed()));

    }
}
