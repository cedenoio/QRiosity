package com.qriosity.service;

import com.google.gson.Gson;
import com.qriosity.mvc.model.SharedJsonItem;
import com.qriosity.mvc.model.VendorJsonItem;
import com.qriosity.mvc.model.ZapposJson;
import com.qriosity.mvc.model.ZapposJsonItem;

import java.util.ArrayList;
import java.util.List;

import com.qriosity.mvc.model.zappos.ZapposProductJson;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class ZapposDataTransformerImpl implements VendorDataTransformer {

    // we're faking data returned by other services using the starting point in the result set
    private int startIndex;
    private int limit;

    public ZapposDataTransformerImpl() {
        this.startIndex = 0;
        this.limit = 5;
    }

    public ZapposDataTransformerImpl(int startIndex) {
        this();
        this.startIndex = startIndex;
    }

    @Override
    public List<SharedJsonItem> transform(String json) {
        Gson gson = new Gson();
        final ZapposJson zapposJson = gson.fromJson(json, ZapposJson.class);

        List<SharedJsonItem> sharedJsonItems = new ArrayList<SharedJsonItem>();
        final List<ZapposJsonItem> results = zapposJson.getResults();
        for (int i = this.startIndex, count = 0; i < results.size() && count < this.limit; i++, count++) {
            ZapposJsonItem zapposJsonItem =  results.get(i);
            sharedJsonItems.add(transform(zapposJsonItem));

        }
        return sharedJsonItems;
    }

    @Override
    public SharedJsonItem transform(VendorJsonItem zapposJson) {
        ZapposJsonItem item = (ZapposJsonItem) zapposJson;
        SharedJsonItem sharedJsonItem = new SharedJsonItem();
        sharedJsonItem.setBrandName(item.getBrandName());
        sharedJsonItem.setImageUrl(item.getThumbnailImageUrl());
        sharedJsonItem.setPrice(item.getOriginalPrice());
        sharedJsonItem.setSalePrice(item.getPrice());
        sharedJsonItem.setProductName(item.getProductName());
        sharedJsonItem.setProductUrl(item.getProductUrl());
        sharedJsonItem.setItemId(item.getStyleId());
        return sharedJsonItem;
    }

    @Override
    public SharedJsonItem transformProduct(VendorJsonItem zapposJson) {
        ZapposProductJson product = (ZapposProductJson) zapposJson;
        SharedJsonItem sharedJsonItem = new SharedJsonItem();

        if (product.getProduct().size() > 0) {
            final ZapposProductJson.Product product1 = product.getProduct().get(0);
            final ZapposProductJson.Product.Style item = product1.getStyles().get(0);
            sharedJsonItem.setBrandName(product1.getBrandName());
            sharedJsonItem.setImageUrl(item.getImageUrl());
            sharedJsonItem.setPrice(item.getOriginalPrice());
            sharedJsonItem.setSalePrice(item.getPrice());
            sharedJsonItem.setProductName(product1.getProductName());
            sharedJsonItem.setProductUrl(product1.getDefaultProductUrl());
        }
        return sharedJsonItem;
    }

}
