package test.java;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import sample.Controller.BallCollisionChecker;
import sample.Model.Ball;
import sample.Model.Block;
import sample.Model.Board;
import sample.Model.Coordinates;


public class CheckCollisionWithBlocksTest {

    BallCollisionChecker checker;
    Board board;

    @Before
    public  void setUp() throws Exception{
        board = new Board();
        checker=new BallCollisionChecker(board);
        Block block = new Block(new Coordinates(160,90), 50);
        board.getBlocks().put(block.getIndex(), block);
    }

    @Test
    public void testBottom() throws Exception {
        Ball ball = new Ball(new Coordinates(180,122));
        ball.init();
        checker.setBall(ball);
        ball.nextPosition();
        Assert.assertEquals(true, checker.checkBlocks());

    }

    @Test
    public void testLeftSide() throws Exception {
        Ball ball = new Ball(new Coordinates(158,112));
        ball.init();
        checker.setBall(ball);
        ball.nextPosition();
        Assert.assertEquals(true,checker.checkBlocks());
    }

    @Test
    public void testUpperEdge() throws Exception {
        Ball ball = new Ball(new Coordinates(180,92));
        ball.init();
        ball.flip(-1,-1);
        checker.setBall(ball);
        ball.nextPosition();
        Assert.assertEquals(true,checker.checkBlocks());
    }

    @Test
    public void testRightSide() throws Exception {
        Ball ball = new Ball(new Coordinates(241,112));
        ball.init();
        ball.flip(-1,-1);
        checker.setBall(ball);
        ball.nextPosition();
        Assert.assertEquals(true,checker.checkBlocks());

    }

    @Test
    public void testFalse() throws Exception {
        Ball ball = new Ball(new Coordinates(1000,1504));
        ball.init();
        checker.setBall(ball);
        ball.nextPosition();
        Assert.assertEquals(false,checker.checkBlocks());
    }

}