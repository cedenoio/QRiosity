package com.qriosity.service;

import com.qriosity.mvc.model.SharedJsonItem;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Cache so that we don't have to query Zappos API again.
 *
 * This sucks I know
 */
@Service
public class CrazyCache {

    private Map<String, SharedJsonItem> cachedItems;

    public CrazyCache() {
        this.cachedItems = new ConcurrentHashMap<String, SharedJsonItem>();
    }

    public void addAll(List<SharedJsonItem> itemList) {

        for (int i = 0; i < itemList.size(); i++) {
            SharedJsonItem item = itemList.get(i);
            this.cachedItems.put(item.getItemId(), item);
        }
    }


    public void add(SharedJsonItem item) {
        this.cachedItems.put(item.getItemId(), item);
    }

    public SharedJsonItem get(String itemId) {
        return this.cachedItems.get(itemId);
    }
}
