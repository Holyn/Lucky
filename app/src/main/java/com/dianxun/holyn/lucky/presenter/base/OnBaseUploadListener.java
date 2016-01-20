package com.dianxun.holyn.lucky.presenter.base;

/**
 * Created by holyn on 2016/1/7.
 */
public interface OnBaseUploadListener {
    public void onBegin();
    public void onLoading(long total, long current, boolean isUploading);
    public void onSuccess(Object object);
    public void onError(String msg);
}
