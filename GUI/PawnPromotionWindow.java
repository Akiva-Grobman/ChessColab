package GUI;

import BackendObjects.BackendBoard.Type;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class PawnPromotionWindow {

    private final int ROOK_POSITION = 1;
    private final int KNIGHT_POSITION = 2;
    private final int BISHOP_POSITION = 3;
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
        if(playersColor == Color.white){
            color = "white_";
        } else {
            color = "black_";
        }
        try {
            buttons[0] = new JButton(new ImageIcon(getPieceImage(color, Type.QUEEN)));
            buttons[ROOK_POSITION] = new JButton(new ImageIcon(getPieceImage(color, Type.ROOK)));
            buttons[KNIGHT_POSITION] = new JButton(new ImageIcon(getPieceImage(color, Type.KNIGHT)));
            buttons[BISHOP_POSITION] = new JButton(new ImageIcon(getPieceImage(color, Type.BISHOP)));
        } catch (IOException e) {
            buttons[0] = new JButton("Queen");
            buttons[ROOK_POSITION] = new JButton("Rook");
            buttons[KNIGHT_POSITION] = new JButton("Knight");
            buttons[BISHOP_POSITION] = new JButton("Bishop");
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
            case ROOK_POSITION:
                pieceChosenType = Type.ROOK;
                break;
            case KNIGHT_POSITION:
                pieceChosenType = Type.KNIGHT;
                break;
            case BISHOP_POSITION:
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
