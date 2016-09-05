/**
 * 
 */
package com.framework.dao.common;

import com.framework.entry.BaseEntry;

/**
 * 副本数据操作类型
 * 
 * @author matrix
 * @since 2016年8月19日 下午4:56:25
 * @param <T>
 */
public interface ICopyDao<T extends BaseEntry> extends ISingleTableDao<T> {

    /**
     * 当从正本同步数据到副本时，需要带主键
     * 
     * @param entry 待新增的数据实体
     * 
     * @return 主键
     */
    public abstract long insertForOrigin(final T entry);

}
