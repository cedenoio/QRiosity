package com.qriosity.service;

import com.qriosity.model.SharedJsonItem;

import java.util.List;

/**
 * Transforms the vendor JSON into the json the UI is expecting
 */
public interface VendorDataTransformer {

    public List<SharedJsonItem> transform(String json);

}
