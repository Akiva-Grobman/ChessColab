package GameObjects;

import java.awt.*;
import java.util.List;

import GameLogic.BackendBoard;
import GameLogic.BackendBoard.Type;

public class Rook extends Piece {

    public Rook(Color color) {
        super(Type.ROOK, color);
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
