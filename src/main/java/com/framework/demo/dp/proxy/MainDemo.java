package com.framework.demo.dp.proxy;

/**
 * Created by matrix_stone on 2016/11/11.
 */
public class MainDemo {


    public static void main(String[] args) {
        ISubject iSubject = new SubjectImpl();

        SubjectProxy sp = new SubjectProxy(iSubject);

        sp.request();
    }

}
