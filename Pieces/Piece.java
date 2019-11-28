package Pieces;

import BackendObjects.BackendBoard;
import BackendObjects.BackendBoard.Type;
import java.awt.*;
import java.util.List;

public abstract class Piece implements Cloneable{

    protected Point position;
    protected boolean isAlive;
    protected Color color;
    private Type pieceType;

    public Piece(Type pieceType, Color color) {
        this.isAlive = true;
        this.pieceType = pieceType;
        this.color = color;
    }

    public abstract List<Point> getAllMoves(BackendBoard board);

    public abstract void makeAMove(Point position);

    public void setPosition(Point position){
        this.position = position;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Type getPieceType() {
        return pieceType;
    }

    public void setPieceType(Type pieceType) {
        this.pieceType = pieceType;
    }

    boolean isInBounds(int position){
        return position >= 0 && position < 8;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "Piece{" +
                "position=" + position +
                ", isAlive=" + isAlive +
                ", color=" + color +
                ", pieceType=" + pieceType +
                '}';
    }
}
