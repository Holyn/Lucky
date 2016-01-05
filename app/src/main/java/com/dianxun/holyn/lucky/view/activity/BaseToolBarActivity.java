package com.dianxun.holyn.lucky.view.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.dianxun.holyn.lucky.R;
import com.dianxun.holyn.lucky.view.fragment.food.FoodDetailFragment2;
import com.dianxun.holyn.lucky.view.module.BaseToolBarActivityModule;

import java.util.LinkedList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by holtn on 2015/12/27.
 */
public class BaseToolBarActivity extends BaseActivity {
    @Bind(R.id.tv_toolbar_title)
    TextView tvToolbarTitle;
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    private FragmentManager fragmentManager;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_base_tool_bar;
    }

    @Override
    protected List<Object> getModules() {
        LinkedList<Object> modules = new LinkedList<>();
        modules.add(new BaseToolBarActivityModule());
        return modules;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentManager = getSupportFragmentManager();
        initToolBar();
    }

    protected FragmentManager getBaseFragmentManager(){
        return this.fragmentManager;
    }

    protected Toolbar getBaseToolbar(){return this.toolbar;}

    public void setToolBarTitle(String title) {
        tvToolbarTitle.setText(title);
    }

    private void initToolBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        toolbar.setNavigationIcon(R.mipmap.ic_menu_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
