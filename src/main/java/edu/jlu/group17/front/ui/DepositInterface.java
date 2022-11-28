package edu.jlu.group17.front.ui;

import javax.swing.*;
import java.io.IOException;

/**
 * @author 10186
 */
public class DepositInterface extends AbstractInterface{
    public DepositInterface() throws IOException {
    }

    @Override
    public void init() {
        jf.setTitle("存款界面");

        Box vBox=Box.createHorizontalBox();
        vBox.add(new JLabel("存入金额："));
        vBox.add(new JTextField(10));
        JButton btn=new JButton("确定");
        //TODO:确定按钮的ActionListener
        Box box=Box.createVerticalBox();
        box.add(Box.createVerticalStrut(150));
        box.add(vBox);
        box.add(Box.createVerticalStrut(20));
        box.add(btn);
        bgPanel.add(box);
        jf.add(bgPanel);
        jf.setVisible(true);
    }

    public static void main(String[] args) throws IOException {
        new DepositInterface().init();
    }
}
