package com.cpcnet.mc.mcgateway.utils;

import com.google.common.hash.Hashing;
import org.apache.commons.lang3.StringUtils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * @Description: 加密
 * @Author: Ebon Zheng
 * @Date: 2023/8/1
 */
@SuppressWarnings("UnstableApiUsage")
public class CipherUtil {
    private CipherUtil() {
    }

    /**
     * 获取字符串MD5摘要
     */
    public static String getMd5(String str) {
        if (str == null) {
            throw new IllegalArgumentException("content cannot be null");
        }
        return Hashing.md5().hashBytes(str.getBytes()).toString();
    }

    public static String getSha256(String str) {
        if (str == null) {
            throw new IllegalArgumentException("content cannot be null");
        }
        return Hashing.sha256().hashBytes(str.getBytes()).toString();
    }

    /**
     * Base64编码
     *
     * @param content 待编码的原文
     * @return 编码后的base 64 code
     */
    public static String base64Encode(String content) {
        return StringUtils.isEmpty(content) ? "" : Base64.getEncoder().encodeToString(content.getBytes());
    }

    /**
     * Base64解码
     *
     * @param encodedContent 待解码的Base64编码
     * @return 解码后的byte[]
     */
    public static String base64Decode(String encodedContent) {
        return StringUtils.isEmpty(encodedContent) ? "" :
                new String(Base64.getDecoder().decode(encodedContent), StandardCharsets.UTF_8);
    }

    /**
     * 十六进制转ABCD
     * <p>
     * 例：1）1->A; 2)2->B 3)11->AE
     * </p>
     *
     * @param hex 十六进制
     * @return 字母串
     */
    public static String hex2alphabet(String hex) {
        int val = Integer.parseInt(hex, 16);
        StringBuilder alphabets = new StringBuilder();
        char alphabet = 'A';
        while (val != 0) {
            if ((val & 1) != 0) {
                alphabets.append(alphabet);
            }
            val >>= 1;
            alphabet++;
        }
        if (alphabets.length() == 0) {
            alphabets.append("#");
        }
        return alphabets.toString();
    }

    /**
     * 字母串转16进制
     * <p>
     * 例：1）A->1; 2)B->2 3)ABCD->f
     * </p>
     *
     * @param alphabets 字母串
     * @return 16进制
     */
    public static String alphabet2hex(String alphabets) {
        if (StringUtils.isBlank(alphabets)) {
            return "0";
        }
        int dec = 0;
        for (int i = 0; i < alphabets.length(); i++) {
            char alphabet = Character.toUpperCase(alphabets.charAt(i));
            dec += (int) Math.pow(2, alphabet - (double) 65);
        }
        return Integer.toHexString(dec);
    }

    /**
     * MD5加码 生成32位md5码
     *
     * @param buffer 原字符串
     * @return md5加密
     */
    public static String string2Md5(String buffer) {
        String string = null;
        char hexDigist[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(buffer.getBytes());
            byte[] datas = md.digest(); //16个字节的长整数

            char[] str = new char[2 * 16];
            int k = 0;

            for (int i = 0; i < 16; i++) {
                byte b = datas[i];
                str[k++] = hexDigist[b >>> 4 & 0xf];//高4位
                str[k++] = hexDigist[b & 0xf];//低4位
            }
            string = new String(str);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return string;
    }
}
