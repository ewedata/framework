package com.framework.core.cache;

/**
 * 
 * @author matrix
 *
 */
public interface IKVCacheService {

    public boolean put(String key, Object value);

    public Object get(String key);

    public boolean remove(String key);

    public void clear();

}
