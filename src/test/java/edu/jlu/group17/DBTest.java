package edu.jlu.group17;

import edu.jlu.group17.back.entity.Client;
import edu.jlu.group17.back.utils.JDBCUtils;
import net.sf.jsqlparser.statement.select.Select;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

public class DBTest {
    @Test
    public void test01(){
        JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDataSource());
        String sql="select * from client";
        List<Map<String, Object>> ans=template.queryForList(sql);
        Assert.assertEquals(4,ans.size());
    }
    @Test
    public void test02(){
        JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDataSource());
        String sql= "select * from client where id=420101198107178762";
        var ans=template.queryForMap(sql);
        Assert.assertFalse(ans.isEmpty());
        System.out.println(ans);
    }
}
