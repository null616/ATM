package edu.jlu.group17.front.ui;

import edu.jlu.group17.back.controller.ClientLoginController;
import edu.jlu.group17.front.component.BackgroundPanel;
import edu.jlu.group17.front.utils.ScreenUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class MainInterface {
    JFrame jf=new JFrame("ATM");
    final int WIDTH=500,HEIGHT=500;
    public void init() throws IOException {
        jf.setBounds((ScreenUtils.getScreenWidth()-WIDTH)/2,(ScreenUtils.getScreenHeight()-HEIGHT)/2,WIDTH,HEIGHT);
        jf.setResizable(false);
//        jf.setIconImage(ImageIO.read(new File("TODO:")));
        var bgPanel = new BackgroundPanel(ImageIO.read(new File("F:\\IDEA项目\\ATM\\src\\main\\java\\edu\\jlu\\group17\\front\\images\\background.png")));
        //组装登录元素
        Box vBox=Box.createVerticalBox();
        //组装账号
        Box uBox=Box.createHorizontalBox();
        JLabel uLabel=new JLabel("账号：");
        JTextField uField=new JTextField(15);

        uBox.add(uLabel);
        uBox.add(Box.createHorizontalStrut(20));
        uBox.add(uField);
        //组装密码
        Box pBox=Box.createHorizontalBox();
        JLabel pLabel=new JLabel("密 码：");
        JTextField pField=new JPasswordField(15);

        pBox.add(pLabel);
        pBox.add(Box.createHorizontalStrut(20));
        pBox.add(pField);

        Box btnBox=Box.createHorizontalBox();
        JButton loginBtn=new JButton("登录");
        loginBtn.addActionListener((e)->{
                String cardNumber=uField.getText().trim();
                String pwd = pField.getText().trim();
                var res= ClientLoginController.login(cardNumber,pwd);
                if(!res.getCode()){
                    JOptionPane.showMessageDialog(null,res.getMsg(),res.getMsg(),JOptionPane.ERROR_MESSAGE);
                }
                else{
                    JOptionPane.showMessageDialog(null,res.getData().getName()+"登录成功","登录成功",JOptionPane.INFORMATION_MESSAGE);

                }
            }
        );
        btnBox.add(loginBtn);

        vBox.add(Box.createVerticalStrut(50));
        vBox.add(uBox);
        vBox.add(Box.createVerticalStrut(20));
        vBox.add(pBox);
        vBox.add(Box.createVerticalStrut(40));
        vBox.add(btnBox);

        bgPanel.add(vBox);
        jf.add(bgPanel);
        jf.setVisible(true);
    }
    public static void main(String[] args) throws IOException {
        new MainInterface().init();
    }
}
