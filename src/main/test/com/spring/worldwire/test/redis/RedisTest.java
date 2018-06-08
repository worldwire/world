package com.spring.worldwire.test.redis;

import redis.clients.jedis.Jedis;

/**
 * Desc: Desc
 * User: luxun
 * Date: 2018/6/5 15:18
 * To change this template use File | Settings | File and Code Templates | Includes | File Header.
 */
public class RedisTest {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("47.104.201.53", 6379);
        //验证密码，如果没有设置密码这段代码省略
        jedis.auth("123456QWEasd");
        jedis.connect();//连接
        jedis.set("test","haha");
        System.out.println(jedis.get("test"));
    }
}
