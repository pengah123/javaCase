package Servlet;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.rmi.AccessException;

public class userDoa {
    DataSource ds = JDBCUtils.getDataSource();
    JdbcTemplate template = new JdbcTemplate(ds);

    public static void main(String[] args) {
        userDoa userDoa = new userDoa();
        User user = new User();
        user.setName("zhangsan");
        user.setPassword("zhangsan");
        User result=userDoa.logindoa(user);
        System.out.println(result.toString());
    }
    public User logindoa(User user) {
        try {
            String sql = "select * from usertable where name=? and password=?";
            User result = template.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), user.getName(), user.getPassword());
            return result;
        }catch (DataAccessException e){
            return null;
        }
    }
}
