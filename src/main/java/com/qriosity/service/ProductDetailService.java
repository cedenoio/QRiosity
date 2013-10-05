package com.qriosity.service;

import com.qriosity.mvc.model.SharedJsonItem;
import com.qriosity.mvc.model.ZapposJson;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 */
@Service
public class ProductDetailService {
    private String url = "/Product/styleId/{%s}?includes=[\"styles\"]&key={%s}";
    private static final String zapposApiKey = "52ddafbe3ee659bad97fcce7c53592916a6bfd73";

    @Resource
    private RestTemplate restTemplate;

    private ZapposDataTransformerImpl dataTransformer;

    public ProductDetailService() {
        this.dataTransformer = new ZapposDataTransformerImpl();

    }

    public SharedJsonItem getProductDetail(String itemId) {

        final String formattedUrl = String.format(url, itemId, zapposApiKey);
        final ZapposJson zapposJson = restTemplate.getForObject(formattedUrl, ZapposJson.class);

        if (zapposJson.getResults().size() > 0) {
            final SharedJsonItem item = dataTransformer.transform(zapposJson.getResults().get(0));
            return item;
        }
        return null;
    }

}
