package com.dianxun.holyn.lucky.view.module;

import com.dianxun.holyn.lucky.view.activity.BaseToolBarActivity;
import com.dianxun.holyn.lucky.view.activity.NewAnnounceActivity;
import com.dianxun.holyn.lucky.view.fragment.newannounce.NewAnnounceFragment;

import dagger.Module;

/**
 * Created by holyn on 2015/12/11.
 */

@Module(complete = false ,
        injects = {
                BaseToolBarActivity.class, NewAnnounceActivity.class, NewAnnounceFragment.class
        })
public class BaseToolBarActivityModule {

}
