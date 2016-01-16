package test.java;

import org.junit.Test;
import sample.Controller.BallCollisionChecker;
import sample.Model.Ball;
import sample.Model.Board;
import sample.Model.Coordinates;
import sample.Model.Paddle;

import static junit.framework.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CheckPaddleTest{

    Board board = new Board();
    Paddle paddle;
    Ball ball;
    BallCollisionChecker checker= new BallCollisionChecker(board);

    @Test
    public void testPaddle(){
        paddle = board.getPaddle();
        for(int i= 1; i < 160; ++i) {
            Coordinates position = new Coordinates(Board.TOTAL_WIDTH / 2 - 80, Board.TOTAL_HEIGHT - paddle.getHeight() - 2);
            ball = new Ball(position);
            checker.setBall(ball);
            ball.init();
            ball.flip(0,-1);
            ball.nextPosition();
            if(ball.getPosition().getX()>=paddle.getPosition()-paddle.getWidth()/2
                    && ball.getPosition().getX()<=paddle.getPosition()+paddle.getWidth()/2){
                assertTrue(checker.checkPaddle(paddle));
            }else {
                assertFalse(checker.checkPaddle(paddle));
            }
        }
    }
}
