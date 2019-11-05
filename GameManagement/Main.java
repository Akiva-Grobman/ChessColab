package GameManagement;

import GUI.GUIBoard;
import GameLogic.BackendBoard;

import java.awt.*;
import java.util.List;

public class Main {

    private BackendBoard backendBoard;
    private GUIBoard GUIBoard;
    private Color currentPlayersColor = Color.white;

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

    void drawTiles(List<Point> legalMovesForPiece) {
        this.GUIBoard.drawTiles(legalMovesForPiece);
    }

    void resetTileColors() {
        this.GUIBoard.resetTiles();
    }

    public static void main(String [] args){
        // todo add open window
        new Main();
    }

}
