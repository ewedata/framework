package com.framework.core.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.framework.core.utils.SerializationUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author matrix
 *
 */
public class RedisKVCacheService implements IKVCacheService {

    private static final Logger LOG = LoggerFactory.getLogger(RedisKVCacheService.class);

    @Autowired
    JedisPool pool;

    @Override
    public boolean put(String key, Object value) {

        Jedis jedis = null;
        boolean ret = false;

        try {
            jedis = pool.getResource();
            if ("OK".equals(jedis.set(key.getBytes(), SerializationUtil.serialize(value)))) {
                ret = true;
            }
        } catch (Exception e) {
            LOG.error("put key to redis fail ... ");
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }

        return ret;
    }

    @Override
    public Object get(String key) {

        Jedis jedis = null;
        Object ret = null;
        try {
            jedis = pool.getResource();
            ret = SerializationUtil.deserialize(jedis.get(key.getBytes()));
        } catch (Exception e) {
            LOG.error("get key from redis fail, key:" + key);
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return ret;
    }

    @Override
    public boolean remove(String key) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void clear() {
        // TODO Auto-generated method stub

    }

}
