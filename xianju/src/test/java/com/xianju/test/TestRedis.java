package com.xianju.test;

import com.xianju.demo.vo.AliPay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

public class TestRedis {
    public static void main(String[] args) {
        /*System.out.println(getSendCaptcha().toString());*/
        Object aliPay =  redisTemplate.opsForValue().get("eyJhbGciOiJIUzI1NiJ9.eyJ1c2VyaWQiOjkxMzgzMDAsImJsb2NrZWQiOmZhbHNlfQ.BIv76e7HTtvmW8gE0NEzKDYn5ByRvzcpip-hzoMAdjA");
        System.out.println(aliPay.toString());
    }
    @Autowired
    private static RedisTemplate redisTemplate;
    public static Object getSendCaptcha(){
        Object sendCaptcha = redisTemplate.opsForValue().get("1366737405@qq.com");
        return sendCaptcha;
    }
}
