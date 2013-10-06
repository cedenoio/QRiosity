package com.qriosity.service;

import com.qriosity.mvc.model.SharedJsonItem;
import com.qriosity.mvc.model.zappos.ZapposProductJson;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: ayethu
 * Date: 10/6/13
 * Time: 10:04 AM
 * To change this template use File | Settings | File Templates.
 */
@Service
public class ZapposImageService {

    private String url = "http://api.zappos.com/Image?styleId=[%s]&recipe=[\"OUTFIT\"]&key=52ddafbe3ee659bad97fcce7c53592916a6bfd73";

    @Resource
    private RestTemplate restTemplate;

    public void get(List<SharedJsonItem> itemList) {

        Map<String, SharedJsonItem> itemListAsMap = new HashMap<String, SharedJsonItem>();
        List<String> styleIds = new ArrayList<String>();
        for (SharedJsonItem item: itemList) {
            styleIds.add("\"" + item.getItemId() + "\"");
            itemListAsMap.put(item.getItemId(), item);
        }

        final String styles = StringUtils.join(styleIds, ',');
        final String formattedUrl = String.format(url, styles);

        Map<String, Object> json = restTemplate.getForObject(formattedUrl, Map.class);
        for (int i=0; i < styleIds.size(); i++) {
            final String styleId = StringUtils.strip(styleIds.get(i), "\"");
            final List<Map> test = (List<Map>) json.get(styleId);
            for (Map image: test) {
                final String filename = (String) image.get("filename");
                if (filename.endsWith("p-OUTFIT.png")) {
                    itemListAsMap.get(styleId).setHighresImageUrl(filename);
                }
            }
        }
    }
}
