package com.dianxun.holyn.lucky.view.activity;

import android.os.Bundle;

import com.dianxun.holyn.lucky.R;
import com.dianxun.holyn.lucky.view.fragment.classify.Classify2Fragment;

/**
 * Created by holyn on 2016/1/7.
 */
public class ClassifyActivity2 extends BaseToolBarActivity{
    private Classify2Fragment classifyFragment2 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showClassifyFragment2();
    }

    private void showClassifyFragment2(){
        if (classifyFragment2 == null) {
            classifyFragment2 = Classify2Fragment.newInstance();
        }
        getBaseFragmentManager().beginTransaction().replace(R.id.fl_container, classifyFragment2).commit();

    }
}
