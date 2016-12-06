package com.framework.dao.common;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author LIUAOZ
 * @since 2016年12月6日 上午11:22:53
 * @version 1.0
 */
@Repository
public abstract class BaseDao implements IBaseDao {

    final protected Logger logger = LoggerFactory.getLogger(this.getClass());

    private String tableName;


    @Autowired(required = true)
    private SqlSessionFactory sqlSessionFactory;

    @Autowired
    public SqlSessionTemplate sqlSessionTemplate;

    /*
     * 首先检查原来的getSqlSessionTemplate实例是否存在 如果不存在，则创建一个SqlMapClientTemplate实例
     */
    public SqlSessionTemplate getSqlSessionTemplate() {
        if (this.sqlSessionTemplate == null) {
            this.sqlSessionTemplate = new SqlSessionTemplate(this.sqlSessionFactory);
        }
        return this.sqlSessionTemplate;
    }

    protected BaseDao() {
        super();
    }

    @Override
    public void save() {
        sqlSessionTemplate.insert("", "");

    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}
