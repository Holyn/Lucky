package com.dianxun.holyn.lucky.view.fragment.me;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewGroup;

import com.dianxun.holyn.lucky.model.parcelable.BookPar;
import com.dianxun.holyn.lucky.presenter.base.OnBaseGetNetDataListener;
import com.dianxun.holyn.lucky.view.fragment.BasePresenterXRViewFragment;
import com.dianxun.holyn.lucky.view.utils.DividerGridItemDecoration;

import java.util.List;

import cn.bingoogolapple.androidcommon.adapter.BGAOnRVItemClickListener;

/**
 * Created by holyn on 2016/1/14.
 */
public class MeOrderListFragment extends BasePresenterXRViewFragment implements BGAOnRVItemClickListener {

    private MeOrderRVAdapter meOrderRVAdapter;

    private String userId = "1";
    private int page = 1;

    public static MeOrderListFragment newInstance(String userId) {
        MeOrderListFragment meOrderListFragment = new MeOrderListFragment();
        Bundle args = new Bundle();
        args.putString("userId", userId);
        meOrderListFragment.setArguments(args);
        return meOrderListFragment;
    }

    public MeOrderListFragment() {

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setLoadingListenner(true, true);
        userId = getArguments().getString("userId");

        showEmptyView();
        getBookParList(userId, page);
    }

    @Override
    protected void initXRecyclerViewAdapter() {
        meOrderRVAdapter = new MeOrderRVAdapter(getXrecyclerview());
        meOrderRVAdapter.setOnRVItemClickListener(this);
        getXrecyclerview().setLayoutManager(new LinearLayoutManager(getActivity()));
        getXrecyclerview().setAdapter(meOrderRVAdapter);
        getXrecyclerview().addItemDecoration(new DividerGridItemDecoration(getActivity()));
    }

    private void getBookParList(String userId, final int page){
        getBasePresenter().setOnGetBookParListListener(userId, page, new OnBaseGetNetDataListener() {
            @Override
            public void onBegin() {

            }

            @Override
            public void onSuccess(Object object) {
                List<BookPar> bookParList = (List<BookPar>)object;
                if (page == 1){
                    showContentView();
                    meOrderRVAdapter.clear();
                    completeRefresh();

                    if (bookParList.size() == 0){
                        toastMsg("没数据");
                    }else{
                        meOrderRVAdapter.addNewDatas(bookParList);
                        smoothScrollToPosition(0);
                        meOrderRVAdapter.notifyDataSetChanged();
                    }
                } else {
                    completeLoadMore();
                    if (bookParList.size() == 0) {
                        toastMsg("数据已加载完毕");
                    }else{
                        meOrderRVAdapter.addMoreDatas(bookParList);
                        meOrderRVAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onError(String msg) {
                if (page == 1){
                    completeRefresh();
                    showErrorView();
                }else{
                    completeLoadMore();
                }
            }
        });
    }

    @Override
    public void onRVItemClick(ViewGroup viewGroup, View view, int i) {
        toastMsg("====> click_"+i);
    }

    @Override
    public void onLoadMore() {
        page ++ ;
        getBookParList(userId, page);
    }

    @Override
    public void onRefresh() {
        page = 1;
        getBookParList(userId, page);
    }
}
