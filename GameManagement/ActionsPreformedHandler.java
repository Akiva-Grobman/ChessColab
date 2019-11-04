package GameManagement;

import GUI.GUIBoard;
import GameLogic.BackendBoard;

import java.awt.*;

public class ActionsPreformedHandler {

    private BackendBoard backendBoard;
    private boolean isFirstClick;
    private Point origin;
    private Point destination;

    ActionsPreformedHandler(BackendBoard backendBoard) {
        isFirstClick = true;
        this.backendBoard = backendBoard;
    }

    public BackendBoard getBackendBoardInstance() {
        return backendBoard;
    }

    public void mouseClicked(Point position) {
        if(isFirstClick){
            origin = position;
            isFirstClick = false;
        } else {
            backendBoard.makeAMove(origin, position);
            isFirstClick = true;
        }
        repaintGUIBoard();
    }

    public void mousePressed(Point position) {
        repaintGUIBoard();
    }

    public void mouseReleased(Point position) {
        repaintGUIBoard();
    }

    public void mouseEntered(Point position) {
        repaintGUIBoard();

    }

    public void mouseExited(Point position) {
        repaintGUIBoard();
    }

    // todo figure out a way to repaint the board
    public void repaintGUIBoard(){

    }

}
