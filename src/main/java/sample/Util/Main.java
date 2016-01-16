package sample.Util;

import sample.Controller.BallsController;
import sample.Controller.PaddleController;
import sample.Model.Board;
import sample.View.ViewCreator;

public class Main  {


    public static void main(String[] args) {
        Board board = new Board(0);
        board.init();
        PaddleController paddleController = new PaddleController(board);
        paddleController.init();
        ViewCreator view = new ViewCreator(board, paddleController);
        BallsController ballsController = new BallsController(board, view);
        new Thread(ballsController).start();
        new Thread(paddleController).start();
    }
}
