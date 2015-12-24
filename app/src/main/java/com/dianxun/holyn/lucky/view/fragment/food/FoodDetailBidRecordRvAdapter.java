package com.dianxun.holyn.lucky.view.fragment.food;

import android.support.v7.widget.RecyclerView;

import com.dianxun.holyn.lucky.R;
import com.dianxun.holyn.lucky.model.parcelable.BidRecordPar;

import cn.bingoogolapple.androidcommon.adapter.BGARecyclerViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * Created by holyn on 2015/12/24.
 */
public class FoodDetailBidRecordRvAdapter extends BGARecyclerViewAdapter<BidRecordPar> {

    public FoodDetailBidRecordRvAdapter(RecyclerView recyclerView) {
        super(recyclerView, R.layout.item_food_detail_bid_record);
    }

    @Override
    protected void fillData(BGAViewHolderHelper bgaViewHolderHelper, int i, BidRecordPar bidRecordPar) {
        bgaViewHolderHelper.setText(R.id.tv_name, bidRecordPar.getName());

    }
}
