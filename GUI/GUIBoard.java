package GUI;

import GameLogic.BackendBoard;
import javax.swing.*;
import java.awt.*;

public class GUIBoard extends JFrame {

    private final int ROWS = 8;
    private final int COLUMNS = 8;
    private Tile [][] board;
    static final int TILE_SIZE = 90;


    public static void main(String [] args){
        new GUIBoard();
    }

    GUIBoard() {
        boardSetUP();
        addPiecesToBoard();
        this.repaint();
        this.setVisible(true);
    }

    public void paintComponents(Graphics g){
        super.paintComponents(g);
        paintBoard();
    }

    private void paintBoard() {
        for (int x = 0; x < ROWS; x++) {
            for (int y = 0; y < COLUMNS; y++) {
                board[x][y].paint();
            }
        }
    }


    private void addPiecesToBoard() {
        Color color;
        for (int x = 0; x < COLUMNS; x++)
        {
            for (int y = 0; y < ROWS; y++)
            {
                if(x == 0 || x == 7)
                {
                    if(x == 0)
                    {
                        color  = Color.black;
                    }
                    else
                    {
                        color = Color.white;
                    }
                    switch(y)
                    {
                        // rooks
                        case 0:
                        case 7:
                        {
                            board[x][y].addPiece(color, BackendBoard.Type.ROOK);
                            break;
                        }
                        // knights
                        case 1:
                        case 6:
                        {
                            board[x][y].addPiece(color, BackendBoard.Type.KNIGHT);
                            break;
                        }
                        // bishops
                        case 2:
                        case 5:
                        {
                            board[x][y].addPiece(color, BackendBoard.Type.BISHOP);
                            break;
                        }
                    }
                }
                if(x == 1)
                {
                    color  = Color.black;
                }
                else
                {
                    color = Color.white;
                }
                // pawns
                if(x == 1 || x == 6)
                {
                    board[x][y].addPiece(color, BackendBoard.Type.PAWN);
                }
            }
        }
        // black king
        board[0][4].addPiece(Color.black, BackendBoard.Type.KING);
        // black queen
        board[0][3].addPiece(Color.black, BackendBoard.Type.QUEEN);
        // white king
        board[7][4].addPiece(Color.white, BackendBoard.Type.KING);
        // white queen
        board[7][3].addPiece(Color.white, BackendBoard.Type.QUEEN);
    }


    private void boardSetUP() {
        int windowWidth = TILE_SIZE * ROWS;
        int windowHeight = TILE_SIZE * COLUMNS;
        this.setLayout(new GridLayout(ROWS,COLUMNS));
        this.setSize(windowWidth, windowHeight);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        board = new Tile[ROWS][COLUMNS];
        addTiles();
    }

    private void addTiles() {
        Tile tile;
        Color color;
        for (int y = 0; y < ROWS; y++) {
            for (int x = 0; x < COLUMNS; x++) {
                if((x + y) % 2 == 0){
                    color = Color.white;
                } else {
                    color = Color.black;
                }
                tile = new Tile(x, y, color);
                this.add(tile);
                board[x][y] = tile;
            }
        }
    }
}
