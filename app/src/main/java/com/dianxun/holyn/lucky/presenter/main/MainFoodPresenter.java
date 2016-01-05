package com.dianxun.holyn.lucky.presenter.main;

import android.widget.Toast;

import com.dianxun.holyn.lucky.model.http.HttpURL;
import com.dianxun.holyn.lucky.model.parcelable.FoodPar;
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

public class MainFoodPresenter extends Presenter{

    private UniqueViewInterface uniqueViewInterface;

    @Inject
    public MainFoodPresenter() { }

    @Override
    public void initialize() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    public void setView(UniqueViewInterface uniqueViewInterface) {
        this.uniqueViewInterface = uniqueViewInterface;
    }

    public void getFoodList(){
        RequestParams params = new RequestParams(HttpURL.FOOD_LIST);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                List<FoodPar> foodParList = null;
                try {
                    JsonParser parser = new JsonParser();
                    JsonObject jsonObject = parser.parse(result).getAsJsonObject();
                    JsonElement listEle = jsonObject.get("list");
                    if (listEle.isJsonArray()){
                        JsonArray jsonArray = listEle.getAsJsonArray();
                        java.lang.reflect.Type type = new TypeToken<List<FoodPar>>() {}.getType();
                        foodParList = new Gson().fromJson(jsonArray.toString(), type);
                        uniqueViewInterface.successGetFoodList(foodParList);
                    } else {
                        uniqueViewInterface.errorGetFoodList("商品列表获取失败");
                    }
                }catch (Exception e){
                    System.out.println(e);
                    uniqueViewInterface.errorGetFoodList("商品列表获取失败");
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
                uniqueViewInterface.errorGetFoodList("商品列表获取失败");
            }
        });
    }

    /**
     * View interface created to abstract the view implementation used in this presenter.
     */
    public interface UniqueViewInterface {

        void successGetFoodList(List<FoodPar> foodParList);

        void errorGetFoodList(String msg);
    }
}
