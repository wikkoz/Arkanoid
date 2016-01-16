package sample.Model;

import java.util.Random;

public class BlocksFactory {
    private int width = 0;
    private int height = 0;

    public Block createBlock(Board board, int durabilitySettings){
        int startingDurability = 1 + durabilitySettings/3;
        int maxDurability = durabilitySettings + 1;
        Block temp = new Block(board ,new Coordinates(width, height), new Random().nextInt(maxDurability)+startingDurability);
        width += Block.WIDTH;
        if(width >= Board.TOTAL_WIDTH-Block.WIDTH) {
            width = 0;
            height += Block.HEIGHT;
        }
        return temp;
    }
}
