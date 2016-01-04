package com.dianxun.holyn.lucky.view.fragment.order;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.dianxun.holyn.lucky.model.parcelable.NewAnnouncePar;
import com.dianxun.holyn.lucky.model.parcelable.OrderPar;
import com.dianxun.holyn.lucky.presenter.newannounce.NewAnnouncePresenter;
import com.dianxun.holyn.lucky.presenter.order.OrderPresenter;
import com.dianxun.holyn.lucky.view.activity.BaseToolBarActivity;
import com.dianxun.holyn.lucky.view.fragment.BaseXRecyclerViewFragment;
import com.dianxun.holyn.lucky.view.fragment.newannounce.NewAnnounceRvAdapter;
import com.dianxun.holyn.lucky.view.utils.DividerGridItemDecoration;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by holyn on 2015/12/27.
 */
public class OrderFragment extends BaseXRecyclerViewFragment implements OrderPresenter.UniqueViewInterface {

    @Inject
    OrderPresenter orderPresenter;

    private OrderRvAdapter recyclerViewAdapter;

    public static OrderFragment newInstance() {
        return new OrderFragment();
    }

    public OrderFragment() {

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        beginLoadOrderPar();
        setLoadingListenner(true, true);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((BaseToolBarActivity) getActivity()).setToolBarTitle("晒单");
        orderPresenter.resume();
    }

    @Override
    protected void initPresenter() {
        orderPresenter.setBaseViewInferface(this);
        orderPresenter.setUniqueViewInterface(this);
        orderPresenter.initialize();
    }

    @Override
    protected void initXRecyclerViewAdapter() {
        recyclerViewAdapter = new OrderRvAdapter(getXrecyclerview());

//        recyclerViewAdapter.setOnRVItemClickListener(this);
//        recyclerViewAdapter.setOnItemChildClickListener(this);

        getXrecyclerview().setLayoutManager(new LinearLayoutManager(getActivity()));

        getXrecyclerview().setAdapter(recyclerViewAdapter);
        getXrecyclerview().addItemDecoration(new DividerGridItemDecoration(getActivity()));
    }

    private void beginLoadOrderPar() {
        orderPresenter.getOrderPar();
    }

    @Override
    public void successGetOrderPar(OrderPar orderPar) {
        List<OrderPar> orderParList = new ArrayList<OrderPar>();
        for (int i = 0; i < 10; i++) {
            OrderPar order = new OrderPar();
            order.setTitle("我的宝贝_" + i);
            orderParList.add(order);
        }
        if(recyclerViewAdapter!=null && getXrecyclerview()!=null){
            recyclerViewAdapter.addNewDatas(orderParList);
            getXrecyclerview().smoothScrollToPosition(0);
            recyclerViewAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void errorGetOrderPar() {
        getXrecyclerview().loadMoreComplete();
        getXrecyclerview().refreshComplete();
    }

    @Override
    public void onLoadMore() {
        new Handler().postDelayed(new Runnable() {
            public void run() {
                List<OrderPar> orderParList = new ArrayList<OrderPar>();
                for (int i = 0; i < 10; i++) {
                    OrderPar order = new OrderPar();
                    order.setTitle("我的宝贝_" + i);
                    orderParList.add(order);
                }
                recyclerViewAdapter.addMoreDatas(orderParList);
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
                List<OrderPar> orderParList = new ArrayList<OrderPar>();
                for (int i = 0; i < 10; i++) {
                    OrderPar order = new OrderPar();
                    order.setTitle("我的宝贝_" + i);
                    orderParList.add(order);
                }
                recyclerViewAdapter.addNewDatas(orderParList);
                getXrecyclerview().smoothScrollToPosition(0);
                recyclerViewAdapter.notifyDataSetChanged();
                getXrecyclerview().refreshComplete();
            }
        }, 2000);
    }

    @Override
    public void onPause() {
        super.onPause();
        orderPresenter.pause();
    }
}
