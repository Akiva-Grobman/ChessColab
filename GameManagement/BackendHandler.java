package GameManagement;

import BackendObjects.BackendBoard;
import BackendObjects.Pieces.Pawn;
import BackendObjects.Pieces.Piece;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BackendHandler {

    private Main controller;
    private BackendBoard board;
    private Color currentPlayersColor;
    private boolean canMakeEnPassantMove;

    public BackendHandler(Main controller, BackendBoard board) {
        this.controller = controller;
        this.board = board;
        currentPlayersColor = Color.white;
        canMakeEnPassantMove = false;
    }

    List<Point> getPieceLegalMoves(Point piecePosition) {
        List<Point> illegalMoves = getPieceIllegalMoves(piecePosition);
        List<Point> legalMoves = board.getPiece(piecePosition).getAllMoves(board);
        for (Point illegalMove: illegalMoves) {
            legalMoves.remove(illegalMove);
        }
        return legalMoves;
    }

    List<Point> getPieceIllegalMoves(Point piecePosition) {
        BackendBoard board = getBackendBoardClone();
        List<Point> kingCheckingMoves = new ArrayList<>();
        Point playersKingPosition = board.getPlayerKingPosition(currentPlayersColor);
        List<Point> enemyMoves;
        for (Point positionBeingChecked : board.getPiece(piecePosition).getAllMoves(board)) {
            board.makeAMove(piecePosition, positionBeingChecked);
            enemyMoves = board.getAllEnemyMoves(currentPlayersColor);
            for (Point enemyMove: enemyMoves) {
                if(enemyMove.equals(playersKingPosition)) {
                    kingCheckingMoves.add(positionBeingChecked);
                }
            }
            board.makeAMove(positionBeingChecked, piecePosition);
        }
        return kingCheckingMoves;
    }

    boolean getHasPiece(Point piecePosition) {
        return board.getHasPiece(piecePosition);
    }

    Color getPieceColor(Point piecePosition) {
        return board.getPiece(piecePosition).getColor();
    }

    BackendBoard.Type getPieceType(Point piecePosition) {
        return board.getPiece(piecePosition).getPieceType();
    }

    boolean isCurrentPlayersPiece(Point position) {
        if(board.getPiece(position) == null) {
            return false;
        }
        return board.getPiece(position).getColor() == currentPlayersColor;
    }

    void handleMove(Point origin, Point destination) {
        setSpecialMovesFlag(origin);
        for (Point legalDestination: getPieceLegalMoves(origin)) {
            if(destination.equals(legalDestination)){
                if(isPromotingPawn(origin)) {
                    handlePromotion(origin);
                }
                board.makeAMove(origin, destination);
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
        if(board.getPiece(origin) == null) {
            changeCurrentPlayersColor();
        }
    }

    private void setSpecialMovesFlag(Point origin) {

    }

    private void handleEnPassant(Point destination) {

    }

    private boolean isPromotingPawn(Point piecePosition) {
        int oneBeforeLastLine;
        if(currentPlayersColor == Color.white) {
            oneBeforeLastLine = 1;
        } else {
            oneBeforeLastLine = 6;
        }
        if(board.getPiece(piecePosition) instanceof Pawn) {
            return piecePosition.y == oneBeforeLastLine;
        } else {
            return false;
        }
    }

    private void handlePromotion(Point piecePosition) {
        Piece promotedPiece;
        promotedPiece = controller.getNewPiece(piecePosition, currentPlayersColor);
        board.changePieceType(piecePosition, promotedPiece);
    }

    private void changeCurrentPlayersColor() {
        if (currentPlayersColor == Color.white){
            currentPlayersColor = Color.black;
        } else {
            currentPlayersColor = Color.white;
        }
    }

    private void terminateGame() {
        controller.gameOver();
    }

    private boolean gameIsOver() {
        return board.getPlayerKingPosition(currentPlayersColor) == null;
    }

    private BackendBoard getBackendBoardClone() {
        try {
            return new BackendBoard(board);
        } catch (CloneNotSupportedException e) {
            throw new Error("board cloning failed \n" + e.getMessage());
        }
    }
}
