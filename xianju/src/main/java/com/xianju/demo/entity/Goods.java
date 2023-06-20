package com.xianju.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 
 * </p>
 *
 * @author feige
 * @since 2023-04-04
 */
@ApiModel(value = "Goods对象", description = "")
@TableName("`goods`")
public class Goods implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("货物id")
    private Integer uid;

    @ApiModelProperty("卖家id")
    private Integer sellerId;



    @ApiModelProperty("货物状态")
    private String state;

    @ApiModelProperty("所属游戏")
    private String game;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("货物描述")
    private String detail;

    @ApiModelProperty("主图")
    private String pic;

    @ApiModelProperty("价格")
    private BigDecimal price;

    @ApiModelProperty("发布时间")
    private Date publishTime;

    private Date publish_time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }
    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
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

    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                ", uid=" + uid +
                ", sellerId=" + sellerId +
                ", state='" + state + '\'' +
                ", game='" + game + '\'' +
                ", title='" + title + '\'' +
                ", detail='" + detail + '\'' +
                ", pic='" + pic + '\'' +
                ", price=" + price +
                ", publish_time=" + publishTime +
                '}';
    }

    public Date getPublish_time() {
        return publishTime;
    }

    public void setPublish_time(Date publishTime) {
        this.publish_time = publishTime;
    }
}
