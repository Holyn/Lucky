package com.dianxun.holyn.lucky.presenter.me;

import com.dianxun.holyn.lucky.model.http.HttpURL;
import com.dianxun.holyn.lucky.presenter.Presenter;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import javax.inject.Inject;

/**
 * Created by holyn on 2015/12/10.
 */

public class MeRegisterPresenter extends Presenter{

    private UniqueViewInterface uniqueViewInterface;

    @Inject
    public MeRegisterPresenter() { }

    @Override
    public void initialize() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    public void setUniqueViewInterface(UniqueViewInterface uniqueViewInterface) {
        this.uniqueViewInterface = uniqueViewInterface;
    }

    public void getCode(String tel){
        uniqueViewInterface.getCodeBegin();
        String url = HttpURL.USER_GET_CODE+HttpURL.PARAM_TEL+tel;
        RequestParams params = new RequestParams(url);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    JsonParser parser = new JsonParser();
                    JsonObject jsonObject = parser.parse(result).getAsJsonObject();
                    JsonElement msgtEle = jsonObject.get("msg");
                    if(msgtEle == null){
                        uniqueViewInterface.getCodeError("出现异常");
                    }else{
                        String msg = msgtEle.getAsString();
                        if (msg.equals(HttpURL.MSG_TEL_ERROR)){
                            uniqueViewInterface.getCodeError("电话号码已注册");
                        }else{
                            uniqueViewInterface.getCodeSuccess(msg);
                        }
                    }
                }catch (Exception e){
                    uniqueViewInterface.getCodeError("出现异常");
                    System.out.println(e);
                }

            }

            @Override
            public void onFinished() {

            }

            @Override
            public void onCancelled(CancelledException cex) {
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                uniqueViewInterface.getCodeError("出现异常");
            }
        });
    }

    public void doReg(String tel, String password, String name){
        uniqueViewInterface.doRegBegin();
        String url = HttpURL.USER_DO_REG+HttpURL.PARAM_TEL+tel
                +HttpURL.ONE_SPRIT+HttpURL.PARAM_PASSWORD+password
                +HttpURL.ONE_SPRIT+HttpURL.PARAM_NAMW+name;
        RequestParams params = new RequestParams(url);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    JsonParser parser = new JsonParser();
                    JsonObject jsonObject = parser.parse(result).getAsJsonObject();
                    JsonElement msgtEle = jsonObject.get("msg");
                    if(msgtEle == null){
                        uniqueViewInterface.doRegError("出现异常");
                    }else{
                        String msg = msgtEle.getAsString();
                        if (msg.equals(HttpURL.MSG_TEL_ERROR)){
                            uniqueViewInterface.doRegError("电话号码已注册");
                        }else if(msg.equals(HttpURL.MSG_OK)){
                            uniqueViewInterface.doRegSuccess("注册成功");
                        }
                    }
                }catch (Exception e){
                    uniqueViewInterface.doRegError("出现异常");
                    System.out.println(e);
                }

            }

            @Override
            public void onFinished() {

            }

            @Override
            public void onCancelled(CancelledException cex) {
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                uniqueViewInterface.doRegError("出现异常");
            }
        });
    }

    /**
     * View interface created to abstract the view implementation used in this presenter.
     */
    public interface UniqueViewInterface {

        void getCodeBegin();

        void getCodeSuccess(String code);

        void getCodeError(String msg);

        void doRegBegin();

        void doRegSuccess(String msg);

        void doRegError(String msg);
    }
}
