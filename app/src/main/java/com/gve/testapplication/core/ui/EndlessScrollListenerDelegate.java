package com.gve.testapplication.core.ui;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.concurrent.Callable;

/**
 * Created by gve on 28/11/2017.
 */

public class EndlessScrollListenerDelegate extends RecyclerView.OnScrollListener {


    private Callable fetch;
    private EndlessScrollListener endlessScrollListener;

    public EndlessScrollListenerDelegate(Callable fetch) {
        endlessScrollListener = new EndlessScrollListener(fetch);
    }

    /**
     * Callback method to be invoked when the RecyclerView has been scrolled. This will be
     * called after the scroll has completed.
     * <p>
     * This callback will also be called if visible item range changes after a layout
     * calculation. In that case, dx and dy will be 0.
     *
     * @param recyclerView The RecyclerView which scrolled.
     * @param dx           The amount of horizontal scroll.
     * @param dy           The amount of vertical scroll.
     */

    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        final int visibleItemsCount = recyclerView.getLayoutManager().getChildCount();
        final int totalItemsCount = recyclerView.getLayoutManager().getItemCount();
        final int firstVisibleItem = ((LinearLayoutManager) recyclerView.getLayoutManager())
                .findFirstVisibleItemPosition();

        endlessScrollListener.onScrolled(visibleItemsCount, totalItemsCount, firstVisibleItem);
    }
}
