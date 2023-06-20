package com.xianju.demo.vo;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

public class GoodsVo {
    private Integer uid;

    private Integer sellerId;
    private Integer seller_id;

    private String state;

    private String game;

    private String title;

    private String detail;

    private String pic;

    private BigDecimal price;

    private Date publishTime;
    private Date publish_time;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }


    public Integer getSeller_id() {
        return sellerId;
    }

    public void setSeller_id(Integer sellerId) {
        this.seller_id = sellerId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }


    public Date getPublish_time() {
        return publishTime;
    }

    public void setPublish_time(Date publishTime) {
        this.publish_time = publishTime;
    }
}
