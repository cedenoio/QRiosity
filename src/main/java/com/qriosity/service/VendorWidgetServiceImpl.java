package com.qriosity.service;

import com.qriosity.mvc.model.SharedJsonItem;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
@Service
public class VendorWidgetServiceImpl implements VendorWidgetService {

    // URLs provided by vendors to get widget related info
    private Map<String, String> vendorUrlMap;
    //
    private Map<String, VendorDataTransformer> vendorDataTransformerMap;

    public VendorWidgetServiceImpl() {

        this.vendorUrlMap = new HashMap<String, String>();
        this.vendorUrlMap.put("zappos",
                "http://api.zappos.com/Search/Similarity?type=moreLikeThis&limit=15&styleId=%s&emphasis=color&key=52ddafbe3ee659bad97fcce7c53592916a6bfd73");

        this.vendorUrlMap.put("macys",
                "http://api.zappos.com/Search/Similarity?type=moreLikeThis&limit=15&styleId=%s&emphasis=color&key=52ddafbe3ee659bad97fcce7c53592916a6bfd73");

        this.vendorUrlMap.put("tjmaxx",
                "http://api.zappos.com/Search/Similarity?type=moreLikeThis&limit=15&styleId=%s&emphasis=color&key=52ddafbe3ee659bad97fcce7c53592916a6bfd73");


        this.vendorDataTransformerMap = new HashMap<String, VendorDataTransformer>();
        this.vendorDataTransformerMap.put("zappos",
                new ZapposDataTransformerImpl());
        this.vendorDataTransformerMap.put("macys",
                new ZapposDataTransformerImpl(5));
        this.vendorDataTransformerMap.put("tjmaxx",
                new ZapposDataTransformerImpl(10));
    }

    @Override
    public List<SharedJsonItem> getProductRelatedData(String vendor, String itemId) {

        final String vendorUrl = vendorUrlMap.get(vendor);
        if (vendorUrl != null) {
            // this assumes that the itemID is a part of the URL
            final String vendorJson = readFromVendorUrl(String.format(vendorUrl, itemId));
            final VendorDataTransformer dataTransformer = vendorDataTransformerMap.get(vendor);

            final List<SharedJsonItem> sharedJsonItemList = dataTransformer.transform(vendorJson);
            return sharedJsonItemList;
        }
        else {
            return null;
        }
    }

    private String readFromVendorUrl(String vendorUrl) {
        HttpClient httpclient = HttpClientBuilder.create().build();
        HttpGet httpget = new HttpGet(vendorUrl);
        HttpResponse response = null;

        BufferedReader br = null;
        String vendorResponse = null;
        try {
            response = httpclient.execute(httpget);
            br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            vendorResponse = this.readerToString(br);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (br != null) {
                try {
                    br.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return vendorResponse;
    }

    private String readerToString(BufferedReader br) {
        final StringBuilder sb = new StringBuilder();
        String line;
        try {
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
