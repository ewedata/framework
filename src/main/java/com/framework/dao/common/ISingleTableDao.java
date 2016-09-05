/**
 * 
 */
package com.framework.dao.common;

import com.framework.entry.BaseEntry;

/**
 * The Interface for single table operating
 * 
 * @author matrix
 * @since 2016年8月19日 下午1:56:13
 * @param <T> entry type
 */
public interface ISingleTableDao<T extends BaseEntry> {
    /**
     * 使用主键删除实体
     * 
     * @param entryID 实体主键
     * @return 是否成功
     */
    boolean deleteByID(final long entryID);

    /**
     * 获取实体类型
     * 
     * @return 对应的实体类型
     */
    Class<T> getEntryInfo();

    /**
     * 新增数据
     * 
     * @param entry 待新增的数据实体
     * 
     * @return 主键
     */
    long insert(final T entry);

    /**
     * 使用主键查询实体
     * 
     * @param entryID 实体主键
     * @return 对应的实体，如果不存在返回null
     */
    T queryByID(final long entryID);

    /**
     * 更新数据
     * 
     * @param tobeUpdate 待更新数据
     * @return 是否成功
     */
    boolean update(final T tobeUpdate);
}
