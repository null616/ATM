package edu.jlu.group17.back.controller;


import edu.jlu.group17.back.entity.Client;
import edu.jlu.group17.back.utils.JDBCUtils;
import edu.jlu.group17.back.utils.R;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @author 10186
 */
public class ClientLoginController {
    public static R<Client> login(String id, String pwd){
        JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDataSource());
        String sql="select * from client where id=?";
        List<Client> query = template.query(sql, new BeanPropertyRowMapper<>(Client.class), id);
        if(query.isEmpty()){
            return R.error("插入卡片错误");
        }
        if(!query.get(0).getPassword().equals(pwd)){
            return R.error("密码错误");
        }
        return R.success(query.get(0));
    }
}
