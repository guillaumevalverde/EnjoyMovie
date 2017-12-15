package com.gve.testapplication.feature.moviedetail.presentation;

/**
 * Created by gve on 16/12/2017.
 */

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;

/**
 * A {@link ViewPager} that allows pseudo-infinite paging with a wrap-around effect. Should be used with an {@link
 * InfiniteFragmentPagerAdapter}.
 */
public class InfiniteViewPager extends ViewPager {

    private static final String TAG = InfiniteViewPager.class.getSimpleName();

    public InfiniteViewPager(Context context) {
        super(context);
    }

    public InfiniteViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setAdapter(PagerAdapter adapter) {
        super.setAdapter(adapter);
        // offset first element so that we can scroll to the left
        setCurrentItem(0);
    }

    @Override
    public void setCurrentItem(int item, boolean smoothScroll) {
        Log.d(TAG, "ViewPager: setCurrentItem: " + item);
        if (getAdapter().getCount() == 0) {
            super.setCurrentItem(item, smoothScroll);
            return;
        }
        item = getOffsetAmount() + (item % getAdapter().getCount());
        super.setCurrentItem(item, smoothScroll);
    }

    @Override
    public int getCurrentItem() {
        Log.d(TAG, "ViewPager: gCurrentItem");
        if (getAdapter().getCount() == 0) {
            return super.getCurrentItem();
        }
        int position = super.getCurrentItem();
        if (getAdapter() instanceof InfiniteViewPagerAdapter) {
            InfiniteViewPagerAdapter infAdapter = (InfiniteViewPagerAdapter) getAdapter();
            // Return the actual item position in the data backing InfinitePagerAdapter
            return (position % infAdapter.getRealCount());
        } else {
            return super.getCurrentItem();
        }
    }

    @Override
    public void setCurrentItem(int item) {
        Log.d(TAG, "ViewPager: setCurrentItem: " + item);
        // offset the current item to ensure there is space to scroll
        setCurrentItem(item, false);
    }

    private int getOffsetAmount() {
        Log.d(TAG, "ViewPager: getOffsetAmount");
        if (getAdapter().getCount() == 0) {
            Log.d(TAG, "ViewPager: 1");

            return 0;
        }
        if (getAdapter() instanceof InfiniteViewPagerAdapter) {
            Log.d(TAG, "ViewPager: 2");
            InfiniteViewPagerAdapter infAdapter = (InfiniteViewPagerAdapter) getAdapter();
            // allow for 100 back cycles from the beginning
            // should be enough to create an illusion of infinity
            // warning: scrolling to very high values (1,000,000+) results in
            // strange drawing behaviour
            Log.d(TAG, "return 0 offset" + infAdapter.getRealCount());
            return infAdapter.getRealCount() * 100;
        } else {
            Log.d(TAG, "ViewPager: 3");
            return 0;
        }
    }
}
