/*
 * Copyright (C) 2014 Pedro Vicente Gómez Sánchez.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.dianxun.holyn.lucky.view.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.dianxun.holyn.lucky.view.activity.BaseActivity;
import com.dianxun.holyn.lucky.view.widget.dialog.LoadingDialog;

import butterknife.ButterKnife;

/**
 * Base fragment created to be extended by every fragment in this application. This class provides
 * dependency injection configuration, ButterKnife Android library configuration and some methods
 * common to every fragment.
 *
 */
public abstract class BaseFragment extends Fragment {

  private View rootView;
  public LoadingDialog loadingDialog = null;

  @Override public void onAttach(Activity activity) {
    super.onAttach(activity);
    injectDependencies();
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    rootView = inflater.inflate(getFragmentLayout(), container, false);
    return rootView;
  }

  @Override public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    injectViews(view);
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    ButterKnife.unbind(this);
  }

  /**
   * Every fragment has to inflate a layout in the onCreateView method. We have added this method to
   * avoid duplicate all the inflate code in every fragment. You only have to return the layout to
   * inflate in this method when extends BaseFragment.
   */
  protected abstract int getFragmentLayout();

  public View getRootView(){
    return rootView;
  }

  /**
   * Replace every field annotated using @Inject annotation with the provided dependency specified
   * inside a Dagger module value.
   */
  private void injectDependencies() {
    ((BaseActivity) getActivity()).inject(this);
  }

  /**
   * Replace every field annotated with ButterKnife annotations like @InjectView with the proper
   * value.
   *
   * @param view to extract each widget injected in the fragment.
   */
  private void injectViews(final View view) {
    ButterKnife.bind(this, view);
  }

  protected void showLoadingDialog() {
    if (loadingDialog == null) {
      loadingDialog = new LoadingDialog(getActivity(), null);
    }
    loadingDialog.show();
  }

  protected void showLoadingDialog(String msg) {
    if (loadingDialog == null) {
      loadingDialog = new LoadingDialog(getActivity(), msg);
    }else {
      loadingDialog.setMessage(msg);
    }
    loadingDialog.show();
  }

  protected void closeLoadingDialog() {
    if (loadingDialog != null) {
      if (loadingDialog.isShowing()) {
        loadingDialog.cancel();
        loadingDialog = null;
      }
    }
  }

  protected void toastMsg(String msg) {
    if (getActivity() != null) {
      Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }
  }
}
