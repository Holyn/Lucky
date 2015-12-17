package com.dianxun.holyn.lucky.view.fragment.Main;

import android.support.v7.widget.RecyclerView;

import com.dianxun.holyn.lucky.R;
import com.dianxun.holyn.lucky.model.parcelable.FoodPar;

import cn.bingoogolapple.androidcommon.adapter.BGAOnRVItemClickListener;
import cn.bingoogolapple.androidcommon.adapter.BGARecyclerViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * Created by lenovo on 2015/12/17.
 */
public class MainFoodRecyclerViewAdapter extends BGARecyclerViewAdapter<FoodPar> {

    public MainFoodRecyclerViewAdapter(RecyclerView recyclerView) {
        super(recyclerView, R.layout.item_grid_main_food);
    }

    @Override
    public void setOnRVItemClickListener(BGAOnRVItemClickListener onRVItemClickListener) {
        super.setOnRVItemClickListener(onRVItemClickListener);
    }

    @Override
    protected void fillData(BGAViewHolderHelper bgaViewHolderHelper, int i, FoodPar foodPar) {

    }
}
