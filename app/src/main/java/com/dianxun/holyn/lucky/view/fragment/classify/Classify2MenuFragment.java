package com.dianxun.holyn.lucky.view.fragment.classify;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewGroup;

import com.dianxun.holyn.lucky.model.parcelable.CompanyTypePar;
import com.dianxun.holyn.lucky.presenter.base.OnBaseGetNetDataListener;
import com.dianxun.holyn.lucky.view.activity.BaseActivity;
import com.dianxun.holyn.lucky.view.fragment.BasePresenterXRViewFragment;

import java.util.List;

import cn.bingoogolapple.androidcommon.adapter.BGAOnRVItemClickListener;
import de.greenrobot.event.EventBus;

/**
 * Created by holyn on 2016/1/7.
 */
public class Classify2MenuFragment extends BasePresenterXRViewFragment implements BGAOnRVItemClickListener {

    private Classify2MenuRVAdapter classify2MenuRVAdapter;

    public static Classify2MenuFragment newInstance() {
        return new Classify2MenuFragment();
    }

    public Classify2MenuFragment() {

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setLoadingListenner(false, false);
        getCompanyTypeParList();
    }

    @Override
    protected void initXRecyclerViewAdapter() {
        classify2MenuRVAdapter = new Classify2MenuRVAdapter(getXrecyclerview());
        classify2MenuRVAdapter.setOnRVItemClickListener(this);
        getXrecyclerview().setLayoutManager(new LinearLayoutManager(getActivity()));
        getXrecyclerview().setAdapter(classify2MenuRVAdapter);
    }

    private void getCompanyTypeParList(){
        getBasePresenter().setOnGetCompanyTypeParListListener(new OnBaseGetNetDataListener() {
            @Override
            public void onBegin() {
                ((BaseActivity) getActivity()).showLoadingDialog();
            }

            @Override
            public void onSuccess(Object object) {
                List<CompanyTypePar> companyTypeParList = (List<CompanyTypePar>) object;
                classify2MenuRVAdapter.addNewDatas(companyTypeParList);
                classify2MenuRVAdapter.notifyDataSetChanged();
                EventBus.getDefault().post(companyTypeParList.get(0));
                ((BaseActivity) getActivity()).closeLoadingDialog();
            }

            @Override
            public void onError(String msg) {
                ((BaseActivity) getActivity()).closeLoadingDialog();
                toastMsg(msg);
            }
        });
    }

    @Override
    public void onRVItemClick(ViewGroup viewGroup, View view, int i) {
//        getXrecyclerview().getChildAt()
        EventBus.getDefault().post(classify2MenuRVAdapter.getItem(i - 1));
        System.out.println("====> viewGroup.getChildCount() = " + viewGroup.getChildCount());
        for (int j =0; j<viewGroup.getChildCount(); j++){
            viewGroup.getChildAt(j).setSelected(false);
        }
        view.setSelected(true);
    }
}
