package com.dianxun.holyn.lucky.view.fragment.classify;

import android.support.v7.widget.RecyclerView;

import com.dianxun.holyn.lucky.R;
import com.dianxun.holyn.lucky.model.http.HttpURL;
import com.dianxun.holyn.lucky.model.parcelable.ClassifyFoodPar;
import com.squareup.picasso.Picasso;

import cn.bingoogolapple.androidcommon.adapter.BGARecyclerViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * Created by holyn on 2016/1/7.
 */
public class Classify2ContentRVAdapter extends BGARecyclerViewAdapter<ClassifyFoodPar> {

    public Classify2ContentRVAdapter(RecyclerView recyclerView) {
        super(recyclerView, R.layout.item_classify_2_content);
    }

    @Override
    protected void fillData(BGAViewHolderHelper bgaViewHolderHelper, int i, ClassifyFoodPar classifyFoodPar) {
        Picasso.with(mContext)
                .load(HttpURL.URL_PIC_PRE + HttpURL.FOOD + classifyFoodPar.getPic())
                .resize(200,200)
                .centerCrop()
                .into(bgaViewHolderHelper.getImageView(R.id.iv_pic));
        bgaViewHolderHelper.setText(R.id.tv_name, classifyFoodPar.getName());

    }
}
