package edu.jlu.group17.front.ui;

import edu.jlu.group17.front.component.BackgroundPanel;
import edu.jlu.group17.front.utils.ScreenUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

/**
 * @author 10186
 */
public abstract class AbstractInterface {
    JFrame jf=new JFrame();
    BackgroundPanel bgPanel;
    final int WIDTH=500,HEIGHT=500;
    AbstractInterface() throws IOException, UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        jf.setBounds((ScreenUtils.getScreenWidth()-WIDTH)/2,(ScreenUtils.getScreenHeight()-HEIGHT)/2,WIDTH,HEIGHT);
        jf.setResizable(false);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        bgPanel = new BackgroundPanel(ImageIO.read(new File(
                "F:\\IDEA项目\\ATM\\src\\main\\java\\edu\\jlu\\group17\\front\\images\\background.png")));
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    }

    /**
     * 初始化
     */
    public abstract void init()throws IOException;
}
