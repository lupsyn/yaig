package com.tigerspike.data.persist;

/**
 * Copyright (c) 2016.  All rights reserved.
 *
 * @author enricodelzotto
 * @since 22/03/16
 */
public interface ISharedDataController {

    void save(String key, Object toSave);

    <S> Object load(Class<S> classType, String key);

    void deleteInternal(String key);
}
