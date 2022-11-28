package edu.jlu.group17.front.ui;

import edu.jlu.group17.back.entity.Client;

import javax.swing.*;
import java.io.IOException;

/**
 * @author 10186
 */
public class InfoInterface extends AbstractInterface{
    private final Client client;
    public InfoInterface(Client c) throws IOException {
        client=c;
    }

    @Override
    public void init() {
        jf.setTitle("个人信息");
        JLabel label1=new JLabel("银行卡号："+client.getCard_number());
        JLabel label2=new JLabel("姓名："+client.getName());
        JLabel label3=new JLabel("余额："+client.getBalance());
        Box box=Box.createVerticalBox();
        box.add(label1);
        box.add(label2);
        box.add(label3);
        bgPanel.add(box);
        jf.add(bgPanel);
        jf.setVisible(true);
    }

    public static void main(String[] args) throws IOException {
        Client c=new Client();
        c.setCard_number("622203011195143");
        c.setName("测试");
        c.setBalance(985.211);
        new InfoInterface(c).init();
    }
}
