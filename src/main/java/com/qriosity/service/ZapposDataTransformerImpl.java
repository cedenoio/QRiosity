package com.qriosity.service;

import com.google.gson.Gson;
import com.qriosity.mvc.model.SharedJsonItem;
import com.qriosity.mvc.model.ZapposJson;
import com.qriosity.mvc.model.ZapposJsonItem;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class ZapposDataTransformerImpl implements VendorDataTransformer {
    @Override
    public List<SharedJsonItem> transform(String json) {
        Gson gson = new Gson();
        final ZapposJson zapposJson = gson.fromJson(json, ZapposJson.class);

        List<SharedJsonItem> sharedJsonItems = new ArrayList<SharedJsonItem>();
        final List<ZapposJsonItem> results = zapposJson.getResults();
        for (int i = 0; i < results.size(); i++) {
            ZapposJsonItem zapposJsonItem =  results.get(i);
            sharedJsonItems.add(transform(zapposJsonItem));
        }
        return sharedJsonItems;
    }

    private SharedJsonItem transform(ZapposJsonItem zapposJson) {
        SharedJsonItem sharedJsonItem = new SharedJsonItem();
        sharedJsonItem.setBrandName(zapposJson.getBrandName());
        sharedJsonItem.setImageUrl(zapposJson.getThumbnailImageUrl());
        sharedJsonItem.setPrice(zapposJson.getOriginalPrice());
        sharedJsonItem.setSalePrice(zapposJson.getPrice());
        sharedJsonItem.setProductName(zapposJson.getProductName());

        return sharedJsonItem;
    }

}
