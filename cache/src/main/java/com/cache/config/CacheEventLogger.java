package com.cache.config;

import org.ehcache.event.CacheEvent;
import org.ehcache.event.CacheEventListener;

public class CacheEventLogger implements CacheEventListener<Object, Object>{

	@Override
	public void onEvent(CacheEvent<? extends Object, ? extends Object> event) {
		System.err.println(event.getKey()+"-"+event.getOldValue()+"-"+event.getNewValue());
	}
	

}
