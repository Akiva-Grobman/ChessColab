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
    private List<Point> illegalMovesForPiece;
    private Point origin;
    private Point destination;

    ActionsPreformedHandler(BackendBoard backendBoard, Main controller) {
        this.backendBoard = backendBoard;
        this.controller = controller;
        isChoosingPiece = true;
        canMakeEnPassantMove = false;
    }

    public void mouseEntered(Point position) {
        if(isChoosingPiece && isCurrentPlayersPiece(position)) {
            choosingPiece(position);
            drawTiles();
        }
    }

    public void mouseExited() {
        if(isChoosingPiece) {
            controller.resetTilesToOriginalColors();
            isChoosingPiece = true;
        }
    }

    public void mousePressed(Point position) {
        if(cancelingClick(position)){
            controller.resetTilesToOriginalColors();
            isChoosingPiece = true;
        }
        if(isChoosingPiece && isCurrentPlayersPiece(position)){
            choosingPiece(position);
            drawTiles();
            if(hasMoves()) {
                controller.drawPieceTileRed(position);
                isChoosingPiece = false;
            }
            // todo check logic
        } else if(!isChoosingPiece && backendBoard.getHasPiece(origin)) {
            destination = position;
            if (isCurrentPlayersPiece(destination)) {
                choosingPiece(position);
                drawTiles();
                if(hasMoves()) {
                    controller.drawPieceTileRed(position);
                    isChoosingPiece = false;
                }
            } else {
                tryToMakeAMove(position);
            }
            controller.resetTilesToOriginalColors();
            isChoosingPiece = true;
        }
        updateGUIBoard();
    }

    private boolean cancelingClick(Point position) {
        boolean playersPiece;
        try {
            playersPiece = areTheSameColor(backendBoard.getPiece(position).getColor(), controller.getCurrentPlayersColor());
        } catch (NullPointerException e) {
            playersPiece = false;
        }
        return position.equals(origin) || playersPiece;
    }

    private void choosingPiece(Point position) {
        legalMovesForPiece = null;
        illegalMovesForPiece = null;
        origin = position;
        legalMovesForPiece = getLegalMoves(position);
        illegalMovesForPiece = getIllegalMovesForPiece(position, legalMovesForPiece);
    }

    private void tryToMakeAMove(Point position) {
        for (Point legalDestination: legalMovesForPiece) {
            if(position.equals(legalDestination)){
                backendBoard.makeAMove(origin, destination);
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

    private void drawTiles() {
        if(hasMoves()) {
            controller.drawLegalTiles(legalMovesForPiece);
        }
        if(illegalMovesForPiece.size() != 0) {
            controller.drawIllegalTiles(illegalMovesForPiece);
        }
    }

    private void updateGUIBoard(){
        controller.updateGUIBoard();
    }

    private List<Point> getLegalMoves(Point position) {
        List<Point> moves = backendBoard.getPiece(position).getAllMoves(backendBoard);
        if(moves.size() != 0) {
            removeCheckingMoves(position, moves);
        }
        setFlagsForSpecialMoves(position, moves);
        return moves;
    }

    private List<Point> getIllegalMovesForPiece(Point playersPosition, List<Point> moves) {
        BackendBoard board = getBackendBoardClone();
        List<Point> kingCheckingMoves = new ArrayList<>();
        List<Point> enemyMoves;
        Point playersKingPosition;
        for (Point positionBeingChecked: moves) {
            board.makeAMove(playersPosition, positionBeingChecked);
            enemyMoves = board.getAllEnemyMoves(positionBeingChecked);
            playersKingPosition = board.getPlayerKingPosition(positionBeingChecked);
            for (Point enemyPossibleMove: enemyMoves) {
                if (enemyPossibleMove.equals(playersKingPosition)){
                    kingCheckingMoves.add(positionBeingChecked);
                }
            }
            board.makeAMove(positionBeingChecked, playersPosition);
        }
        return kingCheckingMoves;
    }

    private void removeCheckingMoves(Point playersPosition, List<Point> moves){
        List<Point> kingCheckingMoves;
        kingCheckingMoves = getIllegalMovesForPiece(playersPosition, moves);
        // remove king checking moves
         for (Point checkingMove: kingCheckingMoves) {
                moves.remove(checkingMove);
         }
    }

    // special moves todo add castling and promotion (הצרחה וחייל למלכה )
    private void setFlagsForSpecialMoves(Point position, List<Point> moves) {
        // en passant flag
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
    }

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

    private boolean isCurrentPlayersPiece(Point position) {
        try {
            return areTheSameColor(backendBoard.getPiece(position).getColor(), controller.getCurrentPlayersColor());
        } catch (NullPointerException e){
            return false;
        }
    }

    private boolean areTheSameColor(Color color, Color currentPlayersColor) {
        return color == currentPlayersColor;
    }

    private boolean hasMoves() {
        return legalMovesForPiece.size() != 0;
    }

    private BackendBoard getBackendBoardClone(){
        try {
            return new BackendBoard(this.backendBoard);
        } catch (CloneNotSupportedException e) {
            throw new Error("board cloning failed \n" + e.getMessage());
        }
    }

    public BackendBoard getBackendBoardInstance() {
        return backendBoard;
    }


}
