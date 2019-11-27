package com.RedisCache.RedisCache.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RedisCacheService {

	@Autowired
	private JedisConnectionFactory jedisConnectionFactory;
	
	public void refresh() {
		log.info("Started Redis Cache Service : refresh");
		jedisConnectionFactory.getConnection().flushAll();
		log.info("Successfull Redis Cache Service : refresh");
	}
}
