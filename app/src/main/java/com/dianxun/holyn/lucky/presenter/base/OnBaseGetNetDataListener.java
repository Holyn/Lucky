package com.dianxun.holyn.lucky.presenter.base;

/**
 * Created by holyn on 2016/1/7.
 */
public interface OnBaseGetNetDataListener {
    public void onBegin();
    public void onSuccess(Object object);
    public void onError(String  msg);
}
