package com.clockbone.domain;

import java.util.List;

/**
 * Created by qinjun on 2016/2/24.
 */
public class Order {

    private int totalCount;
    private int totalCoupon;
    private int totalCurrency;

    private int originalCoupon;
    private int originalCurrency;
    private List<OrderItem> itemsList;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalCoupon() {
        return totalCoupon;
    }

    public void setTotalCoupon(int totalCoupon) {
        this.totalCoupon = totalCoupon;
    }

    public int getTotalCurrency() {
        return totalCurrency;
    }

    public void setTotalCurrency(int totalCurrency) {
        this.totalCurrency = totalCurrency;
    }

    public int getOriginalCoupon() {
        return originalCoupon;
    }

    public void setOriginalCoupon(int originalCoupon) {
        this.originalCoupon = originalCoupon;
    }

    public int getOriginalCurrency() {
        return originalCurrency;
    }

    public void setOriginalCurrency(int originalCurrency) {
        this.originalCurrency = originalCurrency;
    }

    public List<OrderItem> getItemsList() {
        return itemsList;
    }

    public void setItemsList(List<OrderItem> itemsList) {
        this.itemsList = itemsList;
    }
}
