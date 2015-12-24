package com.dianxun.holyn.lucky.presenter.food;

import android.widget.Toast;

import com.dianxun.holyn.lucky.model.http.HttpURL;
import com.dianxun.holyn.lucky.model.parcelable.BidRecordPar;
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

public class FoodDetailPresenter extends Presenter{


    private View view;

    @Inject
    public FoodDetailPresenter() { }

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

    public void getFoodDetail(){

        view.successLoading(new BidRecordPar());

        RequestParams params = new RequestParams(HttpURL.FOOD_LIST);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

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

        void successLoading(BidRecordPar bidRecordPar);
    }
}
