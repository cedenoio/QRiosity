package com.qriosity.service;

import com.google.gson.Gson;
import com.qriosity.mvc.model.SharedJsonItem;
import com.qriosity.mvc.model.ZapposJson;
import com.qriosity.mvc.model.zappos.ZapposProductJson;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 */
@Service
public class ProductDetailService {
    private String url = "http://api.zappos.com/Product/styleId/%s?includes=[\"styles\"]&key=%s";
    private static final String zapposApiKey = "52ddafbe3ee659bad97fcce7c53592916a6bfd73";

    @Resource
    private RestTemplate restTemplate;

    private ZapposDataTransformerImpl dataTransformer;

    public ProductDetailService() {
        this.dataTransformer = new ZapposDataTransformerImpl();

    }

    public SharedJsonItem getProductDetail(String itemId) {

        final String formattedUrl = String.format(url, itemId, zapposApiKey);
        System.out.println(formattedUrl);
        ZapposProductJson json = restTemplate.getForObject(formattedUrl, ZapposProductJson.class);

        if (json.getProduct().size() > 0) {
            final SharedJsonItem item = dataTransformer.transformProduct(json);
            return item;
        }
        return null;
    }

}
