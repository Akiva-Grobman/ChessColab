package GameObjects;

import java.awt.*;
import java.util.List;

import GameLogic.BackendBoard;
import GameLogic.BackendBoard.Type;

public class Knight extends Piece {

    public Knight(Color color) {
        super(Type.KNIGHT, color);
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
