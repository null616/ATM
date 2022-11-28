package edu.jlu.group17.back.controller;

import edu.jlu.group17.back.utils.JDBCUtils;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;

/**
 * @author 10186
 */
public class OperationController {
    public static boolean deposit(double num,String cardNumber) throws SQLException {
        JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDataSource());
        var conn=JDBCUtils.getConnection();
        conn.setAutoCommit(false);
        String sql1="update client set balance=balance-? where card_number=?";
        String sql2="insert into transaction (client_id, 'change') VALUES (?,?)";
        int rs1=0,rs2=0;
        try{
            rs1=template.update(sql1,num,cardNumber);
            rs2 = template.update(sql2, cardNumber, -num);
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
