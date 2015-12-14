package com.dianxun.holyn.lucky.view.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.util.SparseArrayCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.LinearLayout;

import com.dianxun.holyn.lucky.R;
import com.dianxun.holyn.lucky.model.parcelable.FoodPar;
import com.dianxun.holyn.lucky.presenter.mainactivity.MainFoodPresenter;
import com.dianxun.holyn.lucky.view.fragment.ListViewFragment;
import com.dianxun.holyn.lucky.view.module.MainActivityModule;
import com.dianxun.holyn.lucky.view.widget.ViewpagerHeaderScroll.SlidingTabLayout;
import com.dianxun.holyn.lucky.view.widget.ViewpagerHeaderScroll.TouchCallbackLayout;
import com.dianxun.holyn.lucky.view.widget.ViewpagerHeaderScroll.tools.ScrollableFragmentListener;
import com.dianxun.holyn.lucky.view.widget.ViewpagerHeaderScroll.tools.ScrollableListener;
import com.dianxun.holyn.lucky.view.widget.ViewpagerHeaderScroll.tools.ViewPagerHeaderHelper;

import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;

public class MainActivity extends BaseActivity implements MainFoodPresenter.View, TouchCallbackLayout.TouchEventListener, ScrollableFragmentListener,
        ViewPagerHeaderHelper.OnViewPagerTouchListener{

    @Inject
    MainFoodPresenter mainFoodPresenter;

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.viewpager)
    ViewPager viewpager;
    @Bind(R.id.viewpager_ad)
    ViewPager viewpagerAd;
    @Bind(R.id.tabs)
    SlidingTabLayout slidingTabLayout;
    @Bind(R.id.header)
    LinearLayout mHeaderLayoutView;
    @Bind(R.id.layout_header_scroll)
    TouchCallbackLayout layoutHeaderScroll;

    private static final long DEFAULT_DURATION = 300L;//持续时间
    private static final float DEFAULT_DAMPING = 1.5f;//减幅

    private SparseArrayCompat<ScrollableListener> mScrollableListenerArrays =
            new SparseArrayCompat<>();

    private ViewPagerHeaderHelper mViewPagerHeaderHelper;

    private int mTouchSlop;
    private int mTabHeight;
    private int mHeaderHeight;

    private Interpolator mInterpolator = new DecelerateInterpolator();

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initToolBar();

//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mainFoodPresenter.loadFoodList();
//            }
//        });


        mTouchSlop = ViewConfiguration.get(this).getScaledTouchSlop();
        mTabHeight = getResources().getDimensionPixelSize(R.dimen.tabs_height);
        mHeaderHeight = getResources().getDimensionPixelSize(R.dimen.viewpager_header_height);

        mViewPagerHeaderHelper = new ViewPagerHeaderHelper(this, this);

        layoutHeaderScroll.setTouchEventListener(this);

//        mHeaderLayoutView = findViewById(R.id.header);

//        SlidingTabLayout slidingTabLayout = (SlidingTabLayout) findViewById(R.id.tabs);
        //slidingTabLayout.setDistributeEvenly(true);

        viewpager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));

        slidingTabLayout.setViewPager(viewpager);

        ViewCompat.setTranslationY(viewpager, mHeaderHeight);

    }

    @Override
    protected List<Object> getModules() {
        LinkedList<Object> modules = new LinkedList<>();
        modules.add(new MainActivityModule());
        return modules;
    }

    private void initToolBar() {
        mainFoodPresenter.setView(this);

//        toolbar.setTitle("holyn");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        toolbar.setNavigationIcon(R.drawable.ic_menu_search_mtrl_alpha);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.action_more) {
                    System.out.println("====》 点击action_more");
                }
                return true;
            }
        });
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showFanArt(String tvShowFanArtUrl) {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void successLoading(List<FoodPar> foodParList) {
        System.out.println("====>MainActivity :: " + foodParList.get(0).getPic());
    }

    @Override
    public boolean onLayoutInterceptTouchEvent(MotionEvent event) {
        return mViewPagerHeaderHelper.onLayoutInterceptTouchEvent(event,
                mTabHeight + mHeaderHeight);
    }

    @Override
    public boolean onLayoutTouchEvent(MotionEvent event) {
        return mViewPagerHeaderHelper.onLayoutTouchEvent(event);
    }

    @Override
    public boolean isViewBeingDragged(MotionEvent event) {
//        System.out.println("====> viewpager = "+viewpager);
//        System.out.println("====> mScrollableListenerArrays = "+mScrollableListenerArrays);
        System.out.println("====> mScrollableListenerArrays.size() = "+mScrollableListenerArrays.size());
        System.out.println("====> viewpager.getCurrentItem() = "+viewpager.getCurrentItem());
        System.out.println("====> mScrollableListenerArrays.valueAt(viewpager.getCurrentItem()) = "+mScrollableListenerArrays.valueAt(viewpager.getCurrentItem()));

        return mScrollableListenerArrays.valueAt(viewpager.getCurrentItem()).isViewBeingDragged(event);
    }

    @Override
    public void onMoveStarted(float eventY) {

    }

    @Override
    public void onMove(float eventY, float yDx) {
        float headerTranslationY = ViewCompat.getTranslationY(mHeaderLayoutView) + yDx;
        if (headerTranslationY >= 0) { // pull end
            headerExpand(0L);
        } else if (headerTranslationY <= -mHeaderHeight) { // push end
            headerFold(0L);
        } else {
            ViewCompat.animate(mHeaderLayoutView)
                    .translationY(headerTranslationY)
                    .setDuration(0)
                    .start();
            ViewCompat.animate(viewpager)
                    .translationY(headerTranslationY + mHeaderHeight)
                    .setDuration(0)
                    .start();
        }
    }

    @Override
    public void onMoveEnded(boolean isFling, float flingVelocityY) {

        float headerY = ViewCompat.getTranslationY(mHeaderLayoutView); // 0到负数
        if (headerY == 0 || headerY == -mHeaderHeight) {
            return;
        }

        if (mViewPagerHeaderHelper.getInitialMotionY() - mViewPagerHeaderHelper.getLastMotionY()
                < -mTouchSlop) {  // pull > mTouchSlop = expand
            headerExpand(headerMoveDuration(true, headerY, isFling, flingVelocityY));
        } else if (mViewPagerHeaderHelper.getInitialMotionY()
                - mViewPagerHeaderHelper.getLastMotionY()
                > mTouchSlop) { // push > mTouchSlop = fold
            headerFold(headerMoveDuration(false, headerY, isFling, flingVelocityY));
        } else {
            if (headerY > -mHeaderHeight / 2f) {  // headerY > header/2 = expand
                headerExpand(headerMoveDuration(true, headerY, isFling, flingVelocityY));
            } else { // headerY < header/2= fold
                headerFold(headerMoveDuration(false, headerY, isFling, flingVelocityY));
            }
        }
    }

    private long headerMoveDuration(boolean isExpand, float currentHeaderY, boolean isFling,
                                    float velocityY) {

        long defaultDuration = DEFAULT_DURATION;

        if (isFling) {

            float distance = isExpand ? Math.abs(mHeaderHeight) - Math.abs(currentHeaderY)
                    : Math.abs(currentHeaderY);
            velocityY = Math.abs(velocityY) / 1000;

            defaultDuration = (long) (distance / velocityY * DEFAULT_DAMPING);

            defaultDuration = defaultDuration > DEFAULT_DURATION ? DEFAULT_DURATION : defaultDuration;
        }

        return defaultDuration;
    }

    private void headerFold(long duration) {
        ViewCompat.animate(mHeaderLayoutView)
                .translationY(-mHeaderHeight)
                .setDuration(duration)
                .setInterpolator(mInterpolator)
                .start();

        ViewCompat.animate(viewpager).translationY(0).
                setDuration(duration).setInterpolator(mInterpolator).start();

        mViewPagerHeaderHelper.setHeaderExpand(false);
    }

    private void headerExpand(long duration) {
        ViewCompat.animate(mHeaderLayoutView)
                .translationY(0)
                .setDuration(duration)
                .setInterpolator(mInterpolator)
                .start();

        ViewCompat.animate(viewpager)
                .translationY(mHeaderHeight)
                .setDuration(duration)
                .setInterpolator(mInterpolator)
                .start();
        mViewPagerHeaderHelper.setHeaderExpand(true);
    }


    @Override
    public void onFragmentAttached(ScrollableListener listener, int position) {
        mScrollableListenerArrays.put(position, listener);
    }

    @Override
    public void onFragmentDetached(ScrollableListener fragment, int position) {
        mScrollableListenerArrays.remove(position);
    }

    private class ViewPagerAdapter extends FragmentPagerAdapter {

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

//            if (position == 3) {
//                return ScrollViewFragment.newInstance(position);
//            }
            return ListViewFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return getString(R.string.tab_country);
                case 1:
                    return getString(R.string.tab_continent);
                case 2:
                    return getString(R.string.tab_city);
                case 3:
                    return getString(R.string.tab_scroll_view);
            }

            return "";
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
