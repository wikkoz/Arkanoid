package sample.Model.EffectsPack;

import sample.Model.Board;
import sample.Model.Coordinates;
import sample.Model.Effects;

import java.util.Timer;
import java.util.TimerTask;

public class Shorten extends Effects {
    public Shorten(Coordinates coordinates) {
        super(coordinates);
        numberOfColor = 3;
    }

    @Override
    public void use(Board board) {
        board.getPaddle().changeWidth(0.8);
        Timer timer = new Timer();
        timer.schedule(new TimerTask(){
            @Override
            public void run(){
                board.getPaddle().changeWidth(1.25);
                expired=true;
            }
        },1000*10);
    }
}
