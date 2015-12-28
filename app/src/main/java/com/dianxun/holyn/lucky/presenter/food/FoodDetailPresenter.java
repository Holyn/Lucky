package com.dianxun.holyn.lucky.presenter.food;

import android.os.Handler;
import android.widget.Toast;

import com.dianxun.holyn.lucky.model.http.HttpURL;
import com.dianxun.holyn.lucky.model.parcelable.BidRecordPar;
import com.dianxun.holyn.lucky.model.parcelable.FoodPar;
import com.dianxun.holyn.lucky.presenter.Presenter;
import com.dianxun.holyn.lucky.view.BaseViewInferface;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by holyn on 2015/12/10.
 */

public class FoodDetailPresenter extends Presenter{

    private BaseViewInferface baseViewInferface;
    private UniqueViewInterface uniqueViewInterface;

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

    public void setBaseViewInferface(BaseViewInferface baseViewInferface) {
        this.baseViewInferface = baseViewInferface;
    }

    public void setUniqueViewInterface(UniqueViewInterface uniqueViewInterface) {
        this.uniqueViewInterface = uniqueViewInterface;
    }

    public void getBidRecordPar(){

        //测试
        new Handler().postDelayed(new Runnable() {
            public void run() {
                baseViewInferface.loadingSuccess();
                List<BidRecordPar> recordParList = new ArrayList<BidRecordPar>();
                for (int i = 0; i<10; i++){
                    BidRecordPar recordPar = new BidRecordPar();
                    recordPar.setId("1111");
                    recordPar.setName("holyn_"+ i);
                    recordParList.add(recordPar);
                }
                uniqueViewInterface.successGetBidRecordPar(recordParList);
            }
        }, 2000);

//        RequestParams params = new RequestParams(HttpURL.FOOD_LIST);
//        x.http().get(params, new Callback.CommonCallback<String>() {
//            @Override
//            public void onSuccess(String result) {
//
//            }
//
//            @Override
//            public void onFinished() {
//
//            }
//
//            @Override
//            public void onCancelled(CancelledException cex) {
//                Toast.makeText(x.app(), "cancelled", Toast.LENGTH_LONG).show();
//            }
//
//            @Override
//            public void onError(Throwable ex, boolean isOnCallback) {
//                Toast.makeText(x.app(), ex.getMessage(), Toast.LENGTH_LONG).show();
//            }
//        });
    }

    /**
     * View interface created to abstract the view implementation used in this presenter.
     */
    public interface UniqueViewInterface {

        void successGetBidRecordPar(List<BidRecordPar> recordParListr);
    }
}
