package com.dianxun.holyn.lucky.view.fragment.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.dianxun.holyn.lucky.R;
import com.dianxun.holyn.lucky.model.parcelable.FoodPar;
import com.dianxun.holyn.lucky.presenter.main.MainFoodPresenter;
import com.dianxun.holyn.lucky.view.activity.BaseToolBarActivity;
import com.dianxun.holyn.lucky.view.activity.ClassifyActivity;
import com.dianxun.holyn.lucky.view.activity.ClassifyContentListActivity;
import com.dianxun.holyn.lucky.view.activity.FoodDetailActivity;
import com.dianxun.holyn.lucky.view.activity.MeActivity;
import com.dianxun.holyn.lucky.view.activity.NewAnnounceActivity;
import com.dianxun.holyn.lucky.view.activity.OrderActivity;
import com.dianxun.holyn.lucky.view.activity.RoomActivity;
import com.dianxun.holyn.lucky.view.fragment.BaseFragment;
import com.dianxun.holyn.lucky.view.utils.DividerGridItemDecoration;
import com.dianxun.holyn.lucky.view.utils.LocalImageHolderView2;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import cn.bingoogolapple.androidcommon.adapter.BGAOnItemChildClickListener;
import cn.bingoogolapple.androidcommon.adapter.BGAOnRVItemClickListener;

/**
 * Created by holyn on 2016/1/5.
 */
public class MainFragment2 extends BaseFragment implements  MainFoodPresenter.UniqueViewInterface, BGAOnRVItemClickListener, BGAOnItemChildClickListener, XRecyclerView.LoadingListener{
    @Inject
    MainFoodPresenter mainFoodPresenter;

    @Bind(R.id.xrecyclerview)
    XRecyclerView xrecyclerview;

    @Bind(R.id.tv_bottom_tab_1)//夺宝
    TextView tvBottomTab1;
    @Bind(R.id.tv_bottom_tab_2)//最新揭晓
    TextView tvBottomTab2;
    @Bind(R.id.tv_bottom_tab_3)//晒单
    TextView tvBottomTab3;
    @Bind(R.id.tv_bottom_tab_4)//房间
    TextView tvBottomTab4;
    @Bind(R.id.tv_bottom_tab_5)//我
    TextView tvBottomTab5;

    private ConvenientBanner convenientBanner;

    private MainFoodRecyclerViewAdapter recyclerViewAdapter;

    public static MainFragment2 newInstance() {
        return new MainFragment2();
    }

    public MainFragment2() {

    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_main2;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initPresenter();
        initXRecyclerViewAdapter();
        initBottomTabs();
        getFoodList();
    }

    @Override
    public void onResume() {
        super.onResume();
        ((BaseToolBarActivity)getActivity()).setToolBarTitle("我是幸运儿");
        mainFoodPresenter.resume();
        //开始自动翻页
        convenientBanner.startTurning(5000);
    }

    @Override
    public void onPause() {
        super.onPause();
        mainFoodPresenter.pause();
        //停止翻页
        convenientBanner.stopTurning();
    }

    protected void initPresenter() {
        mainFoodPresenter.setView(this);
        mainFoodPresenter.initialize();
    }


    protected void initXRecyclerViewAdapter() {
        recyclerViewAdapter = new MainFoodRecyclerViewAdapter(xrecyclerview);
        recyclerViewAdapter.setOnRVItemClickListener(this);
        recyclerViewAdapter.setOnItemChildClickListener(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),2);
        xrecyclerview.setLayoutManager(gridLayoutManager);
        xrecyclerview.addHeaderView(getHeaderView());
        xrecyclerview.setAdapter(recyclerViewAdapter);
        xrecyclerview.addItemDecoration(new DividerGridItemDecoration(getActivity()));

        setLoadingListenner(true,false);
    }

    private View getHeaderView(){
        View headerView = View.inflate(getActivity(), R.layout.headrview_main2, null);
        initHeaderViewConvenientBanner(headerView);
        initHeaderViewMenu(headerView);
        return headerView;
    }

    private void initHeaderViewConvenientBanner(View headerView){
        convenientBanner = (ConvenientBanner)headerView.findViewById(R.id.convenientBanner);

        ArrayList<Integer> localImages = new ArrayList<Integer>();
        localImages.add(R.mipmap.test_iv_1);
        localImages.add(R.mipmap.test_iv_2);
        localImages.add(R.mipmap.test_iv_3);
        localImages.add(R.mipmap.test_iv_2);


        //本地图片例子
        convenientBanner.setPages(
                new CBViewHolderCreator<LocalImageHolderView2>() {
                    @Override
                    public LocalImageHolderView2 createHolder() {
                        return new LocalImageHolderView2();
                    }
                }, localImages)
                //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
                .setPageIndicator(new int[]{R.mipmap.ad_point_nor, R.mipmap.ad_point_check})
                        //设置指示器的方向
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
//                .setOnPageChangeListener(this)//监听翻页事件
                .setOnItemClickListener(new com.bigkoo.convenientbanner.listener.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        Toast.makeText(getActivity(),"item_"+position, Toast.LENGTH_SHORT).show();
                    }
                });

//        convenientBanner.setManualPageable(false);//设置不能手动影响

        //网络加载例子
//        networkImages= Arrays.asList(images);
//        convenientBanner.setPages(new CBViewHolderCreator<NetworkImageHolderView>() {
//            @Override
//            public NetworkImageHolderView createHolder() {
//                return new NetworkImageHolderView();
//            }
//        },networkImages);
    }

    private void initHeaderViewMenu(View headerView){
        TextView tvCenterMenu1 = (TextView)headerView.findViewById(R.id.tv_center_menu_1);
        TextView tvCenterMenu2 = (TextView)headerView.findViewById(R.id.tv_center_menu_2);
        TextView tvCenterMenu3 = (TextView)headerView.findViewById(R.id.tv_center_menu_3);

        tvCenterMenu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ClassifyActivity.class));
            }
        });
        tvCenterMenu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ClassifyContentListActivity.class));
            }
        });
        tvCenterMenu3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ClassifyContentListActivity.class));
            }
        });
    }

    private void initBottomTabs(){
        tvBottomTab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        tvBottomTab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), NewAnnounceActivity.class));
            }
        });
        tvBottomTab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), OrderActivity.class));
            }
        });
        tvBottomTab4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), RoomActivity.class));
            }
        });
        tvBottomTab5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MeActivity.class));
            }
        });
    }

    private void getFoodList(){
        mainFoodPresenter.getFoodList();
    }

    @Override
    public void onLoadMore() {

    }

    @Override
    public void onRefresh() {
        getFoodList();
    }

    @Override
    public void onItemChildClick(ViewGroup viewGroup, View view, int i) {
        switch (view.getId()){
            case R.id.btn_duobao:
                Toast.makeText(getActivity(), "点击了夺宝按钮", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void successGetFoodList(List<FoodPar> foodParList) {
        recyclerViewAdapter.clear();
        recyclerViewAdapter.addNewDatas(foodParList);
        recyclerViewAdapter.notifyDataSetChanged();

        xrecyclerview.refreshComplete();
    }

    @Override
    public void onRVItemClick(ViewGroup viewGroup, View view, int i) {
        startActivity(new Intent(getActivity(), FoodDetailActivity.class));
    }

    @Override
    public void errorGetFoodList(String msg) {

    }

    protected void setLoadingListenner(boolean isEnablePullRefresh, boolean isEnableLoadingMore){
        if (isEnablePullRefresh || isEnableLoadingMore){
            xrecyclerview.setLoadingListener(this);
            setPullRefreshEnabled(isEnablePullRefresh);
            setLoadingMoreEnabled(isEnableLoadingMore);
        }
    }

    protected void setPullRefreshEnabled(boolean isEnable){
        if (isEnable){
            xrecyclerview.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        }
        xrecyclerview.setPullRefreshEnabled(isEnable);
    }

    protected void setLoadingMoreEnabled(boolean isEnable){
        if (isEnable){
            xrecyclerview.setLaodingMoreProgressStyle(ProgressStyle.BallRotate);
        }
        xrecyclerview.setLoadingMoreEnabled(isEnable);
    }
}
