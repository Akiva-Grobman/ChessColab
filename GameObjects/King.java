package GameObjects;

import java.awt.*;
import java.util.List;
import GameLogic.BackendBoard.Type;

public class King extends Piece {

    public King(Color color){
        super(Type.KING, color);
    }

    private  boolean isLegal(Point origin, Point destinion){
        return false;
    }

    @Override
    List<Point> getAllMoves(Point position) {
        return null;
    }

    @Override
    void makeAMove(Point position) { //todo give a board
        if(moveIsLegal(position)){
            // board.makeAMove(x,y, position.x, position.y);
        }
    }

    private boolean moveIsLegal(Point position) {
        return false;
    }
}
