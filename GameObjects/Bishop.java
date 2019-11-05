package GameObjects;

import java.awt.*;
import java.util.List;

import GameLogic.BackendBoard;
import GameLogic.BackendBoard.Type;

public class Bishop extends Piece {

    public Bishop(Color color) {
        super(Type.BISHOP, color);
    }

    private  boolean isLegal(Point origin, Point destination){
        return false;
    }

    @Override
    public List<Point> getAllMoves(BackendBoard board) {
        return null;
    }

    @Override
    public void makeAMove(Point position) {

    }

}
