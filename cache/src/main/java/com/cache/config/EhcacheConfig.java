package com.cache.config;

import java.time.Duration;

import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.spi.CachingProvider;

import org.ehcache.config.CacheConfiguration;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.ExpiryPolicyBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.MemoryUnit;
import org.ehcache.jsr107.Eh107Configuration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import com.cache.pojo.Product;
import com.cache.pojo.Review;

@EnableCaching
@Configuration
public class EhcacheConfig {

	@Bean
	@Order(0)
	public CacheManager getCacheManager() {
		CachingProvider provider = Caching.getCachingProvider();
		CacheManager cacheManager = provider.getCacheManager();
		cacheManager.createCache("product", createConfiguration(Integer.class, Product.class));
		cacheManager.createCache("review", createConfiguration(Integer.class, Review.class));
		return cacheManager;
	}

	private <K,V>javax.cache.configuration.Configuration<K, V> createConfiguration(Class<K> keyType, Class<V> valueType) {
		CacheConfiguration<K, V> configuration = CacheConfigurationBuilder
				.newCacheConfigurationBuilder(keyType, valueType,
						ResourcePoolsBuilder.newResourcePoolsBuilder().offheap(10, MemoryUnit.MB))
				.withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofMinutes(10)))
				.build();
		javax.cache.configuration.Configuration<K, V> ehcacheCacheConfiguration  = Eh107Configuration
		.fromEhcacheCacheConfiguration(configuration);
		return ehcacheCacheConfiguration;
	}
}
