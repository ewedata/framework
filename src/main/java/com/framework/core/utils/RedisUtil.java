package com.framework.core.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.framework.core.init.DemoProperties;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * This Class is for managing Reids.
 * 
 * @author matrix
 *
 */
public class RedisUtil {

    private static final Logger LOG = LoggerFactory.getLogger(RedisUtil.class);

    private static DemoProperties prop = new DemoProperties();

    private static JedisPool pool;

    private static JedisPoolConfig config;

    private static int tryTimes;

    /**
     * private constructor
     */
    private RedisUtil() {}

    static {
        tryTimes = Integer.valueOf(prop.getProperty("redis.pool.tryTimes"));

        config = new JedisPoolConfig();
        config.setMaxIdle(Integer.valueOf(prop.getProperty("redis.pool.maxIdle")));
        config.setTestOnBorrow(Boolean.valueOf(prop.getProperty("redis.pool.testOnBorrow")));
        config.setTestOnReturn(Boolean.valueOf(prop.getProperty("redis.pool.testOnReturn")));

        try {
            pool = new JedisPool(config, prop.getProperty("redis.ip"),
                    Integer.valueOf(prop.getProperty("redis.port").trim()),
                    Integer.valueOf(prop.getProperty("redis.pool.timeOut")));
        } catch (Exception e) {
            LOG.error("create redis pool fail." + e.getMessage());
        }
    }

    /**
     * get the first db from redis, select 0 command
     * 
     * @return
     */
    public static Jedis getRedis() {
        return getRedis(0);
    }

    /**
     * get the special db from redis
     * 
     * @param db
     * @return
     */
    public static Jedis getRedis(int db) {
        Jedis jedis = null;
        int count = 0;
        do {
            try {
                jedis = pool.getResource();
            } catch (Exception e) {}
            if (db < 0 || db > 20) db = 0;
            if (jedis != null) {
                jedis.select(db);
                return jedis;
            }
            count++;
            LOG.error("无法取得连接,重新尝试,第:" + count + "次");
        } while (jedis == null && count < tryTimes);
        throw new RuntimeException("连接不可用!");
    }

    /**
     * close jedis
     */
    public static void closeJedis() {
        pool.getResource().close();
    }



}
