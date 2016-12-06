package com.framework.dao.common;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.framework.core.common.PageCond;
import com.framework.core.utils.TransferUtil;
import com.framework.entity.BaseEntity;

/**
 * @author matrix
 * @since 2016年8月17日 下午5:15:13
 * @param <T>
 */
public abstract class SingleTableDao<T extends BaseEntity> extends BaseDao
        implements
            ISingleTableDao<T> {


    private final Class<T> entryClassInfo;
    @Autowired
    private IColumsDao rawDao;
    private final String tableName;

    public String getTableName() {
        return tableName;
    }

    /**
     * 
     */
    public SingleTableDao() {
        super();
        entryClassInfo = extractEntryClassInfo();
        tableName = TransferUtil.extractTableName(entryClassInfo);
    }

    /**
     * 使用列名值映射删除数据
     * 
     * @param columnValues 列名值映射
     * @return 是否删除成功
     */
    protected boolean deleteByColumns(final Map<String, Object> columnValues) {
        return rawDao.deleteByColumns(entryClassInfo, columnValues);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.bocom.egif.dao.ISingleTableDao#deleteByID(long)
     */
    @Override
    public boolean deleteByID(final long entryID) {
        return rawDao.deleteByEntryID(entryID, entryClassInfo);
    }

    /**
     * 提取实体类型信息
     * 
     * @return 实体类型信息
     */
    protected Class<T> extractEntryClassInfo() {
        for (Class<?> current = getClass(); (current != SingleTableDao.class)
                || (current != Object.class); current = current.getSuperclass()) {
            final Type genericSuperType = current.getGenericSuperclass();
            if (!(genericSuperType instanceof ParameterizedType)) {
                continue;
            }

            final ParameterizedType genericSuperClass = (ParameterizedType) genericSuperType;
            final Type[] actualTypes = genericSuperClass.getActualTypeArguments();
            if (actualTypes.length == 0) {
                continue;
            }

            final Type firstType = actualTypes[0];
            if (!(firstType instanceof Class)) {
                continue;
            }
            @SuppressWarnings("unchecked")
            final Class<T> firstClass = (Class<T>) firstType;
            if (BaseEntity.class.isAssignableFrom(firstClass)) {
                return firstClass;
            }
        }

        throw new IllegalStateException("无法获取有效的entry信息");
    }

    /**
     * @return 标准的sql mapper命名空间
     */
    protected String extractStandardNameSpace() {
        return this.getClass().getName() + ".";
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.bocom.egif.dao.ISingleTableDao#getCondition()
     */
    @Override
    public Class<T> getEntryInfo() {
        return entryClassInfo;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.bocom.egif.dao.ISingleTableDao#insert(com.bocom.egif.entry.BaseEntry)
     */
    @Override
    public long insert(final T entry) {
        return rawDao.insert(entry);
    }

    /**
     * 新增数据带主键
     * 
     * @param entry 待新增的数据实体
     * 
     * @return 主键
     */
    protected long insertWithPk(final T entry) {
        return rawDao.insertWithPK(entry);
    }

    /**
     * 查询表内所有值
     * 
     * @return 对应的实体列表
     */
    protected List<T> queryAll() {

        return null;
    }

    /**
     * 查询表内数据数量
     * 
     * @return 总数
     */
    protected int queryAllCount() {
        return 0;
    }

    /**
     * 使用列名和值查询实体
     * 
     * @param columnValues 列值
     * @return 对应的实体，如果不存在返回null
     */
    protected T queryByColumns(final Map<String, Object> columnValues) {
        return rawDao.queryByColumns(entryClassInfo, columnValues);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.bocom.egif.dao.ISingleTableDao#queryById(long)
     */
    @Override
    public T queryByID(final long entryID) {
        return rawDao.queryByEntryID(entryID, entryClassInfo);
    }

    /**
     * 根据条件查询表内数据数量
     * 
     * @param columnValues 条件
     * @return 符合条件的数据数量
     */
    protected int queryCountByColumns(final Map<String, Object> columnValues) {
        return 0;
    }

    /**
     * 查询对应于columnValues的对应数据实体主键
     * 
     * @param columnValues 查询线索
     * @return 对应的数据实体主键
     */
    protected long queryEntryIdByColumns(final Map<String, Object> columnValues) {
        return rawDao.queryEntryIdByColumns(entryClassInfo, columnValues);
    }

    /**
     * 查询对应于columnValues的对应数据实体主键列表
     * 
     * 
     * 类型信息
     * 
     * @param columnValues 查询线索
     * @return 对应的数据实体主键
     */
    protected List<Long> queryEntryIdListByColumns(final Map<String, Object> columnValues,
            final PageCond pageCond) {
        return rawDao.queryEntryIdListByColumns(entryClassInfo, columnValues, pageCond);
    }

    /**
     * 使用列名和值查询实体列表
     * 
     * @param columnValues 列名和值映射，当值为列表时，采用in做为操作符
     * @return 对应的实体列表，如果不存在返回null
     */
    protected List<T> queryListByColumns(final Map<String, Object> columnValues,
            final PageCond pageCond) {
        return rawDao.queryListByColumns(entryClassInfo, columnValues, pageCond);
    }

    /**
     * 按照指定列名，使用列名和值查询实体列表
     * 
     * @param columnValues 列名和值映射，当值为列表时，采用in做为操作符
     * @param resultColumnsName 指定的返回列
     * @return 对应的实体列表，如果不存在返回空列表
     */
    protected List<T> queryListByColumns(final Map<String, Object> columnValues,
            final Set<String> resultColumnsName, final PageCond pageCond) {
        return rawDao.queryListByColumns(entryClassInfo, columnValues, resultColumnsName, pageCond);
    }

    protected void setCount(final PageCond queryCond, final int count) {

    }

    /**
     * 
     * 将通过手写sql,查询出来的map列表类型转换成实体类型list
     * 
     * @param sourceMap 要转换的list
     * @return 指定entry类型的list
     */
    protected List<T> transferMapToEntryList(final List<Map<String, Object>> sourceMap) {
        return null;
    }

    /**
     * 
     * 将通过手写sql,查询出来的map列表类型转换成实体类型list
     * 
     * @param <E> 实体类型
     * 
     * @param sourceMap 要转换的list
     * @param classInfo 目标类型
     * @return 指定entry类型的list
     */
    protected <E extends BaseEntity> List<E> transferMapToEntryList(
            final List<Map<String, Object>> sourceMap, final Class<E> classInfo) {
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.bocom.egif.dao.ISingleTableDao#updateEntry(com.bocom.egif.entry.BaseEntry )
     */
    @Override
    public boolean update(final T tobeUpdate) {
        return rawDao.update(tobeUpdate);
    }

    /**
     * 使用条件更新数据
     * 
     * @param tobeUpdate 待更新数据
     * @param columnValues 条件列
     * @return 是否更新成功
     */
    protected boolean updateByColumns(final T tobeUpdate, final Map<String, Object> columnValues) {
        return rawDao.updateByColumns(tobeUpdate, columnValues);
    }

    /**
     * 按照主键更新制定列值
     * 
     * @param tobeUpdate 待更新的数据
     * @param columnNameSet 列名集合
     * @return 是否更新成功
     */
    protected boolean updateColumns(final T tobeUpdate, final Set<String> columnNameSet) {
        return rawDao.updateColumns(tobeUpdate, columnNameSet);
    }
}
