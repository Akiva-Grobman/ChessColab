package GameObjects;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import GameLogic.BackendBoard;
import GameLogic.BackendBoard.Type;

public class Knight extends Piece {

    public Knight(Color color) {
        super(Type.KNIGHT, color);
    }

    @Override
    public List<Point> getAllMoves(BackendBoard board) {
        List<Point> moves = new ArrayList<>();

        return moves;
    }

    @Override
    public void makeAMove(Point position) {

    }


}
