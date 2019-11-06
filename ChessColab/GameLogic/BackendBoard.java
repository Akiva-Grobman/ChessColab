package GameLogic;

import GUI.GUIBoard;
import GameObjects.*;

import java.awt.*;
import java.util.Arrays;

public class BackendBoard {

    private Tile [][] board;
    public static final int ROWS = 8;
    public static final int COLUMNS = 8;

    public Piece getPiece(Point position) {
        return board[position.x][position.y].getPiece();
    }

    public enum Type {
        KING,
        QUEEN,
        ROOK,
        BISHOP,
        KNIGHT,
        PAWN
    }

    public BackendBoard(){
        initializeBoard();
    }

    private void initializeBoard() {
        board = new Tile[ROWS][COLUMNS];
        Color color;
        for (int y = 0; y < ROWS; y++) {
            for (int x = 0; x < COLUMNS; x++) {
                if((x + y) % 2 == 0) {
                    color = Color.white;
                } else {
                    color = Color.black;
                }
                board[x][y] = new Tile(x, y, color);
            }
        }
        addPieces();
    }

    private void addPieces() {
        Color color;
        for (int x = 0; x < ROWS; x++)
        {
            for (int y = 0; y < COLUMNS; y++)
            {
                color = getPieceColor(x);
                if(x == 0 || x == 7)
                {
                    switch(y)
                    {
                        case 0:
                        case 7:
                        {
                            board[x][y].addPiece(new Rook(color));
                            break;
                        }
                        case 1:
                        case 6:
                        {
                            board[x][y].addPiece(new Knight(color));
                            break;
                        }
                        case 2:
                        case 5:
                        {
                            board[x][y].addPiece(new Bishop(color));
                            break;
                        }
                    }
                }
                if(x == 1 || x == 6)
                {
                    board[x][y].addPiece(new Pawn(color));
                }
            }
        }
        // black king
        board[0][4].addPiece(new King(Color.black));
        // black queen
        board[0][3].addPiece(new Queen(Color.black));
        // white king
        board[7][4].addPiece(new King(Color.white));
        // white queen
        board[7][3].addPiece(new Queen(Color.white));
    }

    private Color getPieceColor(int row){
        if(row == 0 || row == 1){
            return Color.black;
        } else {
            return Color.white;
        }
    }

    public boolean getHasPiece(Point position) {
        return board[position.x][position.y].isHasPiece();
    }

    public void makeAMove(Point origin, Point destination) {
        board[destination.x][destination.y].updateTile(board[origin.x][origin.y].getPiece(), true);
        board[origin.x][origin.y].updateTile(null, false);
    }

    @Override
    public String toString() {
        String stringBoard = "";
        for (Tile[] tiles: board) {
            for (Tile tile: tiles) {
                stringBoard += tile.toString();
            }
            stringBoard += "\n--------------------------------------------\n";
        }
        return stringBoard;
    }
}
