package com.dianxun.holyn.lucky.view.fragment.room;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dianxun.holyn.lucky.model.parcelable.OrderPar;
import com.dianxun.holyn.lucky.model.parcelable.RoomPar;
import com.dianxun.holyn.lucky.presenter.order.OrderPresenter;
import com.dianxun.holyn.lucky.presenter.room.RoomPresenter;
import com.dianxun.holyn.lucky.view.activity.BaseToolBarActivity;
import com.dianxun.holyn.lucky.view.activity.RoomActivity;
import com.dianxun.holyn.lucky.view.fragment.BaseXRecyclerViewFragment;
import com.dianxun.holyn.lucky.view.fragment.order.OrderRvAdapter;
import com.dianxun.holyn.lucky.view.utils.DividerGridItemDecoration;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import cn.bingoogolapple.androidcommon.adapter.BGAOnRVItemClickListener;

/**
 * Created by holyn on 2015/12/27.
 */
public class RoomFragment extends BaseXRecyclerViewFragment implements RoomPresenter.UniqueViewInterface, BGAOnRVItemClickListener {

    @Inject
    RoomPresenter roomPresenter;

    private int page = 1;

    private RoomRvAdapter recyclerViewAdapter;
    private boolean isPullRefresh = false;

    public static RoomFragment newInstance() {
        return new RoomFragment();
    }

    public RoomFragment() {

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadRoomParList(page);
        setLoadingListenner(true, true);
    }

    @Override
    public void onResume() {
        super.onResume();
//        ((RoomActivity) getActivity()).setToolBarTitle("房间");
        roomPresenter.resume();
    }

    @Override
    protected void initPresenter() {
        roomPresenter.setBaseViewInferface(this);
        roomPresenter.setUniqueViewInterface(this);
        roomPresenter.initialize();
    }

    @Override
    protected void initXRecyclerViewAdapter() {
        recyclerViewAdapter = new RoomRvAdapter(getXrecyclerview());

        recyclerViewAdapter.setOnRVItemClickListener(this);
//        recyclerViewAdapter.setOnItemChildClickListener(this);

        getXrecyclerview().setLayoutManager(new GridLayoutManager(getActivity(),3));

        getXrecyclerview().setAdapter(recyclerViewAdapter);
        getXrecyclerview().addItemDecoration(new DividerGridItemDecoration(getActivity()));
    }

    @Override
    public void onRVItemClick(ViewGroup viewGroup, View view, int i) {
        Toast.makeText(getActivity(), "item_"+i, Toast.LENGTH_SHORT).show();
    }

    private void loadRoomParList(int page) {
        roomPresenter.getRoomParList(page);
    }

    @Override
    public void successGetRoomParList(List<RoomPar> roomParList) {
        if (page == 1){
            if (isPullRefresh){
                recyclerViewAdapter.clear();
                getXrecyclerview().refreshComplete();
                setLoadingMoreEnabled(true);
                isPullRefresh = false;
            }
            recyclerViewAdapter.addNewDatas(roomParList);
            getXrecyclerview().smoothScrollToPosition(0);
            recyclerViewAdapter.notifyDataSetChanged();
        }else{
            recyclerViewAdapter.addMoreDatas(roomParList);
            recyclerViewAdapter.notifyDataSetChanged();
            getXrecyclerview().loadMoreComplete();
        }

        if (roomParList.size() == 0){
            Toast.makeText(getActivity(),"数据已加载完毕", Toast.LENGTH_SHORT).show();
            setLoadingMoreEnabled(false);
        }
    }

    @Override
    public void errorGetRoomParList() {
        if (page == 1){
            getXrecyclerview().loadMoreComplete();
        }else{
            getXrecyclerview().refreshComplete();
        }
    }

    @Override
    public void onLoadMore() {
        page++;
        loadRoomParList(page);
    }

    @Override
    public void onRefresh() {
        isPullRefresh = true;
        page = 1;
        loadRoomParList(page);
    }

    @Override
    public void onPause() {
        super.onPause();
        roomPresenter.pause();
    }
}
