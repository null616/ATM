package edu.jlu.group17.front.ui;

import edu.jlu.group17.back.controller.OperationController;
import edu.jlu.group17.back.entity.Client;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @author 10186
 */
public class TransferInterface extends AbstractInterface{
    private final Client client;
    public TransferInterface(Client c) throws IOException {
        client=c;
    }

    @Override
    public void init() {
        jf.setTitle("转账界面");
        Box box1=Box.createHorizontalBox(),box2=Box.createHorizontalBox(),box3=Box.createHorizontalBox();
        box1.add(new JLabel("转账账号："));
        box1.add(Box.createHorizontalStrut(50));
        var field1=new JTextField(20);
        box1.add(field1);
        box2.add(new JLabel("转账金额："));
        box2.add(Box.createHorizontalStrut(50));
        var field2=new JTextField(20);
        box2.add(field2);

        JButton btn1=new JButton("确定"),btn2=new JButton("返回");
        btn1.addActionListener(e -> {
            String cardNumber=field1.getText().trim();
            if(cardNumber.equals(client.getCard_number())){
                JOptionPane.showMessageDialog(jf,"不能给自己转账！","警告",JOptionPane.WARNING_MESSAGE);
                return;
            }
            double money= Double.parseDouble(field2.getText().trim());
            var res= OperationController.transferGetName(cardNumber);
            if(!res.getCode()){
                JOptionPane.showMessageDialog(jf,res.getMsg(),res.getMsg(),JOptionPane.ERROR_MESSAGE);
            }
            else{
                int select=JOptionPane.showConfirmDialog(jf,"收款人姓名："+res.getData().getName()+"，确定转账？","提示",JOptionPane.YES_NO_OPTION);
                if(select==JOptionPane.YES_OPTION){
                    try {
                        if(OperationController.transfer(client,res.getData(),money)){
                            JOptionPane.showMessageDialog(jf,"转账成功","转账成功",JOptionPane.INFORMATION_MESSAGE);
                        }
                        else{
                            JOptionPane.showMessageDialog(jf,"转账失败","转账失败",JOptionPane.WARNING_MESSAGE);
                        }
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                else{
                    //TODO:
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
        new TransferInterface(null).init();
    }
}
