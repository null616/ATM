package edu.jlu.group17.front.component;

import javax.swing.*;
import java.awt.*;

/**
 * @author 10186
 */
public class BackgroundPanel extends JPanel {
    private final Image backIcon;

    public BackgroundPanel(Image backIcon) {
        this.backIcon = backIcon;
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(backIcon,0,0,this.getWidth(),this.getHeight(),null);
    }

}
