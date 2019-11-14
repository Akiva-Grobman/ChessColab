package GameObjects;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import GameLogic.BackendBoard;
import GameLogic.BackendBoard.Type;

public class Pawn extends Piece {

    private boolean enPassantStillPossible;
    private boolean isInEnPassantPosition;
    private List<Point> enPassantMoves;

    public Pawn(Color color) {
        super(Type.PAWN, color);
        isInEnPassantPosition = false;
        enPassantStillPossible = true;
    }

    @Override
    public List<Point> getAllMoves(BackendBoard board) {
        List<Point> moves = new ArrayList<>();
        Point origin = new Point();
        origin.x = this.position.x;
        origin.y = this.position.y;
        List<Point> normalMoves = getNormalMoves(origin, board);
        List<Point> killsMoves = getKillsMoves(origin, board);
        enPassantMoves = getEnPassantMoves(origin, board);
        moves.addAll(normalMoves);
        moves.addAll(killsMoves);
        moves.addAll(enPassantMoves);
        return moves;
    }

    @Override
    public void makeAMove(Point position) {
        if(enPassantStillPossible) {
            checkIfInEnPassantPosition(this.position, position);
        }
        this.position.x = position.x;
        this.position.y = position.y;
    }

    private List<Point> getNormalMoves(Point origin, BackendBoard board) {
        List<Point> moves = new ArrayList<>();
        Point destination = new Point(origin.x, origin.y);
        if(isInBounds(origin.y+1) && (board.getPiece(origin).color) == Color.black) {
            destination.y += 1;
            if (isNormalMove(destination, board)) {
                moves.add(new Point(destination.x, destination.y));
                if(!isInEnPassantPosition && isInBounds(origin.y+2)) {
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
                if(!isInEnPassantPosition && isInBounds(origin.y-2)) {
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

    private void checkIfInEnPassantPosition(Point oldPosition, Point newPosition) {
        if(oldPosition.y == 1 || oldPosition.y == 6) {
            if(newPosition.y == 3 || newPosition.y == 4) {
                isInEnPassantPosition = true;
            } else {
                enPassantStillPossible = false;
            }
        }
    }

    private List<Point> getEnPassantMoves(Point piecePosition, BackendBoard board) {
        List<Point> enPassantMoves = new ArrayList<>();
        Point checkedPosition = new Point(this.position.x, this.position.y);
        if((piecePosition.y == 3 && color == Color.white) || (piecePosition.y == 4 && color == Color.black)){
            checkedPosition.x += 1;
            if(isInBounds(checkedPosition.x) && hasEnPassantMove(board.getPiece(checkedPosition) ,board)){
                if(color == Color.white){
                    checkedPosition.y -= 1;
                } else {
                    checkedPosition.y += 1;
                }
                enPassantMoves.add(checkedPosition);
            }
            checkedPosition = new Point(this.position.x, this.position.y);
            if(isInBounds(checkedPosition.x) && hasEnPassantMove(board.getPiece(checkedPosition) ,board)){
                if(color == Color.white){
                    checkedPosition.y -= 1;
                } else {
                    checkedPosition.y += 1;
                }
                enPassantMoves.add(checkedPosition);
            }
        }
        return enPassantMoves;
    }

    private boolean hasEnPassantMove(Piece piece, BackendBoard board){
        if(piece instanceof Pawn){
            Pawn tempPawn = (Pawn) piece;
            if(tempPawn.isInEnPassantPosition) {
                return true;
            }
        }
        return false;
    }

    public List<Point> getEnPassantMoves() {
        return enPassantMoves;
    }
}
