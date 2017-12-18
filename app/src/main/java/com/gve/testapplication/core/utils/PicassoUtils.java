package com.gve.testapplication.core.utils;

import android.widget.ImageView;

import com.gve.testapplication.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
import com.squareup.picasso.Transformation;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;


/**
 * Created by gve on 16/11/2017.
 */

public class PicassoUtils {

    public static final int RADIUS = 10;
    public static final int MARGIN = 0;
    public static final Transformation TRANSFORMATION =
            new RoundedCornersTransformation(RADIUS, MARGIN);


    public static void showImageWithPicasso(Picasso picasso, ImageView imageIV, String url) {
        showImageWithPicasso(picasso, imageIV, url, null);
    }

    public static void showImageWithPicasso(Picasso picasso, ImageView imageIV, String url,
                                            Callback callback) {
        if (!url.isEmpty()) {
            if (callback == null) {
                getRequest(picasso, url)
                        .into(imageIV);
            } else {
                getRequest(picasso, url)
                        .into(imageIV, callback);
            }
        } else {
            imageIV.setImageDrawable(imageIV.getResources()
                    .getDrawable(R.drawable.rounded));
        }
    }

    private static RequestCreator getRequest(Picasso picasso, String url) {
        return picasso.load(url)
                .transform(TRANSFORMATION)
                .placeholder(R.drawable.rounded)
                .fit()
                .centerInside()
                .error(R.drawable.rounded);
    }


}
