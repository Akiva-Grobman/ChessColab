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
        List<Point> possibleMoves = getAllPossibleMoves();
        for (Point move: possibleMoves) {
            if(isLegalMove(move, board)){
                moves.add(move);
            }
        }
        return moves;
    }

    @Override
    public void makeAMove(Point position) {
        this.position.x = position.x;
        this.position.y = position.y;
    }

    private List<Point> getAllPossibleMoves() {
        List<Point> possibleMoves = new ArrayList<>();
        Point position = new Point(this.position.x, this.position.y);
        int [] firstHalfOfMove = {2, -2};
        int [] secondHalfOvMove = {1,-1};
        for (int fistHalf: firstHalfOfMove) {
            for (int secondHalf: secondHalfOvMove) {
                position.y += fistHalf;
                position.x += secondHalf;
                addIfInBounds(position, possibleMoves);
                position = new Point(this.position.x, this.position.y);

                position.x += fistHalf;
                position.y += secondHalf;
                addIfInBounds(position, possibleMoves);
                position = new Point(this.position.x, this.position.y);
            }
        }
        return possibleMoves;
    }

    private void addIfInBounds(Point position, List<Point> possibleMoves) {
        if(isInBounds(position.y) && isInBounds(position.x)){
            possibleMoves.add(position);
        }
    }

    private boolean isLegalMove(Point position, BackendBoard board) {
        return !board.getHasPiece(position) || board.getPiece(position).getColor() != this.color;
    }

}
