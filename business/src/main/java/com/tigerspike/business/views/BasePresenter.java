package com.tigerspike.business.views;

/**
 * Copyright (c) 2017.
 *
 * @author enricodelzotto
 * @since 15/02/2017
 */
public interface BasePresenter<V,S> {

    void onViewAttached(V view);

    void onViewDetached(S state);

    void onDestroyed();



}