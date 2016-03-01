package com.clockbone.domain;

/**
 * Created by qinjun on 2016/2/24.
 */
public class OrderItem extends  Item{
    private Integer key;
    private Integer count;

    private String ItemName;

    private String itemCategory;

    public String getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
    }

    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String itemName) {
        ItemName = itemName;
    }

    @Override
    public Integer getKey() {
        return key;
    }

    @Override
    public void setKey(Integer key) {
        this.key = key;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    private Integer totalCount ;
    private Integer totalCoupon ;
    private Integer totalCurrency ;

    private Integer originalCoupon ;
    private Integer originalCurrency ;

    private Integer currentCoupon;

    private Integer currentCurrency;

    private Integer coupon;

    private Integer currency;

    private Integer price;

    @Override
    public Integer getPrice() {
        return price;
    }

    @Override
    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getCurrentCoupon() {
        return currentCoupon;
    }

    public void setCurrentCoupon(Integer currentCoupon) {
        this.currentCoupon = currentCoupon;
    }

    public Integer getCurrentCurrency() {
        return currentCurrency;
    }

    public void setCurrentCurrency(Integer currentCurrency) {
        this.currentCurrency = currentCurrency;
    }

    public Integer getCoupon() {
        return coupon;
    }

    public void setCoupon(Integer coupon) {
        this.coupon = coupon;
    }

    public Integer getCurrency() {
        return currency;
    }

    public void setCurrency(Integer currency) {
        this.currency = currency;
    }

    private Integer buyCount;

    public Integer getBuyCount() {
        return buyCount;
    }

    public void setBuyCount(Integer buyCount) {
        this.buyCount = buyCount;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getTotalCoupon() {
        return totalCoupon;
    }

    public void setTotalCoupon(Integer totalCoupon) {
        this.totalCoupon = totalCoupon;
    }

    public Integer getTotalCurrency() {
        return totalCurrency;
    }

    public void setTotalCurrency(Integer totalCurrency) {
        this.totalCurrency = totalCurrency;
    }

    public Integer getOriginalCoupon() {
        return originalCoupon;
    }

    public void setOriginalCoupon(Integer originalCoupon) {
        this.originalCoupon = originalCoupon;
    }

    public Integer getOriginalCurrency() {
        return originalCurrency;
    }

    public void setOriginalCurrency(Integer originalCurrency) {
        this.originalCurrency = originalCurrency;
    }
}
