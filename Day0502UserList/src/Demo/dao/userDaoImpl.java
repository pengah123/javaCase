package Demo.dao;

import Demo.doMain.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class userDaoImpl implements userDao {
    JdbcTemplate templete = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<User> findAll() {
        String sql = "select * from user";
        List<User> list = templete.query(sql, new BeanPropertyRowMapper<User>(User.class));
        return list;
    }

    @Override
    public User varifyLogin(User user) {
        try {
            String sql = "select* from user where username=? and password=?";
            User user1 = templete.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), user.getUsername(), user.getPassword());
            return user1;
        } catch (DataAccessException e) {
            return null;
        }

    }

    @Override
    public void addUser(User user) {
        String sql = "insert into user (username,age,gender,hometown,email) values(?,?,?,?,?)";
        templete.update(sql, new Object[]{user.getUsername(), user.getAge(), user.getGender(), user.getHometown(), user.getEmail()});
    }


    @Override
    public void updateUser(User user) {
        String sql = "update user set age=? ,gender=?,hometown=?,email=? where id=?";
        templete.update(sql, new Object[]{user.getAge(), user.getGender(), user.getHometown(), user.getEmail(), user.getId()});
    }

    @Override
    public void deleteUser(String id) {
        int i = Integer.parseInt(id);
        String sql = "delete from user where id=?";
        templete.update(sql, i);
    }

    @Override
    public User findUser(String id) {
        int i = Integer.parseInt(id);
        String sql = "select* from user where id=?";
        User user = templete.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), i);
        return user;
    }

    @Override
    public int Count(Map<String, String[]> condition) {
        String sql = "select count(*) from user where 1=1";
        StringBuilder sb = new StringBuilder(sql);
//        遍历条件map
        Set<String> keys = condition.keySet();
        List<Object> params = new ArrayList<>();
        for (String key : keys) {
            if (key.equals("currPage") || key.equals("rows"))
                continue;
            String value = condition.get(key)[0];
            if (value != null && !value.equals("")) {
                sb.append(" and " + key + " like ? ");
                params.add("%" + value + "%");
            }
        }
        return templete.queryForObject(sb.toString(), Integer.class, params.toArray());
    }

    @Override
    public List<User> pageList(int start, int rows, Map<String, String[]> condition) {
        String sql = "select * from user where 1=1";
        StringBuilder sb = new StringBuilder(sql);
//        遍历条件map
        Set<String> keys = condition.keySet();
        List<Object> params = new ArrayList<>();
        for (String key : keys) {
            if (key.equals("currPage") || key.equals("rows"))
                continue;
            String value = condition.get(key)[0];
            if (value != null && !value.equals("")) {
                sb.append(" and " + key + " like ? ");
                params.add("%" + value + "%");
            }
        }
        sb.append(" limit ?, ?");
        params.add(start);
        params.add(rows);
        try {
            return templete.query(sb.toString(), new BeanPropertyRowMapper<User>(User.class), params.toArray());
        } catch (Exception e) {
            return null;
        }
    }
}
