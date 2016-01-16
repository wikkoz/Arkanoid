package sample.Controller;

import sample.Model.Ball;
import sample.Model.Board;
import sample.Model.Effects;
import sample.Model.Paddle;
import sample.Util.State;
import sample.View.Repainter;

import java.util.Iterator;

public class BallsController implements Runnable {

    public BallsController(Board board, Repainter repainter) {
        this.board = board;
        this.repainter = repainter;
        this.paddle = board.getPaddle();
        collisionChecker = new BallCollisionChecker(board);
    }

    private Board board;
    private Repainter repainter;
    private Paddle paddle;
    private boolean isMoved;
    private BallCollisionChecker collisionChecker;


    @Override
    public void  run() {
        while (board.getNumberOfBalls() > 0) {
            board.mutex(()->ballMove());
            for (Effects effects : board.getEffects()) {
                effects.fall(board.getEffects());
            }
            if(board.getBlocks().isEmpty())
                board.init();
            repainter.repaint();
            try {
                Thread.sleep(10);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Board.gameState= State.ENDED;
        repainter.repaint();
    }

    public void ballMove(){
        Iterator<Ball> it = board.getBalls().iterator();
        while(it.hasNext()) {
            Ball ball = it.next();
            if (ball.isAlive()) {
                isMoved = false;
                collisionChecker.setBall(ball);
                ball.nextPosition();
                isMoved |= collisionChecker.checkBlocks();
                isMoved |= collisionChecker.checkUpperEdge();
                isMoved |= collisionChecker.checkLeftEdge();
                isMoved |= collisionChecker.checkRightEdge();
                isMoved |= collisionChecker.checkPaddle(paddle);;
                if (!isMoved) {
                    ball.move();
                }

                if (collisionChecker.outOfBoard() == true) {
                    it.remove();
                }
            }
        }
    }
}