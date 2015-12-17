package com.dianxun.holyn.lucky.view.fragment.Main;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;

import com.dianxun.holyn.lucky.R;
import com.dianxun.holyn.lucky.model.parcelable.FoodPar;
import com.dianxun.holyn.lucky.presenter.mainactivity.MainFoodPresenter;
import com.dianxun.holyn.lucky.view.utils.DividerGridItemDecoration;
import com.dianxun.holyn.lucky.view.widget.ViewpagerHeaderScroll.delegate.RecycleViewDelegate;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by holyn on 2015/12/17.
 */
public class MainRVGridFragment extends BaseViewPagerFragment implements  MainFoodPresenter.View {

    @Inject
    MainFoodPresenter mainFoodPresenter;

    private RecyclerView recyclerView;
    private MainFoodRecyclerViewAdapter recyclerViewAdapter;
    private List<FoodPar> foodParList = new ArrayList<FoodPar>();
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

        for (int i = 0 ; i < 19 ;i++){
            FoodPar foodPar = new FoodPar();
            foodPar.setName(i+"-Holyn");
            foodParList.add(foodPar);
        }
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

        recyclerViewAdapter.addNewDatas(foodParList);
        recyclerView.smoothScrollToPosition(0);
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

    }
}
