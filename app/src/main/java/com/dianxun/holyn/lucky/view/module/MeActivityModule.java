package com.dianxun.holyn.lucky.view.module;

import com.dianxun.holyn.lucky.view.activity.MeActivity;
import com.dianxun.holyn.lucky.view.fragment.Me.MeLoginFragment;

import dagger.Module;

/**
 * Created by holyn on 2015/12/11.
 */

@Module(complete = false ,
        injects = {
                MeActivity.class, MeLoginFragment.class
        })
public class MeActivityModule {

}
