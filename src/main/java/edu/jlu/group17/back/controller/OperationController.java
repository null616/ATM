package edu.jlu.group17.back.controller;

import edu.jlu.group17.back.entity.Client;
import edu.jlu.group17.back.utils.JDBCUtils;
import edu.jlu.group17.back.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;

/**
 * @author 10186
 */
@Slf4j
public class OperationController {
    /**
     * 存款
     * @param num 存款金额
     * @param client 客户实体类
     * @return 成功或失败
     */
    public static boolean deposit(double num,Client client) throws SQLException {
        JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDataSource());
        var conn= Objects.requireNonNull(template.getDataSource()).getConnection();
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
        var conn= Objects.requireNonNull(template.getDataSource()).getConnection();
        conn.setAutoCommit(false);
        String sql1="update client set balance=balance-? where card_number=?";
        return depositOrWithdraw(-num, client, template, conn, sql1);
    }

    private static boolean depositOrWithdraw(double num, @NotNull Client client, @NotNull JdbcTemplate template, @NotNull Connection conn, String sql1) throws SQLException {
        TransactionSynchronizationManager.initSynchronization();
        String sql2="insert into atm.transaction (client_id, money_change) VALUES (?,?)";
        log.info(String.valueOf(client));
        int rs1=0,rs2=0;
        try{
            rs1=template.update(sql1,num,client.getCard_number());
            rs2 = template.update(sql2, client.getId(), num);
            conn.commit();
        }catch (SQLException e){
            e.printStackTrace();
            conn.rollback();
        }catch (Exception e) {
            log.error(e.toString());
            conn.rollback();
        }finally{
            conn.setAutoCommit(true);
            JDBCUtils.close(null,conn);
        }
        return rs1==rs2 && rs1==1;
    }

    /**
     * 查询银行卡账号是否存在
     * @param cardNumber 银行卡账号
     * @return 查询成功返回姓名，失败返回失败状态
     */
    public static R<Client> transferGetName(String cardNumber){
        JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDataSource());
        String sql="select * from client where card_number=?";
        var query = template.queryForObject(sql, new BeanPropertyRowMapper<>(Client.class), cardNumber);
        if(query==null) {
            return R.error("无该银行卡账号");
        } else {
            return R.success(query);
        }
    }
    public static boolean transfer(Client oldClient,Client newClient,double num) throws SQLException {
        TransactionSynchronizationManager.initSynchronization();
        JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDataSource());
        var conn= Objects.requireNonNull(template.getDataSource()).getConnection();
        conn.setAutoCommit(false);
        String sql1="update client set balance=balance+? where card_number=?";
        String sql2="insert into atm.transaction (client_id, money_change) VALUES (?,?)";
        int rs1=0,rs2=0,rs3=0,rs4=0;
        try{
            rs1=template.update(sql1,-num,oldClient.getCard_number());
            rs2=template.update(sql2,oldClient.getId(),-num);
            rs3=template.update(sql1,num,newClient.getCard_number());
            rs4=template.update(sql2,newClient.getId(),num);
            conn.commit();
        }catch (SQLException e){
            e.printStackTrace();
            conn.rollback();
        }catch (Exception e) {
            log.error(e.toString());
            conn.rollback();
        }finally{
            conn.setAutoCommit(true);
            JDBCUtils.close(null,conn);
        }
        return rs1==rs2 && rs3==rs4 && rs2==1 &&rs3==1;
    }
}
