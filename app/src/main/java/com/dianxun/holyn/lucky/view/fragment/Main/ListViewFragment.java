package com.dianxun.holyn.lucky.view.fragment.Main;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.dianxun.holyn.lucky.R;
import com.dianxun.holyn.lucky.model.parcelable.FoodPar;
import com.dianxun.holyn.lucky.presenter.mainactivity.MainFoodPresenter;
import com.dianxun.holyn.lucky.view.widget.ViewpagerHeaderScroll.delegate.AbsListViewDelegate;

import java.util.List;

import javax.inject.Inject;


public class ListViewFragment extends BaseViewPagerFragment
        implements AbsListView.OnItemClickListener, MainFoodPresenter.View {

    @Inject
    MainFoodPresenter mainFoodPresenter;

    private ListView mListView;
    private ListAdapter mAdapter;
    private AbsListViewDelegate mAbsListViewDelegate = new AbsListViewDelegate();

    public static ListViewFragment newInstance(int index) {
        ListViewFragment fragment = new ListViewFragment();
        Bundle args = new Bundle();
        args.putInt(BUNDLE_FRAGMENT_INDEX, index);
        fragment.setArguments(args);
        return fragment;
    }

    public ListViewFragment() {
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_list_view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String[] listArrays = null;
        switch (mFragmentIndex) {
            case 1:
                listArrays = getResources().getStringArray(R.array.continents);
                break;
            case 2:
                listArrays = getResources().getStringArray(R.array.cities);
                break;
            default:
                listArrays = getResources().getStringArray(R.array.countries);
                break;
        }
        mAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1,
                android.R.id.text1, listArrays);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mainFoodPresenter.setView(this);
        mainFoodPresenter.initialize();

        mListView = (ListView) getRootView().findViewById(android.R.id.list);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(this);
        mListView.setEmptyView(getRootView().findViewById(android.R.id.empty));
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
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public boolean isViewBeingDragged(MotionEvent event) {
        return mAbsListViewDelegate.isViewBeingDragged(event, mListView);
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
