package cn.edu.jju.outschoolreaders.config;

import java.time.Duration;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.ibatis.cache.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import cn.edu.jju.outschoolreaders.OutschoolReadersApplication;

@Component
@Scope("prototype")
public class RedisCache implements Cache {

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;// = new RedisTemplate<String, Object>();
			//=(RedisTemplate<String, Object>) OutschoolReadersApplication.getApplicationContext().
			//getBean(RedisTemplate.class);
	
	private final ReadWriteLock lock = new ReentrantReadWriteLock();
	
	private String id;
	
//	public RedisCache() {
//		
//	}
//	
	public RedisCache(String id) {
		this.id = id;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void putObject(Object key, Object value) {
		redisTemplate.opsForValue().set(key.toString(), value, Duration.ofHours(1));
	}

	@Override
	public Object getObject(Object key) {
		return redisTemplate.opsForValue().get(key);
	}

	@Override
	public Object removeObject(Object key) {
		return redisTemplate.delete(key.toString());
	}

	@Override
	public void clear() {
		redisTemplate.execute(new RedisCallback<Object>() {
			@Override
			public Object doInRedis(RedisConnection connection) throws DataAccessException {
				connection.flushDb();
				return null;
			}			
		});
	}

	@Override
	public int getSize() {
		Integer i = redisTemplate.execute(new RedisCallback<Integer>() {
			@Override
			public Integer doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.dbSize().intValue();
			}			
		});
		return i;
	}

	@Override
	public ReadWriteLock getReadWriteLock() {
		return lock;
	}

}
