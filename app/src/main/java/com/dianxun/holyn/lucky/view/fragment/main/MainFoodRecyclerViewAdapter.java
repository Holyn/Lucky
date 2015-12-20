package com.dianxun.holyn.lucky.view.fragment.main;

import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;

import com.dianxun.holyn.lucky.R;
import com.dianxun.holyn.lucky.model.http.HttpURL;
import com.dianxun.holyn.lucky.model.parcelable.FoodPar;
import com.squareup.picasso.Picasso;

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
        Picasso.with(mContext).load(HttpURL.URL_PIC_PRE+HttpURL.FOOD+foodPar.getPic()).into(bgaViewHolderHelper.getImageView(R.id.iv_food));
        bgaViewHolderHelper.setText(R.id.tv_title, foodPar.getName());

        //总数量
        String num = foodPar.getNum();
        //已售数量
        String sale = foodPar.getSales();

        try{
            int num_int = Integer.parseInt(num);
            int sale_int = Integer.parseInt(sale);
//            int rest_int = num_int - sale_int;

            double sale_percent = (double)sale_int/num_int;
//            double rest_percent = (double)rest_int/num_int;

            int sale_per_int = (int)(sale_percent*100);
//            int rest_per_int = (int)rest_percent*100;

            bgaViewHolderHelper.setText(R.id.tv_sale_progress, String.valueOf(sale_per_int)+"%");
            ((ProgressBar)bgaViewHolderHelper.getView(R.id.progressBar)).setProgress(sale_per_int);

        }catch (Exception e){
            System.out.println(e);
        }
    }
}
