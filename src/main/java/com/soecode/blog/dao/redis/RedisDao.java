package com.soecode.blog.dao.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@Repository
public class RedisDao {
    private static final Logger logger = Logger.getLogger(String.valueOf(RedisDao.class));
    @Autowired
    private JedisPool jedisPool;

    public JedisPool getJedisPool() {
        return jedisPool;
    }

    public void setJedisPool(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    /**
     * jedisPool.getResource() 从连接池中获取一个链接
     */
    /**
     * 设置单key的值
     * @param key
     * @return
     */
    public String get(String key) {
        String msg;
        Object result = null;
        Jedis jedis =jedisPool.getResource();
        if(jedis == null){
            msg="get Redis Connection error";
            logger.info(msg);
            throw new RuntimeException(msg);
        }
        try {
            result = jedis.get(key);
        }catch (Exception e){
            msg="get Redis Connection error";
            logger.info(msg);
        }finally {
            jedis.close();
        }
        return (String) result;
    }

    /*  EX second ：设置键的过期时间为 second 秒。
                   SET key value EX second 效果等同于 SETEX key second value 。
               PX millisecond ：设置键的过期时间为 millisecond 毫秒。
                   SET key value PX millisecond 效果等同于 PSETEX key millisecond value 。
               NX ：只在键不存在时，才对键进行设置操作。
                   SET key value NX 效果等同于 SETNX key value 。
               XX ：只在键已经存在时，才对键进行设置操作。*/
    public String set(String key, String value, long time) {
        Object result ;
        Jedis jedis =jedisPool.getResource();
        if (time <= 0) {
            result = jedis.set(key,value);
        }else {
            String nxxx = this.existKey(key) ? "XX" : "NX";
            result = jedis.set(key,value,nxxx,"EX", time);
        }
        return (String) result;
    }
    public  String setMap (String key ,Map<String,String> map){
        Object result ;
        Jedis jedis =jedisPool.getResource();
        result = jedis.hmset(key,map);
        jedis.close();
        return (String) result;
    }
    public  Map<String, String> hgetAll(String  key ){
        Object result ;
        Jedis jedis =jedisPool.getResource();
        result = jedis.hgetAll(key);
        jedis.close();
        return (Map<String, String>) result;
    }

    public String hget( String key,String field) {
        Object result ;
        Jedis jedis = jedisPool.getResource();
        result = jedis.hget(key,field);
        jedis.close();
        return (String) result;
    }

    public Map hgetall (String key) {
        Object result ;
        Jedis jedis = jedisPool.getResource();
        result = jedis.hgetAll(key);
        jedis.close();
        return (HashMap) result;
    }

    public Object hset(String key, String field , String value) {
        return jedisPool.getResource().hset(key,field,value);
    }

    public boolean existKey(String key) {
        Boolean result;
        Jedis jedis =jedisPool.getResource();
        result = Boolean.valueOf(jedis.get(key));
        jedis.close();
        return result;
    }

    /**
     * 设置Key 过期时间
     */
    public long  setExpire(String key, int expire){
        Object result;
        Jedis jedis = jedisPool.getResource();
        result =jedis.expire(key,expire);
        jedis.close();
        return (long) result;
    }

    /**
     * 删除给定的一个或多个 key 。不存在的 key 会被忽略。
     *
     * @param key
     * @return 被删除 key 的数量。
     *
     */
    public long del(String key) {
        long result;
        String msg;
        Jedis jedis = jedisPool.getResource();
        if (jedis == null) {
            msg = "get Redis Connection error";
            logger.info(msg);
            throw new RuntimeException(msg);
        }
        try {
            result = jedis.del(key);
        } catch (Exception e) {
            msg = "invoke redis del error:" + e.getMessage();
            logger.info(msg);
            throw new RuntimeException(msg);
        } finally {
            jedis.close();
        }
        return result;

    }

    /**
     * 删除哈希表 key 中的一个或多个指定域，不存在的域将被忽略。
     *
     * @param key
     * @param field
     * @return 被成功移除的域的数量，不包括被忽略的域
     *
     */
    public long hdel(String key, String... field) {
        long result;
        String msg;
        Jedis jedis = jedisPool.getResource();
        if (jedis == null) {
            msg = "get Redis Connection error";
            logger.info(msg);
            throw new RuntimeException(msg);
        }
        try {
            result = jedis.hdel(key, field);
        } catch (Exception e) {
            msg = "invoke redis hdel error:" + e.getMessage();
            logger.info(msg);
            throw new RuntimeException(msg);
        } finally {
            jedis.close();
        }
        return result;

    }

    /**
     * 排序的zset
     */
    public String zset(String key, String field){
        Object result = null;
//        String msg;
//        Jedis jedis = jedisPool.getResource();
//        if (jedis == null) {
//            msg = "get Redis Connection error";
//            logger.info(msg);
//            throw new RuntimeException(msg);
//        }
//        try {
//            result = jedis.zrange(key, field);
//        } catch (Exception e) {
//            msg = "invoke redis hdel error:" + e.getMessage();
//            logger.info(msg);
//            throw new RuntimeException(msg);
//        } finally {
//            jedis.close();
//        }
        return null;

//    }
//    public List<>  hlist(String key){
//
//        Jedis jedis = jedisPool.getResource();
//        jedis.hgetAll()
    }

//    public void hmset(String key, String token) {
//    }
}
