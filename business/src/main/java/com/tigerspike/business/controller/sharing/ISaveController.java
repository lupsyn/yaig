package com.tigerspike.business.controller.sharing;

import com.tigerspike.business.entity.FlickrImage;

/**
 * Copyright (c) 2017.
 * All rights reserved.
 *
 * @author enricodelzotto
 * @since 15/02/2017
 */
public interface ISaveController {


    public void onSuccess(String savedOn);

    public void onError(String error);


}
