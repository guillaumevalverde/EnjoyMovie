package com.gve.testapplication;


import com.gve.testapplication.core.presentation.recyclerview.endlesslistscroll.EndlessScrollListener;
import com.gve.testapplication.test_common.BaseTest;

import org.junit.Test;
import org.mockito.Mock;

import java.util.concurrent.Callable;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by gve on 18/12/2017.
 */

public class EndlessScrollingListenerTest extends BaseTest {

    @Mock
    private Callable mockFetch;

    @Test
    public void mockEndlessScrollingNoLoadingNeeded() throws Exception {
        EndlessScrollListener endlessScrollListener =
                new EndlessScrollListener(mockFetch, 2);
        endlessScrollListener.onScrolled(5, 10, 0);

        verify(mockFetch,  times(0)).call();
    }

    @Test
    public void mockEndlessScrollingLoadingNeeded() throws Exception {
        EndlessScrollListener endlessScrollListener =
                new EndlessScrollListener(mockFetch, 2);
        endlessScrollListener.onScrolled(8, 10, 2);

        verify(mockFetch,  times(1)).call();
    }
}
