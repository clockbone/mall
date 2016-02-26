package com.clockbone.domain;

/**
 * Created by qinjun on 2016/2/24.
 */
public class OrderItem extends  Item{
    private Integer totalCount ;
    private Integer totalCoupon ;
    private Integer totalCurrency ;

    private Integer originalCoupon ;
    private Integer originalCurrency ;

    private Integer currentCoupon;

    private Integer currentCurrency;

    private Integer coupon;

    private Integer currency;

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
