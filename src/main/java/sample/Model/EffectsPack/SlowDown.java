package sample.Model.EffectsPack;

import sample.Model.Ball;
import sample.Model.Board;
import sample.Model.Coordinates;
import sample.Model.Effects;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class SlowDown extends Effects {
    public SlowDown(Coordinates coordinates) {
        super(coordinates);
        numberOfColor = 4;
    }

    @Override
    public void use(Board board) {
        int number = new Random().nextInt(board.getBalls().size());
        Ball ball= board.getBalls().get(number);
        ball.changeSpeed(0.5);
        Timer timer = new Timer();
        timer.schedule(new TimerTask(){
            @Override
            public void run(){
                ball.changeSpeed(2);
                expired=true;
            }
        },1000*10);
    }
}
