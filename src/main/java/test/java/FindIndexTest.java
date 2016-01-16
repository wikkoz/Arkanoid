package test.java;

import org.junit.Test;
import sample.Model.Block;
import sample.Model.Coordinates;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FindIndexTest{

    Coordinates coords = new Coordinates(10,10);

    @Test
    public void testFirstRow(){
        Coordinates position = new Coordinates(coords);
        int index;
        for(int i = 0;i<14; ++i){
            index = Block.findIndex(position);
            position.move(Block.WIDTH,0);
            if(i<11)
                assertTrue(" "+ index+" "+i,i==index);
            else
                assertFalse(i == index);
        }
    }
    @Test
    public void testSecondRow(){
        Coordinates position = new Coordinates(coords);
        position.move(0,Block.HEIGHT);
        int index;
        for(int i = 0;i <14; ++i){
            index = Block.findIndex(position);
            if(i<11)
                assertTrue(i+11==index);
            else
                assertFalse(i+11==index);
            position.move(Block.WIDTH, 0);
        }
    }
    @Test
    public void testNegative(){
        Coordinates coords = new Coordinates(-5,-142);
        int index = Block.findIndex(coords);
        assertTrue(index==0);
        coords = new Coordinates(84,-13);
        index = Block.findIndex(coords);
        assertTrue(index==coords.getX()/Block.WIDTH);
    }
}
