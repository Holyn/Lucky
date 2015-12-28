package com.dianxun.holyn.lucky.view.fragment.food;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.dianxun.holyn.lucky.R;
import com.dianxun.holyn.lucky.model.parcelable.BidRecordPar;
import com.dianxun.holyn.lucky.presenter.food.FoodDetailPresenter;
import com.dianxun.holyn.lucky.view.BaseViewInferface;
import com.dianxun.holyn.lucky.view.activity.FoodDetailActivity;
import com.dianxun.holyn.lucky.view.fragment.BaseFragment;
import com.dianxun.holyn.lucky.view.utils.DividerGridItemDecoration;
import com.dianxun.holyn.lucky.view.utils.LocalImageHolderView;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jcodecraeer.xrecyclerview.progressindicator.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bingoogolapple.bgabanner.BGABanner;

/**
 * Created by holyn on 2015/12/24.
 */
public class FoodDetailFragment extends BaseFragment implements FoodDetailPresenter.UniqueViewInterface ,BaseViewInferface, XRecyclerView.LoadingListener{

    @Inject
    FoodDetailPresenter foodDetailPresenter;

    @Bind(R.id.avloadingIndicatorView)
    AVLoadingIndicatorView avloadingIndicatorView;
    @Bind(R.id.tv_load)
    TextView tvLoad;
    @Bind(R.id.empty_view)
    LinearLayout emptyView;
    @Bind(R.id.xrecyclerview)
    XRecyclerView xrecyclerview;
    @Bind(R.id.content_view)
    RelativeLayout contentView;

    private FoodDetailBidRecordRvAdapter recyclerViewAdapter;

    public static FoodDetailFragment newInstance() {
        return new FoodDetailFragment();
    }


    public FoodDetailFragment() {

    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_food_detail;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((FoodDetailActivity) getActivity()).setToolBarTitle("奖品详情");
        foodDetailPresenter.resume();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initPresenter();
        initXRecyclerViewAdapter();

        loadidRecordPar();
    }

    private  void initPresenter(){
        foodDetailPresenter.setBaseViewInferface(this);
        foodDetailPresenter.setUniqueViewInterface(this);
        foodDetailPresenter.initialize();
    }

    private  void initXRecyclerViewAdapter(){
        recyclerViewAdapter = new FoodDetailBidRecordRvAdapter(xrecyclerview);

//        recyclerViewAdapter.setOnRVItemClickListener(this);
//        recyclerViewAdapter.setOnItemChildClickListener(this);

        xrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));

        xrecyclerview.addHeaderView(getHeaderView());

        xrecyclerview.setAdapter(recyclerViewAdapter);
        xrecyclerview.addItemDecoration(new DividerGridItemDecoration(getActivity()));

        setLoadingListenner(false, true);
    }

    private View getHeaderView(){
        View headerView = View.inflate(getActivity(), R.layout.headrview_food_detail, null);


        ConvenientBanner convenientBanner = (ConvenientBanner)headerView.findViewById(R.id.convenientBanner);

        ArrayList<Integer> localImages = new ArrayList<Integer>();
        localImages.add(R.mipmap.test_food_1);
        localImages.add(R.mipmap.test_food_2);
        localImages.add(R.mipmap.test_food_3);
        localImages.add(R.mipmap.test_food_4);


        //本地图片例子
        convenientBanner.setPages(
                new CBViewHolderCreator<LocalImageHolderView>() {
                    @Override
                    public LocalImageHolderView createHolder() {
                        return new LocalImageHolderView();
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


        return headerView;
    }

    private void loadidRecordPar() {
        foodDetailPresenter.getBidRecordPar();
    }

    @Override
    public void onPause() {
        super.onPause();
        foodDetailPresenter.pause();
    }

    @Override
    public void successGetBidRecordPar(List<BidRecordPar> recordParList) {
        recyclerViewAdapter.addMoreDatas(recordParList);
        recyclerViewAdapter.notifyDataSetChanged();
        xrecyclerview.loadMoreComplete();
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

    @Override
    public void onLoadMore() {
        loadidRecordPar();
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void loadingBegin() {

    }

    @Override
    public void loadingError() {
        avloadingIndicatorView.setVisibility(View.GONE);
        tvLoad.setText("加载失败，请稍后重试");
    }

    @Override
    public void loadingSuccess() {
        emptyView.setVisibility(View.GONE);
        contentView.setVisibility(View.VISIBLE);
    }
}
