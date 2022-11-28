package edu.jlu.group17.back.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCUtils {
    private static DataSource ds;
    static {
        Properties pro=new Properties();
        try {
            pro.load(JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties"));
            ds= DruidDataSourceFactory.createDataSource(pro);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
    public static void close(Statement stmt,Connection conn) throws SQLException {
        if(stmt!=null) {
            stmt.close();
        }
        if(conn!=null) {
            conn.close();
        }
    }
    public static void close(ResultSet rs, Statement stmt, Connection conn) throws SQLException{
        if(rs!=null) {
            rs.close();
        }
        close(stmt,conn);
    }
    public static DataSource getDataSource(){
        return ds;
    }
}
