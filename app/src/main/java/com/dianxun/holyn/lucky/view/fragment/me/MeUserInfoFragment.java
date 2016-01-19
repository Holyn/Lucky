package com.dianxun.holyn.lucky.view.fragment.me;

import com.dianxun.holyn.lucky.presenter.base.BasePresenter;
import com.dianxun.holyn.lucky.view.fragment.BaseFragment;

import javax.inject.Inject;

/**
 * Created by holyn on 2016/1/14.
 */
public class MeUserInfoFragment extends BaseFragment{
    @Inject
    BasePresenter basePresenter;

    @Override
    protected int getFragmentLayout() {
        return 0;
    }
}
