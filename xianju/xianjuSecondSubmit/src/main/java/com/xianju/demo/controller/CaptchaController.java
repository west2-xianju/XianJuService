package com.xianju.demo.controller;

import com.xianju.demo.BaseResponse.Result;
import com.xianju.demo.Util.CaptchaUtils;
import com.xianju.demo.Util.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/captcha")
public class CaptchaController {
    @Autowired
    private MailUtil mailUtil;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 发送邮件
     */
    @RequestMapping("/email")//邮件测试
    public Result<?> sendMailTest() {
        String emailMsg = "测试发送邮件";
        try {
            // 发送普通文本邮件
            MailUtil.sendMail("gaoyifei040229@163.com", "邮件发送测试", "<a href='https://www.baidu.com' >百度一下</a>");
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }return Result.success("发送成功");
    }

    @PostMapping("/{email}")
    public Result<?> sendMail(@PathVariable String email){

        String captcha = CaptchaUtils.creatCaptcha();
        redisTemplate.opsForValue().set(email,captcha,240, TimeUnit.MINUTES);
        try {
            // 发送普通文本邮件
            MailUtil.sendMail(email, "欢迎使用闲橘！您的验证码是"+captcha, "<a href='https://www.baidu.com' >百度一下</a>");
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        HashMap<String, String> data = new HashMap<>();
        data.put("send_captcha:",passwordEncoder.encode(captcha));
        return Result.success(data,"success");
    }
}
