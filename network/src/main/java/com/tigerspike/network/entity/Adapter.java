package com.tigerspike.network.entity;

import com.tigerspike.business.entity.FlickrImage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Copyright (c) 2016. SmartFocus UK Limited. All rights reserved.
 *
 * @author enricodelzotto
 * @since 14/02/2017
 */
public class Adapter {

//    public static FlickrPublicFeed convertFlickrResponseDTO(FlickrPublicFeedDTO dto) {
//        FlickrPublicFeed toRet = new FlickrPublicFeed(dto.getTitle(),
//                dto.getLink(),
//                "",
//                dto.getModified(),
//                dto.getGenerator(),
//                convertListFlickrImageDto(dto.getImagesDTO()));
//        return toRet;
//    }


    public static FlickrImage convertFlickrImageDto(FlickrImageDTO dto) {
        FlickrImage toRet = new FlickrImage(dto.getTitle(),
                dto.getDirectMedia().getLink(),
                dto.getLink(),
                dto.getDate_taken(),
                dto.getPublished(),
                dto.getAuthor(),
                dto.getAuthor_id(),
                dto.getTags());
        return toRet;
    }

    public static List<FlickrImage> convertListFlickrImageDto(List<FlickrImageDTO> listDto) {
        List<FlickrImage> toRet = new ArrayList<>();
        //Sort List by date_taken parameter
        Collections.sort(listDto);
        for (FlickrImageDTO listElement : listDto) {
            toRet.add(convertFlickrImageDto(listElement));
        }
        return toRet;
    }


}