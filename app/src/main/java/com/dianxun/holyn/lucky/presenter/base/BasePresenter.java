package com.dianxun.holyn.lucky.presenter.base;

import com.dianxun.holyn.lucky.model.http.HttpURL;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.lang.reflect.Type;

/**
 * Created by holyn on 2016/1/7.
 */
public class BasePresenter implements BasePresenterInterface{
    @Override
    public void setOnBaseGetNetDataListener(String url,final Class cl, final OnBaseGetNetDataListener onBaseGetNetDataListener) {
        onBaseGetNetDataListener.onBegin();
        RequestParams params = new RequestParams(url);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    JsonParser parser = new JsonParser();
                    JsonObject jsonObject = parser.parse(result).getAsJsonObject();
                    JsonElement msgtEle = jsonObject.get("msg");
                    if (msgtEle == null) {
                        onBaseGetNetDataListener.onError("出现异常");
                    } else {
                        if (msgtEle.isJsonObject()) {
                            onBaseGetNetDataListener.onSuccess(new Gson().fromJson(msgtEle, cl));
                        } else if (msgtEle.isJsonPrimitive()){
                            String msg = msgtEle.getAsString();
                            if (msg.equals(HttpURL.MSG_TEL_ERROR)){
                                onBaseGetNetDataListener.onError("账号或者密码错误");
                            }else if (msg.equals(HttpURL.MSG_OK)){
                                onBaseGetNetDataListener.onError("成功");
                            }
                        }
                    }
                } catch (Exception e) {
                    onBaseGetNetDataListener.onError("出现异常");
                    System.out.println(e);
                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                onBaseGetNetDataListener.onError("出现异常");
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    @Override
    public void setOnBaseGetNetDataListener(String url, Type type, OnBaseGetNetDataListener onBaseGetNetDataListener) {

    }
}
