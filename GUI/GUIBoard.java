package GUI;

import GameLogic.BackendBoard;
import GameManagement.ActionsPreformedHandler;
import GameObjects.Piece;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GUIBoard extends JFrame {

    private Tile [][] board;
    private BackendBoard backendBoard;
    static final int TILE_SIZE = 90;

    public GUIBoard(ActionsPreformedHandler handler) {
        backendBoard = handler.getBackendBoardInstance();
        boardSetUP(handler);
        syncWithLogicBoard();
        paintBoard();
        this.setVisible(true);
    }

    public void updateBoard(){
        syncWithLogicBoard();
    }

    public void paintBoard() {
        for (int x = 0; x < BackendBoard.ROWS; x++) {
            for (int y = 0; y < BackendBoard.COLUMNS; y++) {
                board[x][y].repaint();
            }
        }
    }

    private void boardSetUP(ActionsPreformedHandler handler) {
        int windowWidth = TILE_SIZE * BackendBoard.ROWS;
        int windowHeight = TILE_SIZE * BackendBoard.COLUMNS;
        this.setLayout(new GridLayout(BackendBoard.ROWS, BackendBoard.COLUMNS));
        this.setSize(windowWidth, windowHeight);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        board = new Tile[BackendBoard.ROWS][BackendBoard.COLUMNS];
        addTiles(handler);
    }

    private void addTiles(ActionsPreformedHandler handler) {
        Tile tile;
        Color color;
        for (int x = 0; x < BackendBoard.ROWS; x++) {
            for (int y = 0; y < BackendBoard.COLUMNS; y++) {
                if((x + y) % 2 == 0){
                    color = Color.white;
                } else {
                    color = Color.gray;
                }
                tile = new Tile(x, y, color, handler);
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
                updatedTile(tile, position);
            }
        }
    }

    private void updatedTile(Tile tile, Point position) {
        if(backendBoard.getHasPiece(position)){
            Piece piece = backendBoard.getPiece(position);
            tile.addPiece(piece.getColor(), piece.getPieceType());
        } else {
            tile.removePiece();
        }
    }

    public void drawTiles(List<Point> legalMovesForPiece) {
        for (Tile[] tiles: board) {
            for (Tile tile: tiles) {
                for (int i = 0; i < legalMovesForPiece.size(); i++) {
                    if(tile.x == legalMovesForPiece.get(i).x && tile.y == legalMovesForPiece.get(i).y){
                        tile.tileColor = Color.cyan;
                    }
                }
            }
        }
        paintBoard();
    }

    public void resetTiles() {
        for (Tile[] tiles: board) {
            for (Tile tile: tiles) {
                tile.resetTileColor();
            }
        }
        paintBoard();
    }
}
