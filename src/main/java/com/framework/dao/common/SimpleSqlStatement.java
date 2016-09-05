/**
 * 
 */
package com.framework.dao.common;

/**
 * @author matrix
 * @since 2016年8月19日 下午4:56:39
 */
public enum SimpleSqlStatement {
	/**
	 * 
	 */
	DELETE,
	/**
	 * 
	 */
	INSERT,
	/**
	 * 
	 */
	INSERTWITHPK,
	/**
	 * 
	 */
	QUERY,
	/**
	 * 
	 */
	QUERYONE,
	/**
	 * 
	 */
	QUERYALL,
	/**
	 * 
	 */
	UPDATE;
	/**
	 * @return
	 */
	private static String extractNameSpace() {
		return SingleTableDao.class.getName() + ".";
	}

	private final String statement;

	/**
	 * @param statement
	 */
	private SimpleSqlStatement() {
		this.statement = extractNameSpace() + super.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return statement;
	}
}
