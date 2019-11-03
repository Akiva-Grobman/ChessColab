package GUI;

import GameLogic.BackendBoard.Type;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

class Tile extends JPanel implements MouseListener {

    int x;
    int y;
    boolean isEmpty;
    Color color;
    Type pieceType;
    Image pieceImage;

    Tile(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.pieceImage = null;
        panelSetUp();
    }

    private void panelSetUp() {
        this.setPreferredSize(new Dimension(GUIBoard.TILE_SIZE, GUIBoard.TILE_SIZE));
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.addMouseListener(this);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        this.isEmpty = false;
        this.pieceType = Type.KING;
        this.setBackground(color);
        if(!isEmpty){
            drawImage(g);
            this.repaint();
        }
    }

    private void drawImage(Graphics g) {
        if(pieceImage == null)
        {
            try {
                pieceImage = getPieceImage();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        g.drawImage(pieceImage,0, 0, this);
    }

    private BufferedImage getPieceImage() throws IOException {
        BufferedImage pieceImage;
        if(color == Color.black){
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
                pieceImage = getImageFromFileName("white_king.png");
                break;
            case QUEEN:
                pieceImage = getImageFromFileName("white_queen.png");
                break;
            case ROOK:
                pieceImage = getImageFromFileName("white_rook.png");
                break;
            case BISHOP:
                pieceImage = getImageFromFileName("white_bishop.png");
                break;
            case KNIGHT:
                pieceImage = getImageFromFileName("white_knight.png");
                break;
            case PAWN:
                pieceImage = getImageFromFileName("white_pawn.png");
                break;
        }
        return pieceImage;
    }

    private BufferedImage getBlackImages() throws IOException {
        BufferedImage pieceImage = null;
        switch (pieceType) {
            case KING:
                pieceImage = getImageFromFileName("black_king.png");
                break;
            case QUEEN:
                pieceImage = getImageFromFileName("black_queen.png");
                break;
            case ROOK:
                pieceImage = getImageFromFileName("black_rook.png");
                break;
            case BISHOP:
                pieceImage = getImageFromFileName("black_bishop.png");
                break;
            case KNIGHT:
                pieceImage = getImageFromFileName("black_knight.png");
                break;
            case PAWN:
                pieceImage = getImageFromFileName("black_pawn.png");
                break;
        }
        return pieceImage;
    }

    private BufferedImage getImageFromFileName(String fileName) throws IOException {
        File classPathInput = new File(Tile.class.getResource(fileName).getFile());
        return ImageIO.read(classPathInput);  // todo not working
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
