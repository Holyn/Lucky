package com.dianxun.holyn.lucky.presenter.base;

import java.lang.reflect.Type;

/**
 * Created by holyn on 2016/1/7.
 */
public interface BasePresenterInterface {
    /**
     *  请求一个实体jsonObject
     */
    public void setOnBaseGetNetDataListener(String url, Class cl, OnBaseGetNetDataListener onBaseGetNetDataListener);

    /**
     * 请求一个列表jsonArray
     */
    public void setOnBaseGetNetDataListener(String url, Type type, OnBaseGetNetDataListener onBaseGetNetDataListener);
}
