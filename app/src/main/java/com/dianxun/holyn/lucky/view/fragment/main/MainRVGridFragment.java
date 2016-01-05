package com.dianxun.holyn.lucky.view.fragment.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dianxun.holyn.lucky.R;
import com.dianxun.holyn.lucky.model.parcelable.FoodPar;
import com.dianxun.holyn.lucky.presenter.main.MainFoodPresenter;
import com.dianxun.holyn.lucky.view.activity.FoodDetailActivity;
import com.dianxun.holyn.lucky.view.utils.DividerGridItemDecoration;
import com.dianxun.holyn.lucky.view.widget.ViewpagerHeaderScroll.delegate.RecycleViewDelegate;

import java.util.List;

import javax.inject.Inject;

import cn.bingoogolapple.androidcommon.adapter.BGAOnItemChildClickListener;
import cn.bingoogolapple.androidcommon.adapter.BGAOnRVItemClickListener;

/**
 * Created by holyn on 2015/12/17.
 */
public class MainRVGridFragment extends BaseViewPagerFragment implements  MainFoodPresenter.UniqueViewInterface, BGAOnRVItemClickListener, BGAOnItemChildClickListener {

    @Inject
    MainFoodPresenter mainFoodPresenter;

    private RecyclerView recyclerView;
    private MainFoodRecyclerViewAdapter recyclerViewAdapter;
    private RecycleViewDelegate recycleViewDelegate = new RecycleViewDelegate();

    public static MainRVGridFragment newInstance(int index) {
        MainRVGridFragment fragment = new MainRVGridFragment();
        Bundle args = new Bundle();
        args.putInt(BUNDLE_FRAGMENT_INDEX, index);
        fragment.setArguments(args);
        return fragment;
    }

    public MainRVGridFragment() {
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_recycleview;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        for (int i = 0 ; i < 19 ;i++){
//            FoodPar foodPar = new FoodPar();
//            foodPar.setName(i+"-Holyn");
//            foodParList.add(foodPar);
//        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mainFoodPresenter.setView(this);
        mainFoodPresenter.initialize();

        recyclerView = (RecyclerView)getRootView().findViewById(R.id.base_recycleview);
        recyclerViewAdapter = new MainFoodRecyclerViewAdapter(recyclerView);
        recyclerViewAdapter.setOnRVItemClickListener(this);
        recyclerViewAdapter.setOnItemChildClickListener(this);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),2);
        recyclerView.setLayoutManager(gridLayoutManager);

        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.addItemDecoration(new DividerGridItemDecoration(getActivity()));

        mainFoodPresenter.getFoodList();
    }

    @Override
    public void onRVItemClick(ViewGroup viewGroup, View view, int i) {
        startActivity(new Intent(getActivity(), FoodDetailActivity.class));
    }

    @Override
    public void onItemChildClick(ViewGroup viewGroup, View view, int i) {
        switch (view.getId()){
            case R.id.btn_duobao:
                Toast.makeText(getActivity(), "点击了夺宝按钮",Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override public void onResume() {
        super.onResume();
        mainFoodPresenter.resume();
    }

    @Override public void onPause() {
        super.onPause();
        mainFoodPresenter.pause();
    }

    @Override
    public boolean isViewBeingDragged(MotionEvent event) {
       return recycleViewDelegate.isViewBeingDragged(event, recyclerView);
    }

    @Override
    public void errorGetFoodList(String msg) {

    }

    @Override
    public void successGetFoodList(List<FoodPar> foodParList) {
        System.out.println("====> successLoading_"+getArguments().getInt(BUNDLE_FRAGMENT_INDEX)+" -- foodParList.size() = "+foodParList.size());
        recyclerViewAdapter.addNewDatas(foodParList);
        recyclerView.smoothScrollToPosition(0);
        recyclerViewAdapter.notifyDataSetChanged();
    }
}
