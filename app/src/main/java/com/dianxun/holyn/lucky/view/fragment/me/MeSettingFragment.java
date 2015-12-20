package com.dianxun.holyn.lucky.view.fragment.me;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dianxun.holyn.lucky.R;
import com.dianxun.holyn.lucky.view.activity.MeActivity;

/**
 * Created by holyn on 2015/12/20.
 */
public class MeSettingFragment extends Fragment{
    private View rootView;

    public static MeSettingFragment newInstance(){
        return new MeSettingFragment();
    }

    public MeSettingFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MeActivity) getActivity()).setToolBarTitle("设置");
        ((MeActivity) getActivity()).setMenuSettingEnable(false);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_me_setting, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
