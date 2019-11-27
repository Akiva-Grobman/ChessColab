package GUI;

import GameLogic.BackendBoard.Type;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class PawnPromotionWindow {

    public Type pieceChosenType;
    private JDialog dialog;

    public PawnPromotionWindow(Color playersColor, GUIBoard frame) {
        pieceChosenType = null;
        JOptionPane promotionWindow = new JOptionPane();
        JButton [] buttons = getButtons(playersColor);
        promotionWindow.setMessage("Please choose the piece you'd like to promote to");
        promotionWindow.setMessageType(JOptionPane.QUESTION_MESSAGE);
        promotionWindow.setOptions(new Object[] { buttons[0], buttons[1], buttons[2], buttons[3] });
        dialog = promotionWindow.createDialog(frame, "Pawn Promotion");
        dialog.setVisible(true);
    }

    private JButton[] getButtons(Color playersColor) {
        JButton[] buttons = new JButton[4];
        String color;
        if(playersColor == Color.black){
            color = "white_";
        } else {
            color = "black_";
        }
        try {
            buttons[0] = new JButton(new ImageIcon(getPieceImage(color, Type.QUEEN)));
            buttons[1] = new JButton(new ImageIcon(getPieceImage(color, Type.ROOK)));
            buttons[2] = new JButton(new ImageIcon(getPieceImage(color, Type.KNIGHT)));
            buttons[3] = new JButton(new ImageIcon(getPieceImage(color, Type.BISHOP)));
        } catch (IOException e) {
            buttons[0] = new JButton("Queen");
            buttons[1] = new JButton("Rook");
            buttons[2] = new JButton("Knight");
            buttons[3] = new JButton("Bishop");
        }
        addListeners(buttons);
        return buttons;
    }

    private void addListeners(JButton[] buttons) {
        for (int i = 0; i < buttons.length; i++) {
            int finalI = i;
            buttons[i].addActionListener(actionEvent -> clicked(finalI));
        }
    }

    private void clicked(int buttonIndex) {
        dialog.dispose();
        switch (buttonIndex){
            case 1:
                pieceChosenType = Type.ROOK;
                break;
            case 2:
                pieceChosenType = Type.KNIGHT;
                break;
            case 3:
                pieceChosenType = Type.BISHOP;
                break;
            default:
                pieceChosenType = Type.QUEEN;
        }
    }

    private BufferedImage getPieceImage(String pieceColor, Type pieceType) throws IOException{
        return getImageFromFileName("Images/" + pieceColor + pieceType.toString().toLowerCase() + ".png");
    }

    private BufferedImage getImageFromFileName(String fileName) throws IOException {
        return ImageIO.read(getClass().getResource(fileName));
    }

}
