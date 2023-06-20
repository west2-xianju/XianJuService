package com.xianju.demo.vo;

import java.util.Date;

public class UserCommentSelect {

    private int uid;
    private int fromId;

    private  int from_id;
    private int to_id;
    private int good_id;
    private Date modified_time;
    private String comment_pic;
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

    public Date getModified_time() {
        return modifiedTime;
    }

    public void setModified_time(Date modifiedTime) {
        this.modified_time = modifiedTime;
    }

    public String getComment_pic() {
        return commentPic;
    }

    public void setComment_pic(String commentPic) {
        this.comment_pic = commentPic;
    }


    private int toId;
    private int goodId;
    private String detail;
    private String time;
    private int star;
    private boolean modified;
    private Date modifiedTime;
    private String commentPic;
    private String username;
    private String nickname;
    private String profile;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
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




    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }
}
