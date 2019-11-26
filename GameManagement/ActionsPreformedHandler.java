package GameManagement;

import GameLogic.BackendBoard;
import GameObjects.King;
import GameObjects.Pawn;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ActionsPreformedHandler {

    private BackendBoard backendBoard;
    private Main controller;
    private boolean isChoosingPiece;
    private boolean canMakeEnPassantMove;
    private List<Point> legalMovesForPiece;
    private Point origin;
    private Point destination;

    ActionsPreformedHandler(BackendBoard backendBoard, Main controller) {
        this.backendBoard = backendBoard;
        this.controller = controller;
        isChoosingPiece = true;
        canMakeEnPassantMove = false;
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
        for (Point legalDestination: legalMovesForPiece) {
            if(position.equals(legalDestination)){
                backendBoard.makeAMove(origin, destination);
                terminateGame();
                if(gameIsOver()){
                    terminateGame();
                    return;
                }
                if(canMakeEnPassantMove){
                    handleEnPassant(destination);
                    canMakeEnPassantMove = false;
                }
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
            legalMovesForPiece = getLegalMoves(position);
            drawLegalTiles(position);
            controller.drawPieceTileRed(position);
        }
    }

    private void updateBoards(){
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
        if(backendBoard.getPiece(position) == null) {
            return null;
        }
        List<Point> moves = backendBoard.getPiece(position).getAllMoves(backendBoard);

        if(moves.size() != 0) {
            removeCheckingMoves(position, moves, backendBoard);
        }
        // will make a flag if the player can make an enPassant move
        if(backendBoard.getPiece(position) instanceof Pawn){
            Pawn tempPawn = (Pawn) backendBoard.getPiece(position);
            for (Point legalMove: moves) {
                for (Point enPasantMove: tempPawn.getEnPassantMoves()) {
                    if(legalMove.equals(enPasantMove)){
                        canMakeEnPassantMove = true;
                        break;
                    }
                }
            }
        }

        return moves;
    }

    private void removeCheckingMoves(Point playersPosition, List<Point> moves, BackendBoard board){
        List<Point> kingCheckingMoves = new ArrayList<>();
        List<Point> enemyMoves;
        Point playersKingPosition;
        BackendBoard newBoard = null;

        // new board instance with different pointer
        try {
            newBoard = new BackendBoard(board);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        // find all king checking moves
        for (Point positionBeingChecked: moves) {
            newBoard.makeAMove(playersPosition, positionBeingChecked);
            enemyMoves = newBoard.getAllEnemyMoves(positionBeingChecked);
            playersKingPosition = newBoard.getPlayerKingPosition(positionBeingChecked);
            for (Point enemyPossibleMove: enemyMoves) {
                if (enemyPossibleMove.equals(playersKingPosition)){
                    kingCheckingMoves.add(positionBeingChecked);
                }
            }
            newBoard.makeAMove(positionBeingChecked, playersPosition);
        }

        // remove king checking moves
         for (Point checkingMove: kingCheckingMoves) {
                moves.remove(checkingMove);

         }

    }

    // special moves todo add castling and promotion (הצרחה וחייל למלכה )
    private void handleEnPassant(Point position) {
        Pawn tempPawn = (Pawn) backendBoard.getPiece(position);
        Point enemyPawn = new Point(position.x, position.y);
        for (Point enPassantMove: tempPawn.getEnPassantMoves()) {
            if(position.equals(enPassantMove)){
                if(tempPawn.getColor() == Color.white){
                    enemyPawn.y += 1;
                } else {
                    enemyPawn.y -= 1;
                }
                backendBoard.clearTile(enemyPawn);
            }
        }
    }

    private boolean gameIsOver() {
        int kingCount = 0;
        Point tempPosition;
        for (int y = 0; y < BackendBoard.ROWS; y++) {
            for (int x = 0; x < BackendBoard.COLUMNS; x++) {
                tempPosition = new Point(x,y);
                if(backendBoard.getPiece(tempPosition) != null){
                    if(backendBoard.getPiece(tempPosition) instanceof King){
                        kingCount++;
                    }
                }
                if(kingCount == 2) {
                    break;
                }
            }
        }
        return kingCount < 2;
    }

    private void terminateGame() {
        controller.gameOver();
    }

    public BackendBoard getBackendBoardInstance() {
        return backendBoard;
    }

}
