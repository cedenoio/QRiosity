package com.qriosity.mvc.model;

/**
 * JSON class that will be normalized to so that UI can understand
 */
public class SharedJsonItem {

    private String imageUrl;
    private String brandName;
    private String productName;
    private String price;
    private String salePrice;
    private String productUrl;

    public String getProductUrl() {
        return productUrl;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(String salePrice) {
        this.salePrice = salePrice;
    }

    @Override
    public String toString() {
        return "SharedJsonItem{" +
                "imageUrl='" + imageUrl + '\'' +
                ", brandName='" + brandName + '\'' +
                ", productName='" + productName + '\'' +
                ", price='" + price + '\'' +
                ", salePrice='" + salePrice + '\'' +
                '}';
    }
}
