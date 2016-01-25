package com.dianxun.holyn.lucky.view.fragment.me;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dianxun.holyn.lucky.R;
import com.dianxun.holyn.lucky.model.parcelable.UserPar;
import com.dianxun.holyn.lucky.model.sharedpreferences.UserInfoSP;
import com.dianxun.holyn.lucky.view.activity.MeActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;

/**
 * Created by holyn on 2015/12/20.
 */
public class MeSettingFragment extends Fragment implements View.OnClickListener{
    @Bind(R.id.ll_01)
    LinearLayout ll01;//个人资料
    @Bind(R.id.ll_02)
    LinearLayout ll02;//我的订单
    @Bind(R.id.ll_03)
    LinearLayout ll03;//我的钱包
    @Bind(R.id.ll_04)
    LinearLayout ll04;//充值中心
    @Bind(R.id.ll_05)
    LinearLayout ll05;//我的评价
    @Bind(R.id.ll_06)
    LinearLayout ll06;//修改密码
    @Bind(R.id.ll_07)
    LinearLayout ll07;//用户登录
    @Bind(R.id.tv_login)
    TextView tvLogin;
    private View rootView;

    private UserPar userPar;

    public static MeSettingFragment newInstance() {
        return new MeSettingFragment();
    }

    public MeSettingFragment() {
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDetach() {
        EventBus.getDefault().unregister(this);
        ButterKnife.unbind(this);
        super.onDetach();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userPar = UserInfoSP.getSingleInstance(getActivity()).getUserPar();
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
        if (rootView != null) {
            ViewGroup parent = (ViewGroup) rootView.getParent();
            if (null != parent) {
                parent.removeView(rootView);
            }
            ButterKnife.bind(this, rootView);
            return rootView;
        }
        rootView = inflater.inflate(R.layout.fragment_me_setting, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView() {
        setTvLoginText(userPar);

        ll01.setOnClickListener(this);
        ll02.setOnClickListener(this);
        ll03.setOnClickListener(this);
        ll04.setOnClickListener(this);
        ll05.setOnClickListener(this);
        ll06.setOnClickListener(this);
        ll07.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (userPar.getPassword().equals("")) {//还没有登录
            ((MeActivity) getActivity()).showMeLoginFragment();
        } else {
            switch (v.getId()){
                case R.id.ll_01:
                    ((MeActivity) getActivity()).showMeUserInfoFragment();
                    break;
                case R.id.ll_02:
                    ((MeActivity) getActivity()).showMeOrderListFragment(userPar.getId());
                    break;
                case R.id.ll_03:
                    break;
                case R.id.ll_04:
                    break;
                case R.id.ll_05:
                    break;
                case R.id.ll_06:
                    break;
                case R.id.ll_07:
                    showExitDialog();
                    break;
            }
        }
    }

    @Subscribe
    public void onEventSubscribeLogin(UserPar userPar) {
        System.out.println("====> " + userPar.getTel() + "::" + userPar.getPic());

        setTvLoginText(userPar);
        this.userPar = userPar;
    }

    private void setTvLoginText(UserPar userPar){
        if (userPar.getPassword().equals("")) {//还没有登录
            tvLogin.setText("用户登录");
        } else {
            tvLogin.setText("用户注销");
        }
    }

    private void showExitDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("退出登录");
        builder.setMessage("确定要退出当前的用户吗？");
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setPositiveButton("退出登录", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                UserInfoSP.getSingleInstance(getActivity()).setPassword("");
                UserInfoSP.getSingleInstance(getActivity()).setPic("");
                userPar.setPassword("");
                userPar.setPic("");
                tvLogin.setText("用户登录");
            }
        });
        builder.create().show();
    }
}
