package com.framework.dao.common;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.framework.core.common.PageCond;
import com.framework.entry.BaseEntry;

/**
 * @author matrix
 * @since 2016年8月17日 下午4:17:50
 */
public interface IColumsDao extends ISimpleDao {
    /**
     * 使用列值进行删除，仅删除一行数据
     * 
     * @param classInfo 类型信息
     * @param columnValues 列条件
     * @param daoType 用来操作的dao类型
     * @param <E> 类型信息
     * @return 是否成功删除 <code>true</code>删除一行 <code>false</code>删除失败
     * 
     */
    <E extends BaseEntry> boolean deleteByColumns(final Class<E> classInfo,
            final Map<String, Object> columnValues);

    /**
     * 查询对应于columnValues的对应数据实体
     * 
     * @param <E> 类型信息
     * @param classInfo 类型信息
     * @param columnValues 查询线索
     * @param daoType 用来操作的dao类型
     * @return 查询获得的数据
     */
    <E extends BaseEntry> E queryByColumns(final Class<E> classInfo,
            final Map<String, Object> columnValues);

    /**
     * 按照指定列名，取得对应于columnsValues值的对应列值
     * 
     * @param <E> 类型信息
     * @param classInfo 类型信息
     * @param columnValues 查询线索
     * @param daoType 用来操作的dao类型
     * @param resultColumnsName 结果列名
     * @return 查询获得的数据
     */
    <E extends BaseEntry> E queryByColumns(final Class<E> classInfo,
            final Map<String, Object> columnValues, final Set<String> resultColumnsName);

    /**
     * 查询对应于columnValues的对应数据实体主键
     * 
     * @param <E> 类型信息
     * @param classInfo 类型信息
     * @param columnValues 查询线索
     * @param daoType 用来操作的dao类型
     * @return 对应的数据实体主键
     */
    <E extends BaseEntry> long queryEntryIdByColumns(final Class<E> classInfo,
            final Map<String, Object> columnValues);

    /**
     * 查询对应于columnValues的对应数据实体主键列表
     * 
     * @param <E> 类型信息
     * @param classInfo 类型信息
     * @param columnValues 查询线索
     * @param daoType 用来操作的dao类型
     * @return 对应的数据实体主键
     */
    <E extends BaseEntry> List<Long> queryEntryIdListByColumns(final Class<E> classInfo,
            final Map<String, Object> columnValues, final PageCond pageCond);

    /**
     * 使用列名和值查询实体列表
     * 
     * @param <E> 实体类型
     * @param classInfo 类型信息
     * @param columnValues 列名和值映射，当值为列表时，采用in做为操作符
     * @param daoType 用来操作的dao类型
     * @return 对应的实体列表，如果不存在返回空列表
     */
    <E extends BaseEntry> List<E> queryListByColumns(final Class<E> classInfo,
            final Map<String, Object> columnValues, final PageCond pageCond);

    /**
     * 按照指定列名，使用列名和值查询实体列表
     * 
     * @param <E> 实体类型
     * @param classInfo 类型信息
     * @param columnValues 列名和值映射，当值为列表时，采用in做为操作符
     * @param daoType 用来操作的dao类型
     * @param resultColumnsName 指定的返回列
     * @return 对应的实体列表，如果不存在返回空列表
     */
    <E extends BaseEntry> List<E> queryListByColumns(final Class<E> classInfo,
            final Map<String, Object> columnValues, final Set<String> resultColumnsName,
            final PageCond pageCond);

    /**
     * 使用条件更新列值，但只更新一行，多行时返回false
     * 
     * @param tobeUpdate 待更新的实体数据
     * @param columnValues 更新条件
     * @param daoType 用来操作的dao类型
     * @return 是否更新成功 <code>true</code>更新一行 <code>false</code>更新失败
     */
    boolean updateByColumns(final BaseEntry tobeUpdate, final Map<String, Object> columnValues);

    /**
     * 使用主键更新指定列的信息
     * 
     * @param tobeUpdate 待更新的数据，含主键
     * @param columnNameSet 指定的列名集合
     * @param daoType dao类型
     * @return 是否更新成功
     */
    boolean updateColumns(final BaseEntry tobeUpdate, final Set<String> columnNameSet);
}
