package com.dianxun.holyn.lucky.view.fragment.me;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.dianxun.holyn.lucky.R;
import com.dianxun.holyn.lucky.model.http.HttpURL;
import com.dianxun.holyn.lucky.model.parcelable.UserPar;
import com.dianxun.holyn.lucky.model.sharedpreferences.UserInfoSP;
import com.dianxun.holyn.lucky.presenter.me.MeLoginPresenter;
import com.dianxun.holyn.lucky.view.activity.MeActivity;
import com.dianxun.holyn.lucky.view.fragment.BaseFragment;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

/**
 * Created by holyn on 2015/12/20.
 */
public class MeLoginFragment extends BaseFragment implements MeLoginPresenter.UniqueViewInterface {
    @Inject
    MeLoginPresenter meLoginPresenter;

    @Bind(R.id.et_acount)
    EditText etAcount;
    @Bind(R.id.et_password)
    EditText etPassword;
    @Bind(R.id.btn_submit)
    Button btnSubmit;
    @Bind(R.id.tv_register)
    TextView tvRegister;
    @Bind(R.id.iv_header)
    ImageView ivHeader;

    public static MeLoginFragment newInstance() {
        return new MeLoginFragment();
    }

    public MeLoginFragment() {
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_me_login;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MeActivity) getActivity()).setToolBarTitle("用户登录");
        ((MeActivity) getActivity()).setMenuSettingEnable(false);
        meLoginPresenter.resume();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        meLoginPresenter.setUniqueViewInterface(this);
        meLoginPresenter.initialize();

        initView();
    }

    private void initView(){

        etAcount.setText(UserInfoSP.getSingleInstance(getActivity()).getTel());
        if (UserInfoSP.getSingleInstance(getActivity()).getPassword().equals("")){//还没有登录
            etPassword.setText("");
        }else{
            etPassword.setText(UserInfoSP.getSingleInstance(getActivity()).getPassword());
        }

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitLogin();
            }
        });

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MeActivity)getActivity()).showMeRegisterFragment();
            }
        });

    }

    private void submitLogin(){
        String acount = etAcount.getText().toString().trim();
        if (TextUtils.isEmpty(acount)) {
            toastMsg("用户名为空");
            return;
        }

        String password = etPassword.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            toastMsg("密码为空");
            return;
        }

        meLoginPresenter.doLogin(acount, password);
    }

    @Override
    public void loginSuccess(UserPar userPar) {
        EventBus.getDefault().post(userPar);
        UserInfoSP.getSingleInstance(getActivity()).setUserPar(userPar);
        closeLoadingDialog();
        toastMsg("登录成功");
        (MeLoginFragment.this.getActivity()).onBackPressed();;

    }

    @Override
    public void loginBegin() {
        showLoadingDialog("正在登录....");
    }

    @Override
    public void loginError(String msg) {
        closeLoadingDialog();
        toastMsg(msg);
    }

    @Override
    public void onPause() {
        super.onPause();
        meLoginPresenter.pause();
    }
}
