package GUI;

import GameLogic.BackendBoard.Type;
import GameManagement.ActionsPreformedHandler;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

class Tile extends JPanel implements MouseListener {

    private ActionsPreformedHandler handler;
    private Color originalColor;
    private Color pieceColor;
    private Type pieceType;
    private BufferedImage image;
    private boolean hasPiece;
    Color tileColor;
    int x;
    int y;

    Tile(int x, int y, Color color, ActionsPreformedHandler handler) {
        this.x = x;
        this.y = y;
        this.tileColor = color;
        this.originalColor = color;
        this.handler = handler;
        this.hasPiece = true;
        panelSetUp();
        this.repaint();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        this.setBackground(tileColor);
        if(!hasPiece){
            try {
                image = getPieceImage();
            } catch (IOException e) {
                e.printStackTrace();
            }
            g.drawImage(image, 10, 10, GUIBoard.TILE_SIZE - 20, GUIBoard.TILE_SIZE - 20, this);
        }
    }

    private void panelSetUp() {
        this.setPreferredSize(new Dimension(GUIBoard.TILE_SIZE, GUIBoard.TILE_SIZE));
        this.setBorder(BorderFactory.createLineBorder(Color.darkGray));
        this.addMouseListener(this);
    }

    private BufferedImage getPieceImage() throws IOException{
        BufferedImage pieceImage;
        if(pieceColor == Color.black){
             pieceImage = getBlackImages();
        } else {
            pieceImage = getWhiteImages();
        }
        return pieceImage;
    }

    private BufferedImage getWhiteImages() throws IOException {
        BufferedImage pieceImage = null;
        switch (pieceType) {
            case KING:
                pieceImage = getImageFromFileName("Images/white_king.png");
                break;
            case QUEEN:
                pieceImage = getImageFromFileName("Images/white_queen.png");
                break;
            case ROOK:
                pieceImage = getImageFromFileName("Images/white_rook.png");
                break;
            case BISHOP:
                pieceImage = getImageFromFileName("Images/white_bishop.png");
                break;
            case KNIGHT:
                pieceImage = getImageFromFileName("Images/white_knight.png");
                break;
            case PAWN:
                pieceImage = getImageFromFileName("Images/white_pawn.png");
                break;
        }
        return pieceImage;
    }

    private BufferedImage getBlackImages() throws IOException {
        BufferedImage pieceImage = null;
        switch (pieceType) {
            case KING:
                pieceImage = getImageFromFileName("Images/black_king.png");
                break;
            case QUEEN:
                pieceImage = getImageFromFileName("Images/black_queen.png");
                break;
            case ROOK:
                pieceImage = getImageFromFileName("Images/black_rook.png");
                break;
            case BISHOP:
                pieceImage = getImageFromFileName("Images/black_bishop.png");
                break;
            case KNIGHT:
                pieceImage = getImageFromFileName("Images/black_knight.png");
                break;
            case PAWN:
                pieceImage = getImageFromFileName("Images/black_pawn.png");
                break;
        }
        return pieceImage;
    }

    private BufferedImage getImageFromFileName(String fileName) throws IOException {
        return ImageIO.read(getClass().getResource(fileName));
    }

    void addPiece(Color pieceColor, Type pieceType) {
        this.pieceColor = pieceColor;
        this.pieceType = pieceType;
        this.hasPiece = false;
    }

    void removePiece() {
        this.pieceType = null;
        this.pieceColor = null;
        this.hasPiece = true;
    }

    void resetTileColor(){
        tileColor = originalColor;
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        handler.mousePressed(new Point(this.x, this.y));
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {
        handler.mouseEntered(new Point(this.x, this.y));
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {
        handler.mouseExited();
    }

    // unnecessary at the moment
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        // no need to handle this event
    }

    // unnecessary at the moment
    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        // no need to handle this event
    }
}
