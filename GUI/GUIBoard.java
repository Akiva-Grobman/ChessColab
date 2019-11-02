package GUI;

import javax.swing.*;
import java.awt.*;

public class GUIBoard extends JFrame {

    private final int ROWS = 8;
    private final int COLUMNS = 8;
    static final int TILE_SIZE = 90;

    public static void main(String [] args){
        new GUIBoard();
    }

    GUIBoard() {
        boardSetUP();
        this.setVisible(true);
    }

    private void boardSetUP() {
        int windowWidth = TILE_SIZE * ROWS;
        int windowHeight = TILE_SIZE * COLUMNS;
        this.setLayout(new GridLayout(ROWS,COLUMNS));
        this.setSize(windowWidth, windowHeight);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        addTiles();
    }

    private void addTiles() {
        Tile tile;
        Color color;
        for (int y = 0; y < ROWS; y++) {
            for (int x = 0; x < COLUMNS; x++) {
                if((x + y) % 2 == 0){
                    color = Color.black;
                } else {
                    color = Color.white;
                }
                tile = new Tile(x, y, color);
                this.add(tile);
            }
        }
    }
}
