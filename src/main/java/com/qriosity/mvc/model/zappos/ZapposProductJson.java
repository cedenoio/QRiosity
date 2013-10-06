package com.qriosity.mvc.model.zappos;

import com.qriosity.mvc.model.VendorJsonItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ayethu
 * Date: 10/5/13
 * Time: 3:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class ZapposProductJson implements VendorJsonItem {

    private String statusCode;
    private List<Product> product;

    public ZapposProductJson() {
        this.product = new ArrayList<Product>();
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public List<Product> getProduct() {
        return product;
    }

    public void setProduct(List<Product> product) {
        this.product = product;
    }

    public static class Product {
        private String brandId;
        private String brandName;
        private String productId;
        private String productName;

        private String defaultProductUrl;
        private String defaultImageUrl;
        private List<Style> styles;

        public Product() {
            this.styles = new ArrayList<Style>();
        }

        public static class Style {
            private String percentOff;
            private String imageUrl;
            private String price;
            private String originalPrice;
            private String productUrl;
            private String styleId;
            private String color;

            public String getColor() {
                return color;
            }

            public void setColor(String color) {
                this.color = color;
            }

            public String getPercentOff() {
                return percentOff;
            }

            public void setPercentOff(String percentOff) {
                this.percentOff = percentOff;
            }

            public String getImageUrl() {
                return imageUrl;
            }

            public void setImageUrl(String imageUrl) {
                this.imageUrl = imageUrl;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getOriginalPrice() {
                return originalPrice;
            }

            public void setOriginalPrice(String originalPrice) {
                this.originalPrice = originalPrice;
            }

            public String getProductUrl() {
                return productUrl;
            }

            public void setProductUrl(String productUrl) {
                this.productUrl = productUrl;
            }

            public String getStyleId() {
                return styleId;
            }

            public void setStyleId(String styleId) {
                this.styleId = styleId;
            }
        }

        public String getBrandId() {
            return brandId;
        }

        public void setBrandId(String brandId) {
            this.brandId = brandId;
        }

        public String getBrandName() {
            return brandName;
        }

        public void setBrandName(String brandName) {
            this.brandName = brandName;
        }

        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public String getDefaultProductUrl() {
            return defaultProductUrl;
        }

        public void setDefaultProductUrl(String defaultProductUrl) {
            this.defaultProductUrl = defaultProductUrl;
        }

        public String getDefaultImageUrl() {
            return defaultImageUrl;
        }

        public void setDefaultImageUrl(String defaultImageUrl) {
            this.defaultImageUrl = defaultImageUrl;
        }

        public List<Style> getStyles() {
            return styles;
        }

        public void setStyles(List<Style> styles) {
            this.styles = styles;
        }
    }
}
