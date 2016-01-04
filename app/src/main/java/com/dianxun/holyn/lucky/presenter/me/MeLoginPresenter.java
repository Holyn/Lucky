package com.dianxun.holyn.lucky.presenter.me;

import android.widget.Toast;

import com.dianxun.holyn.lucky.model.http.HttpURL;
import com.dianxun.holyn.lucky.model.parcelable.FoodPar;
import com.dianxun.holyn.lucky.model.parcelable.UserPar;
import com.dianxun.holyn.lucky.presenter.Presenter;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by holyn on 2015/12/10.
 */

public class MeLoginPresenter extends Presenter{

    private FoodPar foodPar;
    private UniqueViewInterface uniqueViewInterface;

    @Inject
    public MeLoginPresenter() { }

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

    public void doLogin(String acount, String password){
        uniqueViewInterface.loginBegin();
        String url = HttpURL.USER_DO_LOGIN+HttpURL.PARAM_TEL+acount+HttpURL.ONE_SPRIT+HttpURL.PARAM_PASSWORD+password;
        RequestParams params = new RequestParams(url);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                UserPar userPar = null;
                try {
                    JsonParser parser = new JsonParser();
                    JsonObject jsonObject = parser.parse(result).getAsJsonObject();
                    JsonElement msgtEle = jsonObject.get("msg");
                    if(msgtEle == null){
                        uniqueViewInterface.loginError("出现异常");
                    }else{
                        if (msgtEle.isJsonObject()){
                            userPar = new Gson().fromJson(msgtEle, UserPar.class);
                            uniqueViewInterface.loginSuccess(userPar);
                        } else {
                            uniqueViewInterface.loginError("账号或者密码错误");
                        }
                    }
                }catch (Exception e){
                    uniqueViewInterface.loginError("出现异常");
                    System.out.println(e);
                }

            }

            @Override
            public void onFinished() {

            }

            @Override
            public void onCancelled(CancelledException cex) {
                Toast.makeText(x.app(), "cancelled", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(x.app(), ex.getMessage(), Toast.LENGTH_LONG).show();
                uniqueViewInterface.loginError("出现异常");
            }
        });
    }

    /**
     * View interface created to abstract the view implementation used in this presenter.
     */
    public interface UniqueViewInterface {

        void loginBegin();

        void loginSuccess(UserPar userPar);

        void loginError(String msg);
    }
}
