package com.xianju.demo.vo;

public class ForgetUser {
    private String username;
    private String password;
    private String email;
    private String captcha;
    private String send_captcha;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public String getSend_captcha() {
        return send_captcha;
    }

    public void setSend_captcha(String send_captcha) {
        this.send_captcha = send_captcha;
    }
}
