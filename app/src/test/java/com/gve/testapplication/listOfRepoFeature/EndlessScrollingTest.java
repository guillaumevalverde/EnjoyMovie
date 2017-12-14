package com.gve.testapplication.listOfRepoFeature;

import com.gve.testapplication.core.ui.EndlessScrollListener;
import com.gve.testapplication.test_common.BaseTest;

import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.concurrent.Callable;

/**
 * Created by gve on 29/11/2017.
 */

public class EndlessScrollingTest extends BaseTest {

    @Mock
    private Callable mockFetch;

    @Test
    public void mockEndlessScrollingNoLoadingNeeded() throws Exception {
        EndlessScrollListener endlessScrollListener =
                new EndlessScrollListener(mockFetch, 2);
        endlessScrollListener.onScrolled(5, 10, 0);

        try {
            verify(mockFetch,  times(0)).call();
        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false);
        }
    }



    @Test
    public void mockEndlessScrollingLoadingNeeded() throws Exception {
        EndlessScrollListener endlessScrollListener =
                new EndlessScrollListener(mockFetch, 2);
        endlessScrollListener.onScrolled(8, 10, 2);

        try {
            verify(mockFetch,  times(1)).call();
        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false);
        }
    }
}
