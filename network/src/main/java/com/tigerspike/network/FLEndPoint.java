package com.tigerspike.network;

import com.tigerspike.network.entity.FlickrPublicFeedDTO;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Copyright (c) 2016.  All rights reserved.
 *
 * @author enricodelzotto
 * @since 14/02/2017
 */
public interface FLEndPoint {


//    Public feed
//
//    Returns a list of public content matching some criteria.
//    URL
//
//    https://api.flickr.com/services/feeds/photos_public.gne
//    Query string parameters
//
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


    //https://api.flickr.com/services/feeds/photos_public.gne?format=json&nojsoncallback=1
    @GET("/services/feeds/photos_public.gne?")
    Observable<FlickrPublicFeedDTO> getPublicImages(
            @Query("format") String format,
            @Query("nojsoncallback") int value,
            @Query("id") String id,
            @Query("ids") String ids,
            @Query("tags") String tags,
            @Query("tagmode") String tagMode,
            @Query("lang") String lang);

}
