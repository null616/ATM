package edu.jlu.group17.front.ui;

import edu.jlu.group17.back.controller.OperationController;
import edu.jlu.group17.back.entity.Client;
import edu.jlu.group17.back.entity.Transaction;

import javax.swing.*;
import java.io.IOException;
import java.util.List;

/**
 * @author 10186
 */
public class TransactionInterface extends AbstractInterface{
    private final Client client;
    public TransactionInterface(Client c) throws IOException, UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        super();
        client=c;
    }

    @Override
    public void init() throws IOException {
        jf.setTitle("流水记录界面");
        final String[]columnName={"时间","金额变化","说明"};
        List<Transaction> transactionList = OperationController.transaction(client);
        Object[][]data=new Object[transactionList.size()][3];
        for (int i = 0; i < transactionList.size(); i++) {
            data[i][0]=transactionList.get(i).getCreate_time();
            data[i][1]=transactionList.get(i).getMoney_change();
            data[i][2]=transactionList.get(i).getExplanation();
        }
        Box box=Box.createVerticalBox();
        JTable table=new JTable(data,columnName);
        JButton btn=new JButton("返回");
        btn.addActionListener(e -> {
            try {
                new OperationInterface(client).init();
            } catch (IOException | UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException |
                     IllegalAccessException ex) {
                throw new RuntimeException(ex);
            }
            jf.dispose();
        });
        box.add(new JScrollPane(table));
        JPanel panel=new JPanel();
        panel.add(Box.createHorizontalGlue());
        panel.add(btn);
        panel.add(Box.createHorizontalGlue());
        box.add(panel);

        bgPanel.add(box);
        jf.add(bgPanel);
        jf.setVisible(true);
    }
}
