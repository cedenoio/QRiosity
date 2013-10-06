package com.qriosity.service;

import com.qriosity.mvc.model.SharedJsonItem;
import com.qriosity.mvc.model.VendorJsonItem;
import com.qriosity.mvc.model.ZapposJsonItem;

import java.util.List;

/**
 * Transforms the vendor JSON into the json the UI is expecting
 */
public interface VendorDataTransformer {

    public List<SharedJsonItem> transform(String json);

    public SharedJsonItem transform(VendorJsonItem item);

    public SharedJsonItem transformProduct(VendorJsonItem item);
}
