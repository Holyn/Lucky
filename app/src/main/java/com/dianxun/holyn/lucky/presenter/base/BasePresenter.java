package com.dianxun.holyn.lucky.presenter.base;

import com.dianxun.holyn.lucky.model.http.HttpURL;
import com.dianxun.holyn.lucky.model.parcelable.ClassifyFoodPar;
import com.dianxun.holyn.lucky.model.parcelable.CompanyTypePar;
import com.dianxun.holyn.lucky.model.parcelable.FoodPar;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by holyn on 2016/1/7.
 */
public class BasePresenter implements BasePresenterInterface{

    @Inject
    public BasePresenter() { }

    public void setOnGetCompanyTypeParListListener(OnBaseGetNetDataListener onBaseGetNetDataListener){
        String url = HttpURL.COMPANY_CLASSIFY_TYPE_ID_LIST;
        setOnBaseGetNetDataListener(url, new TypeToken<List<CompanyTypePar>>() {}.getType(), onBaseGetNetDataListener);
    }

    public void setOnGetClassifyFoodParListListener(String typeId, int page, OnBaseGetNetDataListener onBaseGetNetDataListener){
        String url = HttpURL.FOOD_CLASSIFY__LIST+String.valueOf(page)+HttpURL.ONE_SPRIT+HttpURL.PARAM_TYPE_ID+typeId;
        setOnBaseGetNetDataListener(url, new TypeToken<List<ClassifyFoodPar>>() {}.getType(), onBaseGetNetDataListener);
    }

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
                            onBaseGetNetDataListener.onError(msg);
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
    public void setOnBaseGetNetDataListener(String url, final Type type, final OnBaseGetNetDataListener onBaseGetNetDataListener) {
        onBaseGetNetDataListener.onBegin();
        RequestParams params = new RequestParams(url);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    JsonParser parser = new JsonParser();
                    JsonObject jsonObject = parser.parse(result).getAsJsonObject();
                    JsonElement listEle = jsonObject.get("list");
                    if (listEle.isJsonArray()){
                        JsonArray jsonArray = listEle.getAsJsonArray();
                        onBaseGetNetDataListener.onSuccess(new Gson().fromJson(jsonArray.toString(), type));
                    } else {
                        onBaseGetNetDataListener.onError("出现异常");
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
}
