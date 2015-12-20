package com.dianxun.holyn.lucky.view.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import com.dianxun.holyn.lucky.R;
import com.dianxun.holyn.lucky.view.fragment.classify.ClassifyContentListFragment;
import com.dianxun.holyn.lucky.view.module.ClassifyContentListActivityModule;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

import java.util.LinkedList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by holyn on 2015/12/20.
 */
public class ClassifyContentListActivity extends BaseActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.tv_toolbar_title)
    TextView tvToolbarTitle;
    @Bind(R.id.viewpagertab)
    SmartTabLayout viewpagertab;
    @Bind(R.id.viewpager)
    ViewPager viewpager;

    private Menu mMenu;//控制菜单的显示或者隐藏

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_tab_layout_custom;
    }

    @Override
    protected List<Object> getModules() {
        LinkedList<Object> modules = new LinkedList<>();
        modules.add(new ClassifyContentListActivityModule());
        return modules;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initToolBar();

        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getSupportFragmentManager(), FragmentPagerItems.with(this)
                .add(R.string.tab_popularity, ClassifyContentListFragment.class)
                .add(R.string.tab_newest, ClassifyContentListFragment.class)
                .add(R.string.tab_progress, ClassifyContentListFragment.class)
                .add(R.string.tab_total_demand, ClassifyContentListFragment.class)
                .create());

        viewpager.setAdapter(adapter);

        SmartTabLayout viewPagerTab = (SmartTabLayout) findViewById(R.id.viewpagertab);
        viewPagerTab.setViewPager(viewpager);
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

        setToolBarTitle("十元专区");

    }

    public void setToolBarTitle(String title) {
        tvToolbarTitle.setText(title);
    }

    public void setMenuSettingEnable(boolean isEnable) {
        if (isEnable) {
            showMenu();
        } else {
            hiddenMenu();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.mMenu = menu;
        getMenuInflater().inflate(R.menu.menu_classify_content_detail, menu);
        hiddenMenu();
        return super.onCreateOptionsMenu(menu);
    }

    private void hiddenMenu() {
        if (null != mMenu) {
            for (int i = 0; i < mMenu.size(); i++) {
                mMenu.getItem(i).setVisible(false);
                mMenu.getItem(i).setEnabled(false);
            }
        }
    }

    private void showMenu() {
        if (null != mMenu) {
            for (int i = 0; i < mMenu.size(); i++) {
                mMenu.getItem(i).setVisible(true);
                mMenu.getItem(i).setEnabled(true);
            }
        }
    }
}
