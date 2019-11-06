package GameObjects;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import GameLogic.BackendBoard;
import GameLogic.BackendBoard.Type;

public class Pawn extends Piece {

    private boolean madeAMove;

    public Pawn(Color color) {
        super(Type.PAWN, color);
        madeAMove = false;
    }



    @Override
    public List<Point> getAllMoves(BackendBoard board) {
        Point origin = new Point();
        origin.x = this.position.x;
        origin.y = this.position.y;
        List<Point> normalMoves = getNormalMoves(origin, board);
        List<Point> killsMoves = getKillsMoves(origin, board);
        List<Point> moves = normalMoves; // I know!!!
        return moves;
    }

    @Override
    public void makeAMove(Point position) {
        this.position.x = position.x;
        this.position.y = position.y;
    }


    /*
    private boolean isLegal(Point origin, Point destination, BackendBoard board) {
        List<Point> normalMoves = getNormalMoves(origin, board);
        List<Point> killsMoves = getKillsMoves(origin, board);
        if(!hasEnemy(destination, board) && !madeAMove) {

        }
        return false;
    }
    */


    public List<Point> getNormalMoves(Point origin, BackendBoard board) {
        List<Point> moves = new ArrayList<>();
        Point destination = origin;
        if(isInBounds(origin.y+1)) {
            destination.y = origin.y + 1;
            if (isNormalMove(origin, destination, board)) {
                moves.add(destination);
            }
        }
        if(isInBounds(origin.y-1)) {
            destination.y = origin.y-1;
            if (isNormalMove(origin, destination, board)) {
                moves.add(destination);
            }
        }

        if(isInBounds(origin.y-2)) {
            destination.y = origin.y-2;
            if (isNormalMove(origin, destination, board)) {
                moves.add(destination);
            }
        }

        if(isInBounds(origin.y+2)) {
            destination.y = origin.y+2;
            if (isNormalMove(origin, destination, board)) {
                moves.add(destination);
            }
        }
        return moves;
    }


    private boolean isNormalMove(Point origin, Point destination, BackendBoard board) {
        if(this.color == Color.white) {
            if (!hasEnemy (destination, board) && ((origin.y+2 == destination.y) ) && !madeAMove) {
                return true;
            }
            else if (!hasEnemy (destination, board) && ((origin.y+1 == destination.y) )) {
                return true;
            }
        } else {
            if (!hasEnemy(destination, board) && ((origin.y-2 == destination.y)) && !madeAMove) {
                return true;
            }
            else if (!hasEnemy(destination, board) && ((origin.y-1 == destination.y)) ) {
                return true;
            }
        }
        return false;
    }

    public List<Point> getKillsMoves(Point origin, BackendBoard board) {
        List<Point> killsMoves = new ArrayList<>();
        Point destination = new Point();
        if(isInBounds(origin.x+1) && isInBounds(origin.y-1)) {
            destination.x = origin.x + 1;
            destination.y = origin.y - 1;
            if (isKillMove(origin, destination, board)) {
                killsMoves.add(destination);
            }
        }

        if(isInBounds(origin.x-1) && isInBounds(origin.y-1)) {
            destination.x = origin.x - 1;
            destination.y = origin.y - 1;
            if (isKillMove(origin, destination, board)) {
                killsMoves.add(destination);
            }
        }

        if(isInBounds(origin.x+1) && isInBounds(origin.y+1)) {
            destination.x = origin.x + 1;
            destination.y = origin.y + 1;
            if (isKillMove(origin, destination, board)) {
                killsMoves.add(destination);
            }
        }

        if(isInBounds(origin.x-1) && isInBounds(origin.y-1)) {
            destination.x = origin.x - 1;
            destination.y = origin.y - 1;
            if (isKillMove(origin, destination, board)) {
                killsMoves.add(destination);
            }
        }

        return killsMoves;
    }

    private boolean isKillMove(Point origin, Point destination, BackendBoard board) {
        List<Point> moves = new ArrayList<>();
        if(this.color == Color.white) {
            if (hasEnemy(destination, board) && ((origin.y+1 == destination.y) && (origin.x-1 == destination.x ||origin.x+1 == destination.x) )) {
                return true;
            }
        }
        else if (hasEnemy(destination, board) && ((origin.y-1 == destination.y) && (origin.x-1 == destination.x ||origin.x+1 == destination.x) )) {
            return true;
        }
        return false;
    }


    private boolean hasEnemy(Point destination, BackendBoard board) {
        if(board.getPiece(destination) == null){
            return false;
        }
        return board.getPiece(destination).color != this.color;
    }


}
