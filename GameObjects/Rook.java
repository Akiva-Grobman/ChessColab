package GameObjects;

import java.awt.*;
import java.util.List;
import GameLogic.BackendBoard.Type;

public class Rook extends Piece {

    public Rook(Color color) {
        super(Type.ROOK, color);
    }

    private  boolean isLegal(Point origin, Point destinion){
        return false;
    }

    @Override
    List<Point> getAllMoves(Point position) {
        return null;
    }

    @Override
    void makeAMove(Point position) {

    }
}
