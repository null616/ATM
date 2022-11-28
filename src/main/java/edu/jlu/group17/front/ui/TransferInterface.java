package edu.jlu.group17.front.ui;

import javax.swing.*;
import java.io.IOException;

/**
 * @author 10186
 */
public class TransferInterface extends AbstractInterface{
    public TransferInterface() throws IOException {
    }

    @Override
    public void init() {
        jf.setTitle("转账界面");
        Box box1=Box.createHorizontalBox(),box2=Box.createHorizontalBox(),box3=Box.createHorizontalBox();
        box1.add(new JLabel("转账账号："));
        box1.add(Box.createHorizontalStrut(50));
        box1.add(new JTextField(20));
        box2.add(new JLabel("转账金额："));
        box2.add(Box.createHorizontalStrut(50));
        box2.add(new JTextField(20));

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
        new TransferInterface().init();
    }
}
