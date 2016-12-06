package com.framework.entity;

public class DemoEntry extends BaseEntity {

    private static final long serialVersionUID = 232464701066739458L;

    private String demoName;

    private String comm;

    public String getDemoName() {
        return demoName;
    }

    public void setDemoName(String demoName) {
        this.demoName = demoName;
    }

    public String getComm() {
        return comm;
    }

    public void setComm(String comm) {
        this.comm = comm;
    }

}
