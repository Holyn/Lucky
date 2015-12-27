package com.dianxun.holyn.lucky.view.fragment.food;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.dianxun.holyn.lucky.R;
import com.dianxun.holyn.lucky.model.parcelable.BidRecordPar;
import com.dianxun.holyn.lucky.presenter.food.FoodDetailPresenter;
import com.dianxun.holyn.lucky.view.activity.FoodDetailActivity;
import com.dianxun.holyn.lucky.view.fragment.BaseFragment;
import com.dianxun.holyn.lucky.view.utils.DividerGridItemDecoration;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import cn.bingoogolapple.bgabanner.BGABanner;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import cn.bingoogolapple.refreshlayout.BGAStickinessRefreshViewHolder;

/**
 * Created by holyn on 2015/12/24.
 */
public class FoodDetailFragment extends BaseFragment implements FoodDetailPresenter.UniqueViewInterface, BGARefreshLayout.BGARefreshLayoutDelegate {

    @Inject
    FoodDetailPresenter foodDetailPresenter;

    @Bind(R.id.rv_recyclerview_data)
    RecyclerView rvRecyclerviewData;
    @Bind(R.id.rl_recyclerview_refresh)
    BGARefreshLayout rlRecyclerviewRefresh;

    private FoodDetailBidRecordRvAdapter recyclerViewAdapter;

    public static FoodDetailFragment newInstance() {
        return new FoodDetailFragment();
    }


    public FoodDetailFragment() {
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_food_detail;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((FoodDetailActivity) getActivity()).setToolBarTitle("奖品详情");
        foodDetailPresenter.resume();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        foodDetailPresenter.setUniqueViewInterface(this);
        foodDetailPresenter.initialize();

        initBGARefreshLayout();
        initRecycleView();

        begainLoadFoodDetail();
    }

    private void initBGARefreshLayout(){
        rlRecyclerviewRefresh.setDelegate(this);

        BGAStickinessRefreshViewHolder stickinessRefreshViewHolder = new BGAStickinessRefreshViewHolder(getActivity(), true);
        stickinessRefreshViewHolder.setStickinessColor(R.color.red);
        stickinessRefreshViewHolder.setRotateImage(R.mipmap.bga_refresh_stickiness);
        rlRecyclerviewRefresh.setRefreshViewHolder(stickinessRefreshViewHolder);

        rlRecyclerviewRefresh.setCustomHeaderView(getBGABanner(), true);
    }

    private View getBGABanner(){
        View headerView = View.inflate(getActivity(), R.layout.view_custom_header, null);
        final BGABanner banner = (BGABanner) headerView.findViewById(R.id.banner);
        final List<View> views = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            views.add(View.inflate(getActivity(), R.layout.view_image, null));
        }
        banner.setViews(views);
        return headerView;
    }

    private void initRecycleView(){
        recyclerViewAdapter = new FoodDetailBidRecordRvAdapter(rvRecyclerviewData);

//        recyclerViewAdapter.setOnRVItemClickListener(this);
//        recyclerViewAdapter.setOnItemChildClickListener(this);

        rvRecyclerviewData.setLayoutManager(new LinearLayoutManager(getActivity()));

        rvRecyclerviewData.setAdapter(recyclerViewAdapter);
        rvRecyclerviewData.addItemDecoration(new DividerGridItemDecoration(getActivity()));
    }

    private void begainLoadFoodDetail(){
        rlRecyclerviewRefresh.beginRefreshing();
        foodDetailPresenter.getFoodDetail();
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout bgaRefreshLayout) {
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... params) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                rlRecyclerviewRefresh.endLoadingMore();
                List<BidRecordPar> recordParList = new ArrayList<BidRecordPar>();
                for (int i = 0; i<10; i++){
                    BidRecordPar recordPar = new BidRecordPar();
                    recordPar.setId("1111");
                    recordPar.setName("holyn_"+(recyclerViewAdapter.getItemCount()+i));
                    recordParList.add(recordPar);
                }
                recyclerViewAdapter.addMoreDatas(recordParList);

            }
        }.execute();

        return true;
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout bgaRefreshLayout) {


    }

    @Override public void onPause() {
        super.onPause();
        foodDetailPresenter.pause();
    }

    @Override
    public void successGetBidRecordPar(BidRecordPar bidRecordPar) {
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... params) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                rlRecyclerviewRefresh.endRefreshing();
                rlRecyclerviewRefresh.setPullDownRefreshEnable(false);
                List<BidRecordPar> recordParList = new ArrayList<BidRecordPar>();
                for (int i = 0; i<10; i++){
                    BidRecordPar recordPar = new BidRecordPar();
                    recordPar.setId("1111");
                    recordPar.setName("holyn_"+i);
                    recordParList.add(recordPar);
                }
                recyclerViewAdapter.addNewDatas(recordParList);
                rvRecyclerviewData.smoothScrollToPosition(0);
                recyclerViewAdapter.notifyDataSetChanged();

            }
        }.execute();
    }
}
