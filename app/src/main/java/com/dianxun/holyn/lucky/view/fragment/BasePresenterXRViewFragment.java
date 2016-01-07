package com.dianxun.holyn.lucky.view.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dianxun.holyn.lucky.R;
import com.dianxun.holyn.lucky.presenter.base.BasePresenter;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jcodecraeer.xrecyclerview.progressindicator.AVLoadingIndicatorView;

import javax.inject.Inject;

import butterknife.Bind;

/**
 * Created by holyn on 2016/1/7.
 */
public abstract class BasePresenterXRViewFragment extends BaseFragment implements XRecyclerView.LoadingListener{
    @Inject
    BasePresenter basePresenter;

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
        initXRecyclerViewAdapter();
        showContentView();
    }

    protected abstract void initXRecyclerViewAdapter();

    public BasePresenter getBasePresenter(){
        return this.basePresenter;
    }

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

    protected void showEmptyView(){
        if (emptyView != null){
            emptyView.setVisibility(View.VISIBLE);
        }
        if (xrecyclerview != null){
            xrecyclerview.setVisibility(View.GONE);
        }
    }

    protected void showErrorView(){
        if (avloadingIndicatorView != null){
            avloadingIndicatorView.setVisibility(View.GONE);
        }
        if (tvLoad != null){
            tvLoad.setText("加载失败，请稍后重试");
        }
    }

    protected void showContentView(){
        if (emptyView != null){
            emptyView.setVisibility(View.GONE);
        }
        if (xrecyclerview != null){
            xrecyclerview.setVisibility(View.VISIBLE);
        }
    }

}
