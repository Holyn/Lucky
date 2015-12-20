package com.dianxun.holyn.lucky.view.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.dianxun.holyn.lucky.R;
import com.dianxun.holyn.lucky.view.fragment.Me.MeLoginFragment;
import com.dianxun.holyn.lucky.view.fragment.Me.MeMemberCenterFragment;
import com.dianxun.holyn.lucky.view.fragment.Me.MeSettingFragment;
import com.dianxun.holyn.lucky.view.module.MeActivityModule;

import java.util.LinkedList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by holyn on 2015/12/18.
 */
public class MeActivity extends BaseActivity {
    @Bind(R.id.tv_toolbar_title)
    TextView tvToolbarTitle;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.fl_container)
    FrameLayout flContainer;

    private Menu mMenu;//控制菜单的显示或者隐藏

    private FragmentManager fragmentManager;
    private MeMemberCenterFragment meMemberCenterFragment = null;
    private MeSettingFragment meSettingFragment = null;
    private MeLoginFragment meLoginFragment = null;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_me;
    }

    @Override
    protected List<Object> getModules() {
        LinkedList<Object> modules = new LinkedList<>();
        modules.add(new MeActivityModule());
        return modules;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initToolBar();
        fragmentManager = getSupportFragmentManager();
        showMeMemberCenterFragment();
    }

    private void initToolBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        toolbar.setNavigationIcon(R.mipmap.ic_menu_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("====》 setNavigationOnClickListener");
                onBackPressed();
            }
        });

        setMenuListener();

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

    private void setMenuListener() {
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_setting:
                        System.out.println("====》 action_setting");
                        showMeSettingFragment();
                        break;
                }
                return true;
            }
        });
    }

    private void showMeMemberCenterFragment(){
        if (meMemberCenterFragment == null){
            meMemberCenterFragment = MeMemberCenterFragment.newInstance();
        }
        fragmentManager.beginTransaction().replace(R.id.fl_container, meMemberCenterFragment).commit();
    }

    public void showMeSettingFragment(){
        if (meSettingFragment == null){
            meSettingFragment = MeSettingFragment.newInstance();
        }
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fl_container, meSettingFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void showMeLoginFragment(){
        if (meLoginFragment == null){
            meLoginFragment = MeLoginFragment.newInstance();
        }
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fl_container, meLoginFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.mMenu = menu;
        getMenuInflater().inflate(R.menu.menu_me, menu);
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
