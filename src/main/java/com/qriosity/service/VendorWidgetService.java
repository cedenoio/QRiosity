package com.qriosity.service;

import com.qriosity.mvc.model.SharedJsonItem;

import java.util.List;

/**
 *
 */
public interface VendorWidgetService {
    /**
     * Returns JSON formatted data from vendor related to the item
     * @param vendor
     * @param itemId
     * @return
     */
    public List<SharedJsonItem> getProductRelatedData(String vendor, String itemId);



}
