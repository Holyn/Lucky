package com.dianxun.holyn.lucky.view.module;

import com.dianxun.holyn.lucky.view.activity.ClassifyActivity;
import com.dianxun.holyn.lucky.view.activity.ClassifyContentListActivity;
import com.dianxun.holyn.lucky.view.fragment.classify.ClassifyContentListFragment;

import dagger.Module;

/**
 * Created by holyn on 2015/12/11.
 */

@Module(complete = false ,
        injects = {
                ClassifyContentListActivity.class, ClassifyContentListFragment.class
        })
public class ClassifyContentListActivityModule {

}
