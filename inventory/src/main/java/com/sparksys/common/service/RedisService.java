package com.sparksys.common.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.sparksys.common.entity.OperateLog;
import com.sparksys.common.entity.RedisInfoDetail;

public interface RedisService {
	/**
     * set存数据
     * @param key
     * @param value
     * @return
     */
    boolean set(String key, String value);
    /**
     * get获取数据
     * @param key
     * @return
     */
    String get(String key);
    /**
     * 	计数
     * @param key
     * @param delta
     * @return
     */
    Long increment(String key, long delta);
    /**
     * 设置有效天数
     * @param key
     * @param expire
     * @return
     */
    boolean expire(String key, long expire);
    /**
     * 	移除数据
     * @param key
     * @return
     */
    boolean remove(String key);
    /**
     * 	移除数据多个
     * @param keys
     * @return
     */
    boolean removeAll(Set<String> keys);
    /**
     *获取redis服务器信息 
     * @return
     */
    public List<RedisInfoDetail> getRedisInfo();
    /**
     * 获取redis日志列表
     * @param entries
     * @return
     */
    public List<OperateLog> getLogs(long entries);
    /**
     * 获取日志总数
     * @return
     */
    public Long getLogLen();
    /**
     *清空日志 
     * @return
     */
    public String logEmpty();
    /**
     *获取当前数据库中key的数量 
     * @return
     */
    public Map<String,Object> getKeysSize();
    /**
     *获取当前redis使用内存大小情况 
     * @return
     */
    public Map<String,Object> getMemeryInfo();
}
