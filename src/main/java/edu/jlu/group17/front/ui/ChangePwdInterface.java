package edu.jlu.group17.front.ui;

import javax.swing.*;
import java.io.IOException;

public class ChangePwdInterface extends AbstractInterface{
    public ChangePwdInterface() throws IOException {
    }

    @Override
    public void init() throws IOException {
        jf.setTitle("修改密码界面");
        Box box1=Box.createHorizontalBox(),box2=Box.createHorizontalBox(),box3=Box.createHorizontalBox();
        box1.add(new JLabel("新密码："));
        box1.add(Box.createHorizontalStrut(50));
        box1.add(new JPasswordField(20));
        box2.add(new JLabel("确认密码："));
        box2.add(Box.createHorizontalStrut(50));
        box2.add(new JPasswordField(20));

        JButton btn1=new JButton("确定"),btn2=new JButton("返回");
        //TODO:
        box3.add(btn1);
        box3.add(Box.createHorizontalStrut(50));
        box3.add(btn2);
        Box box=Box.createVerticalBox();
        box.add(Box.createVerticalStrut(150));
        box.add(box1);
        box.add(Box.createVerticalStrut(20));
        box.add(box2);
        box.add(Box.createVerticalStrut(20));
        box.add(box3);

        bgPanel.add(box);
        jf.add(bgPanel);
        jf.setVisible(true);
    }

    public static void main(String[] args) throws IOException {
        new ChangePwdInterface().init();
    }
}
