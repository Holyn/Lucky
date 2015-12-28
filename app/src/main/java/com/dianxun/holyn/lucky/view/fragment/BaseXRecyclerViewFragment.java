package com.dianxun.holyn.lucky.view.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dianxun.holyn.lucky.R;
import com.dianxun.holyn.lucky.view.BaseViewInferface;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jcodecraeer.xrecyclerview.progressindicator.AVLoadingIndicatorView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by holyn on 2015/12/27.
 */
public abstract class BaseXRecyclerViewFragment extends BaseFragment implements BaseViewInferface, XRecyclerView.LoadingListener{

    @Bind(R.id.avloadingIndicatorView)
    AVLoadingIndicatorView avloadingIndicatorView;
    @Bind(R.id.tv_load)
    TextView tvLoad;
    @Bind(R.id.empty_view)
    LinearLayout emptyView;
    @Bind(R.id.xrecyclerview)
    XRecyclerView xrecyclerview;

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_xrecycleview;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initPresenter();
        initXRecyclerViewAdapter();
    }

    protected abstract void initXRecyclerViewAdapter();

    protected abstract void initPresenter();

    public XRecyclerView getXrecyclerview() {
        return xrecyclerview;
    }

    protected void addHeaderView(View headerView) {
        xrecyclerview.addHeaderView(headerView);
    }

    protected void setLayoutManager(RecyclerView.LayoutManager layoutManager){
        xrecyclerview.setLayoutManager(layoutManager);
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
        xrecyclerview.setVisibility(View.VISIBLE);
    }
}
