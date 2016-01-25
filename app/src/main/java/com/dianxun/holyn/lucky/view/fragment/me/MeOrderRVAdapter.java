package com.dianxun.holyn.lucky.view.fragment.me;

import android.support.v7.widget.RecyclerView;

import com.dianxun.holyn.lucky.R;
import com.dianxun.holyn.lucky.model.parcelable.BookPar;

import cn.bingoogolapple.androidcommon.adapter.BGARecyclerViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * Created by holyn on 2016/1/25.
 */
public class MeOrderRVAdapter extends BGARecyclerViewAdapter<BookPar>{

    public MeOrderRVAdapter(RecyclerView recyclerView) {
        super(recyclerView, R.layout.item_me_order_list);
    }

    @Override
    protected void fillData(BGAViewHolderHelper bgaViewHolderHelper, int i, BookPar bookPar) {
        bgaViewHolderHelper.setText(R.id.tv_name, bookPar.getName());
        bgaViewHolderHelper.setText(R.id.tv_company_name, bookPar.getCompanyName());
        bgaViewHolderHelper.setText(R.id.tv_show, bookPar.getShow());
        bgaViewHolderHelper.setText(R.id.tv_tel, bookPar.getTel());
    }
}
