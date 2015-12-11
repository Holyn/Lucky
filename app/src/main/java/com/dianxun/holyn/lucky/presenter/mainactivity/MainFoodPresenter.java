package com.dianxun.holyn.lucky.presenter.mainactivity;

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
import javax.inject.Singleton;

/**
 * Created by holyn on 2015/12/10.
 */

@Singleton
public class MainFoodPresenter extends Presenter{
    
    private FoodPar foodPar;
    private View view;

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

    public void setView(View view) {
        this.view = view;
    }

    public void loadFoodList(){
        RequestParams params = new RequestParams(HttpURL.HOME_FOOD_LIST);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
//                Toast.makeText(x.app(), result, Toast.LENGTH_LONG).show();

                System.out.println(result);

                List<FoodPar> foodParList = null;
                try {
                    JsonParser parser = new JsonParser();
                    JsonObject jsonObject = parser.parse(result).getAsJsonObject();

                    JsonElement listEle = jsonObject.get("list");
//                    System.out.println("====>listEle = "+listEle);
                    if (listEle.isJsonArray()){
                        System.out.println("====>listEle.isJsonArray()");
                        JsonArray jsonArray = listEle.getAsJsonArray();
                        java.lang.reflect.Type type = new TypeToken<List<FoodPar>>() {}.getType();
                        foodParList = new Gson().fromJson(jsonArray.toString(), type);

                        System.out.println("====》 列表长度 = "+foodParList.size());

                        view.successLoading(foodParList);

                    } else {

                    }


                }catch (Exception e){
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
            }
        });
    }

    /**
     * View interface created to abstract the view implementation used in this presenter.
     */
    public interface View {

        void showLoading();

        void showFanArt(final String tvShowFanArtUrl);

//        void showChapters(final ChapterCollection episodes);

        void hideLoading();

        void successLoading(List<FoodPar> foodParList);
    }
}
