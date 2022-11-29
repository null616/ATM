package edu.jlu.group17.back.controller;

import edu.jlu.group17.back.entity.Client;
import edu.jlu.group17.back.utils.JDBCUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author 10186
 */
public class OperationController {
    /**
     * 存款
     * @param num 存款金额
     * @param client 客户实体类
     * @return 成功或失败
     */
    public static boolean deposit(double num,Client client) throws SQLException {
        JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDataSource());
        var conn=JDBCUtils.getConnection();
        conn.setAutoCommit(false);
        String sql1="update client set balance=balance+? where card_number=?";
        return depositOrWithdraw(num, client, template, conn, sql1);
    }

    /**
     * 取款
     * @param num 取款金额
     * @param client 客户实体类
     * @return 成功或失败
     */
    public static boolean withdraw(double num, Client client)throws SQLException{
        JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDataSource());
        var conn=JDBCUtils.getConnection();
        conn.setAutoCommit(false);
        String sql1="update client set balance=balance-? where card_number=?";
        return depositOrWithdraw(num, client, template, conn, sql1);
    }

    private static boolean depositOrWithdraw(double num, @NotNull Client client, @NotNull JdbcTemplate template, @NotNull Connection conn, String sql1) throws SQLException {
        String sql2="insert into transaction (client_id, 'change') VALUES (?,?)";
        int rs1=0,rs2=0;
        try{
            rs1=template.update(sql1,num,client.getCard_number());
            rs2 = template.update(sql2, client.getId(), num);
            conn.commit();
        }catch (SQLException e){
            e.printStackTrace();
            conn.rollback();
        }finally {
            conn.setAutoCommit(true);
            JDBCUtils.close(null,conn);
        }
        return rs1==rs2 && rs1==1;
    }
}
