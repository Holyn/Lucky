package com.dianxun.holyn.lucky.view.widget.ViewpagerHeaderScroll.delegate;

import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by holyn on 2015/12/17.
 */
public class RecycleViewDelegate implements ViewDelegate{

    private final int[] mViewLocationResult = new int[2];
    private final Rect mRect = new Rect();

    public boolean isViewBeingDragged(MotionEvent event, RecyclerView view) {

        if (view.getAdapter() == null || view.getAdapter().getItemCount() == 0) {
            return true;
        }
        view.getLocationOnScreen(mViewLocationResult);
        final int viewLeft = mViewLocationResult[0], viewTop = mViewLocationResult[1];
        mRect.set(viewLeft, viewTop, viewLeft + view.getWidth(), viewTop + view.getHeight());
        final int rawX = (int) event.getRawX(), rawY = (int) event.getRawY();

        if (mRect.contains(rawX, rawY)) {
            return isReadyForPull(view, rawX - mRect.left, rawY - mRect.top);
        }

        return false;
    }

    @Override
    public boolean isReadyForPull(View view, float x, float y) {
        boolean ready = false;

        // First we check whether we're scrolled to the top
        RecyclerView recyclerView = (RecyclerView) view;

        if (recyclerView.getAdapter().getItemCount() == 0) {
            ready = true;
        }
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof LinearLayoutManager){
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager)layoutManager;
            if (linearLayoutManager.findFirstCompletelyVisibleItemPosition() == 0){
                final View firstVisibleChild = recyclerView.getChildAt(0);
                ready = firstVisibleChild != null
                        && firstVisibleChild.getTop() >= recyclerView.getPaddingTop();
            }
        }else if (layoutManager instanceof GridLayoutManager){
            GridLayoutManager gridLayoutManager = (GridLayoutManager)layoutManager;
            final View firstVisibleChild = recyclerView.getChildAt(0);
            ready = firstVisibleChild != null
                    && firstVisibleChild.getTop() >= recyclerView.getPaddingTop();
        }

        return ready;
    }
}
