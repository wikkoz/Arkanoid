package sample.Model;

import java.util.ArrayList;
import java.util.List;

public class Paddle {
    private int position;
    private int width = 150;
    private final int HEIGHT = 20;
    private int speed = 0;
    private int maxSpeed;
    private final double MAXRATIO =0.85;
    private final int STARTING_SPEED = 15;
    private List<Effects> effects = new ArrayList<Effects>();

    public Paddle(){}

    public int getHeight() {
        return HEIGHT;
    }

    public int getWidth() {
        return width;
    }

    public int getPosition() {
        return position;
    }

    public synchronized void move(){
        position=position + speed;
        if(position+width/2>Board.TOTAL_WIDTH)
            position=Board.TOTAL_WIDTH-width/2;
        if(position-width/2<0)
            position=width/2;
    }

    public int getSpeed() {
        return speed;
    }

    public boolean checkIfHasPoint(int x){
        return position-width/2<=x && position+width/2>=x;
    }

    public double getRatio(int x){
        double ratio = (x-position)/(double)width*2;
        if(ratio >MAXRATIO)
            ratio = MAXRATIO;
        if(ratio <-MAXRATIO)
            ratio = -MAXRATIO;
        return ratio;
    }

    public List<Effects> getEffects() {
        return effects;
    }

    public boolean checkPoint(Coordinates point) {
        int x = point.getX();
        return x>=position-width/2 && x<=position+width/2;
    }
    public void changeWidth(double howMuch){
        width *= howMuch;
    }

    public void setSpeed(int i ){
        speed=maxSpeed*i;
    }

    public void reset(int level, List<Effects> effects){
        position = Board.TOTAL_WIDTH/2;
        maxSpeed = STARTING_SPEED + level;
        effects.clear();
    }
}
