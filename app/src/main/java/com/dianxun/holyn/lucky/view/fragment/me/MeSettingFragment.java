package com.dianxun.holyn.lucky.view.fragment.me;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.dianxun.holyn.lucky.R;
import com.dianxun.holyn.lucky.model.sharedpreferences.UserInfoSP;
import com.dianxun.holyn.lucky.view.activity.MeActivity;
import com.holyn.selectlocalimage.LocalImageVo;
import com.holyn.selectlocalimage.SelectLocalPicActivity;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by holyn on 2015/12/20.
 */
public class MeSettingFragment extends Fragment {
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
    private View rootView;

    public static MeSettingFragment newInstance() {
        return new MeSettingFragment();
    }

    public MeSettingFragment() {
    }

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
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView(){
        ll01.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (UserInfoSP.getSingleInstance(getActivity()).getPassword().equals("")){//还没有登录
                    ((MeActivity) getActivity()).showMeLoginFragment();
                }else{
                    Intent intent = new Intent(getActivity(), SelectLocalPicActivity.class);
                    intent.putExtra(SelectLocalPicActivity.EXTRA_IS_SHOW_CAMERA, true);
                    intent.putExtra(SelectLocalPicActivity.EXTRA_MAX_SELECT, 2);
                    startActivityForResult(intent, 0);
                }
            }
        });

        ll02.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (UserInfoSP.getSingleInstance(getActivity()).getPassword().equals("")){//还没有登录
                    ((MeActivity) getActivity()).showMeLoginFragment();
                }else{

                }
            }
        });

        ll03.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

            }
        });

        ll04.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

            }
        });

        ll05.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

            }
        });

        ll06.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

            }
        });

        ll07.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        System.out.println("====> onActivityResult........");
        if (resultCode == Activity.RESULT_OK){
            List<LocalImageVo> localImageVoList = data.getParcelableArrayListExtra(SelectLocalPicActivity.EXTRA_SELECT_IMAGEVOS);
            for(int i=0; i<localImageVoList.size(); i++){
                LocalImageVo imageVo = localImageVoList.get(i);
                System.out.println("====>("+i+")::"+imageVo.getPath());
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
