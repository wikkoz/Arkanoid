package sample.Model.EffectsPack;

import sample.Model.Board;
import sample.Model.Coordinates;
import sample.Model.Effects;

import java.util.Timer;
import java.util.TimerTask;

public class Lengthen extends Effects {

    public Lengthen(Coordinates coordinates) {
        super(coordinates);
        numberOfColor = 1;
    }

    @Override
    public void use(final Board board) {
        board.getPaddle().changeWidth(2);
        Timer timer = new Timer();
        timer.schedule(new TimerTask(){
            @Override
            public void run(){
                board.getPaddle().changeWidth(0.5);
                expired=true;
            }
        },1000*10);
    }

}
