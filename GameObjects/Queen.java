package GameObjects;

import java.awt.*;
import java.util.List;
import GameLogic.BackendBoard.Type;

public class Queen extends piece {

    public Queen(Color color) {
        super(Type.QUEEN, color);
    }

    @Override
    List<Point> getAllMoves(Point position) {
        return null;
    }

    @Override
    void makeAMove(Point position) {

    }
}
