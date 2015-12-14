package com.dianxun.holyn.lucky.view.widget.ViewpagerHeaderScroll.tools;

public interface ScrollableFragmentListener {

    public void onFragmentAttached(ScrollableListener fragment, int position);

    public void onFragmentDetached(ScrollableListener fragment, int position);
}
