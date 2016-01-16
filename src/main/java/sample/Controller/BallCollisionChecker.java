package sample.Controller;

import sample.Model.*;

public class BallCollisionChecker{


    private Ball ball;
    private Board board;
    private CollisionController controller;

    public BallCollisionChecker(Board board){
        this.board=board;
        controller = new CollisionController(board);
    }

    public void setBall(Ball ball) {
        this.ball = ball;
    }

    public boolean checkBlocks(){
        Coordinates nextPosition = ball.getNextPosition();
        int index = Block.findIndex(nextPosition);
        if(board.getBlocks().containsKey(index)) {
            controller.collisionWithBlock(ball);
            return true;
        }
        return false;

    }

    public boolean checkPaddle(Paddle paddle){
        Coordinates nextPosition = ball.getNextPosition();
        Coordinates position = ball.getPosition();
        int topOfPaddle =Board.TOTAL_HEIGHT-paddle.getHeight();
        if(nextPosition.getY()<topOfPaddle)
            return false;
        int xPosition = (int)((position.getY()-topOfPaddle)/ball.getYspeed()*ball.getXspeed()+position.getX());
        if(paddle.checkIfHasPoint(xPosition)){
            controller.collisionWithThePaddle(ball, xPosition);
            return true;
        }
        return false;
    }

    public boolean checkRightEdge(){
        Coordinates nextPosition = ball.getNextPosition();
        if(nextPosition.getX()>Board.TOTAL_WIDTH){
            int y=(Board.TOTAL_WIDTH-1-ball.getPosition().getX())*ball.getYspeed()/ball.getXspeed();
            ball.setPosition(new Coordinates(Board.TOTAL_WIDTH-1, ball.getPosition().getY()+y));
            ball.flip(-1,1);
            return  true;
        }
        return false;
    }

    public boolean checkUpperEdge(){
        Coordinates nextPosition = ball.getNextPosition();
        if(nextPosition.getY()<0){
            int x = (ball.getPosition().getY()-1)*ball.getXspeed()/ball.getYspeed();
            ball.setPosition(new Coordinates(ball.getPosition().getX()+x, 1));
            ball.flip(1,-1);
            return  true;
        }
        return false;
    }

    public boolean checkLeftEdge(){
        Coordinates nextPosition = ball.getNextPosition();
        if(nextPosition.getX()<0){
            int y=(ball.getPosition().getX()-1)*ball.getYspeed()/ball.getXspeed();
            ball.setPosition(new Coordinates(1,ball.getPosition().getY()+y));
            ball.flip(-1,1);
            return  true;
        }
        return false;
    }

    public boolean outOfBoard(){
        return ball.getPosition().getY() > board.TOTAL_HEIGHT;
    }
}
