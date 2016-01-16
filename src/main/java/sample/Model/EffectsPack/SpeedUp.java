package sample.Model.EffectsPack;

import sample.Model.Ball;
import sample.Model.Board;
import sample.Model.Coordinates;
import sample.Model.Effects;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class SpeedUp extends Effects {
    public SpeedUp(Coordinates coordinates) {
        super(coordinates);
        numberOfColor = 5;
    }

    @Override
    public void use(Board board) {
        int number = new Random().nextInt(board.getBalls().size());
        Ball ball= board.getBalls().get(number);
        ball.changeSpeed(1.25);
        Timer timer = new Timer();
        timer.schedule(new TimerTask(){
            @Override
            public void run(){
                ball.changeSpeed(0.8);
                expired=true;
            }
        },1000*10);
    }
}