package com.framework.entry;

import java.util.Date;

import com.framework.core.annotatioin.EntryPK;

/**
 * The basic entry of all the other entry.
 * 
 * @author matrix
 * @since 2016年8月17日 下午3:33:53
 */
public class BaseEntry {

    @EntryPK
    public Long id;

    public String creator;

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
