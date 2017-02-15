package com.tigerspike.business.controller.network;


/**
 * Copyright (c) 2017.  All rights reserved.
 *
 * @author enricodelzotto
 * @since 27/07/2016
 */
public interface INetworkController {
//    id (Optional)
//    A single user ID. This specifies a user to fetch for.
//    ids (Optional)
//    A comma delimited list of user IDs. This specifies a list of users to fetch for.
//    tags (Optional)
//    A comma delimited list of tags to filter the feed by.
//            tagmode (Optional)
//    Control whether items must have ALL the tags (tagmode=all), or ANY (tagmode=any) of the tags. Default is ALL.
//            format (Optional)
//    The format of the feed. See the feeds page for feed format information. Default is Atom 1.0.
//    lang (Optional)
//    The display language for the feed. See the feeds page for feed language information. Default is US English (en-us).


    void getPublicImages(ImagesCallBack callback);
    void getPublicImagesSearchByTag(String tags,ImagesCallBack callback);



}
