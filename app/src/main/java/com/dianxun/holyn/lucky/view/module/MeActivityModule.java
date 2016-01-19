package com.dianxun.holyn.lucky.view.module;

import com.dianxun.holyn.lucky.view.activity.MeActivity;
import com.dianxun.holyn.lucky.view.fragment.BasePresenterXRViewFragment;
import com.dianxun.holyn.lucky.view.fragment.me.MeLoginFragment;
import com.dianxun.holyn.lucky.view.fragment.me.MeOrderListFragment;
import com.dianxun.holyn.lucky.view.fragment.me.MeRegisterFragment;
import com.dianxun.holyn.lucky.view.fragment.me.MeUserInfoFragment;

import dagger.Module;

/**
 * Created by holyn on 2015/12/11.
 */

@Module(complete = false ,
        injects = {
                MeActivity.class, MeLoginFragment.class, MeRegisterFragment.class,
                BasePresenterXRViewFragment.class, MeUserInfoFragment.class, MeOrderListFragment.class
        })
public class MeActivityModule {

}
