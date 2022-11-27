package edu.jlu.group17.front.ui;

import java.awt.*;

public class ScreenUtils {
    public static int getScreenWidth(){
        return Toolkit.getDefaultToolkit().getScreenSize().width;
    }
    public static int getScreenHeight(){
        return Toolkit.getDefaultToolkit().getScreenSize().height;
    }

    private ScreenUtils() {
    }
}
