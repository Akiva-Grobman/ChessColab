package GameLogic;

import GameObjects.Piece;

import java.awt.*;

class Tile {

    Piece piece;
    Point position;
    boolean hasPiece;
    Color tileColor;

    public Tile(int x, int y, Color tileColor) {
        this.hasPiece = false;
        this.position.x = x;
        this.position.y = y;
        this.tileColor = tileColor;
    }

    public void updateTile(Piece piece, boolean hasPiece){
        piece = piece;
        hasPiece = hasPiece;
    }
}
