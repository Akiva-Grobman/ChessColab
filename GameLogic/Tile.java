package GameLogic;

import GameObjects.Piece;
import GameLogic.BackendBoard.Type;

import java.awt.*;

class Tile {

    private Piece piece;
    private Point position;
    private boolean hasPiece;
    private Color tileColor;

    Tile(int x, int y, Color tileColor) {
        this.hasPiece = false;
        this.position = new Point(x,y);
        this.tileColor = tileColor;
    }

    void addPiece(Piece piece){
        this.piece = piece;
    }

    void updateTile(Piece piece, boolean hasPiece){
        piece = piece;
        hasPiece = hasPiece;
    }

    public boolean isHasPiece() {
        return hasPiece;
    }

    public Piece getPiece() {
        return piece;
    }

}
