package com.framework.demo.dp.proxy;

/**
 * Created by matrix_stone on 2016/11/11.
 */
public class SubjectProxy {

    ISubject isubject;

    public SubjectProxy(ISubject isubject) {
        this.isubject = isubject;
    }

    public void request() {

        System.out.println("代理对象干活了开始");
        isubject.request();
        System.out.println("代理对象干活结束");

    }
}
