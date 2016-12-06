package com.framework.entity;

/**
 * user entry.
 * 
 * @author matrix
 * @since 2016年8月18日 上午11:00:03
 */
public class UserEntry extends BaseEntity {

    private static final long serialVersionUID = -3042404306596772838L;

    private String userName;

    private Integer age;

    private String addr;

    private String tel;

    public UserEntry() {
        super();
    }

    public UserEntry(String userName, int age, String addr, String tel) {
        super();
        this.userName = userName;
        this.age = age;
        this.addr = addr;
        this.tel = tel;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

}
