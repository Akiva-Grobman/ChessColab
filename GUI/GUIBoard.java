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
    private Color White = new Color(242, 218, 182);
    private Color Black = new Color(181, 137, 102);
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
        for (int y = 0; y < BackendBoard.ROWS; y++) {
            for (int x = 0; x < BackendBoard.COLUMNS; x++) {
                board[y][x].repaint();
            }
        }
    }

    private void boardSetUP(ActionsPreformedHandler handler) {
        int windowWidth = TILE_SIZE * BackendBoard.ROWS;
        int windowHeight = TILE_SIZE * BackendBoard.COLUMNS;
        this.setLayout(new GridLayout(BackendBoard.ROWS, BackendBoard.COLUMNS));
        this.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
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
        for (int y = 0; y < BackendBoard.ROWS; y++) {
            for (int x = 0; x < BackendBoard.COLUMNS; x++) {
                if((x + y) % 2 == 0){
                    color = White;
                } else {
                    color = Black;
                }
                tile = new Tile(x, y, color, handler);
                this.add(tile);
                board[y][x] = tile;
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

    public void drawTileRed(Point position){
        board[position.y][position.x].tileColor = Color.red;
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
