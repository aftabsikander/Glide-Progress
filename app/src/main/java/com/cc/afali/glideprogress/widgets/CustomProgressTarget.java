package com.cc.afali.glideprogress.widgets;

import android.support.v7.widget.AppCompatImageView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.bumptech.glide.request.target.Target;

import io.netopen.hotbitmapgg.library.view.RingProgressBar;

/**
 * Created by afali on 1/17/2017.
 */

public class CustomProgressTarget<Z> extends ProgressTarget<String, Z> {

    private final ProgressBar progress;
    private RingProgressBar ringProgressBar = null;
    private final AppCompatImageView image;

    public CustomProgressTarget(Target<Z> target, ProgressBar progress, AppCompatImageView image) {
        super(target);
        this.progress = progress;
        this.image = image;
    }

    public CustomProgressTarget(Target<Z> target, ProgressBar progress, RingProgressBar ringProgressBar, AppCompatImageView image) {
        super(target);
        this.progress = progress;
        this.ringProgressBar = ringProgressBar;
        this.image = image;
    }

    @Override
    protected void onConnecting() {
        Log.e("zzzz", "Connecting");
        //progress.setIndeterminate(true);
        progress.setVisibility(View.VISIBLE);
        if (ringProgressBar != null)
            ringProgressBar.setVisibility(View.VISIBLE);
        //image.setImageLevel(0);
    }

    @Override
    protected void onDownloading(long bytesRead, long expectedLength) {
        Log.e("zzzz", "onDownloading");
        progress.setIndeterminate(false);
        if (ringProgressBar != null) {
            ringProgressBar.setMax(100);
            ringProgressBar.setProgress((int) (100 * bytesRead / expectedLength));
        }
        progress.setProgress((int) (100 * bytesRead / expectedLength));
        image.setImageLevel((int) (10000 * bytesRead / expectedLength));
        /*text.setText(String.format("downloading %.2f/%.2f MB %.1f%%",
                bytesRead / 1e6, expectedLength / 1e6, 100f * bytesRead / expectedLength));*/
    }

    @Override
    protected void onDownloaded() {
        Log.e("zzzz", "onDownloaded");
        //progress.setIndeterminate(true);
        image.setImageLevel(10000);
    }

    @Override
    protected void onDelivered() {
        progress.setVisibility(View.INVISIBLE);
        if (ringProgressBar != null)
            ringProgressBar.setVisibility(View.INVISIBLE);
        image.setImageLevel(0); // reset ImageView default
    }
}
