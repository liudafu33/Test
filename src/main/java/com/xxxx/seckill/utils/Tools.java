package com.xxxx.seckill.utils;

import org.apache.commons.codec.digest.DigestUtils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Tools {

    public static void main(String[] args) {
        //3d9188577cc9bfe9291ac66b5cc872b7
        System.out.println(getMD5String("123465"));
        //E10ADC3949BA59ABBE56E057F20F883E
        System.out.println(MD5("123456"));
        //aeaeb0b96e7f79608da8da42d803ead9
        System.out.println(MD5encrypt("123456"));
        System.out.println(verify("1234567","aeaeb0b96e7f79608da8da42d803ead9"));
    }
    /**
     * md5简单使用实现
     * @param str
     * @return
     */
    public static String getMD5String(String str) {
        try {
            // 生成一个MD5加密计算摘要（如果想使用sha1算法，可以替换成"SHA1"）
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 计算md5函数
            md.update(str.getBytes());
            // digest()最后确定返回md5 hash值，返回值为8位字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            //一个byte是八位二进制，也就是2位十六进制字符（2的8次方等于16的2次方）
            return new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * MD5算法加密 （公司使用）
     * @param source
     * @return
     */
    public static String MD5(String source) {
        System.out.println(source);
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(source.getBytes());
            byte[] b = md.digest();
            StringBuffer sb = new StringBuffer();
            for (byte c : b) {
                int val = ((int) c) & 0xff;
                if (val < 16)
                    sb.append("0");
                sb.append(Integer.toHexString(val));
            }
            return sb.toString().toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * MD5算法加盐 （更复杂加密方式）
     * @param source
     * @return
     */
    private static final String MD5KEY = "fadsjlfjeohkdfgjlk423211";

    /**
     * MD5算法加盐 （更复杂加密方式）
     * @param text 明文
     * @return 密文
     */
    public static String MD5encrypt(String text){
        //加密后的字符串
        String encodeStr = DigestUtils.md5Hex(text + MD5KEY);
        return encodeStr;
    }

    /**
     * MD5验证方法
     * @param text 明文
     * @param md5 密文
     * @return true/false
     */
    public static boolean verify(String text, String md5){
        //根据传入的密钥进行验证
        String md5Text = MD5encrypt(text);
        if (md5Text.equalsIgnoreCase(md5)){
            return true;
        }
        return false;
    }
}
