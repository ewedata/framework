package com.framework.demo.dp.proxy;

/**
 * Created by matrix_stone on 2016/11/11.
 */
public class SubjectImpl implements ISubject {

    @Override
    public void request() {
        System.out.println("目标对象在干活");
    }
}
