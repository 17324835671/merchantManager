package com.sparksys.common.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.shiro.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.sparksys.common.entity.OperateLog;
import com.sparksys.common.entity.RedisInfoDetail;
import com.sparksys.common.service.RedisService;
import com.sparksys.common.utils.DateUtil;
import com.sparksys.common.utils.RedisUtil;

import redis.clients.util.Slowlog;
@Service
public class RedisServiceImpl implements RedisService {

	@Resource
    private RedisTemplate<String, ?> redisTemplate;
	
	@Resource
	RedisUtil redisUtil;
	
	@Override
    public boolean set(final String key, final String value) {
        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                connection.set(serializer.serialize(key), serializer.serialize(value));
                return true;
            }
        });
        return result;
    }

    @Override
    public String get(final String key) {
        String result = redisTemplate.execute(new RedisCallback<String>() {
            @Override
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                byte[] value = connection.get(serializer.serialize(key));
                return serializer.deserialize(value);
            }
        });
        return result;
    }

    @Override
    public boolean expire(final String key, long expire) {
        return redisTemplate.expire(key, expire, TimeUnit.HOURS);
    }

    @Override
    public boolean remove(final String key) {
        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
            @SuppressWarnings("unused")
			@Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                connection.del(key.getBytes());
                return true;
            }
        });
        return result;
    }

	@Override
	public boolean removeAll(Set<String> keys) {
        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
            @SuppressWarnings("unused")
			@Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                for (String key : keys) {
                	connection.del(key.getBytes());
				}
                return true;
            }
        });
        return result;
    }

	@Override
	public List<RedisInfoDetail> getRedisInfo() {
		//获取redis服务器信息
		String info = redisUtil.getRedisInfo();
		List<RedisInfoDetail> ridList = new ArrayList<RedisInfoDetail>();
		String[] strs = info.split("\n");
		RedisInfoDetail rif = null;
		if (strs != null && strs.length > 0) {
			for (int i = 0; i < strs.length; i++) {
				rif = new RedisInfoDetail();
				String s = strs[i];
				String[] str = s.split(":");
				if (str != null && str.length > 1) {
					String key = str[0];
					String value = str[1];
					rif.setKey(key);
					rif.setValue(value);
					ridList.add(rif);
				}
			}
		}
		return ridList;
	}

	@Override
	public List<OperateLog> getLogs(long entries) {
        List<Slowlog> list = redisUtil.getLogs(entries);
		List<OperateLog> opList = null;
		OperateLog op  = null;
		boolean flag = false;
		if (list != null && list.size() > 0) {
			opList = new LinkedList<OperateLog>();
			for (Slowlog sl : list) {
				String args = JSON.toJSONString(sl.getArgs());
				if (args.equals("[\"PING\"]") || args.equals("[\"SLOWLOG\",\"get\"]") || args.equals("[\"DBSIZE\"]") || args.equals("[\"INFO\"]")) {
					continue;
				}	
				op = new OperateLog();
				flag = true;
				op.setId(sl.getId());
				op.setExecuteTime(DateUtil.getDateStr(sl.getTimeStamp() * 1000));
				op.setUsedTime(sl.getExecutionTime()/1000.0 + "ms");
				op.setArgs(args);
				opList.add(op);
			}
		} 
		if (flag) 
			return opList;
		else 
			return null;
	}

	@Override
	public Long getLogLen() {
		return redisUtil.getLogsLen();
		}

	@Override
	public String logEmpty() {
		return redisUtil.logEmpty();
		}

	@Override
	public Map<String, Object> getKeysSize() {
		long dbSize = redisUtil.dbSize();
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("create_time", new Date().getTime());
		map.put("dbSize", dbSize);
		return map;
	}

	@Override
	public Map<String, Object> getMemeryInfo() {
		String[] strs = redisUtil.getRedisInfo().split("\n");
		Map<String, Object> map = null;
		for (int i = 0; i < strs.length; i++) {
			String s = strs[i];
			String[] detail = s.split(":");
			if (detail[0].equals("used_memory")) {
				map = new HashMap<String, Object>();
				map.put("used_memory",detail[1].substring(0, detail[1].length() - 1));
				map.put("create_time", new Date().getTime());
				break;
			}
		}
		return map;
	}

	@Override
	public Long increment(String key, long delta) {
		return redisTemplate.opsForValue().increment(key, delta);
	}
}
