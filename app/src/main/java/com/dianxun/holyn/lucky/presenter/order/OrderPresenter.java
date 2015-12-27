package com.dianxun.holyn.lucky.presenter.order;

import android.os.Handler;

import com.dianxun.holyn.lucky.model.parcelable.NewAnnouncePar;
import com.dianxun.holyn.lucky.model.parcelable.OrderPar;
import com.dianxun.holyn.lucky.presenter.Presenter;
import com.dianxun.holyn.lucky.view.BaseViewInferface;

import javax.inject.Inject;

/**
 * Created by holyn on 2015/12/10.
 */

public class OrderPresenter extends Presenter {

    private BaseViewInferface baseViewInferface;
    private UniqueViewInterface uniqueViewInterface;

    @Inject
    public OrderPresenter() { }

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

    public void getOrderPar(){

        //测试
        new Handler().postDelayed(new Runnable() {
            public void run() {
                baseViewInferface.loadingSuccess();
                uniqueViewInterface.successGetOrderPar(new OrderPar());
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

        void successGetOrderPar(OrderPar orderPar);

        void errorGetOrderPar();
    }
}
