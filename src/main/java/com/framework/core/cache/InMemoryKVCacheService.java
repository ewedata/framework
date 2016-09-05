package com.framework.core.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author matrix
 *
 */
public class InMemoryKVCacheService implements IKVCacheService {

    static {
        System.out.println("init InMemoryKVCacheService ...");
    }

    private final Map<String, Object> map = new ConcurrentHashMap<String, Object>();

    @Override
    public boolean put(String key, Object value) {
        map.put(key, value);
        return true;
    }

    @Override
    public Object get(String key) {
        return map.get(key);
    }

    @Override
    public boolean remove(String key) {
        map.remove(key);
        return true;
    }

    @Override
    public void clear() {
        map.clear();
    }

}
