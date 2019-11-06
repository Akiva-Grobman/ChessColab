package GameObjects;

import GameLogic.BackendBoard;
import GameLogic.BackendBoard.Type;
import java.awt.*;
import java.util.List;

public abstract class Piece {

    protected Point position;
    protected boolean isAlive;
    protected Color color;
    protected Type pieceType;


    public Piece(Type pieceType, Color color) {
        this.isAlive = true;
        this.pieceType = pieceType;
        this.color = color;
    }

    public abstract List<Point> getAllMoves(BackendBoard board);

    public abstract void makeAMove(Point position);

    public int getRow() {
        return this.position.x;
    }

    public int getColumn() {
        return this.position.y;
    }

    public void setRow(int row) {
        this.position.x = row;
    }

    public void setColumn(int column) {
        this.position.y = column;
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

    public boolean isInBounds(int position){
        return position >= 0 && position < 8;
    }


}
