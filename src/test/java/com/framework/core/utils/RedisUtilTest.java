package com.framework.core.utils;

import org.junit.Test;

import com.framework.BaseTest;

import redis.clients.jedis.Jedis;

public class RedisUtilTest extends BaseTest {

    @Test
    public void testGetRedis() {
        Jedis inst = RedisUtil.getRedis();
        System.out.println(inst.get("name"));

    }

    @Test
    public void testGetRedisInt() {
        Jedis inst = RedisUtil.getRedis(3);
        System.out.println(inst.get("name"));

    }

    @Test
    public void testCloseJedis() {
        RedisUtil.closeJedis();
    }

}
