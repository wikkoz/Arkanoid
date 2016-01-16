package sample.Model;


import sample.Util.State;

import java.util.Timer;
import java.util.TimerTask;

public class TimeLeft {
    private int time = 0;

    public TimeLeft(){
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                timeIncrement();
            }
        },1000,1000);
    }

    public void timeIncrement(){
        if(Board.gameState== State.STARTED)
            time++;
    }

    public int getTime(){
        return time;
    }
}
