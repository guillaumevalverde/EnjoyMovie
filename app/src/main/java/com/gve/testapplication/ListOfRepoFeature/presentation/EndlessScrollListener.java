package com.gve.testapplication.ListOfRepoFeature.presentation;

import java.util.concurrent.Callable;

/**
 * Created by gve on 28/11/2017.
 */

public class EndlessScrollListener {

    private int visibleThreshold = 2;

    /** The total number of items in the dataset after the last load */
    private int previousTotalItemCount = 0;

    /** True if we are still waiting for the last set of data to load. */
    private boolean loading = true;

    private Callable fetch;

    public EndlessScrollListener(Callable fetch) {
        this.fetch = fetch;
    }

    public EndlessScrollListener(Callable fetch, int visibleThreshold) {
        this.fetch = fetch;
        this.visibleThreshold = visibleThreshold;
    }

    /** Defines the process for actually loading more data based on page
     * Returns true if more data is being loaded; returns false if there is no more data to load.
     * @return loading
     */
    public boolean onLoadMore() {
        try {
            fetch.call();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Callback method to be invoked when the RecyclerView has been scrolled. This will be
     * called after the scroll has completed.
     * <p>
     * This callback will also be called if visible item range changes after a layout
     * calculation. In that case, dx and dy will be 0.
     *
     */

    public void onScrolled(int visibleItemsCount, int totalItemsCount, int firstVisibleItem) {
        if (totalItemsCount < previousTotalItemCount) {

            this.previousTotalItemCount = totalItemsCount;
            if (totalItemsCount == 0) {
                this.loading = true;
            }
        }
        // If it's still loading, we check to see if the data set count has
        // changed, if so we conclude it has finished loading and update the current page
        // number and total item count.
        // +1 is here to take in account the progress bar as an item
        if (loading && (totalItemsCount > previousTotalItemCount + 1)) {
            loading = false;
            previousTotalItemCount = totalItemsCount;
        }

        // If it isn't currently loading, we check to see if we have breached
        // the visibleThreshold and need to reload more data.
        // If we do need to reload some more data, we execute onLoadMore to fetch the data.
        if (!loading && (firstVisibleItem + visibleItemsCount + visibleThreshold)
                >= totalItemsCount) {
            loading = onLoadMore();
        }
    }
}
