package GameObjects;

import java.awt.*;

abstract class piece {

    protected int row;
    protected int column;
    protected boolean isAlive;
    protected Color color;
    protected Type pieceType;

    enum Type {
        KING,
        QUEEN,
        ROOK,   
        BISHOP, 
        KNIGHT, 
        PAWN    
    }

    public piece(Type pieceType, Color color) {
        this.isAlive = true;
        this.pieceType = pieceType;
        this.color = color;
    }

    abstract void move(Point position);


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
