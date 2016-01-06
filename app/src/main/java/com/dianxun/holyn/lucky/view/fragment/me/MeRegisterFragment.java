package com.dianxun.holyn.lucky.view.fragment.me;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.dianxun.holyn.lucky.R;
import com.dianxun.holyn.lucky.presenter.me.MeRegisterPresenter;
import com.dianxun.holyn.lucky.utils.MatcherUtil;
import com.dianxun.holyn.lucky.view.activity.MeActivity;
import com.dianxun.holyn.lucky.view.fragment.BaseFragment;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

/**
 * Created by holyn on 2016/1/4.
 */
public class MeRegisterFragment extends BaseFragment implements MeRegisterPresenter.UniqueViewInterface {
    @Inject
    MeRegisterPresenter meRegisterPresenter;
    @Bind(R.id.et_tel)
    EditText etTel;
    @Bind(R.id.et_code)
    EditText etCode;
    @Bind(R.id.btn_get_code)
    Button btnGetCode;
    @Bind(R.id.et_name)
    EditText etName;
    @Bind(R.id.et_password)
    EditText etPassword;
    @Bind(R.id.btn_register)
    Button btnRegister;
    @Bind(R.id.tv_go_login)
    TextView tvGoLogin;

    private String code = null;
    private int readSecond = 60;
    private boolean isGetCodeError = false;//获取验证码的时候是否发生错误

    public static MeRegisterFragment newInstance() {
        return new MeRegisterFragment();
    }

    public MeRegisterFragment() {
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_me_register;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDetach() {
        EventBus.getDefault().unregister(this);
        super.onDetach();
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MeActivity) getActivity()).setToolBarTitle("用户注册");
        ((MeActivity) getActivity()).setMenuSettingEnable(false);
        meRegisterPresenter.resume();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        meRegisterPresenter.setUniqueViewInterface(this);
        meRegisterPresenter.initialize();

        initView();
    }

    private void initView() {

        btnGetCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCode();
                read60Second();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doReg();
            }
        });

        tvGoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
    }

    private void getCode() {
        String tel = etTel.getText().toString().trim();
        if (TextUtils.isEmpty(tel)) {
            toastMsg("电话号码为空");
            return;
        }else{
            if (!MatcherUtil.isMobileNO(tel)){
                toastMsg("电话号码格式不正确");
                return;
            }
        }
        meRegisterPresenter.getCode(tel);
    }

    private void doReg() {
        String tel = etTel.getText().toString().trim();
        if (TextUtils.isEmpty(tel)) {
            toastMsg("电话号码为空");
            return;
        }

        String code = etCode.getText().toString().trim();
        if (TextUtils.isEmpty(code)) {
            toastMsg("验证码为空");
            return;
        }else if(!code.equals(this.code)){
            toastMsg("验证码不正确");
            return;
        }

        String name = etCode.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            toastMsg("真实名字为空");
            return;
        }

        String password = etPassword.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            toastMsg("密码为空");
            return;
        }

        meRegisterPresenter.doReg(tel, password, name);

    }

    @Override
    public void doRegBegin() {
        showLoadingDialog("正在注册....");
    }

    @Override
    public void doRegError(String msg) {
        closeLoadingDialog();
        toastMsg(msg);
    }

    @Override
    public void doRegSuccess(String msg) {
        closeLoadingDialog();
        toastMsg(msg);
        etCode.setText("");
        etName.setText("");
        etPassword.setText("");
        getActivity().onBackPressed();
    }

    @Override
    public void getCodeBegin() {
        isGetCodeError = false;
    }

    @Override
    public void getCodeError(String msg) {
        isGetCodeError = true;
        toastMsg(msg);
    }

    @Override
    public void getCodeSuccess(String code) {
        isGetCodeError = false;
        System.out.println("====> code = "+code);
        this.code = code;
    }

    private void read60Second(){
        btnGetCode.setClickable(false);
        btnGetCode.setText((readSecond) + "秒后重试");
        readSecond--;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                btnGetCode.setText((readSecond) + "秒后重试");
                readSecond--;
                if (readSecond == 0 || isGetCodeError == true) {
                    btnGetCode.setClickable(true);
                    btnGetCode.setText("获取验证码");
                    readSecond = 60;
                }else{
                    read60Second();
                }
            }
        }, 1000);
    }

    @Override
    public void onPause() {
        super.onPause();
        meRegisterPresenter.pause();
    }

}
