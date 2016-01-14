package com.dianxun.holyn.lucky.view.fragment.classify;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dianxun.holyn.lucky.R;
import com.dianxun.holyn.lucky.model.parcelable.ClassifyFoodPar;
import com.dianxun.holyn.lucky.model.parcelable.CompanyTypePar;
import com.dianxun.holyn.lucky.presenter.base.OnBaseGetNetDataListener;
import com.dianxun.holyn.lucky.view.fragment.BasePresenterXRViewFragment;
import com.dianxun.holyn.lucky.view.utils.DividerGridItemDecoration;

import java.util.List;

import cn.bingoogolapple.androidcommon.adapter.BGAOnRVItemClickListener;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;

/**
 * Created by holyn on 2016/1/7.
 */
public class Classify2ContentFragment extends BasePresenterXRViewFragment implements BGAOnRVItemClickListener {

    private Classify2ContentRVAdapter classify2ContentRVAdapter;

    private String typeId = "5";
    private int page = 1;

    public static Classify2ContentFragment newInstance() {
        return new Classify2ContentFragment();
    }

    public Classify2ContentFragment() {

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDetach() {
        EventBus.getDefault().unregister(this);
        super.onDetach();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        System.out.println("====> Classify2ContentFragment--onViewCreated....");
        showEmptyView();
        setLoadingListenner(true, true);
    }

    @Subscribe
    public void onEventSubscribeClassifyMenu(CompanyTypePar companyTypePar){
        typeId = companyTypePar.getId();
        page = 1;
        System.out.println("====> id=" + typeId + "::page=" + page);
        getClassifyFoodList(typeId, page);

        showEmptyView();
        getXrecyclerview().resetLoadingMoreView();
    }

    @Override
    protected void initXRecyclerViewAdapter() {
        classify2ContentRVAdapter = new Classify2ContentRVAdapter(getXrecyclerview());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),3);
        classify2ContentRVAdapter.setOnRVItemClickListener(this);
        getXrecyclerview().setLayoutManager(gridLayoutManager);
        getXrecyclerview().addHeaderView(getHeaderView());
        getXrecyclerview().setAdapter(classify2ContentRVAdapter);
        getXrecyclerview().addItemDecoration(new DividerGridItemDecoration(getActivity()));
    }

    private View getHeaderView(){
        View headerView = View.inflate(getActivity(), R.layout.headerview_classify_content, null);
        return headerView;
    }

    private void getClassifyFoodList(String typeId, final int page){
        getBasePresenter().setOnGetClassifyFoodParListListener(typeId, page, new OnBaseGetNetDataListener() {
            @Override
            public void onBegin() {

            }

            @Override
            public void onSuccess(Object object) {
                showContentView();
                List<ClassifyFoodPar> classifyFoodParList = (List<ClassifyFoodPar>) object;
                if (page == 1){
                    classify2ContentRVAdapter.clear();
                    getXrecyclerview().refreshComplete();

                    if (classifyFoodParList.size() == 0){
                        toastMsg("没数据");
                    }else{
                        classify2ContentRVAdapter.addNewDatas(classifyFoodParList);
                        getXrecyclerview().smoothScrollToPosition(0);
                        classify2ContentRVAdapter.notifyDataSetChanged();
                    }
                }else{
                    getXrecyclerview().loadMoreComplete();
                    if (classifyFoodParList.size() == 0) {
                        toastMsg("数据已加载完毕");
                    }else{
                        classify2ContentRVAdapter.addMoreDatas(classifyFoodParList);
                        classify2ContentRVAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onError(String msg) {
                if (page == 1){
                    getXrecyclerview().loadMoreComplete();
                    showErrorView();
                }else{
                    getXrecyclerview().refreshComplete();
                }

            }
        });
    }

    @Override
    public void onLoadMore() {
        page++;
        getClassifyFoodList(typeId, page);
    }

    @Override
    public void onRefresh() {
        page = 1;
        getClassifyFoodList(typeId, page);
    }

    @Override
    public void onRVItemClick(ViewGroup viewGroup, View view, int i) {
        toastMsg("====> click_"+i);
    }
}
