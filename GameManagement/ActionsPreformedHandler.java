package GameManagement;

import GameLogic.BackendBoard;

import java.awt.*;
import java.util.List;

public class ActionsPreformedHandler {

    private BackendBoard backendBoard;
    private Main controller;
    private boolean isChoosingPiece;
    private List<Point> legalMovesForPiece;
    private Point origin;
    private Point destination;

    ActionsPreformedHandler(BackendBoard backendBoard, Main controller) {
        this.backendBoard = backendBoard;
        this.controller = controller;
        isChoosingPiece = true;
    }

    public BackendBoard getBackendBoardInstance() {
        return backendBoard;
    }

    public void mouseClicked(Point position) {
        if(isChoosingPiece){
            origin = position;
            if(hasPlayersPiece(position)) {
                isChoosingPiece = false;
                // get colored tiles()
            }
        } else {
            destination = position;
            if(!origin.equals(destination)){
                /*
                if(areTheSameColor(backendBoard.getPiece(origin).getPiece().getPieceColor, backendBoard.getPiece(destination).getPiece().getPieceColor){
                    // get colored tiles()
                } else {
                    // try to make the move
                }
                 */
            }
            isChoosingPiece = true;
        }
        updateBoards();
    }

    private boolean hasPlayersPiece(Point position) {
        boolean hasPiece = backendBoard.getHasPiece(position);
        boolean isPlayersPiece = false;
        if(hasPiece){
            isPlayersPiece = areTheSameColor(backendBoard.getPiece(position).getColor(), controller.getCurrentPlayersColor());
        }
        return isPlayersPiece;
    }

    private boolean areTheSameColor(Color color, Color currentPlayersColor) {
        return color == currentPlayersColor;
    }

    public void mousePressed(Point position) {
        updateBoards();
    }

    public void mouseReleased(Point position) {
        updateBoards();
    }

    public void mouseEntered(Point position) {
        //updateBoards();
    }

    public void mouseExited(Point position) {
        //updateBoards();
    }

    // todo figure out a way to repaint the board
    private void updateBoards(){
        //controller.updateLogicBoard();
        controller.updateGUIBoard();
    }

}
