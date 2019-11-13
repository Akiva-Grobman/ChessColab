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
        List<Point> moves = new ArrayList<>();
        removeCheckingMoves(moves, board);
        Point origin = new Point();
        origin.x = this.position.x;
        origin.y = this.position.y;
        List<Point> normalMoves = getNormalMoves(origin, board);
        List<Point> killsMoves = getKillsMoves(origin, board);
        moves.addAll(normalMoves);
        moves.addAll(killsMoves);
        return moves;
    }

    @Override
    public void makeAMove(Point position) {
        this.position.x = position.x;
        this.position.y = position.y;
        this.madeAMove = true;
    }

    private List<Point> getNormalMoves(Point origin, BackendBoard board) {
        List<Point> moves = new ArrayList<>();
        Point destination = new Point(origin.x, origin.y);
        if(isInBounds(origin.y+1) && (board.getPiece(origin).color) == Color.black) {
            destination.y += 1;
            if (isNormalMove(destination, board)) {
                moves.add(new Point(destination.x, destination.y));
                if(!madeAMove && isInBounds(origin.y+2)) {
                    destination.y += 1;
                    if (isNormalMove(destination, board)) {
                        moves.add(new Point(destination.x, destination.y));
                    }
                }
            }
        }
        destination.y = origin.y;
        if(isInBounds(origin.y-1) && (board.getPiece(origin).color) == Color.white) {
            destination.y -= 1;
            if (isNormalMove(destination, board)) {
                moves.add(new Point(destination.x, destination.y));
                if(!madeAMove && isInBounds(origin.y-2)) {
                    destination.y -= 1;
                    if (isNormalMove(destination, board)) {
                        moves.add(new Point(destination.x, destination.y));
                    }
                }
            }
        }
        return moves;
    }

    private List<Point> getKillsMoves(Point origin, BackendBoard board) {
        List<Point> killsMoves = new ArrayList<>();
        Point destination;
        if(isInBounds(origin.x+1) && isInBounds(origin.y-1) && (board.getPiece(origin).color) == Color.white) {
            destination = new Point(origin.x + 1, origin.y - 1);
            if (hasEnemyPiece(destination, board)) {
                killsMoves.add(destination);
            }
        }

        if(isInBounds(origin.x-1) && isInBounds(origin.y-1) && (board.getPiece(origin).color) == Color.white) {
            destination = new Point(origin.x - 1, origin.y - 1);
            if (hasEnemyPiece(destination, board)) {
                killsMoves.add(destination);
            }
        }

        if(isInBounds(origin.x+1) && isInBounds(origin.y+1) && (board.getPiece(origin).color) == Color.black) {
            destination = new Point(origin.x + 1, origin.y + 1);
            if (hasEnemyPiece(destination, board)) {
                killsMoves.add(destination);
            }
        }

        if(isInBounds(origin.x-1) && isInBounds(origin.y+1) && (board.getPiece(origin).color) == Color.black) {
            destination = new Point(origin.x - 1, origin.y + 1);
            if (hasEnemyPiece(destination, board)) {
                killsMoves.add(destination);
            }
        }

        return killsMoves;
    }

    private boolean hasEnemyPiece(Point destination, BackendBoard board) {
        if(board.getPiece(destination) == null) {
            return false;
        }
        return board.getPiece(destination).color != this.color;
    }

    private boolean isNormalMove(Point destination, BackendBoard board) {
        return isEmpty(destination, board) && !hasEnemyPiece(destination, board);
    }

    private boolean isEmpty(Point destination, BackendBoard board) {
        return !board.getHasPiece(destination);
    }

}
