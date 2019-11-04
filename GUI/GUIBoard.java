package GUI;

import GameLogic.BackendBoard;
import javax.swing.*;
import java.awt.*;

public class GUIBoard extends JFrame {

    private Tile [][] board;
    private BackendBoard backendBoard;
    static final int TILE_SIZE = 90;

    // todo add main to run the program
    public static void main(String [] args){
        new GUIBoard();
    }

    GUIBoard() {
        boardSetUP();
        syncWithLogicBoard();
        this.repaint();
        this.setVisible(true);
    }

    private void paintBoard() {
        for (int x = 0; x < BackendBoard.ROWS; x++) {
            for (int y = 0; y < BackendBoard.COLUMNS; y++) {
                board[x][y].paint();
            }
        }
    }

    private void boardSetUP() {
        int windowWidth = TILE_SIZE * BackendBoard.ROWS;
        int windowHeight = TILE_SIZE * BackendBoard.COLUMNS;
        this.setLayout(new GridLayout(BackendBoard.ROWS, BackendBoard.COLUMNS));
        this.setSize(windowWidth, windowHeight);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        board = new Tile[BackendBoard.ROWS][BackendBoard.COLUMNS];
        addTiles();
    }

    private void addTiles() {
        Tile tile;
        Color color;
        for (int y = 0; y < BackendBoard.ROWS; y++) {
            for (int x = 0; x < BackendBoard.COLUMNS; x++) {
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

    private void syncWithLogicBoard() {
        Point position;
        for (Tile[] tiles: board) {
            for (Tile tile: tiles) {
                position = new Point(tile.x, tile.y);
                tile = getUpdatedTile(tile, position);
            }
        }
    }

    private Tile getUpdatedTile(Tile tile, Point position) {
        if(backendBoard.getHasPiece(position)){
            tile.addPiece(backendBoard.getPieceColor(position), backendBoard.getPieceType(position));
        } else {
            tile.pieceType = null;
            tile.pieceColor = null;
            tile.isEmpty = true;
        }
        return tile;
    }

    public void paintComponents(Graphics g){
        super.paintComponents(g);
        paintBoard();
    }
}
