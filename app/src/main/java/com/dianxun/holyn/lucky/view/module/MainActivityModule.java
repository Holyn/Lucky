package com.dianxun.holyn.lucky.view.module;

import com.dianxun.holyn.lucky.view.activity.MainActivity;
import com.dianxun.holyn.lucky.view.fragment.Main.ListViewFragment;
import com.dianxun.holyn.lucky.view.fragment.Main.MainRVGridFragment;

import dagger.Module;

/**
 * Created by holyn on 2015/12/11.
 */

@Module(complete = false ,
        injects = {
                MainActivity.class, ListViewFragment.class, MainRVGridFragment.class
        })
public class MainActivityModule {

}
