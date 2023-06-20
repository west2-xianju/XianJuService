package com.xianju.demo.vo;

import java.util.Date;

public class UserCommentVo {
    private int from_id;
    private int to_id;
    private int good_id;
    private String detail;
    private int star;
    private boolean modified;
    private  String modified_time;
    private String comment_pic;

    public int getFrom_id() {
        return from_id;
    }

    public void setFrom_id(int from_id) {
        this.from_id = from_id;
    }

    public int getTo_id() {
        return to_id;
    }

    public void setTo_id(int to_id) {
        this.to_id = to_id;
    }

    public int getGood_id() {
        return good_id;
    }

    public void setGood_id(int good_id) {
        this.good_id = good_id;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public boolean isModified() {
        return modified;
    }

    public void setModified(boolean modified) {
        this.modified = modified;
    }

    public String getModified_time() {
        return modified_time;
    }

    public void setModified_time(String modified_time) {
        this.modified_time = modified_time;
    }

    public String getComment_pic() {
        return comment_pic;
    }

    public void setComment_pic(String comment_pic) {
        this.comment_pic = comment_pic;
    }
}
