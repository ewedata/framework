package com.framework.example;

public interface IFXNewsListenerCalable {

    void injectNewsListener(IFXNewsListener newsListener);

    void injectNewsPersister(IFXNewsPersister newsPersister);

}
