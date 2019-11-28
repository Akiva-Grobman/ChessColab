package GameManagement;

import GUI.GUIBoard;
import GUI.GameOverWindow;
import GUI.PawnPromotionWindow;
import BackendObjects.BackendBoard;
import BackendObjects.Pieces.*;

import java.awt.*;
import java.util.List;

public class Main {

    private BackendHandler backendHandler;
    private GUIBoard GUIBoard;
    private Color currentPlayersColor = Color.white;

    private Main(){
        backendHandler = new BackendHandler(this, new BackendBoard());
        GUIBoard = new GUIBoard(new GUIHandler(this));
    }

    void updateGUIBoard(){
        GUIBoard.updateBoard();
        GUIBoard.paintBoard();
    }

    Color getCurrentPlayersColor() {
        return currentPlayersColor;
    }

    Color getPieceColor(Point piecePosition) {
        return backendHandler.getPieceColor(piecePosition);
    }

    BackendBoard.Type getPieceType(Point piecePosition) {
        return backendHandler.getPieceType(piecePosition);
    }

    Piece getNewPiece(Point position, Color pieceColor) {
        Piece piece;
        int direction;
        PawnPromotionWindow pawnPromotionWindow = new PawnPromotionWindow(pieceColor, GUIBoard);
        if(pawnPromotionWindow.pieceChosenType == null){
            return null;
        }
        if(pieceColor == Color.white) {
            direction = -1;
        } else {
            direction = 1;
        }
        switch (pawnPromotionWindow.pieceChosenType) {
            case QUEEN:
                piece = new Queen(pieceColor);
                break;
            case ROOK:
                piece = new Rook(pieceColor);
                break;
            case KNIGHT:
                piece = new Knight(pieceColor);
                break;
            case BISHOP:
                piece = new Bishop(pieceColor);
                break;
            default:
                throw new Error("piece mismatch " + pawnPromotionWindow.pieceChosenType);
        }
        piece.setPosition(new Point(position.x, position.y + direction));
        return piece;
    }

    void drawLegalTiles(List<Point> legalMovesForPiece) {
        GUIBoard.drawLegalTiles(legalMovesForPiece);
    }

    void drawIllegalTiles(List<Point> illegalMovesForPiece) {
        GUIBoard.drawIllegalTiles(illegalMovesForPiece);
    }

    void resetTilesToOriginalColors() {
        GUIBoard.resetTiles();
    }

    void drawPieceTileRed(Point position) {
        GUIBoard.drawTileRed(position);
    }


    void gameOver() {
        String gameOverMessage;
        if(currentPlayersColor == Color.white){
            gameOverMessage = "White";
        } else {
            gameOverMessage = "Black";
        }
        gameOverMessage += " Won";
        new GameOverWindow(gameOverMessage);
        backendHandler = null;
        GUIBoard.dispose();
    }

    public static void main(String [] args){
        // todo add open window
        new Main();
    }

    boolean isCurrentPieceColor(Point position) {
        return backendHandler.isCurrentPlayersPiece(position);
    }

    List<Point> getIllegalMovesForPiece(Point playersPosition) {
        return backendHandler.getPieceIllegalMoves(playersPosition);
    }

    List<Point> getLegalMovesForPiece(Point playersPosition) {
        return backendHandler.getPieceLegalMoves(playersPosition);
    }

    boolean getHasPiece(Point piecePosition) {
        return backendHandler.getHasPiece(piecePosition);
    }

    void handleMove(Point origin, Point destination) {
        backendHandler.handleMove(origin, destination);
    }

}
