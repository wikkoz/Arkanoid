package sample.Controller;

import sample.Model.*;

public class CollisionController {

    private Paddle paddle;
    private Board board;

    public CollisionController(Board board) {
        this.paddle = board.getPaddle();
        this.board = board;
    }


    public void collisionWithBlock(Ball ball) {
        Coordinates placeOfHit = pointOfCollision(ball);
        Block block = board.getBlocks().get(Block.findIndex(placeOfHit));
        if(block==null)
            return;
        int upperY = block.getCorner().getY();
        int leftX = block.getCorner().getX();
        int rightX = leftX + Block.WIDTH - 1;
        int lowerY = upperY + Block.HEIGHT - 1;
        ball.move();
        if((placeOfHit.getX()==leftX && ball.getXspeed() > 0 ) ||( placeOfHit.getX()==rightX && ball.getXspeed() < 0  )){
            placeOfHit.move(-1,1);
            ball.setPosition(new Coordinates(placeOfHit));
            ball.flip(-1,1);
        }
        if((placeOfHit.getY()==upperY && ball.getYspeed() > 0 ) || (placeOfHit.getY()==lowerY && ball.getYspeed() < 0)) {
            placeOfHit.move(1,-1);
            ball.setPosition(new Coordinates(placeOfHit));
            ball.flip(1, -1);
        }
        block.durabilityDecrease(ball.getPower());
    }

    public void collisionWithThePaddle(Ball ball, int xPosition) {
        ball.paddleFlip(paddle.getRatio(xPosition));
        ball.move();
        ball.setPosition(new Coordinates(xPosition,Board.TOTAL_HEIGHT-paddle.getHeight()-1));

    }

    public Coordinates pointOfCollision(Ball ball) {
        Coordinates position = new Coordinates(ball.getPosition());
        int dx = ball.getXspeed();
        int dy = ball.getYspeed();
        int xi = (int) Math.signum(dx);
        int yi = (int) Math.signum(dy);
        int d, ai, bi;
        dx = Math.abs(dx);
        dy = Math.abs(dy);
        if (dx > dy) {
            ai = (dy - dx) * 2;
            bi = dy * 2;
            d = bi - dx;
            while (!board.getBlocks().containsKey(Block.findIndex(position))&& position.getX()!=ball.getNextPosition().getX()) {
                if (d >= 0) {
                    position.move(xi, yi);
                    d +=ai;
                } else {
                    position.move(xi, 0);
                    d += bi;
                }
            }
        } else {
            ai = (dx - dy) * 2;
            bi = dx * 2;
            d = bi - dy;
            while (!board.getBlocks().containsKey(Block.findIndex(position)) &&  position.getY()!=ball.getNextPosition().getY()) {
              //  System.out.print(xi+ " " +yi+ " ");
                if (d >= 0) {
                    position.move(xi, yi);
                    d +=ai;
                } else {
                    position.move(0, yi);
                    d += bi;
                }
            }
        }
        return position;


    }
}
