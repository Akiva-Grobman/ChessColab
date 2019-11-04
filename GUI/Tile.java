package GUI;

import GameLogic.BackendBoard.Type;
import GameManagement.ActionsPreformedHandler;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

class Tile extends JPanel implements MouseListener {

    private ActionsPreformedHandler handler;
    int x;
    int y;
    boolean isEmpty;
    Color tileColor;
    Color pieceColor; // will probably not need this var
    Type pieceType;

    Tile(int x, int y, Color color, ActionsPreformedHandler handler) {
        this.x = x;
        this.y = y;
        this.tileColor = color;
        this.handler = handler;
        this.isEmpty = true;
        panelSetUp();
        this.repaint();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        this.setBackground(tileColor);
        if(!isEmpty){
            if(pieceColor == Color.black){
                g.setColor(Color.red);
            } else {
                g.setColor(Color.blue);
            }
            g.drawString(pieceType.toString(), 20, 50);
        }
    }

    private void panelSetUp() {
        this.setPreferredSize(new Dimension(GUIBoard.TILE_SIZE, GUIBoard.TILE_SIZE));
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.addMouseListener(this);
    }

    // todo not using this at the moment getting null pointer
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

    // todo will handle all of the actions preformed with the handler

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
