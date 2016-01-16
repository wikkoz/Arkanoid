package sample.View;

import sample.Model.Board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;

public class ViewCreator implements Repainter{

    private JPanel game;
    private JPanel score;
    private JPanel panel;
    private JFrame frame = new JFrame();

    public ViewCreator(Board board, KeyListener keyListener){
        game = new Game(board);
        score = new Score(board);
        panel = new JPanel(new BorderLayout(0,0));
        frame.setSize(Board.TOTAL_WIDTH,Board.TOTAL_HEIGHT+80);
        frame.setLocationRelativeTo(null);
        panel.add(game, BorderLayout.PAGE_START);
        panel.add(score, BorderLayout.PAGE_END);
        frame.setContentPane(panel);
        frame.setVisible(true);
        frame.addKeyListener(keyListener);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    @Override
    public void repaint(){
        panel.repaint();
    }

}
