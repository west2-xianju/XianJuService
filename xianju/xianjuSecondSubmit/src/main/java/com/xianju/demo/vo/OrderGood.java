package com.xianju.demo.vo;

import java.math.BigDecimal;

public class OrderGood {
    private int good_id;

    private BigDecimal price;

    public int getGood_id() {
        return good_id;
    }

    public void setGood_id(int good_id) {
        this.good_id = good_id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
