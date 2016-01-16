package sample.Model;

import sample.Model.EffectsPack.*;

import java.util.Random;

public class Block {

    public static final int WIDTH = 80;
    public static final int HEIGHT = 30;

    private int durability;
    private int index;
    private int score;
    private Effects effect;
    private Board board;
    private Coordinates corner;

    public Block(Coordinates coordinates, int durability){
        corner=coordinates;
        this.durability=durability;
        index = findIndex(coordinates);
    }

    public Block(Board board, Coordinates coordinates, int durability){
        int rand;
        this.board = board;
        corner = coordinates;
        this.durability=durability;
        score = durability;
        index = findIndex(coordinates);
        rand = new Random().nextInt(15);
        switch(rand){
            case 0:
                effect = new Lengthen(new Coordinates(corner.x+WIDTH/2, corner.y+HEIGHT));
                break;
            case 1:
                effect = new NextBall(new Coordinates(corner.x+WIDTH/2, corner.y+HEIGHT));
                break;
            case 2:
                effect = new Shorten(new Coordinates(corner.x+WIDTH/2, corner.y+HEIGHT));
                break;
            case 3:
                effect = new SlowDown(new Coordinates(corner.x+WIDTH/2, corner.y+HEIGHT));
                break;
            case 4:
                effect = new SpeedUp(new Coordinates(corner.x+WIDTH/2, corner.y+HEIGHT));
                break;
        }
    }

    public Coordinates getCorner() {
        return corner;
    }

    public void drop(){
        if(effect != null)
            board.getEffects().add(effect);
    }

    public static int findIndex(Coordinates coordinates){
        int blocksInLine = Board.TOTAL_WIDTH / WIDTH;
        int positionInLine = coordinates.getX()/WIDTH;
        if(positionInLine>Board.TOTAL_WIDTH / WIDTH-1)
            positionInLine=Board.TOTAL_WIDTH / WIDTH-1;
        else if(positionInLine<0)
            positionInLine=0;
        int positionInColumn = coordinates.getY()/HEIGHT;
        if (positionInColumn<0)
            positionInColumn=0;
        else if(positionInColumn>Board.TOTAL_HEIGHT/HEIGHT-1)
            positionInColumn=Board.TOTAL_HEIGHT/HEIGHT-1;
        return positionInColumn * blocksInLine + positionInLine;
    }

    public void durabilityDecrease(int power){
        durability-=power;
        if(durability<=0){
            board.increment(score);
            drop();
            board.getBlocks().remove(this.index);
        }
    }

    public int getIndex() {
        return index;
    }

    public int getDurability() {
        return durability;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Block block = (Block) o;

        if (index != block.index) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return index;
    }
}
