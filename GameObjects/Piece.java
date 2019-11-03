package GameObjects;

import GameLogic.BackendBoard.Type;
import java.awt.*;
import java.util.List;

public abstract class Piece {

    protected int row;
    protected int column;
    protected boolean isAlive;
    protected Color color;
    protected Type pieceType;


    public Piece(Type pieceType, Color color) {
        this.isAlive = true;
        this.pieceType = pieceType;
        this.color = color;
    }

    abstract List<Point> getAllMoves(Point position);

    abstract void makeAMove(Point position);

    public int getRow() {
        return this.row;
    }

    public int getColumn() {
        return this.column;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
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


}
