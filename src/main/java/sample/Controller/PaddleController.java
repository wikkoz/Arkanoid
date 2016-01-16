package sample.Controller;


import sample.Model.Ball;
import sample.Model.Board;
import sample.Model.Effects;
import sample.Model.Paddle;
import sample.Util.State;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Iterator;
import java.util.List;

public class PaddleController extends KeyAdapter implements Runnable {

    Paddle paddle;
    private List<Effects> paddleEffects;
    private List<Effects> lyingEffects;
    private Board board;

    public PaddleController(Board board){
        this.board=board;
        this.lyingEffects = board.getEffects();
    }

    public void init(){
        this.paddle=board.getPaddle();
        this.paddleEffects = board.getPaddle().getEffects();
    }

    private void getNewEffect(){
        Iterator<Effects> it = lyingEffects.iterator();
        while(it.hasNext()){
            Effects effect = it.next();
            if(effect.isDown() && paddle.checkPoint(effect.getPosition())) {
                paddleEffects.add(effect);
                effect.use(board);
                effect.stop();
                it.remove();
            }
        }
    }

    @Override
    public void  run(){
        while(true) {
            paddle.move();
            getNewEffect();
            Iterator<Effects> it = paddleEffects.iterator();
            while(it.hasNext()) {
                Effects effect = it.next();
                if (effect.isExpired())
                    it.remove();
            }
            if(paddle.getSpeed()!=0) {
                board.mutex(()->updateBalls());
            }
            try {
                Thread.sleep(20);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void updateBalls(){
        for (Ball ball : board.getBalls()) {
            ball.init();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        super.keyPressed(e);
        if(Board.gameState== State.NEXTLEVEL) {
            Board.gameState = State.STARTED;
            board.getBalls().get(0).init();
        }
        if(e.getKeyCode()==KeyEvent.VK_LEFT) {
            paddle.setSpeed(-1);
        }
        if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
            paddle.setSpeed(1);
        }
    }

    @Override
    public void keyReleased(KeyEvent e){
        if(e.getKeyCode()==KeyEvent.VK_LEFT) {
            paddle.setSpeed(0);
        }
        if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
            paddle.setSpeed(0);
        }
    }

}
