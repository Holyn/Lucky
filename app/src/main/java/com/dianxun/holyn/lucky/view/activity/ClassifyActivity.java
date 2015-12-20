package com.dianxun.holyn.lucky.view.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.dianxun.holyn.lucky.R;
import com.dianxun.holyn.lucky.view.fragment.classify.ClassifyFragment;
import com.dianxun.holyn.lucky.view.module.ClassifyActivityModule;

import java.util.LinkedList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by holyn on 2015/12/20.
 */
public class ClassifyActivity extends BaseActivity {
    @Bind(R.id.tv_toolbar_title)
    TextView tvToolbarTitle;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.fl_container)
    FrameLayout flContainer;

    private FragmentManager fragmentManager;
    private ClassifyFragment classifyFragment;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_custom;
    }

    @Override
    protected List<Object> getModules() {
        LinkedList<Object> modules = new LinkedList<>();
        modules.add(new ClassifyActivityModule());
        return modules;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initToolBar();
        fragmentManager = getSupportFragmentManager();

        showClassifyFragment();
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

    private void showClassifyFragment(){
        if (classifyFragment == null){
            classifyFragment = ClassifyFragment.newInstance();
        }
        fragmentManager.beginTransaction().replace(R.id.fl_container, classifyFragment).commit();
    }

    public void setToolBarTitle(String title) {
        tvToolbarTitle.setText(title);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
