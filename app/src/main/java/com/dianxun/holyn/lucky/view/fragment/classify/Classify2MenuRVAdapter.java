package com.dianxun.holyn.lucky.view.fragment.classify;



import android.support.v7.widget.RecyclerView;

import com.dianxun.holyn.lucky.R;
import com.dianxun.holyn.lucky.model.http.HttpURL;
import com.dianxun.holyn.lucky.model.parcelable.CompanyTypePar;
import com.squareup.picasso.Picasso;

import cn.bingoogolapple.androidcommon.adapter.BGARecyclerViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * Created by holyn on 2016/1/7.
 */
public class Classify2MenuRVAdapter extends BGARecyclerViewAdapter<CompanyTypePar> {

    public Classify2MenuRVAdapter(RecyclerView recyclerView) {
        super(recyclerView, R.layout.item_classify_2_menu);
    }

    @Override
    protected void fillData(BGAViewHolderHelper bgaViewHolderHelper, int i, CompanyTypePar companyTypePar) {
        if (i == 0){
            bgaViewHolderHelper.getView(R.id.ll_item).setSelected(true);
        }
        Picasso.with(mContext).load(HttpURL.URL_PIC_PRE + HttpURL.COMPANY_TYPE + companyTypePar.getPic()).into(bgaViewHolderHelper.getImageView(R.id.iv_pic));
        bgaViewHolderHelper.setText(R.id.tv_name, companyTypePar.getName());
    }
}
