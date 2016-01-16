package test.java;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import sample.Controller.BallCollisionChecker;
import sample.Model.Ball;
import sample.Model.Board;
import sample.Model.Coordinates;

public class CheckEdgeTest  {

    BallCollisionChecker checker;
    Board board;

    @Before
    public void setUp() throws Exception {
        board= new Board();
        checker = new BallCollisionChecker(board);
    }

    @Test
    public void testCheckLeftEdge() throws Exception {
        Ball ball = new Ball(new Coordinates(1 ,100));
        ball.init();
        ball.flip(-1, 1);
        checker.setBall(ball);
        ball.nextPosition();
        Assert.assertEquals(true, checker.checkLeftEdge());
        ball.nextPosition();
        Assert.assertEquals(false, checker.checkLeftEdge());
    }

    @Test
    public void testCheckUpperEdge() throws Exception {
        Ball ball = new Ball(new Coordinates(100 ,2));
        ball.init();
        checker.setBall(ball);
        ball.nextPosition();
        Assert.assertEquals(true, checker.checkUpperEdge());
        ball.nextPosition();
        Assert.assertEquals(false, checker.checkUpperEdge());
    }

    @Test
    public void testCheckRightEdge() throws Exception {
        Ball ball = new Ball(new Coordinates(Board.TOTAL_WIDTH-2 ,100));
        ball.init();
        checker.setBall(ball);
        ball.nextPosition();
        Assert.assertEquals(true, checker.checkRightEdge());
        ball.nextPosition();
        Assert.assertEquals(false, checker.checkRightEdge());
    }

    @Test
    public void testOutOfBoard() throws Exception {
        Ball ball = new Ball(new Coordinates(100, Board.TOTAL_HEIGHT+3));
        ball.init();
        checker.setBall(ball);
        ball.nextPosition();
        Assert.assertEquals(true, checker.outOfBoard());
        ball = new Ball(new Coordinates(100, Board.TOTAL_HEIGHT-3));
        ball.init();
        checker.setBall(ball);
        ball.nextPosition();
        Assert.assertEquals(false, checker.outOfBoard());
    }
}