package GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import GameLogic.BackendBoard.Type;

public class Tile extends JPanel implements MouseListener {

    int x;
    int y;
    boolean isEmpty;
    Color tileColor;
    Color pieceColor;
    Type pieceType;

    Tile(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.tileColor = color;
        this.isEmpty = true;
        panelSetUp();
        paint();
    }

    public void paint() {
        this.setBackground(tileColor);
        if(!isEmpty){
            this.add(getPieceImage());
        }
    }

    private void panelSetUp() {
        this.setPreferredSize(new Dimension(GUIBoard.TILE_SIZE, GUIBoard.TILE_SIZE));
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.addMouseListener(this);
    }

    private JLabel getPieceImage(){
        JLabel pieceImage;
        if(tileColor == Color.black){
             pieceImage = getBlackImages();
        } else {
            pieceImage = getWhiteImages();
        }
        return pieceImage;
    }

    private JLabel getWhiteImages() {
        JLabel pieceImage = null;
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

    private JLabel getBlackImages() {
        JLabel pieceImage = null;
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
                pieceImage = getImageFromFileName("Images/lack_pawn.png");
                break;
        }
        return pieceImage;
    }

    private JLabel getImageFromFileName(String fileName) {
        return new JLabel(new ImageIcon(getClass().getResource(fileName)));
    }

    void addPiece(Color pieceColor, Type pieceType) {
        this.pieceColor = pieceColor;
        this.pieceType = pieceType;
        this.isEmpty = false;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }
}
