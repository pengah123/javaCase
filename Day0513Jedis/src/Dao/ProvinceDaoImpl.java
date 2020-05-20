package Dao;

import Domain.Province;
import Utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class ProvinceDaoImpl implements ProvinceDao {
     private JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * 查询所有的结果集
     * @return
     */
    @Override
    public List<Province> findAll() {
        String sql="select* from province";
        List<Province> list = template.query(sql, new BeanPropertyRowMapper<Province>(Province.class));
        return list;
    }
}
