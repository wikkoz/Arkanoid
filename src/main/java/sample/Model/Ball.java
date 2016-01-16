package sample.Model;

public class Ball {
    public static int TOTALSPEED=10;
    public static final int radius = 15;
    private  Coordinates position;
    private int Xspeed=-10;
    private int Yspeed=-10;
    private double totalSpeed;
    private int power;
    private Coordinates nextPosition;
    boolean isAlive;

    public Ball(Coordinates position) {
        this.totalSpeed = TOTALSPEED;
        this.power = 1;
        this.position = position;
        isAlive=false;
    }

    public void init(){
        if(!isAlive) {
            double temp = totalSpeed / Math.sqrt(2);
            Xspeed = (int) temp;
            Yspeed = -Xspeed;
            isAlive = true;
        }
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void nextPosition(){
        nextPosition = position.checkingMove(Xspeed, Yspeed);
    }

    public Coordinates getNextPosition() {
        return nextPosition;
    }

    public void flip(int xMod, int yMod){
        Yspeed *= yMod;
        Xspeed *= xMod;
    }

    public void move(){
        position.move(Xspeed, Yspeed);    }

    public void paddleFlip(double ratioFromMiddle){
        double mod=Math.signum(ratioFromMiddle);
        double Xratio = Math.abs(ratioFromMiddle);
        double Yratio = (1-Math.abs(ratioFromMiddle));
        Xspeed = (int)((Math.sqrt(Xratio)*totalSpeed)*mod);
        Yspeed = -(int)Math.ceil((Math.sqrt(Yratio)*totalSpeed));

    }
    public void changeSpeed(double ratio){
        totalSpeed*=ratio;
        Xspeed*=ratio;
        Yspeed*=ratio;
    }

    public int getXspeed() {
        return Xspeed;
    }

    public int getYspeed() {
        return Yspeed;
    }

    public Coordinates getPosition() {
        return position;
    }

    public int getPower() {
        return power;
    }


    public void setPosition(Coordinates position) {
        this.position = position;
    }
}
