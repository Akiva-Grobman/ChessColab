package GameObjects;

import java.awt.*;
import java.util.List;

import GameLogic.BackendBoard;
import GameLogic.BackendBoard.Type;

public class King extends Piece {

    public King(Color color){
        super(Type.KING, color);
    }

    private  boolean isLegal(Point origin, Point destination){
        return false;
    }

    @Override
    List<Point> getAllMoves(Point position) {
        return null;
    }

    @Override
    void makeAMove(Point position, BackendBoard board) {

    }

    private boolean moveIsLegal(Point position) {
        return false;
    }
}
