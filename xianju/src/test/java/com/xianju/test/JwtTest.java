package com.xianju.test;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.xianju.demo.Util.JwtUtil;
import io.jsonwebtoken.Claims;

public class JwtTest {
    public static void main(String[] args) {

        String jwt = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJibG9ja2VkIjpmYWxzZSwidXNlcmlkIjo5MTM4MzAwfQ.G1wGIyfw4YqOd66E1m4rD8W7e7y0LcB_f_AcUsshvA0";
        DecodedJWT claims = JwtUtil.parseToken(jwt);
        System.out.println(claims.getClaim("userid").asInt());
    }
}
