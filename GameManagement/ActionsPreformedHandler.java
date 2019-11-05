package GameManagement;

import GUI.GUIBoard;
import GameLogic.BackendBoard;

import java.awt.*;

public class ActionsPreformedHandler {

    private BackendBoard backendBoard;
    private Main controller;
    private boolean isFirstClick;
    private Point origin;
    private Point destination;

    ActionsPreformedHandler(BackendBoard backendBoard, Main controller) {
        this.backendBoard = backendBoard;
        this.controller = controller;
        isFirstClick = true;
    }

    public BackendBoard getBackendBoardInstance() {
        return backendBoard;
    }

    public void mouseClicked(Point position) {
        if(isFirstClick){
            origin = position;
            isFirstClick = false;
        } else {
            destination = position;
            isFirstClick = true;
        }
        updateBoards();
    }

    public void mousePressed(Point position) {
        updateBoards();
    }

    public void mouseReleased(Point position) {
        updateBoards();
    }

    public void mouseEntered(Point position) {
        updateBoards();
    }

    public void mouseExited(Point position) {
        updateBoards();
    }

    // todo figure out a way to repaint the board
    private void updateBoards(){
        //controller.updateLogicBoard();
        System.out.println(backendBoard);
        controller.updateGUIBoard();
    }

}
