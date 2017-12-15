package com.gve.testapplication.feature.moviedetail.presentation;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.gve.testapplication.feature.Movie;
import com.gve.testapplication.feature.data.MovieDetailRepo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gve on 16/12/2017.
 */

public class InfiniteViewPagerAdapter extends PagerAdapter {

    private static final String TAG = InfiniteViewPagerAdapter.class.getSimpleName();
    Context mContext;
    LayoutInflater mLayoutInflater;
    private List<Movie> resources = new ArrayList<>();
    private MovieDetailRepo repo;
    private Picasso picasso;

    public InfiniteViewPagerAdapter(Context context, Movie movieRef, MovieDetailRepo repo, Picasso picasso) {
        mContext = context;
        Log.v(TAG, "movieref: " + movieRef.toString());
        this.resources.add(movieRef);
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.repo = repo;
        this.picasso = picasso;
    }

    @Override
    public int getCount() {
        if (getRealCount() <= 1) {
            return getRealCount();
        }
        // warning: scrolling to very high values (1,000,000+) results in
        // strange drawing behaviour
        return Integer.MAX_VALUE;
    }

    /**
     * @return the {@link #getCount()} result of the wrapped adapter
     */
    public int getRealCount() {
        return resources.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        int virtualPosition = position % getRealCount();
        Log.d(TAG, "instantiateItem: real position: " + position);
        Log.d(TAG, "instantiateItem: virtual position: " + virtualPosition);

        Log.d(TAG, "instantiateItem : " + position);
        Log.d(TAG, "instantiateItem : " + getCount() + " - " + container.getChildCount());
        if (getCount() == 1) {
            if (container.getChildCount() != 0) {
                Log.d(TAG, "instantiateItem : " + (container.getChildAt(0) == null));
                return container.getChildAt(0);
            }
            return createView(container, virtualPosition);
        }


        Log.v(TAG, "instantiateItem container size: " + container.getChildCount());

        return createView(container, virtualPosition);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        int virtualPosition = position % getRealCount();
        Log.d(TAG, "destroyItem: real position: " + position);
        Log.d(TAG, "destroyItem: virtual position: " + virtualPosition);

        // only expose virtual position to the inner adapter
        Log.d(TAG, "destroyItem : " + virtualPosition);
        if (getCount() > 1) {
            container.removeView((RelativeLayout) object);
            Log.d(TAG, "destroyItem container size: " + container.getChildCount());
        }
    }


    public void addRessource(List<Movie> resources) {
        this.resources.addAll(resources);
        Log.d(TAG, "add Ressources: :");
        for (Movie movie: resources) {
            Log.d(TAG, "movie: " + movie.toString());
        }
        this.notifyDataSetChanged();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
     //   Log.d(TAG, "isViewFromObject : " + (view == ((RelativeLayout) object)));

        return view == ((RelativeLayout) object);
    }


    private View createView(ViewGroup container, int position) {
        Log.d(TAG, "createView : " + position + ", movie: " + resources.get(position).toString());

        //View itemView = mLayoutInflater.inflate(R.layout.pager_item, container, false);
        //ImageView imageView = itemView.findViewById(R.id.imageView);
        //imageView.setBackgroundColor(itemView.getResources().getColor(resources.get(position)));
        CustomMovieDetailView view = new CustomMovieDetailView(mContext, picasso, resources.get(position), repo);

        container.addView(view);
        return view;
    }


}
