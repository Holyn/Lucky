package com.dianxun.holyn.lucky.view.fragment.me;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.dianxun.holyn.lucky.R;
import com.dianxun.holyn.lucky.model.http.HttpURL;
import com.dianxun.holyn.lucky.model.parcelable.UserPar;
import com.dianxun.holyn.lucky.model.sharedpreferences.UserInfoSP;
import com.dianxun.holyn.lucky.view.activity.MeActivity;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;

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
    private View rootView = null;

    public static MeMemberCenterFragment newInstance() {
        MeMemberCenterFragment fragment = new MeMemberCenterFragment();
        return fragment;
    }

    public MeMemberCenterFragment() {

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
        if (rootView != null) {
            ViewGroup parent = (ViewGroup) rootView.getParent();
            if (null != parent) {
                parent.removeView(rootView);
            }
            return rootView;
        }
        rootView = inflater.inflate(R.layout.fragment_me_member_center, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (UserInfoSP.getSingleInstance(getActivity()).getPassword().equals("")){//还没有登录
            btnLogin.setText("登 录");
        }else{
            btnLogin.setText("注 销");
            String pic = UserInfoSP.getSingleInstance(getActivity()).getPic();
            showPic(pic);
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((btnLogin.getText().toString().replace(" ", "")).equals("登录")) {
                    ((MeActivity) getActivity()).showMeLoginFragment();
                } else {
                    showExitDialog();
                }

            }
        });
    }

    @Subscribe
    public void onEventSubscribeLogin(UserPar userPar) {
        System.out.println("====> " + userPar.getTel() + "::" + userPar.getPic());
        btnLogin.setText("注 销");
        showPic(userPar.getPic());
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
                btnLogin.setText("登 录");
            }
        });
        builder.create().show();
    }

    private void showPic(String pic){
        if (!pic.equals("0") && !TextUtils.isEmpty(pic)){
            Picasso.with(getActivity())
                    .load(HttpURL.URL_PIC_PRE+HttpURL.USER+pic)
                    .resize(200, 200)
                    .centerCrop()
                    .placeholder(R.drawable.ic_picture_empty)
                    .error(R.drawable.ic_picture_empty)
                    .into(rivHeader);
        }
    }
}
