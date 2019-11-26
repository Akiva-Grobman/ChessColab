package GUI;

import javax.swing.*;

public class GameOverWindow {

    private String gameOverMessage;

    public GameOverWindow(String gameOverMessage) {
        this.gameOverMessage = gameOverMessage;
        String[] buttonNames = {"Home", "Exit"};
        int x = JOptionPane.showOptionDialog(null, gameOverMessage,
                "Game Over",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttonNames, buttonNames[0]);
        if(x == 0) {
            // todo send to home page (but we need one first)
        } else if(x == 1) {
            System.exit(0); // user decided to exit game
        }
    }
}
