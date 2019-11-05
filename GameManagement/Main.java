package GameManagement;

import GUI.GUIBoard;
import GameLogic.BackendBoard;

public class Main {

    private BackendBoard backendBoard;
    private GUIBoard GUIBoard;

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

}
