package com.dianxun.holyn.lucky.view.fragment.me;

import android.os.Bundle;
import android.view.View;

import com.dianxun.holyn.lucky.R;
import com.dianxun.holyn.lucky.model.parcelable.FoodPar;
import com.dianxun.holyn.lucky.presenter.me.MeLoginPresenter;
import com.dianxun.holyn.lucky.view.activity.MeActivity;
import com.dianxun.holyn.lucky.view.fragment.BaseFragment;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by holyn on 2015/12/20.
 */
public class MeLoginFragment extends BaseFragment implements MeLoginPresenter.View{
    @Inject
    MeLoginPresenter meLoginPresenter;

    public static MeLoginFragment newInstance(){
        return new MeLoginFragment();
    }

    public MeLoginFragment() {
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_me_login;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MeActivity) getActivity()).setToolBarTitle("用户登录");
        ((MeActivity) getActivity()).setMenuSettingEnable(false);
        meLoginPresenter.resume();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        meLoginPresenter.setView(this);
        meLoginPresenter.initialize();


    }

    @Override public void onPause() {
        super.onPause();
        meLoginPresenter.pause();
    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showFanArt(String tvShowFanArtUrl) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void successLoading(List<FoodPar> foodParList) {

    }
}
