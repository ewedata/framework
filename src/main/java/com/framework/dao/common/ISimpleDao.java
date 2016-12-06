/**
 * 
 */
package com.framework.dao.common;

import com.framework.entry.BaseEntity;

/**
 * @author matrix
 * @since 2016年8月19日 下午4:56:13
 */
public interface ISimpleDao {
	/**
	 * 使用实体主键删除数据
	 * 
	 * @param <E>
	 *            实体类型
	 * 
	 * @param entryID
	 *            实体主键
	 * @param entryClass
	 *            实体类型信息
	 * @return 是否成功删除
	 */
	<E extends BaseEntity> boolean deleteByEntryID(final long entryID,
			final Class<E> entryClass);

	/**
	 * 新增数据
	 * 
	 * @param entry
	 *            数据实体
	 * @return 主键
	 */
	long insert(final BaseEntity entry);

	/**
	 * 带主键，新增数据
	 * 
	 * @param entry
	 *            数据实体
	 * @param daoType
	 *            用来操作的dao类型
	 * @return 主键
	 */
	long insertWithPK(final BaseEntity entry);

	/**
	 * 使用线索查询信息
	 * 
	 * @param <E>
	 *            实体类型
	 * 
	 * @param entryID
	 *            实体主键
	 * @param entryClass
	 *            实体类型信息
	 * @param daoType
	 *            到类型
	 * @return 对应的实体数据
	 */
	<E extends BaseEntity> E queryByEntryID(final long entryID,
			final Class<E> entryClass);

	/**
	 * 使用主键更新信息
	 * 
	 * @param tobeUpdate
	 *            待更新的数据，含主键
	 * @param daoType
	 *            dao类型
	 * @return 是否更新成功
	 */
	boolean update(final BaseEntity tobeUpdate);

}