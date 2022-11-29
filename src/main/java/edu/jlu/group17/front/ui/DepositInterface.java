package edu.jlu.group17.front.ui;

import edu.jlu.group17.back.controller.OperationController;
import edu.jlu.group17.back.entity.Client;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @author 10186
 */
public class DepositInterface extends AbstractInterface{
    private final Client client;
    public DepositInterface(Client c) throws IOException {
        client=c;
    }

    @Override
    public void init() {
        jf.setTitle("存款界面");

        Box box1=Box.createHorizontalBox(),box2=Box.createHorizontalBox();
        box1.add(new JLabel("存入金额："));
        JTextField field = new JTextField(10);
        box1.add(field);
        JButton btn1=new JButton("确定"),btn2=new JButton("返回");
        btn1.addActionListener(e -> {
            int confirmDialog = JOptionPane.showConfirmDialog(jf, "确定存款", "提示", JOptionPane.YES_NO_OPTION);
            if(confirmDialog!=JOptionPane.YES_OPTION){
                //TODO
            }
            else {
                double num= Double.parseDouble(field.getText().trim());
                try {
                    if(OperationController.deposit(num,client)){
                        JOptionPane.showMessageDialog(jf,"存款成功","存款成功",JOptionPane.INFORMATION_MESSAGE);
                    }
                    else{
                        JOptionPane.showMessageDialog(jf,"存款失败","存款失败",JOptionPane.WARNING_MESSAGE);
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }

        });
        btn2.addActionListener(e -> {
            //TODO
        });
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
        new DepositInterface(null).init();
    }
}
