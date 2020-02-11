package com.danbai.dblogin.utils;

import org.springframework.util.DigestUtils;

/**
 * @author danbai
 * @date 2020-02-04 17:34
 */
public class StringUtils {
    private static String md5str = "DanBai";

    /**
     * md5加密
     *
     * @param str 加密的字符串
     * @return MD5
     */
    public static String md5(String str) {
        return DigestUtils.md5DigestAsHex((md5str + str).getBytes());
    }

    /**
     * 字符串空长度判断
     *
     * @param str
     * @return
     */
    public static boolean hasLength(String str) {
        return str != null && !"".equals(str.trim());
    }
}
