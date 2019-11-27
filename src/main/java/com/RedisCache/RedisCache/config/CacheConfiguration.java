package com.RedisCache.RedisCache.config;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
@EnableCaching
@EnableConfigurationProperties
public class CacheConfiguration {

	@Value("${spring.redis.host}")
	private String hostName;
	@Value("${spring.redis.port}")
	private Integer port;
	@Value("${spring.redis.time-to-live-seconds}")
	private Integer ttl;

	@Bean
	JedisConnectionFactory jedisConnectionFactory() {
		final RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(
				this.hostName, this.port);
		return new JedisConnectionFactory(redisStandaloneConfiguration);
	}

	@Bean(value = "redisTemplate")
	public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
		final RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(redisConnectionFactory);
		return redisTemplate;
	}

	@Primary
	@Bean(name = "redisCacheManager")
	public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {

		if (this.ttl > 0) {
			Duration expiration = Duration.ofSeconds(this.ttl);
			return RedisCacheManager.builder(redisConnectionFactory)
					.cacheDefaults(RedisCacheConfiguration.defaultCacheConfig().entryTtl(expiration)).build();
		}

		return RedisCacheManager.builder(redisConnectionFactory)
				.cacheDefaults(RedisCacheConfiguration.defaultCacheConfig()).build();
	}
}
