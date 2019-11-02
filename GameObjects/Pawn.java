package GameObjects;

import java.awt.*;
import java.util.List;import GameLogic.BackendBoard.Type;

public class Pawn extends piece {

    public Pawn(Color color) {
        super(Type.PAWN, color);
    }

    @Override
    List<Point> getAllMoves(Point position) {
        return null;
    }

    @Override
    void makeAMove(Point position) {

    }
}
