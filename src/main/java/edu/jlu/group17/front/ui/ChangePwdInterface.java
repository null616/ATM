package edu.jlu.group17.front.ui;

import edu.jlu.group17.back.controller.OperationController;
import edu.jlu.group17.back.entity.Client;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.io.IOException;

@Slf4j
public class ChangePwdInterface extends AbstractInterface{
    private final Client client;
    public ChangePwdInterface(Client c) throws IOException {
        client=c;
    }

    @Override
    public void init() throws IOException {
        jf.setTitle("修改密码界面");
        Box box1=Box.createHorizontalBox(),box2=Box.createHorizontalBox(),box3=Box.createHorizontalBox();
        box1.add(new JLabel("新密码："));
        box1.add(Box.createHorizontalStrut(50));
        var field1=new JPasswordField(20);

        box1.add(field1);
        box2.add(new JLabel("确认密码："));
        box2.add(Box.createHorizontalStrut(50));
        var field2=new JPasswordField(20);
        box2.add(field2);

        JButton btn1=new JButton("确定"),btn2=new JButton("返回");
        btn1.addActionListener(e -> {
            String pwd1= String.valueOf(field1.getPassword()).trim();
            String pwd2= String.valueOf(field2.getPassword()).trim();
            log.info(pwd1+","+pwd2);
            if(pwd1.isEmpty() || pwd2.isEmpty()){
                JOptionPane.showMessageDialog(jf,"输入不能为空！","警告",JOptionPane.WARNING_MESSAGE);
            }
            else if(!pwd1.equals(pwd2)){
                JOptionPane.showMessageDialog(jf,"确认密码与新密码不一致","警告",JOptionPane.WARNING_MESSAGE);
            }
            else if(JOptionPane.showConfirmDialog(jf,"确定修改密码？","提示",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
                if(OperationController.changePwd(client,pwd1)){
                    JOptionPane.showMessageDialog(jf,"修改密码成功！","提示",JOptionPane.INFORMATION_MESSAGE);
                }
                else{
                    JOptionPane.showMessageDialog(jf,"修改密码失败！","错误",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btn2.addActionListener(e -> {
            //TODO:
        });
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
        new ChangePwdInterface(null).init();
    }
}
