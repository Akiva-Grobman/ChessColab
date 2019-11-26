package GUI;

import javax.swing.*;
import java.awt.*;

class CustomXLabel extends JLabel {

    CustomXLabel(){
        this.setText("X");
        this.setHorizontalAlignment(SwingConstants.CENTER);
        this.setVerticalAlignment(SwingConstants.CENTER);
        this.setFont(new Font("Verdana",Font.BOLD,65));
        this.setForeground(Color.red);
    }

}
