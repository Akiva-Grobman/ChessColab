package GameObjects;

import java.awt.*;
import java.util.List;

public class Knight extends piece {

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
