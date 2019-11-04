package GameManagement;

import GUI.GUIBoard;
import GameLogic.BackendBoard;

public class Main {

    public static void main(String [] args){
        // todo add open window
        BackendBoard backendBoard = new BackendBoard();
        GUIBoard GUIBoard = new GUIBoard(new ActionsPreformedHandler(backendBoard));
    }

}
