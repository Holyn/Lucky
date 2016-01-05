package com.dianxun.holyn.lucky.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.dianxun.holyn.lucky.R;
import com.dianxun.holyn.lucky.view.fragment.main.MainFragment2;

/**
 * Created by holyn on 2016/1/5.
 */
public class MainActivity2 extends BaseToolBarActivity{
    private MainFragment2 mainFragment2 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getBaseToolbar().setNavigationIcon(R.mipmap.ic_search_api_holo_dark);
        getBaseToolbar().setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity2.this, MeActivity.class));
            }
        });
        getBaseToolbar().setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.action_more) {
                    startActivity(new Intent(MainActivity2.this, MeActivity.class));
                }
                return true;
            }
        });

        showMainFragment2();
    }

    private void showMainFragment2(){
        if(mainFragment2 == null){
            mainFragment2 = MainFragment2.newInstance();
        }
        getBaseFragmentManager().beginTransaction().replace(R.id.fl_container, mainFragment2).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
