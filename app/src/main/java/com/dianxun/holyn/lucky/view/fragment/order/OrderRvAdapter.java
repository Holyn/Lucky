package com.dianxun.holyn.lucky.view.fragment.order;

import android.support.v7.widget.RecyclerView;

import com.dianxun.holyn.lucky.R;
import com.dianxun.holyn.lucky.model.parcelable.NewAnnouncePar;
import com.dianxun.holyn.lucky.model.parcelable.OrderPar;

import cn.bingoogolapple.androidcommon.adapter.BGARecyclerViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * Created by holyn on 2015/12/24.
 */
public class OrderRvAdapter extends BGARecyclerViewAdapter<OrderPar> {

    public OrderRvAdapter(RecyclerView recyclerView) {
        super(recyclerView, R.layout.item_order);
    }

    @Override
    protected void fillData(BGAViewHolderHelper bgaViewHolderHelper, int i, OrderPar orderPar) {
        bgaViewHolderHelper.setText(R.id.tv_title, orderPar.getTitle());

    }
}
