package com.xianju.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

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
@ApiModel(value = "Bid对象", description = "")
public class Bid implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("出价id")
    private Integer uid;

    @ApiModelProperty("用户id")
    private Integer fromUser;

    @ApiModelProperty("货物id")
    private Integer goodId;

    @ApiModelProperty("价格")
    private BigDecimal price;

    @ApiModelProperty("出价时间")
    private String time;

    @ApiModelProperty("删除状态")
    private boolean deleted;

    @ApiModelProperty("拒绝状态")
    private boolean rejected;


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
    public Integer getFromUser() {
        return fromUser;
    }

    public void setFromUser(Integer fromUser) {
        this.fromUser = fromUser;
    }
    public Integer getGoodId() {
        return goodId;
    }

    public void setGoodId(Integer goodId) {
        this.goodId = goodId;
    }
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
    public boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
    public boolean getRejected() {
        return rejected;
    }

    public void setRejected(boolean rejected) {
        this.rejected = rejected;
    }


    @Override
    public String toString() {
        return "Bid{" +
            "id=" + id +
            ", uid=" + uid +
            ", fromUser=" + fromUser +
            ", goodId=" + goodId +
            ", price=" + price +
            ", time=" + time +
            ", deleted=" + deleted +
            ", rejected=" + rejected +
        "}";
    }
}
