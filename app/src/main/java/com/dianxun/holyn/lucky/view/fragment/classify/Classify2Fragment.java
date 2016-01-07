package com.dianxun.holyn.lucky.view.fragment.classify;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dianxun.holyn.lucky.R;
import com.dianxun.holyn.lucky.view.activity.BaseToolBarActivity;

/**
 * Created by holyn on 2016/1/7.
 */
public class Classify2Fragment extends Fragment {

    private View rootView;

    public static Classify2Fragment newInstance() {
        return new Classify2Fragment();
    }

    public Classify2Fragment() {

    }

    @Override
    public void onResume() {
        super.onResume();
        ((BaseToolBarActivity) getActivity()).setToolBarTitle("分类浏览");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        if (rootView != null) {
            ViewGroup parent = (ViewGroup) rootView.getParent();
            if (null != parent) {
                parent.removeView(rootView);
            }
            return rootView;
        }
        rootView = inflater.inflate(R.layout.fragment_classify_2, container, false);
        return rootView;
    }
}
