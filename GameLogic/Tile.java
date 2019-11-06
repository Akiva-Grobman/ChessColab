package GameLogic;

import GameObjects.Piece;

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
        this.piece.setPosition(position);
        this.hasPiece = true;
    }

    void updateTile(Piece piece, boolean hasPiece){
        this.piece = piece;
        this.hasPiece = hasPiece;
    }

    boolean isHasPiece() {
        return hasPiece;
    }

    Piece getPiece() {
        return piece;
    }

    @Override
    public String toString() {
        if (piece == null){
            return "null | ";
        }
        return piece.getPieceType() + " | ";
    }
}
