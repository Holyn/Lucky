package com.dianxun.holyn.lucky.view.activity;

import android.os.Bundle;

import com.dianxun.holyn.lucky.R;
import com.dianxun.holyn.lucky.view.fragment.order.OrderFragment;

/**
 * Created by holyn on 2015/12/27.
 */
public class OrderActivity extends BaseToolBarActivity {
    private OrderFragment orderFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showNewAnnounceFragment();
    }

    private void showNewAnnounceFragment() {
        if (orderFragment == null) {
            orderFragment = OrderFragment.newInstance();
        }
        getBaseFragmentManager().beginTransaction().replace(R.id.fl_container, orderFragment).commit();
    }
}
