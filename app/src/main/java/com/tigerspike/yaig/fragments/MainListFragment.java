package com.tigerspike.yaig.fragments;

import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import com.tigerspike.business.entity.FlickrImage;
import com.tigerspike.business.logic.IPermissionCallback;
import com.tigerspike.business.views.MainViewContract;
import com.tigerspike.business.views.MainViewState;
import com.tigerspike.yaig.R;
import com.tigerspike.yaig.adapters.MainAdapter;
import com.tigerspike.yaig.utils.PermissionsManager;
import com.tigerspike.yaig.utils.RecyclerItemClickListener;
import com.tigerspike.yaig.utils.RecyclerViewEmptySupport;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Copyright (c) 2016.  All rights reserved.
 *
 * @author enricodelzotto
 * @since 31/07/2016
 */
public class MainListFragment extends Fragment implements MainViewContract.View {

    @BindView(R.id.recycleview)
    RecyclerViewEmptySupport recycleView;
    @BindView(R.id.emptylist)
    TextView mEventEmpty;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeContainer;

    private PermissionsManager mPermissionManager;
    public IPermissionCallback mPermissionCallback;

    public interface ListCallback {
        void share(FlickrImage image);

        void save(FlickrImage image);

        void open(FlickrImage image);

    }


    private MainAdapter listAdapter;
    private MainViewContract.Presenter mMainViewPresenter;
    private List<FlickrImage> mClientSets;


    private RecyclerItemClickListener mListener;

    private boolean mStatus = true;
    private boolean isRefreshing = false;
    private MainViewState mState;

    public MainListFragment() {
        // Requires empty public constructor
    }

    public static MainListFragment newInstance() {
        return new MainListFragment();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.main_list_fr, container, false);
        ButterKnife.bind(this, view);
        recycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        listAdapter = new MainAdapter(getContext(), new ArrayList<FlickrImage>(), mListCallback);
        recycleView.setAdapter(listAdapter);
        recycleView.setEmptyView(mEventEmpty);
        mState = new MainViewState(mClientSets);
        mPermissionManager = new PermissionsManager(getActivity());
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mMainViewPresenter.onViewAttached(this);
            }
        }, 500);

        initViews();
        return view;
    }


    public void initViews() {
        if (swipeContainer != null) {
            swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    mMainViewPresenter.initialize();
                }
            });
        }
    }

    public void setRefreshing(final boolean value) {
        isRefreshing = value;
        if (getActivity() != null) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    swipeContainer.post(new Runnable() {
                        @Override
                        public void run() {
                            swipeContainer.setRefreshing(value);
                        }
                    });
                }
            });
        }
    }


    @Override
    public void onDestroy() {
        mMainViewPresenter.onViewDetached(mState);
        super.onDestroy();

    }


    @Override
    public void onResume() {
//        mMainViewPresenter.initialize();
        super.onResume();
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {

        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void showLoading() {
        setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        setRefreshing(false);
    }

    @Override
    public void refreshImages(final List<FlickrImage> imageList) {

        getActivity().runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            mState.setImages(imageList);
                                            mClientSets = new ArrayList<FlickrImage>();
                                            mClientSets.addAll(imageList);
                                            if (swipeContainer.isRefreshing()) {
                                                swipeContainer.setRefreshing(false);
                                            }
                                            listAdapter.clear();
                                            // ...the data has come back, add new items to your adapter...
                                            listAdapter.addAll(mClientSets, mStatus);
                                            // Now we call setRefreshing(false) to signal refresh has finished
                                            swipeContainer.setRefreshing(false);
                                            listAdapter.notifyDataSetChanged();

                                        }
                                    }

        );

    }

    @Override
    public void showToast(final String msg) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG).show();
            }
        });

    }


    public IPermissionCallback getPermissionCallback() {
        return mPermissionCallback;
    }

    @Override
    public void requirePermission(IPermissionCallback callback) {
        mPermissionCallback = callback;
        if (mPermissionManager.canIRun()) {
            mPermissionCallback.onAllPermissionAcquired();
        }
    }


    @Override
    public void setPresenter(MainViewContract.Presenter presenter) {
        mMainViewPresenter = presenter;
    }


    private ListCallback mListCallback = new ListCallback() {
        @Override
        public void share(FlickrImage image) {
            mMainViewPresenter.shareImage(image);
        }

        @Override
        public void save(FlickrImage image) {
            mMainViewPresenter.saveImage(image);
        }

        @Override
        public void open(FlickrImage image) {
            mMainViewPresenter.openImage(image);
        }
    };
}
