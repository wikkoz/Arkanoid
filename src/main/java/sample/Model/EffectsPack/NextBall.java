package sample.Model.EffectsPack;

import sample.Model.Board;
import sample.Model.Coordinates;
import sample.Model.Effects;

public class NextBall extends Effects{
    public NextBall(Coordinates coordinates) {
        super(coordinates);
        numberOfColor = 2;
    }

    @Override
    public void use(Board board) {
       board.addNewBall();
       expired=true;
    }
}
