package com.dianxun.holyn.lucky.view.module;

import com.dianxun.holyn.lucky.view.activity.MeActivity;
import com.dianxun.holyn.lucky.view.activity.RoomActivity;
import com.dianxun.holyn.lucky.view.fragment.me.MeLoginFragment;
import com.dianxun.holyn.lucky.view.fragment.room.RoomFragment;

import dagger.Module;

/**
 * Created by holyn on 2015/12/11.
 */

@Module(complete = false ,
        injects = {
                RoomActivity.class, RoomFragment.class
        })
public class RoomActivityModule {

}
