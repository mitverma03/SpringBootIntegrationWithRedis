package com.RedisCache.RedisCache.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.RedisCache.RedisCache.service.RedisCacheService;

@Service
public class RedisScheduler {

	@Autowired
	private RedisCacheService cacheService;
	
	@Scheduled(cron = "* 0 6 * * *")
	public void refresh() {
		cacheService.refresh();
	}
}
