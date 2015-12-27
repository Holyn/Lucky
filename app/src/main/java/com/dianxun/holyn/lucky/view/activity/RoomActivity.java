package com.dianxun.holyn.lucky.view.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dianxun.holyn.lucky.R;
import com.dianxun.holyn.lucky.view.fragment.room.RoomFragment;
import com.dianxun.holyn.lucky.view.module.RoomActivityModule;

import java.util.LinkedList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by holyn on 2015/12/27.
 */
public class RoomActivity extends BaseActivity {


    @Bind(R.id.et_search)
    EditText etSearch;
    @Bind(R.id.tv_search_btn)
    TextView tvSearchBtn;
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    private FragmentManager fragmentManager;
    private RoomFragment roomFragment;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_room;
    }

    @Override
    protected List<Object> getModules() {
        LinkedList<Object> modules = new LinkedList<>();
        modules.add(new RoomActivityModule());
        return modules;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initToolBar();
        fragmentManager = getSupportFragmentManager();
        showRoomFragment();
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

        tvSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RoomActivity.this, "点击搜索",Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void showRoomFragment(){
        if (roomFragment == null){
            roomFragment = RoomFragment.newInstance();
        }
        fragmentManager.beginTransaction().replace(R.id.fl_container, roomFragment).commit();
    }

//    public void setToolBarTitle(String title) {
//        tvToolbarTitle.setText(title);
//    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
