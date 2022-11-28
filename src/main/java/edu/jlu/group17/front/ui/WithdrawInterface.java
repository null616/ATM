package edu.jlu.group17.front.ui;

import javax.swing.*;
import java.io.IOException;

/**
 * @author 10186
 */
public class WithdrawInterface extends AbstractInterface{
    WithdrawInterface() throws IOException {
    }

    @Override
    public void init() {
        jf.setTitle("取款界面");

        Box box1=Box.createHorizontalBox(),box2=Box.createHorizontalBox();
        box1.add(new JLabel("取出金额："));
        box1.add(new JTextField(10));
        JButton btn1=new JButton("确定"),btn2=new JButton("返回");
        //TODO:确定按钮的ActionListener
        box2.add(btn1);
        box2.add(Box.createHorizontalStrut(20));
        box2.add(btn2);
        Box box=Box.createVerticalBox();
        box.add(Box.createVerticalStrut(150));
        box.add(box1);
        box.add(Box.createVerticalStrut(20));
        box.add(box2);
        bgPanel.add(box);
        jf.add(bgPanel);
        jf.setVisible(true);
    }

    public static void main(String[] args) throws IOException {
        new WithdrawInterface().init();
    }
}
