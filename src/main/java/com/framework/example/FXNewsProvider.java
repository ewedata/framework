package com.framework.example;

public class FXNewsProvider implements IFXNewsListenerCalable {

    private IFXNewsListener newsListener;

    private IFXNewsPersister newsPersister;

    public FXNewsProvider(IFXNewsListener newsListener, IFXNewsPersister newsPersister) {
        super();
        this.newsListener = newsListener;
        this.newsPersister = newsPersister;
    }

    public IFXNewsListener getNewsListener() {
        return newsListener;
    }

    public void setNewsListener(IFXNewsListener newsListener) {
        this.newsListener = newsListener;
    }

    public IFXNewsPersister getNewsPersister() {
        return newsPersister;
    }

    public void setNewsPersister(IFXNewsPersister newsPersister) {
        this.newsPersister = newsPersister;
    }

    @Override
    public void injectNewsListener(IFXNewsListener newsListener) {
        this.newsListener = newsListener;

    }

    @Override
    public void injectNewsPersister(IFXNewsPersister newsPersister) {
        this.newsPersister = newsPersister;

    }

}
