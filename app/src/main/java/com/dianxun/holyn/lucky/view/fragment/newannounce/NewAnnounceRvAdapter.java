package com.dianxun.holyn.lucky.view.fragment.newannounce;

import android.support.v7.widget.RecyclerView;

import com.dianxun.holyn.lucky.R;
import com.dianxun.holyn.lucky.model.parcelable.BidRecordPar;
import com.dianxun.holyn.lucky.model.parcelable.NewAnnouncePar;

import cn.bingoogolapple.androidcommon.adapter.BGARecyclerViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * Created by holyn on 2015/12/24.
 */
public class NewAnnounceRvAdapter extends BGARecyclerViewAdapter<NewAnnouncePar> {

    public NewAnnounceRvAdapter(RecyclerView recyclerView) {
        super(recyclerView, R.layout.item_new_announce);
    }

    @Override
    protected void fillData(BGAViewHolderHelper bgaViewHolderHelper, int i, NewAnnouncePar newAnnouncePar) {
        bgaViewHolderHelper.setText(R.id.tv_term, "第"+newAnnouncePar.getTerm()+"期 揭晓时间：2015-12-27 18:57:31");
        bgaViewHolderHelper.setText(R.id.tv_name, "获奖者："+newAnnouncePar.getName()+"（广东佛山）");

    }
}
