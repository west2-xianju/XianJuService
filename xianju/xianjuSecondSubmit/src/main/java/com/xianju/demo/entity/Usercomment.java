package com.xianju.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

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
@ApiModel(value = "Usercomment对象")
public class Usercomment implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("评价id")
    private Integer uid;

    @ApiModelProperty("评价人id")
    private Integer fromId;

    @ApiModelProperty("被评价人id")
    private Integer toId;

    @ApiModelProperty("货物id")
    private Integer goodId;

    @ApiModelProperty("评价内容")
    private String detail;

    @ApiModelProperty("评价时间")
    private String time;

    @ApiModelProperty("评价星级")
    private Integer star;

    @ApiModelProperty("评价是否修改")
    private boolean modified;

    @ApiModelProperty("评价修改时间")
    private String modifiedTime;

    @ApiModelProperty("评价图片")

    private String commentPic;

    public void setFromId(Integer fromId) {
        this.fromId = fromId;
    }

    public Integer getToId() {
        return toId;
    }

    public void setToId(Integer toId) {
        this.toId = toId;
    }

    public Integer getGoodId() {
        return goodId;
    }

    public void setGoodId(Integer goodId) {
        this.goodId = goodId;
    }

    public String getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(String modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public String getCommentPic() {
        return commentPic;
    }

    public void setCommentPic(String commentPic) {
        this.commentPic = commentPic;
    }

    public boolean isModified() {
        return modified;
    }


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
    public Integer getFromId() {
        return fromId;
    }


    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
    public Integer getStar() {
        return star;
    }

    public void setStar(Integer star) {
        this.star = star;
    }
    public boolean getModified() {
        return modified;
    }

    public void setModified(boolean modified) {
        this.modified = modified;
    }


    @Override
    public String toString() {
        return "Usercomment{" +
            "id=" + id +
            ", uid=" + uid +
            ", fromId=" + fromId +
            ", toId=" + toId +
            ", goodId=" + goodId +
            ", detail=" + detail +
            ", time=" + time +
            ", star=" + star +
            ", modified=" + modified +
            ", modifiedTime=" + modifiedTime +
        "}";
    }
}
