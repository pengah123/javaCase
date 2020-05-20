package Utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class JedisUtils {
    private static Jedis jedis;
    static{
        InputStream is = JedisUtils.class.getClassLoader().getResourceAsStream("jedis.properties");
        Properties pro = new Properties();

        try {
            pro.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(Integer.parseInt(pro.getProperty("maxIdle")));
        config.setMaxTotal(Integer.parseInt(pro.getProperty("maxTotal")));

        JedisPool pool = new JedisPool(config,pro.getProperty("host"),Integer.parseInt(pro.getProperty("port")));

        jedis=pool.getResource();
    }

    public static Jedis getJedis() {
        return jedis;
    }
}
