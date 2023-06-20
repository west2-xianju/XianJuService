package com.xianju.demo.vo;

import java.math.BigDecimal;

public class UpdatePrice {
    private int uid;
    private BigDecimal price;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
