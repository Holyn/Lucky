package com.dianxun.holyn.lucky.presenter.base;

import org.xutils.http.RequestParams;

import java.lang.reflect.Type;

/**
 * Created by holyn on 2016/1/7.
 */
public interface BasePresenterInterface {
    /**
     * 请求一个json的基础类型数据jsonPrimitive
     */

    /**
     *  请求一个实体jsonObject
     */
    public void setOnBaseGetNetDataListener(String url, Class cl, OnBaseGetNetDataListener onBaseGetNetDataListener);

    /**
     * 请求一个列表jsonArray
     */
    public void setOnBaseGetNetDataListener(String url, Type type, OnBaseGetNetDataListener onBaseGetNetDataListener);

    /**
     * 上传文件
     */
    public void setOnBaseUploadListener(RequestParams requestParams, OnBaseUploadListener onBaseUploadListener);
}
