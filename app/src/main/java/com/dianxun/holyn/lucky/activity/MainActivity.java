package com.dianxun.holyn.lucky.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.dianxun.holyn.lucky.R;
import com.dianxun.holyn.lucky.model.parcelable.FoodPar;
import com.dianxun.holyn.lucky.module.MainActivityModule;
import com.dianxun.holyn.lucky.presenter.mainactivity.MainFoodPresenter;

import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;

public class MainActivity extends BaseActivity implements MainFoodPresenter.View {

    @Inject
    MainFoodPresenter mainFoodPresenter;

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.button)
    Button button;
    @Bind(R.id.tv)
    TextView tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainFoodPresenter.setView(this);

        setSupportActionBar(toolbar);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainFoodPresenter.loadFoodList();
            }
        });


    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected List<Object> getModules() {
        LinkedList<Object> modules = new LinkedList<>();
        modules.add(new MainActivityModule());
        return modules;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showFanArt(String tvShowFanArtUrl) {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void successLoading(List<FoodPar> foodParList) {
        System.out.println("====>MainActivity :: " + foodParList.get(0).getPic());
        tv.setText("name = " + foodParList.get(0).getName() + " :: type = " + foodParList.get(0).getType());
    }
}
