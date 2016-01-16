package sample.View;

import sample.Model.*;
import sample.Util.State;

import javax.swing.*;
import java.awt.*;
import java.util.Map;
import java.util.TreeMap;

public class Game extends JPanel {

    private Board board;
    private Map<Integer, Color> colorMap =  new TreeMap<>();
    private Map<Integer, Color> effectsMap = new TreeMap<>();
    private Font f = new Font("Dialog", Font.PLAIN, 50);

    public Game(Board board) {
        this.board = board;
        setBackground(new Color(255,255,255));
        setBorder(BorderFactory.createLineBorder(Color.black));
        setPreferredSize(new Dimension(Board.TOTAL_WIDTH, Board.TOTAL_HEIGHT));
        setMap();
    }

    public void setMap(){
        colorMap.put(1,new Color(0, 0,146));
        colorMap.put(2,new Color(0, 106, 183));
        colorMap.put(3,new Color(4, 150, 148));
        colorMap.put(4,new Color(24, 170, 28));
        colorMap.put(5,new Color(62,233, 0));
        colorMap.put(6,new Color(188, 222, 0));
        colorMap.put(7,new Color(255, 211, 0));
        colorMap.put(8,new Color(232, 84, 0));
        colorMap.put(9,new Color(232, 2, 8));
        effectsMap.put(1, new Color(20, 20, 120));
        effectsMap.put(2, new Color(182, 100, 151));
        effectsMap.put(3, new Color(74, 59, 55));
        effectsMap.put(4, new Color(120, 120, 20));
        effectsMap.put(5, new Color(139, 13, 63));
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        drawBlocks(g);
        drawPaddle(g);
        board.mutex(() -> drawBalls(g));
        drawEffects(g);
        if(Board.gameState == State.ENDED) {
            g.setFont(f);
            g.drawString("Game Over", Board.TOTAL_WIDTH / 2-120, Board.TOTAL_HEIGHT / 2);
        }
        else if(Board.gameState== State.NEXTLEVEL) {
            g.setFont(f);
            g.drawString("Level: "+board.getLevel(), Board.TOTAL_WIDTH / 2-100, Board.TOTAL_HEIGHT / 2-70);
            g.drawString("Press any key to start", Board.TOTAL_WIDTH / 2-250, Board.TOTAL_HEIGHT / 2);
        }
    }

    public synchronized void drawBlocks(Graphics g){
        for(Block block:board.getBlocks().values()){
            Coordinates coords = block.getCorner();
            g.setColor(colorMap.get(block.getDurability()));
            g.fillRect(coords.getX(), coords.getY(), Block.WIDTH, Block.HEIGHT);
            g.setColor(Color.BLACK);
            g.drawRect(coords.getX(),coords.getY(), Block.WIDTH, Block.HEIGHT);

        }
    }

    public synchronized void drawBalls(Graphics g){
        for(Ball ball:board.getBalls()){
            Coordinates coords = ball.getPosition();
            g.setColor(Color.DARK_GRAY);
            g.fillOval(coords.getX()-Ball.radius/2, coords.getY()-Ball.radius/2, Ball.radius, Ball.radius);

        }
    }

    public void drawPaddle(Graphics g){
        Paddle paddle = board.getPaddle();
        int Xpos= paddle.getPosition() - paddle.getWidth()/2;
        int Ypos = Board.TOTAL_HEIGHT-paddle.getHeight();
        g.setColor(Color.RED);
        g.fillRect(Xpos,Ypos,paddle.getWidth(),paddle.getHeight());
    }

    public void drawEffects(Graphics g){
        for(Effects effect:board.getEffects()){
            Coordinates coords = effect.getPosition();
            g.setColor(effectsMap.get(effect.getNumberOfColor()));
            g.fillOval(coords.getX()-Effects.radius/2, coords.getY()-Effects.radius/2, Effects.radius, Effects.radius);
        }
    }
}
