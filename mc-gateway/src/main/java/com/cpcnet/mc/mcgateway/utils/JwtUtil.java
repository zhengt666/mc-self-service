package com.cpcnet.mc.mcgateway.utils;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: token
 * @Author: Ebon Zheng
 * @Date: 2023/8/1
 */
public class JwtUtil {
    /**
     * 秘钥：需要妥善保存，一旦泄露token有可能被破解
     */
    private static final String SECRET = "#$@!FX()!4515JF%JS$KLS*KFH##%#QPCIXMSJ";
    /**
     * 签名算法：推荐使用HMAC256
     */
    private static final Algorithm ALGORITHM = Algorithm.HMAC256(SECRET);

    /**
     * 设置默认过期时间 1000*60*60=3600000=1h
     */
    private static final Long EXPIRE_DATE = 3600000L;

    /**
     * 获取token信息
     *
     * @param claimMap 自定义的条件
     * @return token令牌
     */
    public static String createJwtToken(Map<String, Object> claimMap) {
        return createJwtToken(claimMap, EXPIRE_DATE);
    }

    /**
     * @param claimMap 自定义条件
     * @param expire   过期时间 单位：毫秒
     * @return token令牌
     */
    public static String createJwtToken(Map<String, Object> claimMap, Long expire) {
        JWTCreator.Builder builder = JWT.create();
        builder.withClaim("body", claimMap);
        builder.withExpiresAt(new Date(System.currentTimeMillis() + expire));
        return builder.sign(ALGORITHM);
    }

    /**
     * 获取DecodedJWT
     *
     * @param token token令牌
     * @return DecodedJWT
     */
    public static DecodedJWT parseJwtToken(String token) {
        return JWT.require(ALGORITHM).build().verify(token);
    }

    public static Map<String, Object> parseTokenClaim(String token) {
        Map<String, Object> result = null;
        try {
            result = JWT.require(ALGORITHM).build().verify(token).getClaim("body").asMap();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    /**
     * 获取DecodedJWT
     *
     * @param algorithm 指定算法
     * @param token     token令牌
     * @return DecodedJWT
     */
    public static DecodedJWT parseJwtToken(Algorithm algorithm, String token) {
        return JWT.require(algorithm).build().verify(token);
    }


    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", 1);
        String token = JwtUtil.createJwtToken(map);
        System.out.println(token);
        System.out.println(JwtUtil.parseTokenClaim(token));
    }
}
