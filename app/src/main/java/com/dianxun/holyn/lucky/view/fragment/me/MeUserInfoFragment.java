package com.dianxun.holyn.lucky.view.fragment.me;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.dianxun.holyn.lucky.R;
import com.dianxun.holyn.lucky.model.http.HttpURL;
import com.dianxun.holyn.lucky.model.parcelable.UserPar;
import com.dianxun.holyn.lucky.model.sharedpreferences.UserInfoSP;
import com.dianxun.holyn.lucky.presenter.base.BasePresenter;
import com.dianxun.holyn.lucky.presenter.base.OnBaseGetNetDataListener;
import com.dianxun.holyn.lucky.presenter.base.OnBaseUploadListener;
import com.dianxun.holyn.lucky.view.activity.MeActivity;
import com.dianxun.holyn.lucky.view.fragment.BaseFragment;
import com.holyn.selectlocalimage.LocalImageVo;
import com.holyn.selectlocalimage.SelectLocalPicActivity;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;

/**
 * Created by holyn on 2016/1/14.
 */
public class MeUserInfoFragment extends BaseFragment {
    @Inject
    BasePresenter basePresenter;
    @Bind(R.id.riv_header)
    RoundedImageView rivHeader;
    @Bind(R.id.et_address)
    EditText etAddress;
    @Bind(R.id.tv_id)
    TextView tvId;
    @Bind(R.id.et_name)
    EditText etName;
    @Bind(R.id.et_tel)
    EditText etTel;

    private LocalImageVo localImageVo;

    public static MeUserInfoFragment newInstance() {
        return new MeUserInfoFragment();
    }

    public MeUserInfoFragment() {
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_me_userinfo;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MeActivity) getActivity()).setToolBarTitle("个人信息");
        ((MeActivity) getActivity()).setMenuSettingEnable(false);
        ((MeActivity) getActivity()).getTvToolbarSave().setVisibility(View.VISIBLE);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView() {
        ((MeActivity) getActivity()).getTvToolbarSave().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitSaveInfo();
            }
        });

        String pic = UserInfoSP.getSingleInstance(getActivity()).getPic();
        if (!pic.equals("0") && !TextUtils.isEmpty(pic)) {
            Picasso.with(getActivity()).load(HttpURL.URL_PIC_PRE + HttpURL.USER + pic).into(rivHeader);
        }

        rivHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SelectLocalPicActivity.class);
                intent.putExtra(SelectLocalPicActivity.EXTRA_IS_SHOW_CAMERA, true);
                intent.putExtra(SelectLocalPicActivity.EXTRA_MAX_SELECT, 1);
                startActivityForResult(intent, 0);
            }
        });

        tvId.setText(UserInfoSP.getSingleInstance(getActivity()).getId());
        etName.setText(UserInfoSP.getSingleInstance(getActivity()).getName());
        etTel.setText(UserInfoSP.getSingleInstance(getActivity()).getTel());
        etAddress.setText(UserInfoSP.getSingleInstance(getActivity()).getAddress());

    }

    private void submitSaveInfo() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("确定保存？")
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        uploadImage();
                        dialog.dismiss();
                    }
                }).create().show();

    }

    private void uploadImage() {
        showLoadingDialog();

        basePresenter.setOnUploadHeaderListener(localImageVo.getPath(), new OnBaseUploadListener() {
            @Override
            public void onBegin() {

            }

            @Override
            public void onLoading(long total, long current, boolean isUploading) {

            }

            @Override
            public void onSuccess(Object object) {
                String pic = (String) object;
                updateUserInfo(pic);
            }

            @Override
            public void onError(String msg) {
                closeLoadingDialog();
                toastMsg("保存失败");
            }
        });
    }

    private void updateUserInfo(String pic) {
        UserPar userPar = UserInfoSP.getSingleInstance(getActivity()).getUserPar();
        userPar.setPic(pic);
        userPar.setName(etName.getText().toString().trim());
        userPar.setTel(etTel.getText().toString().trim());
        userPar.setAddress(etAddress.getText().toString().trim());

        basePresenter.setOnUpdateUserInfoListener(userPar, new OnBaseGetNetDataListener() {
            @Override
            public void onBegin() {

            }

            @Override
            public void onSuccess(Object object) {
                UserPar userParNew = (UserPar) object;
                UserInfoSP.getSingleInstance(getActivity()).setUserPar(userParNew);
                closeLoadingDialog();
                toastMsg("保存成功");
                ((MeActivity)getActivity()).onBackPressed();
            }

            @Override
            public void onError(String msg) {
                closeLoadingDialog();
                toastMsg("保存失败");
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        ((MeActivity) getActivity()).getTvToolbarSave().setVisibility(View.GONE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            List<LocalImageVo> localImageVoList = data.getParcelableArrayListExtra(SelectLocalPicActivity.EXTRA_SELECT_IMAGEVOS);
            localImageVo = localImageVoList.get(0);
            Picasso.with(getActivity())
                    .load("file://" + localImageVo.getPath())
                    .resize(100, 100)
                    .centerCrop()
                    .placeholder(R.drawable.ic_picture_empty)
                    .error(R.drawable.ic_picture_empty)
                    .into(rivHeader);
        }
    }
}
