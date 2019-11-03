package GameObjects;

import java.awt.*;
import java.util.List;
import GameLogic.BackendBoard.Type;

public class Knight extends Piece {

    public Knight(Color color) {
        super(Type.KNIGHT, color);
    }

    @Override
    List<Point> getAllMoves(Point position) {
        return null;
    }

    @Override
    void makeAMove(Point position) {

    }
}
