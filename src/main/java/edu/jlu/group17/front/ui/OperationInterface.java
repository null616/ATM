package edu.jlu.group17.front.ui;

import edu.jlu.group17.back.entity.Client;

import javax.swing.*;
import java.io.IOException;

public class OperationInterface extends AbstractInterface{
    private Client client;
    OperationInterface(Client c) throws IOException {
        client=c;
    }
    @Override
    public void init() throws IOException {
        jf.setTitle("操作界面");
        Box box1=Box.createHorizontalBox(),box2=Box.createHorizontalBox(),box3=Box.createHorizontalBox();
        JButton deposit= new JButton("存    款"),transfer=   new JButton("转    账"),
                withdraw=new JButton("取    款"),transaction=new JButton("流水记录"),
                info=    new JButton("个人信息"),changePwd=   new JButton("修改密码");
        //TODO: 六个按钮的ActionListener
        box1.add(deposit);
        box1.add(Box.createHorizontalStrut(200));
        box1.add(transfer);

        box2.add(withdraw);
        box2.add(Box.createHorizontalStrut(200));
        box2.add(transaction);

        box3.add(info);
        box3.add(Box.createHorizontalStrut(200));
        box3.add(changePwd);

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
        new OperationInterface(null).init();
    }
}
