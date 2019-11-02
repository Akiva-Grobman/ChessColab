package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import GameLogic.BackendBoard.Type;


public class Tile extends JPanel implements MouseListener {

    int x;
    int y;
    boolean isEmpty;
    Color color;
    Type pieceType;

    public Tile(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.isEmpty = true;
        panelSetUp();
    }

    private void panelSetUp() {
        this.setPreferredSize(new Dimension(GUIBoard.TILE_SIZE, GUIBoard.TILE_SIZE));
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.addMouseListener(this);
    }


    public void paintComponent(Graphics g){
        super.paintComponent(g);
        this.setBackground(color);
        // add image
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
