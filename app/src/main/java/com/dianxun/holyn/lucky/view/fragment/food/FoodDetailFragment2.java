package com.dianxun.holyn.lucky.view.fragment.food;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.dianxun.holyn.lucky.model.parcelable.BidRecordPar;
import com.dianxun.holyn.lucky.presenter.food.FoodDetailPresenter;
import com.dianxun.holyn.lucky.view.activity.FoodDetailActivity;
import com.dianxun.holyn.lucky.view.fragment.BaseXRecyclerViewFragment;
import com.dianxun.holyn.lucky.view.utils.DividerGridItemDecoration;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by holyn on 2015/12/27.
 */
public class FoodDetailFragment2 extends BaseXRecyclerViewFragment implements FoodDetailPresenter.UniqueViewInterface{

    @Inject
    FoodDetailPresenter foodDetailPresenter;

    private FoodDetailBidRecordRvAdapter recyclerViewAdapter;

    public static FoodDetailFragment2 newInstance() {
        return new FoodDetailFragment2();
    }

    public FoodDetailFragment2() {

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        begainLoadFoodDetail();
        setLoadingListenner(true, true);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((FoodDetailActivity) getActivity()).setToolBarTitle("奖品详情");
        foodDetailPresenter.resume();
    }

    @Override
    protected void initPresenter() {
        foodDetailPresenter.setBaseViewInferface(this);
        foodDetailPresenter.setUniqueViewInterface(this);
        foodDetailPresenter.initialize();
    }

    @Override
    protected void initXRecyclerViewAdapter() {
        recyclerViewAdapter = new FoodDetailBidRecordRvAdapter(getXrecyclerview());

//        recyclerViewAdapter.setOnRVItemClickListener(this);
//        recyclerViewAdapter.setOnItemChildClickListener(this);

        getXrecyclerview().setLayoutManager(new LinearLayoutManager(getActivity()));

        getXrecyclerview().setAdapter(recyclerViewAdapter);
        getXrecyclerview().addItemDecoration(new DividerGridItemDecoration(getActivity()));
    }

    private void begainLoadFoodDetail(){
        foodDetailPresenter.getBidRecordPar();
    }

    @Override
    public void successGetBidRecordPar(List<BidRecordPar> recordPars) {
        List<BidRecordPar> recordParList = new ArrayList<BidRecordPar>();
        for (int i = 0; i<10; i++){
            BidRecordPar recordPar = new BidRecordPar();
            recordPar.setId("1111");
            recordPar.setName("holyn_"+i);
            recordParList.add(recordPar);
        }
        recyclerViewAdapter.addNewDatas(recordParList);
        getXrecyclerview().smoothScrollToPosition(0);
        recyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLoadMore() {
        new Handler().postDelayed(new Runnable() {
            public void run() {
                List<BidRecordPar> recordParList = new ArrayList<BidRecordPar>();
                for (int i = 0; i<10; i++){
                    BidRecordPar recordPar = new BidRecordPar();
                    recordPar.setId("1111");
                    recordPar.setName("holyn_"+(recyclerViewAdapter.getItemCount()+i));
                    recordParList.add(recordPar);
                }
                recyclerViewAdapter.addMoreDatas(recordParList);
                recyclerViewAdapter.notifyDataSetChanged();
                getXrecyclerview().loadMoreComplete();
            }
        }, 2000);
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            public void run() {
                recyclerViewAdapter.clear();
                List<BidRecordPar> recordParList = new ArrayList<BidRecordPar>();
                for (int i = 0; i<10; i++){
                    BidRecordPar recordPar = new BidRecordPar();
                    recordPar.setId("1111");
                    recordPar.setName("holyn_"+i);
                    recordParList.add(recordPar);
                }
                recyclerViewAdapter.addNewDatas(recordParList);
                getXrecyclerview().smoothScrollToPosition(0);
                recyclerViewAdapter.notifyDataSetChanged();
                getXrecyclerview().refreshComplete();
            }
        }, 2000);
    }

    @Override
    public void onPause() {
        super.onPause();
        foodDetailPresenter.pause();
    }
}
