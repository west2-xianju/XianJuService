package com.xianju.demo.vo;

import java.math.BigDecimal;
import java.util.Date;

public class DifferentStateOrder {
    private int uid;
    private int from_id;
    private int to_id;
    private int good_id;
    private String state;
    private BigDecimal price;
    private String create_time;
    private String pay_time;
    private String game;
    private String title;
    private String pic;
    private String sellername;
    private String sellernickname;

    private String sellerprofile;

    public String getSellerprofile() {
        return sellerprofile;
    }

    public void setSellerprofile(String sellerprofile) {
        this.sellerprofile = sellerprofile;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getFrom_id() {
        return fromId;
    }

    public void setFrom_id(int fromId) {
        this.from_id = fromId;
    }

    public int getTo_id() {
        return toId;
    }

    public void setTo_id(int toId) {
        this.to_id = toId;
    }

    public int getGood_id() {
        return goodId;
    }

    public void setGood_id(int goodId) {
        this.good_id = goodId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCreate_time() {
        return createTime;
    }

    public void setCreate_time(String createTime) {
        this.create_time = createTime;
    }

    public String getPay_time() {
        return payTime;
    }

    public void setPay_time(String payTime) {
        this.pay_time = payTime;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getSellername() {
        return sellername;
    }

    public void setSellername(String sellername) {
        this.sellername = sellername;
    }

    public String getSellernickname() {
        return sellernickname;
    }

    public void setSellernickname(String sellernickname) {
        this.sellernickname = sellernickname;
    }


    private int fromId;
    private int toId;
    private int goodId;
    private String createTime;
    private String payTime;


}
