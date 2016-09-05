package com.framework.core.common;

/**
 * @author matrix
 * @since 2016年8月17日 下午5:03:53
 */
public class PageCond {

    /**
     * 表示查询结果的总条数。缺省统计。
     */
    private int COUNT;

    /**
     * = START + LIMIT
     */
    private int END;

    /**
     * 表示每页显示数据的条数。缺省为“10”。
     */
    private int LIMIT;

    /**
     * 表示当前页中的数据在结果集中的起始位置。缺省为"0"。
     */
    private int START;

    /**
     * 默认设置，START=0,LIMIT=10,END=10
     */
    public PageCond() {
        super();
        START = 0;
        LIMIT = 10;
        COUNT = 0;
        END = START + LIMIT;
    }

    /**
     * @param START {@link #START}
     * @param LIMIT {@link #LIMIT}
     */
    public PageCond(final int START, final int LIMIT) {
        super();
        this.START = START;
        this.LIMIT = LIMIT;
        this.setEND(START + LIMIT);
    }

    /**
     * @return {@link #COUNT}
     */
    public int getCOUNT() {
        return COUNT;
    }

    /**
     * @return {@link #END}
     */
    public int getEND() {
        return END;
    }

    /**
     * @return {@link #LIMIT}
     */
    public int getLIMIT() {
        return LIMIT;
    }

    /**
     * @return {@link #START}
     */
    public int getSTART() {
        return START;
    }

    /**
     * @param COUNT {@link #COUNT}
     */
    public void setCOUNT(final int COUNT) {
        this.COUNT = COUNT;
    }

    /**
     * @param END {@link #END}
     */
    public void setEND(final int END) {
        this.END = END;
    }

    /**
     * @param LIMIT {@link #LIMIT}
     */
    public void setLIMIT(final int LIMIT) {
        this.LIMIT = LIMIT;
        this.setEND(START + LIMIT);
    }

    /**
     * @param START {@link #START}
     */
    public void setSTART(final int START) {
        this.START = START;
        this.setEND(START + LIMIT);
    }

}
