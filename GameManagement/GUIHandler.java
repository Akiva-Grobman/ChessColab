package GameManagement;

import BackendObjects.BackendBoard;
import BackendObjects.Pieces.*;
import java.awt.*;
import java.util.List;

public class GUIHandler {

    private Main controller;
    private boolean isChoosingPiece;
    private List<Point> legalMovesForPiece;
    private List<Point> illegalMovesForPiece;
    private Point origin;
    private Point destination;

    GUIHandler(Main controller) {
        this.controller = controller;
        isChoosingPiece = true;
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
        }
    }

    public void mousePressed(Point position) {
        if(!isChoosingPiece && cancelingClick(position)){
            isChoosingPiece = (controller.getHasPiece(position) && controller.getPieceColor(position) == controller.getCurrentPlayersColor());
            controller.resetTilesToOriginalColors();
        }
        if(isChoosingPiece && isCurrentPlayersPiece(position)){
            choosingPiece(position);
            drawTiles();
            if(hasMoves()) {
                controller.drawPieceTileRed(position);
                isChoosingPiece = false;
            }
        } else if(!isChoosingPiece && controller.getHasPiece(origin)) {
            destination = position;
            if (isCurrentPlayersPiece(destination)) {
                choosingPiece(destination);
                drawTiles();
                if(hasMoves()) {
                    controller.drawPieceTileRed(destination);
                    isChoosingPiece = false;
                }
            } else {
                controller.handleMove(origin, destination);
            }
            controller.resetTilesToOriginalColors();
            isChoosingPiece = true;
        }
        updateGUIBoard();
    }

    public boolean getHasPiece(Point position) {
        return controller.getHasPiece(position);
    }

    public Color getPieceColor(Point position) {
        return controller.getPieceColor(position);
    }

    public BackendBoard.Type getPieceType(Point piecePosition) {
        return controller.getPieceType(piecePosition);
    }

    private boolean cancelingClick(Point position) {
        boolean hasPlayersPiece;
        try {
            hasPlayersPiece = (controller.getPieceColor(origin) == controller.getCurrentPlayersColor());
        } catch (NullPointerException e) {
            hasPlayersPiece = false;
        }
        return position.equals(origin) || hasPlayersPiece;
    }

    private void choosingPiece(Point position) {
        illegalMovesForPiece = getIllegalMovesForPiece(position);
        legalMovesForPiece = getLegalMoves(position);
        origin = position;
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

    private List<Point> getLegalMoves(Point playersPosition) {
        return controller.getLegalMovesForPiece(playersPosition);
    }

    private List<Point> getIllegalMovesForPiece(Point playersPosition) {
        return controller.getIllegalMovesForPiece(playersPosition);
    }

//    private void setFlagsForSpecialMoves(Point position, List<Point> moves) {
//        // en passant flag
//        if(backendBoard.getPiece(position) instanceof Pawn){
//            Pawn tempPawn = (Pawn) backendBoard.getPiece(position);
//            for (Point legalMove: moves) {
//                for (Point enPassantMove: tempPawn.getEnPassantMoves()) {
//                    if(legalMove.equals(enPassantMove)){
//                        canMakeEnPassantMove = true;
//                        break;
//                    }
//                }
//            }
//        }
//    }
//
//    private void handleEnPassant(Point position) {
//        Pawn tempPawn = (Pawn) backendBoard.getPiece(position);
//        Point enemyPawn = new Point(position.x, position.y);
//        for (Point enPassantMove: tempPawn.getEnPassantMoves()) {
//            if(position.equals(enPassantMove)){
//                if(tempPawn.getColor() == Color.white){
//                    enemyPawn.y += 1;
//                } else {
//                    enemyPawn.y -= 1;
//                }
//                backendBoard.clearTile(enemyPawn);
//            }
//        }
//    }

    private boolean isCurrentPlayersPiece(Point position) {
        return controller.isCurrentPieceColor(position);
    }

    private boolean hasMoves() {
        return legalMovesForPiece.size() != 0;
    }

}
