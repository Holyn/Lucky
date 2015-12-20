package com.dianxun.holyn.lucky.view.fragment.Me;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.dianxun.holyn.lucky.R;
import com.dianxun.holyn.lucky.view.activity.MeActivity;
import com.makeramen.roundedimageview.RoundedImageView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by holyn on 2015/12/19.
 */
public class MeMemberCenterFragment extends Fragment {
    @Bind(R.id.riv_header)
    RoundedImageView rivHeader;
    @Bind(R.id.btn_login)
    Button btnLogin;
    @Bind(R.id.rl_menu_1)//购物车
    RelativeLayout rlMenu1;
    @Bind(R.id.rl_menu_2)//夺宝记录
    RelativeLayout rlMenu2;
    @Bind(R.id.rl_menu_3)//中奖记录
    RelativeLayout rlMenu3;
    @Bind(R.id.rl_menu_4)//消息中心
    RelativeLayout rlMenu4;
    @Bind(R.id.rl_menu_5)//分享
    RelativeLayout rlMenu5;
    @Bind(R.id.rl_menu_6)//充值记录
    RelativeLayout rlMenu6;
    private View rootView;

    public static MeMemberCenterFragment newInstance() {
        MeMemberCenterFragment fragment = new MeMemberCenterFragment();
        return fragment;
    }

    public MeMemberCenterFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MeActivity) getActivity()).setToolBarTitle("会员中心");
        ((MeActivity) getActivity()).setMenuSettingEnable(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_me_member_center, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MeActivity)getActivity()).showMeLoginFragment();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
