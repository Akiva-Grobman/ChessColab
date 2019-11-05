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

    public void mouseClicked(Point position) {
        if(isChoosingPiece){
            choosingPiece(position);
        } else {
            destination = position;
            if(!origin.equals(destination)){
                if(isCurrentPlayersPiece()){
                    choosingPiece(position);
                } else {
                    tryToMakeAMove(position);
                }
            }
            controller.resetTileColors();
            isChoosingPiece = true;
        }
        updateBoards();
    }

    public void mousePressed(Point position) {
    }

    public void mouseReleased(Point position) {

    }

    public void mouseEntered(Point position) {
        drawLegalTiles(position);
    }

    public void mouseExited(Point position) {
        controller.resetTileColors();
    }

    private void tryToMakeAMove(Point position) {
        for (Point legalDestination: legalMovesForPiece) {
            if(position.equals(legalDestination)){
                backendBoard.getPiece(origin).makeAMove(destination);
                break;
            }
        }
        if(backendBoard.getPiece(origin) == null){
            controller.changeCurrentPlayersColor();
        }
    }

    private void choosingPiece(Point position) {
        legalMovesForPiece = null;
        origin = position;
        if(hasPlayersPiece(position)) {
            isChoosingPiece = false;
            drawLegalTiles(position);
            controller.drawPieceTileRed(position);
        }
    }

    private void updateBoards(){
        //controller.updateLogicBoard();
        controller.updateGUIBoard();
    }

    private void drawLegalTiles(Point position) {
        legalMovesForPiece = getLegalMoves(position);
        if(legalMovesForPiece != null) {
            controller.drawTiles(legalMovesForPiece);
        }
    }

    private boolean isCurrentPlayersPiece() {
        if(backendBoard.getPiece(origin) == null || backendBoard.getPiece(destination) == null) {
            return false;
        }
        return areTheSameColor(backendBoard.getPiece(origin).getColor(), backendBoard.getPiece(destination).getColor());
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

    private List<Point> getLegalMoves(Point position) {
        if(this.backendBoard.getPiece(position) == null) {
            return null;
        }
        return this.backendBoard.getPiece(position).getAllMoves(backendBoard);
    }

    public BackendBoard getBackendBoardInstance() {
        return backendBoard;
    }

}
