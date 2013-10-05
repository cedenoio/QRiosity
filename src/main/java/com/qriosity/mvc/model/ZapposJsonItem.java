package com.qriosity.mvc.model;

/**
 * Zappos JSON object for each item
 */
public class ZapposJsonItem implements VendorJsonItem {
    private String percentOff;
    private String thumbnailImageUrl;
    private String isNew;
    private String isHighRes;
    private String price;
    private String productUrl;
    private String brandName;

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getProductUrl() {
        return productUrl;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPercentOff() {
        return percentOff;
    }

    public void setPercentOff(String percentOff) {
        this.percentOff = percentOff;
    }

    public String getThumbnailImageUrl() {
        return thumbnailImageUrl;
    }

    public void setThumbnailImageUrl(String thumbnailImageUrl) {
        this.thumbnailImageUrl = thumbnailImageUrl;
    }

    public String getNew() {
        return isNew;
    }

    public void setNew(String aNew) {
        isNew = aNew;
    }

    public String getHighRes() {
        return isHighRes;
    }

    public void setHighRes(String highRes) {
        isHighRes = highRes;
    }
}