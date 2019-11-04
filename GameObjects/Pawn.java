package GameObjects;

import java.awt.*;
import java.util.List;

import GameLogic.BackendBoard;
import GameLogic.BackendBoard.Type;

public class Pawn extends Piece {

    public Pawn(Color color) {
        super(Type.PAWN, color);
    }

    private  boolean isLegal(Point origin, Point destinion){
        return false;
    }

    @Override
    List<Point> getAllMoves(Point position) {
        return null;
    }

    @Override
    void makeAMove(Point position, BackendBoard board) {

    }

}
