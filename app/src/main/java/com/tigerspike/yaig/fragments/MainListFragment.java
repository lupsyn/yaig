package com.tigerspike.yaig.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.tigerspike.business.entity.FlickrImage;
import com.tigerspike.business.logic.MainViewPresenter;
import com.tigerspike.business.views.MainViewContract;
import com.tigerspike.yaig.R;
import com.tigerspike.yaig.adapters.MainAdapter;
import com.tigerspike.yaig.utils.RecyclerItemClickListener;
import com.tigerspike.yaig.utils.RecyclerViewEmptySupport;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Copyright (c) 2016. SmartFocus UK Limited. All rights reserved.
 *
 * @author enricodelzotto
 * @since 31/07/2016
 */
public class MainListFragment extends Fragment implements MainViewContract.View {

//    @BindView(R.id.recycleview)
    RecyclerViewEmptySupport recycleView;
//    @BindView(R.id.emptylist)
    TextView mEventEmpty;
//    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeContainer;

    private MainAdapter listAdapter;
    private MainViewContract.Presenter mMainViewPresenter;
    private List<FlickrImage> mClientSets;


    private RecyclerItemClickListener mListener;

    private boolean mStatus = true;
    private boolean isRefreshing = false;

    public MainListFragment() {
        // Requires empty public constructor
    }

    public static MainListFragment newInstance() {
        return new MainListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

//        View view = inflater.inflate(R.layout.main_list_fr, container, false);
//        ButterKnife.bind(this, view);
//        recycleView.setLayoutManager(new LinearLayoutManager(getContext()));
//        listAdapter = new MainAdapter(getContext(), new ArrayList<FlickrImage>());
//        recycleView.setAdapter(listAdapter);
//        recycleView.setEmptyView(mEventEmpty);

        View view = inflater.inflate(R.layout.main_list_fr, container, false);
        recycleView = (RecyclerViewEmptySupport) view.findViewById(R.id.recycleview);
        mEventEmpty = (TextView) view.findViewById(R.id.emptylist);
        recycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        listAdapter = new MainAdapter(getContext(), new ArrayList<FlickrImage>());
        recycleView.setAdapter(listAdapter);
        swipeContainer = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
        recycleView.setEmptyView(mEventEmpty);

//        mMainListPresenter = new MainListPresenter(this);
//        mMainListPresenter.onCreateView();

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
        mMainViewPresenter.destroy();
        super.onDestroy();

    }


    @Override
    public void onResume() {
        mMainViewPresenter.initialize();
        super.onResume();
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
                                            if (mListener != null) {
                                                recycleView.removeOnItemTouchListener(mListener);
                                                mListener = null;
                                            }
                                            listAdapter.notifyDataSetChanged();
                                            mListener = new RecyclerItemClickListener(getContext(), new RecyclerItemClickListener.OnItemClickListener() {
                                                @Override
                                                public void onItemClick(View view, int position) {
//                                                    if (!isRefreshing)
//                                                        setChoosen(clientSetList.get(position));

                                                }
                                            });
                                            recycleView.addOnItemTouchListener(mListener);
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


    @Override
    public void setPresenter(MainViewContract.Presenter presenter) {
        this.mMainViewPresenter = presenter;
    }
}
