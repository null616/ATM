package edu.jlu.group17.back.controller;


import edu.jlu.group17.back.entity.Client;
import edu.jlu.group17.back.utils.JDBCUtils;
import edu.jlu.group17.back.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;




/**
 * @author 10186
 */
@Slf4j
public class ClientLoginController {
    public static R<Client> login(String cardNumber, String pwd){
        JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDataSource());
        String sql="select * from client where card_number=?";
        List<Client> query = template.query(sql, new BeanPropertyRowMapper<>(Client.class), cardNumber);
        if(query.isEmpty()){
            log.info("query.isEmpty()");
            return R.error("插入卡片错误");
        }
        if(!query.get(0).getPassword().equals(pwd)){
            log.info(query.get(0) +"   "+pwd);
            return R.error("密码错误");
        }
        return R.success(query.get(0));
    }
}
