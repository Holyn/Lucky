package com.dianxun.holyn.lucky.view.fragment.me;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.dianxun.holyn.lucky.R;
import com.dianxun.holyn.lucky.presenter.base.BasePresenter;
import com.dianxun.holyn.lucky.presenter.base.OnBaseUploadListener;
import com.dianxun.holyn.lucky.view.activity.MeActivity;
import com.dianxun.holyn.lucky.view.fragment.BaseFragment;
import com.holyn.selectlocalimage.LocalImageVo;
import com.holyn.selectlocalimage.SelectLocalPicActivity;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by holyn on 2016/1/14.
 */
public class MeUserInfoFragment extends BaseFragment {
    @Inject
    BasePresenter basePresenter;

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
                uploadImage();
            }
        });

    }

    private void uploadImage() {
        Intent intent = new Intent(getActivity(), SelectLocalPicActivity.class);
        intent.putExtra(SelectLocalPicActivity.EXTRA_IS_SHOW_CAMERA, true);
        intent.putExtra(SelectLocalPicActivity.EXTRA_MAX_SELECT, 1);
        startActivityForResult(intent, 0);
    }

    @Override
    public void onPause() {
        super.onPause();
        ((MeActivity) getActivity()).getTvToolbarSave().setVisibility(View.GONE);
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

                basePresenter.setOnUploadHeaderListener(imageVo.getPath(), new OnBaseUploadListener() {
                    @Override
                    public void onBegin() {

                    }

                    @Override
                    public void onLoading(long total, long current, boolean isUploading) {

                    }

                    @Override
                    public void onSuccess(Object object) {

                    }

                    @Override
                    public void onError(String msg) {

                    }
                });

            }
        }
    }
}
