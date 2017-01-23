package com.cc.afali.glideprogress.adapter;

import android.graphics.Bitmap;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.cc.afali.glideprogress.R;
import com.cc.afali.glideprogress.utilities.DelayBitmapTransformation;
import com.cc.afali.glideprogress.utilities.LoggingListener;
import com.cc.afali.glideprogress.widgets.CustomProgressTarget;
import com.cc.afali.glideprogress.widgets.ProgressTarget;

import java.util.List;

import io.netopen.hotbitmapgg.library.view.RingProgressBar;

/**
 * Created by afali on 1/17/2017.
 */

public class ProgressAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<String> models;

    public ProgressAdapter(List<String> models) {
        this.models = models;
    }

    @Override
    public ProgressViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_items, parent, false);
        return new ProgressViewHolder(view);
    }

    /**
     * Called by RecyclerView to display the data at the specified position. This method should
     * update the contents of the {@link ProgressViewHolder#itemView} to reflect the item at the given
     * position.
     * <p>
     * Note that unlike {@link ListView}, RecyclerView will not call this method
     * again if the position of the item changes in the data set unless the item itself is
     * invalidated or the new position cannot be determined. For this reason, you should only
     * use the <code>position</code> parameter while acquiring the related data item inside
     * this method and should not keep a copy of it. If you need the position of an item later
     * on (e.g. in a click listener), use {@link ProgressViewHolder#getAdapterPosition()} which will
     * have the updated adapter position.
     * <p>
     *
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ProgressViewHolder progressViewHolder = (ProgressViewHolder) holder;
        progressViewHolder.bind(models.get(position));
    }


    @Override
    public int getItemCount() {
        return models.size();
    }

    private class ProgressViewHolder extends RecyclerView.ViewHolder {
        private final AppCompatImageView image;
        private final ProgressBar progress;
        private final RingProgressBar ringProgressBar;
        /**
         * Cache target because all the views are tied to this view holder.
         */
        private final ProgressTarget<String, Bitmap> target;

        ProgressViewHolder(View root) {
            super(root);
            image = (AppCompatImageView) root.findViewById(R.id.image);
            progress = (ProgressBar) root.findViewById(R.id.progress_bar);
            ringProgressBar = (RingProgressBar) root.findViewById(R.id.ring_progressbar);
            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bind(target.getModel());
                }
            });
            target = new CustomProgressTarget<>(new BitmapImageViewTarget(image), progress, ringProgressBar, image);
        }

        void bind(String url) {
            target.setModel(url); // update target's cache
            Glide.with(image.getContext())
                    .load(url)
                    .asBitmap()
                    //.placeholder(R.drawable.progressdrawable)
                    .centerCrop() // needs explicit transformation, because we're using a custom target
                    .transform(new CenterCrop(image.getContext()), new DelayBitmapTransformation(1000))
                    .listener(new LoggingListener<String, Bitmap>())
                    .into(target);
        }
    }


}


