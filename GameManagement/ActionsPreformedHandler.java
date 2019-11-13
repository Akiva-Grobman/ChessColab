package GameManagement;

import GameLogic.BackendBoard;
import GameObjects.Piece;

import java.awt.*;
import java.util.ArrayList;
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
        if(isChoosingPiece && isCurrentPlayersPiece(position) && canMove(position)){
            choosingPiece(position);
        } else if(position.equals(origin)) {
            controller.resetTileColors();
            isChoosingPiece = true;
        } else if(!isChoosingPiece && !isCurrentPlayersPiece(position)) {
            destination = position;
            if (!origin.equals(destination)) {
                if (isCurrentPlayersPiece(destination)) {
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
        if(isChoosingPiece && isCurrentPlayersPiece(position)) {
            drawLegalTiles(position);
        }
    }

    public void mouseExited(Point position) {
        if(isChoosingPiece) {
            controller.resetTileColors();
        }
    }

    private boolean canMove(Point position){
        return getLegalMoves(position).size() != 0;
    }


    private void tryToMakeAMove(Point position) {
        // todo don't make the tile red if there are no moves (DONE: function "canMove")
        for (Point legalDestination: legalMovesForPiece) {
            if(position.equals(legalDestination)){
                backendBoard.makeAMove(origin, destination);
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

    private boolean isCurrentPlayersPiece(Point position) {
        try {
            return areTheSameColor(backendBoard.getPiece(position).getColor(), controller.getCurrentPlayersColor());
        } catch (NullPointerException e){
            return false;
        }
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
        List<Point> moves = this.backendBoard.getPiece(position).getAllMoves(backendBoard);

        // FIXME: "after" is legal moves after clean Sach moves
        //List<Point> after = this.backendBoard.removeMoves(moves, position, backendBoard); // FIXME: 13/11/2019


        return moves;
    }



    public BackendBoard getBackendBoardInstance() {
        return backendBoard;
    }

}
