package com.dianxun.holyn.lucky.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dianxun.holyn.lucky.R;
import com.dianxun.holyn.lucky.view.fragment.food.FoodDetailFragment;
import com.dianxun.holyn.lucky.view.module.FoodDetailActivityModule;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import butterknife.Bind;
import cn.bingoogolapple.bgabanner.BGABanner;
import cn.bingoogolapple.refreshlayout.BGAMoocStyleRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import cn.bingoogolapple.refreshlayout.BGAStickinessRefreshViewHolder;

/**
 * Created by holyn on 2015/12/21.
 */
public class FoodDetailActivity extends BaseActivity{
    @Bind(R.id.tv_toolbar_title)
    TextView tvToolbarTitle;
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    private FragmentManager fragmentManager;
    private FoodDetailFragment foodDetailFragment;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_me;
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
        initToolBar();
        fragmentManager = getSupportFragmentManager();
        showFoodDetailFragment();
    }

    private void showFoodDetailFragment(){
        if (foodDetailFragment == null){
            foodDetailFragment = FoodDetailFragment.newInstance();
        }
        fragmentManager.beginTransaction().replace(R.id.fl_container, foodDetailFragment).commit();
    }

    public void setToolBarTitle(String title) {
        tvToolbarTitle.setText(title);
    }

    private void initToolBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        toolbar.setNavigationIcon(R.mipmap.ic_menu_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
