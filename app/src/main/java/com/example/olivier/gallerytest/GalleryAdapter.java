package com.example.olivier.gallerytest;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.example.olivier.gallerytest.yelp.Business;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by olivier on 11/9/16.
 */
public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.GalleryItemViewHolder> {

  public static class GalleryItemViewHolder extends RecyclerView.ViewHolder {

    public ImageView mImageView;
    public TextView mTitleView;
    private TextView mDescriptionView;

    public GalleryItemViewHolder(View itemView) {
      super(itemView);
      mImageView = (ImageView) itemView.findViewById(R.id.image);
      mTitleView = (TextView) itemView.findViewById(R.id.title);
      mDescriptionView = (TextView) itemView.findViewById(R.id.description);
    }
  }

  private ArrayList<Business> data;
  private Context mContext;

  GalleryAdapter(Context context) {
    this.mContext = context;
  }

  public void setData(ArrayList<Business> data) {
    this.data = data;
  }

  @Override public GalleryItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View v = LayoutInflater.from(parent.getContext())
                           .inflate(R.layout.gallery_item, parent, false);

    GalleryItemViewHolder holder = new GalleryItemViewHolder(v);
    return holder;
  }

  @Override public void onBindViewHolder(final GalleryItemViewHolder holder, int position) {
    holder.mTitleView.setText(data.get(position).name());
    holder.mDescriptionView.setText(data.get(position).phone());
    Glide.with(mContext)
        .load(data.get(position).imageUrl())
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .dontAnimate()
        .fitCenter()
        .listener(new RequestListener<String, GlideDrawable>() {
          @Override
          public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
            Log.e("IMAGE_EXCEPTION", "Exception");
            return false;
          }

          @Override
          public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
            return false;
          }
        })
        .into(holder.mImageView);
  }

  @Override public int getItemCount() {
    return data == null ? 0 : data.size();
  }
}
