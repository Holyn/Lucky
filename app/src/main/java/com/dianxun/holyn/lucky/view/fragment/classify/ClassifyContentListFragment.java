package com.dianxun.holyn.lucky.view.fragment.classify;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;

import com.dianxun.holyn.lucky.R;
import com.dianxun.holyn.lucky.model.parcelable.FoodPar;
import com.dianxun.holyn.lucky.presenter.main.MainFoodPresenter;
import com.dianxun.holyn.lucky.view.fragment.BaseFragment;
import com.dianxun.holyn.lucky.view.fragment.main.MainFoodRecyclerViewAdapter;
import com.dianxun.holyn.lucky.view.utils.DividerGridItemDecoration;
import com.dianxun.holyn.lucky.view.widget.ViewpagerHeaderScroll.delegate.RecycleViewDelegate;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by holyn on 2015/12/20.
 */
public class ClassifyContentListFragment extends BaseFragment implements  MainFoodPresenter.View{

    @Inject
    MainFoodPresenter mainFoodPresenter;

    private RecyclerView recyclerView;
    private MainFoodRecyclerViewAdapter recyclerViewAdapter;

    public static ClassifyContentListFragment newInstance() {
        ClassifyContentListFragment fragment = new ClassifyContentListFragment();
        return fragment;
    }

    public ClassifyContentListFragment() {
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

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),2);
        recyclerView.setLayoutManager(gridLayoutManager);

        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.addItemDecoration(new DividerGridItemDecoration(getActivity()));

        mainFoodPresenter.loadFoodList();
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
    public void showLoading() {

    }

    @Override
    public void showFanArt(String tvShowFanArtUrl) {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void successLoading(List<FoodPar> foodParList) {
        System.out.println("====> successLoading -- foodParList.size() = "+foodParList.size());
        recyclerViewAdapter.addNewDatas(foodParList);
        recyclerView.smoothScrollToPosition(0);
        recyclerViewAdapter.notifyDataSetChanged();
    }
}
