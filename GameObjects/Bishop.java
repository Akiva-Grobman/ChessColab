package GameObjects;

import GUI.Tile;

import java.awt.*;
import java.util.List;import GameLogic.BackendBoard.Type;

public class Bishop extends piece {

    public Bishop(Color color) {
        super(Type.BISHOP, color);
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
