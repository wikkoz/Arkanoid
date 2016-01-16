package sample.View;

import sample.Model.Board;

import javax.swing.*;
import java.awt.*;

public class Score extends JPanel {

    private Board board;
    private Font f = new Font("Dialog", Font.PLAIN, 50);

    public Score(Board board){
        this.board=board;
        setPreferredSize(new Dimension(Board.TOTAL_WIDTH, 60));
        Color color = new Color(128, 77, 13);
        setLayout(null);
        setBackground(color);

    }
 @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setFont(f);
        g.drawString("Score: " + board.getScore(), 5, 50);
        g.drawString("Time: " + board.getTime(), 650, 50);

    }
}
