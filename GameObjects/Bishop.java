package GameObjects;

import java.awt.*;
import java.util.List;

public class Bishop extends piece {

    public Bishop(Color color) {
        super(Type.BISHOP, color);
    }

    @Override
    List<Point> getAllMoves(Point position) {
        return null;
    }

    @Override
    void makeAMove(Point position) {

    }
}
