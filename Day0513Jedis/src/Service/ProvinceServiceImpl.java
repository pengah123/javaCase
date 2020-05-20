package Service;

import Dao.ProvinceDaoImpl;
import Domain.Province;
import Utils.JedisUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import redis.clients.jedis.Jedis;

import java.util.List;

public class ProvinceServiceImpl implements ProvinceService {
    ProvinceDaoImpl dao=new ProvinceDaoImpl();
    Jedis jedis=JedisUtils.getJedis();
    @Override
    public List<Province> findAll() {
        return dao.findAll();
    }

    /**
     * 使用redis缓存
     * @return
     */
    @Override
    public String findAllJson()  {
        String province = jedis.get("province");
        if(province==null||province.length()==0){
//            没有数据，从数据库中查询
            List<Province> all = dao.findAll();
//            序列化为json
            ObjectMapper mapper = new ObjectMapper();
            try {
                province = mapper.writeValueAsString(all);

            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            //                存到jedis中
            jedis.set("province",province);
            jedis.close();
        }
        return jedis.get("province");
    }
}
