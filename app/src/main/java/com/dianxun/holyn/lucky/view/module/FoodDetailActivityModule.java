package com.dianxun.holyn.lucky.view.module;

import com.dianxun.holyn.lucky.view.activity.FoodDetailActivity;
import com.dianxun.holyn.lucky.view.activity.MeActivity;
import com.dianxun.holyn.lucky.view.fragment.food.FoodDetailFragment;
import com.dianxun.holyn.lucky.view.fragment.food.FoodDetailFragment2;
import com.dianxun.holyn.lucky.view.fragment.me.MeLoginFragment;

import dagger.Module;

/**
 * Created by holyn on 2015/12/11.
 */

@Module(complete = false ,
        injects = {
                FoodDetailActivity.class, FoodDetailFragment2.class
        })
public class FoodDetailActivityModule {

}
