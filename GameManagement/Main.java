package GameManagement;

import GUI.GUIBoard;
import GUI.GameOverWindow;
import GUI.PawnPromotionWindow;
import GameLogic.BackendBoard;
import GameObjects.*;

import java.awt.*;
import java.util.List;

public class Main {

    private BackendBoard backendBoard;
    private GUIBoard GUIBoard;
    private Color currentPlayersColor = Color.white;
    private Point redTile;

    private Main(){
        backendBoard = new BackendBoard();
        GUIBoard = new GUIBoard(new ActionsPreformedHandler(backendBoard, this));
    }

    void updateGUIBoard(){
        GUIBoard.updateBoard();
        GUIBoard.paintBoard();
    }

    Color getCurrentPlayersColor() {
        return this.currentPlayersColor;
    }

    void changeCurrentPlayersColor(){
        if (currentPlayersColor == Color.white){
            currentPlayersColor = Color.black;
        } else {
            currentPlayersColor = Color.white;
        }
    }

    void drawLegalTiles(List<Point> legalMovesForPiece) {
        this.GUIBoard.drawLegalTiles(legalMovesForPiece);
    }

    void drawIllegalTiles(List<Point> illegalMovesForPiece) {
        this.GUIBoard.drawIllegalTiles(illegalMovesForPiece);
    }

    void resetTilesToOriginalColors() {
        this.GUIBoard.resetTiles();
    }

    void drawPieceTileRed(Point position) {
        redTile = position;
        GUIBoard.drawTileRed(position);
    }

    Piece getNewPiece(Point position) {
        Piece piece;
        Color pieceColor = backendBoard.getPiece(position).getColor();
        PawnPromotionWindow pw = new PawnPromotionWindow(pieceColor, GUIBoard);
        switch (pw.pieceChosenType) {
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
                throw new Error("piece mismatch " + pw.pieceChosenType);
        }
        return piece;
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
        backendBoard = null;
        GUIBoard.dispose();
    }

    public static void main(String [] args){
        // todo add open window
        new Main();
    }

}
