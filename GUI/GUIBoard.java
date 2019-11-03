package GUI;

import javax.swing.*;
import java.awt.*;

public class GUIBoard extends JFrame {

    public static final int ROWS = 8;
    public static final int COLUMNS = 8;
    private Tile [][] board;
    static final int TILE_SIZE = 90;

    public static void main(String [] args){
        new GUIBoard();
    }

    GUIBoard() {
        boardSetUP();
        addPiecesToBoard();
        this.setVisible(true);
    }

    // todo
    private void addPiecesToBoard() {
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
                    color = Color.white;
                } else {
                    color = Color.black;
                }
                tile = new Tile(x, y, color);
                this.add(tile);
            }
        }
        repaintBoard();
    }

    private void repaintBoard() {

    }
}
