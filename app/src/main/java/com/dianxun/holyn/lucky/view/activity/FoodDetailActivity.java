package com.dianxun.holyn.lucky.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.dianxun.holyn.lucky.R;
import com.dianxun.holyn.lucky.view.module.FoodDetailActivityModule;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import cn.bingoogolapple.bgabanner.BGABanner;
import cn.bingoogolapple.refreshlayout.BGAMoocStyleRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

/**
 * Created by holyn on 2015/12/21.
 */
public class FoodDetailActivity extends BaseActivity implements BGARefreshLayout.BGARefreshLayoutDelegate{

    private BGARefreshLayout mRefreshLayout;
    private RecyclerView mDataRv;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_food_detail;
    }

    @Override
    protected List<Object> getModules() {
        LinkedList<Object> modules = new LinkedList<>();
        modules.add(new FoodDetailActivityModule());
        return modules;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRefreshLayout = (BGARefreshLayout)findViewById(R.id.rl_recyclerview_refresh);
        mDataRv = (RecyclerView)findViewById(R.id.rv_recyclerview_data);

        mRefreshLayout.setDelegate(this);

//        mRefreshLayout.setCustomHeaderView(getCustomHeaderView(FoodDetailActivity.this), false);

        BGABanner banner = (BGABanner)findViewById(R.id.banner);
        final List<View> views = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            views.add(View.inflate(FoodDetailActivity.this, R.layout.view_image, null));
        }
        banner.setViews(views);

        BGAMoocStyleRefreshViewHolder moocStyleRefreshViewHolder = new BGAMoocStyleRefreshViewHolder(FoodDetailActivity.this, true);
        moocStyleRefreshViewHolder.setOriginalImage(R.mipmap.bga_refresh_moooc);
        moocStyleRefreshViewHolder.setUltimateColor(R.color.red);
        mRefreshLayout.setRefreshViewHolder(moocStyleRefreshViewHolder);

        mRefreshLayout.beginRefreshing();
    }

    private  BGABanner getCustomHeaderView(final Context context) {
        BGABanner banner = (BGABanner)findViewById(R.id.banner);
        final List<View> views = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            views.add(View.inflate(context, R.layout.view_image, null));
        }
        banner.setViews(views);
        return banner;
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout bgaRefreshLayout) {
        return false;
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout bgaRefreshLayout) {

    }
}
