package edu.jlu.group17.front.ui;

import edu.jlu.group17.back.controller.ClientLoginController;

import javax.swing.*;
import java.io.IOException;

/**
 * @author 10186
 */
public class MainInterface extends AbstractInterface{

    MainInterface() throws IOException {
    }
@Override
    public void init() {
        jf.setTitle("登录界面");
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
    JPasswordField pField=new JPasswordField(15);

        pBox.add(pLabel);
        pBox.add(Box.createHorizontalStrut(20));
        pBox.add(pField);

        Box btnBox=Box.createHorizontalBox();
        JButton loginBtn=new JButton("登录");
        loginBtn.addActionListener(e->{
                String cardNumber=uField.getText().trim();
                String pwd = String.valueOf(pField.getPassword()).trim();
                var res= ClientLoginController.login(cardNumber,pwd);
                if(!res.getCode()){
                    JOptionPane.showMessageDialog(jf,res.getMsg(),res.getMsg(),JOptionPane.ERROR_MESSAGE);
                }
                else{
                    JOptionPane.showMessageDialog(jf,res.getData().getName()+"登录成功","登录成功",JOptionPane.INFORMATION_MESSAGE);
                    try {
                        new OperationInterface(res.getData()).init();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    jf.dispose();
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
