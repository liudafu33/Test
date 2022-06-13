package com.xxxx.seckill.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;

@Component
public class MD5Util {

    public static String md5(String src){
        return DigestUtils.md5Hex(src);
    }

    private static final String salt="1a2b3c4d";

    public static String inputPassToFromPass(String inputPass){
        String str =""+salt.charAt(0)+salt.charAt(2)+inputPass+salt.charAt(5)+salt.charAt(4);
        return md5(str);
    }

    public static String fromPassToDBPass(String fromPass,String salt){
        String str =""+salt.charAt(0)+salt.charAt(2)+fromPass+salt.charAt(5)+salt.charAt(4);
        return md5(str);
    }

    public static String inputPassToDBPass(String inputPass,String stal){
        String fromPass=inputPassToFromPass(inputPass);
        String dbPass = fromPassToDBPass(fromPass, stal);
        return dbPass;
    }

    public static void main(String[] args) {
        //0491e579a2ce2bd252ce726fdc608d79
        System.out.println(inputPassToFromPass("123456"));

        //78314b9a4b54f73c0e8dd8d4b9de4228
        System.out.println(fromPassToDBPass("b7797cce01b4b131b433b6acf4add449",salt));


        //2015c4d0dace20adea9fff7e9570a4f6
        System.out.println(inputPassToDBPass("123456",salt));

    }
}
