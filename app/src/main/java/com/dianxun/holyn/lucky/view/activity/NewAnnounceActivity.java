package com.dianxun.holyn.lucky.view.activity;

import android.os.Bundle;

import com.dianxun.holyn.lucky.R;
import com.dianxun.holyn.lucky.view.fragment.newannounce.NewAnnounceFragment;

/**
 * Created by holyn on 2015/12/27.
 */
public class NewAnnounceActivity extends BaseToolBarActivity {
    private NewAnnounceFragment newAnnounceFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showNewAnnounceFragment();
    }

    private void showNewAnnounceFragment() {
        if (newAnnounceFragment == null) {
            newAnnounceFragment = NewAnnounceFragment.newInstance();
        }
        getBaseFragmentManager().beginTransaction().replace(R.id.fl_container, newAnnounceFragment).commit();
    }
}
