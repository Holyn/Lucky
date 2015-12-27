package com.dianxun.holyn.lucky.view.fragment.room;

import android.support.v7.widget.RecyclerView;

import com.dianxun.holyn.lucky.R;
import com.dianxun.holyn.lucky.model.http.HttpURL;
import com.dianxun.holyn.lucky.model.parcelable.OrderPar;
import com.dianxun.holyn.lucky.model.parcelable.RoomPar;
import com.squareup.picasso.Picasso;

import cn.bingoogolapple.androidcommon.adapter.BGARecyclerViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * Created by holyn on 2015/12/24.
 */
public class RoomRvAdapter extends BGARecyclerViewAdapter<RoomPar> {

    public RoomRvAdapter(RecyclerView recyclerView) {
        super(recyclerView, R.layout.item_room);
    }



    @Override
    protected void fillData(BGAViewHolderHelper bgaViewHolderHelper, int i, RoomPar roomPar) {
        RoomPar.FoodEntity foodEntity = roomPar.getFood();
        if (foodEntity != null) {
            Picasso.with(mContext).load(HttpURL.URL_PIC_PRE + HttpURL.FOOD + foodEntity.getPic())
                    .into(bgaViewHolderHelper.getImageView(R.id.iv_food));
        }else{
            Picasso.with(mContext).load(R.mipmap.iv_photo_empty)
                    .into(bgaViewHolderHelper.getImageView(R.id.iv_food));
        }
        bgaViewHolderHelper.setText(R.id.tv_name, roomPar.getName());

    }
}
