package GameLogic;

import GUI.GUIBoard;

import java.awt.*;
import java.util.List;

public class BackendBoard {

    private Tile [][] board;

    public enum Type {
        KING,
        QUEEN,
        ROOK,
        BISHOP,
        KNIGHT,
        PAWN
    }

    private GUIBoard graphicalBoard;

    public BackendBoard(){
        board = new Tile[GUIBoard.ROWS][GUIBoard.COLUMNS];
    }

    public void addGUIBoard(GUIBoard newGUIBoard){
        this.graphicalBoard = newGUIBoard;
    }

    public void makeAMove(Point origin, Point destination) {
        updateLogicBoard(origin, destination);
    }

    public void paintBoard(){
        updateGUIBoard();
    }

    private void updateLogicBoard(Point origin, Point destination) {
        board[destination.x][destination.y].updateTile(board[origin.x][origin.y].piece, true);
        board[origin.x][origin.y].updateTile(null, false);
    }

    private void updateGUIBoard(){
        graphicalBoard.repaint();
    }

}
