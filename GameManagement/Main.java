package GameManagement;

import GUI.GUIBoard;
import GameLogic.BackendBoard;

import java.awt.*;
import java.util.List;

public class Main {

    private BackendBoard backendBoard;
    private GUIBoard GUIBoard;
    private Color currentPlayersColor = Color.white;
    private Point redTile;

    private Main(){
        backendBoard = new BackendBoard();
        GUIBoard = new GUIBoard(new ActionsPreformedHandler(backendBoard, this));
    }

    void updateGUIBoard(){
        GUIBoard.updateBoard();
        GUIBoard.paintBoard();
    }

    Color getCurrentPlayersColor() {
        return this.currentPlayersColor;
    }

    void changeCurrentPlayersColor(){
        if (currentPlayersColor == Color.white){
            currentPlayersColor = Color.black;
        } else {
            currentPlayersColor = Color.white;
        }
    }

    void drawTiles(List<Point> legalMovesForPiece,List<Point> illegalMovesForPiece) {
        this.GUIBoard.drawTiles(legalMovesForPiece, illegalMovesForPiece);
    }

    void resetTileColors() {
        this.GUIBoard.resetTiles();
    }

    void drawPieceTileRed(Point position) {
        redTile = position;
        GUIBoard.drawTileRed(position);
    }

    public static void main(String [] args){
        // todo add open window
        new Main();
    }

}
