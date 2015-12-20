package com.dianxun.holyn.lucky.view.fragment.classify;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.dianxun.holyn.lucky.R;
import com.dianxun.holyn.lucky.view.activity.ClassifyActivity;
import com.dianxun.holyn.lucky.view.activity.ClassifyContentListActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by holyn on 2015/12/20.
 */
public class ClassifyFragment extends Fragment {
    @Bind(R.id.ll_01)
    LinearLayout ll01;
    @Bind(R.id.ll_02)
    LinearLayout ll02;
    @Bind(R.id.ll_03)
    LinearLayout ll03;
    @Bind(R.id.ll_04)
    LinearLayout ll04;
    @Bind(R.id.ll_05)
    LinearLayout ll05;
    @Bind(R.id.ll_06)
    LinearLayout ll06;
    @Bind(R.id.ll_07)
    LinearLayout ll07;
    @Bind(R.id.ll_08)
    LinearLayout ll08;
    @Bind(R.id.ll_09)
    LinearLayout ll09;
    private View rootView;

    public static ClassifyFragment newInstance() {
        return new ClassifyFragment();
    }

    public ClassifyFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((ClassifyActivity) getActivity()).setToolBarTitle("分类浏览");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_classify, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ll01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ClassifyContentListActivity.class));
            }
        });
        ll02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ClassifyContentListActivity.class));
            }
        });
        ll03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ClassifyContentListActivity.class));
            }
        });
        ll04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ClassifyContentListActivity.class));
            }
        });
        ll05.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ClassifyContentListActivity.class));
            }
        });
        ll06.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ClassifyContentListActivity.class));
            }
        });
        ll07.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ClassifyContentListActivity.class));
            }
        });
        ll08.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ClassifyContentListActivity.class));
            }
        });
        ll09.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ClassifyContentListActivity.class));
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
