package com.dianxun.holyn.lucky.view.activity;

import com.dianxun.holyn.lucky.view.module.MeActivityModule;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by lenovo on 2015/12/18.
 */
public class MeActivity extends BaseActivity {
    @Override
    protected int getLayoutResId() {
        return 0;
    }

    @Override
    protected List<Object> getModules() {
        LinkedList<Object> modules = new LinkedList<>();
        modules.add(new MeActivityModule());
        return modules;
    }
}
