/**
 * 
 */
package com.framework.dao.common;

import org.springframework.beans.factory.annotation.Autowired;

import com.framework.entity.BaseEntity;

/**
 * @author 朱诗君 zhushijun@bankcomm.com 交通银行 2010-11-4
 * 
 */
public abstract class AbstractGenericDao extends BaseDao {
	/**
	 * 简单的表操作
	 */
	@Autowired
	protected ISimpleDao simpleDao;


	/**
	 * 新增数据
	 * 
	 * @param entry
	 *            待新增
	 * @return 主键
	 */
	public long insert(final BaseEntity entry) {
		return simpleDao.insertWithPK(entry);
	}

	/**
	 * 使用主键更新信息
	 * 
	 * @param tobeUpdate
	 *            待更新的数据，含主键
	 * 
	 * @return 是否更新成功
	 */
	public boolean update(final BaseEntity tobeUpdate) {
		return simpleDao.update(tobeUpdate);
	}

	/**
	 * 使用主键删除数据
	 * 
	 * @param entryPk
	 *            主键
	 * @param entryClass
	 *            类型信息
	 * @return 是否删除成功
	 */
	protected boolean deleteByPk(final long entryPk,
			final Class<? extends BaseEntity> entryClass) {
		return simpleDao.deleteByEntryID(entryPk, entryClass);
	}

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
	 * 
	 * @return 对应的实体数据
	 */
	protected <E extends BaseEntity> E queryByPk(final long entryID,
			final Class<E> entryClass) {
		return simpleDao.queryByEntryID(entryID, entryClass);
	}

}