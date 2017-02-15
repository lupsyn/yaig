package com.tigerspike.yaig.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.squareup.picasso.Picasso;
import com.tigerspike.business.entity.FlickrImage;
import com.tigerspike.yaig.R;
import com.tigerspike.yaig.fragments.MainListFragment;

import java.util.List;

/**
 * Copyright (c) 2016. SmartFocus UK Limited. All rights reserved.
 *
 * @author enricodelzotto
 * @since 31/07/2016
 */
public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private final MainListFragment.ListCallback mCallback;
    private Context mContext;
    private List<FlickrImage> mSetList;

    public MainAdapter(Context context, List<FlickrImage> setList, MainListFragment.ListCallback callback) {
        mContext = context;
        mSetList = setList;
        mCallback = callback;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.main_list_item_card, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final FlickrImage flickrImage = mSetList.get(position);
        holder.mTextTitle.setText(flickrImage.getTitle());
        holder.mTextAuthor.setText(flickrImage.getAuthor());
        holder.mDateTaken.setText(flickrImage.getTimeTaken());
        if (flickrImage.getLink() != null)
            Picasso.with(mContext).load(flickrImage.getLink()).into(holder.mImageView);

        holder.mOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCallback.open(flickrImage);
            }
        });
        holder.mShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCallback.share(flickrImage);
            }
        });
        holder.mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCallback.save(flickrImage);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mSetList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mImageView;
        TextView mTextTitle;
        TextView mTextAuthor;
        TextView mDateTaken;
        ImageView mOpen;
        ImageView mShare;
        ImageView mSave;


        public ViewHolder(View v) {
            super(v);
            mImageView = (ImageView) v.findViewById(R.id.card_thumbnail);
            mTextTitle = (TextView) v.findViewById(R.id.card_title);
            mTextAuthor = (TextView) v.findViewById(R.id.card_author);
            mDateTaken = (TextView) v.findViewById(R.id.card_date_taken);
            mSave = (ImageView) v.findViewById(R.id.card_save);
            mOpen = (ImageView) v.findViewById(R.id.card_open);
            mShare = (ImageView) v.findViewById(R.id.card_share);
        }
    }

    public void clear() {
        mSetList.clear();
        notifyDataSetChanged();
    }

    // Add a list of items
    public void addAll(List<FlickrImage> list, boolean status) {

        mSetList.addAll(list);
        notifyDataSetChanged();
    }
}
