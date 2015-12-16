package com.dianxun.holyn.lucky.view.fragment.Main;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dianxun.holyn.lucky.view.activity.BaseActivity;
import com.dianxun.holyn.lucky.view.widget.ViewpagerHeaderScroll.tools.ScrollableFragmentListener;
import com.dianxun.holyn.lucky.view.widget.ViewpagerHeaderScroll.tools.ScrollableListener;

import butterknife.ButterKnife;

public abstract class BaseViewPagerFragment extends Fragment implements ScrollableListener {

    protected ScrollableFragmentListener mListener;
    protected static final String BUNDLE_FRAGMENT_INDEX = "BaseFragment.BUNDLE_FRAGMENT_INDEX";
    protected int mFragmentIndex;
    private View rootView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            mFragmentIndex = bundle.getInt(BUNDLE_FRAGMENT_INDEX, 0);
        }

        if (mListener != null) {
            mListener.onFragmentAttached(this, mFragmentIndex);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(getFragmentLayout(), container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        injectViews(view);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (ScrollableFragmentListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(
                    activity.toString() + " must implement ScrollableFragmentListener");
        }
        injectDependencies();
    }

    @Override
    public void onDetach() {
        if (mListener != null) {
            mListener.onFragmentDetached(this, mFragmentIndex);
        }

        super.onDetach();
        mListener = null;
    }

    public View getRootView(){
        return rootView;
    }

    /**
     * Every fragment has to inflate a layout in the onCreateView method. We have added this method to
     * avoid duplicate all the inflate code in every fragment. You only have to return the layout to
     * inflate in this method when extends BaseFragment.
     */
    protected abstract int getFragmentLayout();

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
}
