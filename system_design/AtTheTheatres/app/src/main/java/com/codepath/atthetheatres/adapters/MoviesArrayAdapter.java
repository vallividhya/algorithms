package com.codepath.atthetheatres.adapters;


import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.codepath.atthetheatres.R;
import com.codepath.atthetheatres.models.Movie;

import java.util.List;

/**
 * Created by vidhya on 2/12/18.
 */

public class MoviesArrayAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<Movie> movies;
    private boolean orientationLandscape;

    public MoviesArrayAdapter(@NonNull Context context, @NonNull List<Movie> objects, boolean orientationLandscape) {
        this.context = context;
        this.movies = objects;
        this.orientationLandscape = orientationLandscape;
    }

    private Context getContext() {
        return this.context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_movie, parent, false);
        if (orientationLandscape) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie_landscape , parent, false);
            return new ViewHolderLandScape(v);
        } else {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie , parent, false);
            return new ViewHolder(v);
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Movie movie = movies.get(position);
        if (orientationLandscape) {
            ViewHolderLandScape lholder = (ViewHolderLandScape) holder;
            ImageView ivPoster = lholder.getIvMovieImage();
            SimpleTarget<Bitmap> target = lholder.getTarget();
            ivPoster.setImageResource(0);
            String imageUrl = movie.getPosterPath();
            if(!imageUrl.isEmpty()) {
                Glide.with(getContext()).load(imageUrl).asBitmap().into(target);
            }

            TextView textView = lholder.getTvMovieTitle();
            textView.setText(movie.getOriginalTitle());

            TextView textView1 = lholder.getTvMovieDescription();
            textView1.setText(movie.getOverview());
        } else {
            ViewHolder pholder = (ViewHolder) holder;
            ImageView ivPoster = pholder.getIvMovieImage();
            SimpleTarget<Bitmap> target = pholder.getTarget();
            ivPoster.setImageResource(0);
            String imageUrl = movie.getPosterPath();
            if(!imageUrl.isEmpty()) {
                Glide.with(getContext()).load(imageUrl).asBitmap().into(target);
            }

            TextView textView = pholder.getTvMovieTitle();
            textView.setText(movie.getOriginalTitle());

            TextView textView1 = pholder.getTvMovieDescription();
            textView1.setText(movie.getOverview());
        }

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    @Override
    public int getItemViewType(int position) {
        int viewType = 0;
        if (orientationLandscape) {
            viewType = 1;
        }
        return viewType;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivMovieImage;
        TextView tvMovieTitle;
        TextView tvMovieDescription;

        public ViewHolder(View itemView) {
            super(itemView);
            this.ivMovieImage = (ImageView) itemView.findViewById(R.id.ivMovieImage);
            this.tvMovieTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            this.tvMovieDescription = (TextView) itemView.findViewById(R.id.tvOverview);
        }

        public SimpleTarget target = new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap bitmap, GlideAnimation glideAnimation) {
                ivMovieImage.setImageBitmap(bitmap);
            }
        };

        public ImageView getIvMovieImage() {
            return ivMovieImage;
        }

        public void setIvMovieImage(ImageView ivMovieImage) {
            this.ivMovieImage = ivMovieImage;
        }

        public TextView getTvMovieTitle() {
            return tvMovieTitle;
        }

        public void setTvMovieTitle(TextView tvMovieTitle) {
            this.tvMovieTitle = tvMovieTitle;
        }

        public TextView getTvMovieDescription() {
            return tvMovieDescription;
        }

        public void setTvMovieDescription(TextView tvMovieDescription) {
            this.tvMovieDescription = tvMovieDescription;
        }

        public SimpleTarget getTarget() {
            return target;
        }

        public void setTarget(SimpleTarget target) {
            this.target = target;
        }
    }

    public static class ViewHolderLandScape extends RecyclerView.ViewHolder {
        ImageView ivMovieImage;
        TextView tvMovieTitle;
        TextView tvMovieDescription;

        public ViewHolderLandScape(View itemView) {
            super(itemView);
            this.ivMovieImage = (ImageView) itemView.findViewById(R.id.ivMovieImageLandScape);
            this.tvMovieTitle = (TextView) itemView.findViewById(R.id.tvTitleLandScape);
            this.tvMovieDescription = (TextView) itemView.findViewById(R.id.tvOverviewLandScape);
        }

        public SimpleTarget target = new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap bitmap, GlideAnimation glideAnimation) {
                ivMovieImage.setImageBitmap(bitmap);
            }
        };

        public ImageView getIvMovieImage() {
            return ivMovieImage;
        }

        public void setIvMovieImage(ImageView ivMovieImage) {
            this.ivMovieImage = ivMovieImage;
        }

        public TextView getTvMovieTitle() {
            return tvMovieTitle;
        }

        public void setTvMovieTitle(TextView tvMovieTitle) {
            this.tvMovieTitle = tvMovieTitle;
        }

        public TextView getTvMovieDescription() {
            return tvMovieDescription;
        }

        public void setTvMovieDescription(TextView tvMovieDescription) {
            this.tvMovieDescription = tvMovieDescription;
        }

        public SimpleTarget getTarget() {
            return target;
        }

        public void setTarget(SimpleTarget target) {
            this.target = target;
        }
    }
}
