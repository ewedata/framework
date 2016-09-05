package com.framework.dao.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author matrix
 *
 */
public abstract class BaseDao {

    final protected Logger logger = LoggerFactory.getLogger(this.getClass());

    protected BaseDao() {
        super();
    }

}
