package sample.Model;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public abstract class Effects {
    private final int speed = 8;
    public static final int radius = 10;
    private Timer timer;
    boolean isDown = false;
    protected int numberOfColor;
    protected boolean expired=false;

    public Effects(Coordinates coordinates){
        position = coordinates;
    }

    public int getNumberOfColor() {
        return numberOfColor;
    }

    public boolean isDown() {
        return isDown;
    }

    public Coordinates getPosition() {
        return position;
    }

    private Coordinates position;
    public abstract void use(Board board);

    public void fall(List<Effects> effects){
        if(position.y+radius >= Board.TOTAL_HEIGHT) {
            if(!isDown)
                startTimer(effects);
            isDown = true;
            position.y=Board.TOTAL_HEIGHT-radius;
        }
        else{
            position.y+=speed;
        }

    }

    private void startTimer(List<Effects> effects){
        timer = new Timer();
        timer.schedule(new TimerTask(){
            @Override
            public void run(){
                effects.remove(0);
            }
        },1000*2);
    }

    public boolean isExpired() {
        return expired;
    }

    public void stop(){
        timer.cancel();
        timer.purge();
    }
}
