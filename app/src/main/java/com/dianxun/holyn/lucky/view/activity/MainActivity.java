package com.dianxun.holyn.lucky.view.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.util.SparseArrayCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.dianxun.holyn.lucky.R;
import com.dianxun.holyn.lucky.model.parcelable.VoPicPar;
import com.dianxun.holyn.lucky.view.fragment.Main.MainRVGridFragment;
import com.dianxun.holyn.lucky.view.module.MainActivityModule;
import com.dianxun.holyn.lucky.view.widget.ViewpagerHeaderScroll.SlidingTabLayout;
import com.dianxun.holyn.lucky.view.widget.ViewpagerHeaderScroll.TouchCallbackLayout;
import com.dianxun.holyn.lucky.view.widget.ViewpagerHeaderScroll.tools.ScrollableFragmentListener;
import com.dianxun.holyn.lucky.view.widget.ViewpagerHeaderScroll.tools.ScrollableListener;
import com.dianxun.holyn.lucky.view.widget.ViewpagerHeaderScroll.tools.ViewPagerHeaderHelper;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;

public class MainActivity extends BaseActivity implements TouchCallbackLayout.TouchEventListener, ScrollableFragmentListener,
        ViewPagerHeaderHelper.OnViewPagerTouchListener {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.viewpager)
    ViewPager viewpager;
    @Bind(R.id.tabs)
    SlidingTabLayout slidingTabLayout;
    @Bind(R.id.header)
    LinearLayout mHeaderLayoutView;
    @Bind(R.id.layout_header_scroll)
    TouchCallbackLayout layoutHeaderScroll;

    private static final long DEFAULT_DURATION = 300L;//持续时间
    private static final float DEFAULT_DAMPING = 1.5f;//减幅

    @Bind(R.id.viewpager_ad)
    ViewPager viewpagerAd;
    @Bind(R.id.rb_ad_0)
    RadioButton rbAd0;
    @Bind(R.id.rb_ad_1)
    RadioButton rbAd1;
    @Bind(R.id.rb_ad_2)
    RadioButton rbAd2;
    @Bind(R.id.rb_ad_3)
    RadioButton rbAd3;
    @Bind(R.id.tv_title)
    TextView tvTitle;//广告标题


    /* 滚动图片  开始 */
    private BaseAdAdapter pagerAdapter;
    private List<VoPicPar> picParList = new ArrayList<VoPicPar>();
    private ArrayList<View> views = new ArrayList<View>();
    private List<ImageView> imageViews = new ArrayList<ImageView>(4);//广告图片显示组件
    private ScheduledExecutorService scheduledExecutorService;// 控制viewpager自动滑动
    private int currentItem = 0; // 当前图片的索引号
    /* 滚动图片  结束 */

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
//        slidingTabLayout.setDistributeEvenly(true);

        viewpager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
        slidingTabLayout.setViewPager(viewpager);


        ViewCompat.setTranslationY(viewpager, mHeaderHeight);

        initViewpagerAD();
        getADPicList();

    }

    @Override
    protected List<Object> getModules() {
        LinkedList<Object> modules = new LinkedList<>();
        modules.add(new MainActivityModule());
        return modules;
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        // 启动循环线程
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        // 每两秒钟切换一次图片显示
        scheduledExecutorService.scheduleAtFixedRate(new ScrollTask(), 1, 3, TimeUnit.SECONDS);
    }

    @Override
    protected void onPause() {
        super.onPause();
        scheduledExecutorService.shutdown();// 停止循环线程
    }

    private void initToolBar() {
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

    private void initViewpagerAD() {
        for (int i = 0; i < 4; i++) {
            View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.imageview_ad, null);
            ImageView imageView = (ImageView) view.findViewById(R.id.iv_ad);
            imageViews.add(imageView);
            views.add(view);
        }

        pagerAdapter = new BaseAdAdapter(views);
        viewpagerAd.setAdapter(pagerAdapter);
        viewpagerAd.setOnPageChangeListener(new MyOnPageChangeListener());
    }

    private void getADPicList(){
        for (int i = 0; i < 4; i++){
            if (i == 0){
                Picasso.with(MainActivity.this).load(R.mipmap.iv_test_ad).into(imageViews.get(0));
            }else if (i == 1){
                Picasso.with(MainActivity.this).load(R.mipmap.iv_test_ad).into(imageViews.get(1));
            }else if (i == 2){
                Picasso.with(MainActivity.this).load(R.mipmap.iv_test_ad).into(imageViews.get(2));
            }else if (i == 3){
                Picasso.with(MainActivity.this).load(R.mipmap.iv_test_ad).into(imageViews.get(3));
            }
        }
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

    /** 商品页面切换viewpager适配器 */
    private class ViewPagerAdapter extends FragmentPagerAdapter {

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return MainRVGridFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return getString(R.string.tab_popularity);
                case 1:
                    return getString(R.string.tab_newest);
                case 2:
                    return getString(R.string.tab_progress);
                case 3:
                    return getString(R.string.tab_total_demand);
            }

            return "";
        }
    }

    /**
     * 换行图片切换任务
     */
    private class ScrollTask implements Runnable {
        public void run() {
            synchronized (viewpagerAd) {
                currentItem = (currentItem + 1) % views.size();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        viewpagerAd.setCurrentItem(currentItem);// 切换当前显示的图片

                        if (currentItem < picParList.size()) {
                            String title = picParList.get(currentItem).getName().toString();
                            if (!TextUtils.isEmpty(title)) {
                                tvTitle.setText(title);
                            }
                        } else {
                            tvTitle.setText("");
                        }
                    }
                });
            }
        }
    }

    private class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        public void onPageSelected(int arg0) {
            currentItem = arg0;
            switch (arg0) {
                case 0:
                    rbAd0.setChecked(true);
                    rbAd1.setChecked(false);
                    rbAd2.setChecked(false);
                    rbAd3.setChecked(false);
                    break;
                case 1:
                    rbAd0.setChecked(false);
                    rbAd1.setChecked(true);
                    rbAd2.setChecked(false);
                    rbAd3.setChecked(false);
                    break;
                case 2:
                    rbAd0.setChecked(false);
                    rbAd1.setChecked(false);
                    rbAd2.setChecked(true);
                    rbAd3.setChecked(false);
                    break;
                case 3:
                    rbAd0.setChecked(false);
                    rbAd1.setChecked(false);
                    rbAd2.setChecked(false);
                    rbAd3.setChecked(true);
                    break;
            }
        }

        @Override
        public void onPageScrollStateChanged(int arg0) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
            // TODO Auto-generated method stub

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
