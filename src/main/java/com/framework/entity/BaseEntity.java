package com.framework.entity;

import java.io.Serializable;
import java.sql.Date;

import com.framework.core.annotatioin.EntryPK;

/**
 * The basic entry of all the other entry.
 * 
 * @author matrix
 * @since 2016年8月17日 下午3:33:53
 */
public abstract class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @EntryPK
    public Long id;

    public String creator;

    public String operator;

    public Date createTime;

    public Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
