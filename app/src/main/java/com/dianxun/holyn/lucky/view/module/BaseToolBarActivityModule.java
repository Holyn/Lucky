package com.dianxun.holyn.lucky.view.module;

import com.dianxun.holyn.lucky.view.activity.BaseToolBarActivity;
import com.dianxun.holyn.lucky.view.activity.ClassifyActivity2;
import com.dianxun.holyn.lucky.view.activity.MainActivity2;
import com.dianxun.holyn.lucky.view.activity.NewAnnounceActivity;
import com.dianxun.holyn.lucky.view.activity.OrderActivity;
import com.dianxun.holyn.lucky.view.fragment.BasePresenterXRViewFragment;
import com.dianxun.holyn.lucky.view.fragment.classify.Classify2ContentFragment;
import com.dianxun.holyn.lucky.view.fragment.classify.Classify2Fragment;
import com.dianxun.holyn.lucky.view.fragment.classify.Classify2MenuFragment;
import com.dianxun.holyn.lucky.view.fragment.main.MainFragment2;
import com.dianxun.holyn.lucky.view.fragment.newannounce.NewAnnounceFragment;
import com.dianxun.holyn.lucky.view.fragment.order.OrderFragment;

import dagger.Module;

/**
 * Created by holyn on 2015/12/11.
 */

@Module(complete = false ,
        injects = {
                BaseToolBarActivity.class, BasePresenterXRViewFragment.class,
                NewAnnounceActivity.class, NewAnnounceFragment.class,
                OrderActivity.class, OrderFragment.class,
                MainActivity2.class, MainFragment2.class,
                ClassifyActivity2.class, Classify2MenuFragment.class, Classify2ContentFragment.class
        })
public class BaseToolBarActivityModule {

}
