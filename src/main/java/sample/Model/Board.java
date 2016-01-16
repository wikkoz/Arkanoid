package sample.Model;


import sample.Util.Function;
import sample.Util.State;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Board {

    public static State gameState = State.NONSTARTED;
    public static final int TOTAL_WIDTH = 881;
    public static final int TOTAL_HEIGHT = 600;
    private final  int STARTING_SPEED = 6;
    private final  double SPEED_LEVEL_RATIO = 0.5;
    private final Lock lock = new ReentrantLock();
    private final int levelModification = 2;
    private final int startingLayer = 3;
    private int score = 0;

    private final TimeLeft timer = new TimeLeft();
    private Map<Integer, Block> blocks = new HashMap<Integer, Block>();
    private List<Ball> balls = new ArrayList<Ball>();
    private Paddle paddle = new Paddle();
    private List<Effects> effects = new ArrayList<>();
    private int level;

    public List<Effects> getEffects() {
        return effects;
    }

    public Paddle getPaddle() {
        return paddle;
    }

    public List<Ball> getBalls() {
        return balls;
    }

    public Map<Integer, Block> getBlocks() {
        return blocks;
    }

    public Board(){}

    public Board(int level){
        this.level=level;
    }

    public void init(){
        gameState=State.NEXTLEVEL;
        balls.clear();
        effects.clear();
        Ball.TOTALSPEED = (int)(SPEED_LEVEL_RATIO*level)+STARTING_SPEED;
        int number = (level/levelModification + startingLayer)*(TOTAL_WIDTH/Block.WIDTH);
        BlocksFactory factory = new BlocksFactory();
        for(int i = 0; i<number; ++i){
            blocks.put(i, factory.createBlock(this, level));
        }
        paddle.reset(level, getEffects());
        //for(int i =0 ;i <20; i++)
            mutex(()->addNewBall());
        level++;
    }

    public int getNumberOfBalls(){
        return balls.size();
    }

    public synchronized void addNewBall(){
        Coordinates position = new Coordinates(paddle.getPosition(), Board.TOTAL_HEIGHT-paddle.getHeight());
        balls.add(new Ball(position));
    }

    public void mutex(Function function){
        lock.lock();
        function.perform();
        lock.unlock();
    }

    public int getScore() {
        return score;
    }

    public void increment(int i){
        score+=i;
    }

    public int getTime(){
        return timer.getTime();
    }

    public int getLevel() {
        return level;
    }
}
