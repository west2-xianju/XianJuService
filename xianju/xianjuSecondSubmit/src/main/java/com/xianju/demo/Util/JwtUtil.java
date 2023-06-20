package com.xianju.demo.Util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.xianju.demo.entity.User;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;

public class JwtUtil {

    // 签名密钥
    private static final String SECRET = "XianJuJWT";

    public static String  getToken(User user){

        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        //生成签名密钥
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET);

        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        JwtBuilder builder = Jwts.builder()
                .claim("userid",user.getUid()) // 设置载荷信息
                .claim("blocked",user.getBlocked())
                .signWith(signatureAlgorithm, signingKey);

        //生成JWT
        return builder.compact();
    }

    /**
     * 获取token中的参数
     *
     * @param token
     * @return
     */
    public static DecodedJWT parseToken(String token) {

        DecodedJWT jwt = null;
        try {
            jwt = JWT.decode(token);
            return jwt;
        } catch (JWTDecodeException exception) {
            //Invalid token

        }
        return jwt;
    }


}
