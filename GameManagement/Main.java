package GameManagement;

import GUI.GUIBoard;
import GameLogic.BackendBoard;

import java.awt.*;

public class Main {

    private BackendBoard backendBoard;
    private GUIBoard GUIBoard;
    private Color currentPlayersColor;

    private Main(){
        backendBoard = new BackendBoard();
        GUIBoard = new GUIBoard(new ActionsPreformedHandler(backendBoard, this));
    }

    void updateGUIBoard(){
        GUIBoard.updateBoard();
        GUIBoard.paintBoard();
    }

//    void updateLogicBoard(){
//
//    }

    public static void main(String [] args){
        // todo add open window
        new Main();
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

}
