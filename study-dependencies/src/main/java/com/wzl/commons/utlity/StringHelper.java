package com.wzl.commons.utlity;

import org.springframework.util.DigestUtils;

public class StringHelper {
    /**
     * 生成md5
     * @param
     * @return
     */
    public static String getMD5(String str) {
        String md5 = DigestUtils.md5DigestAsHex(str.getBytes());
        return md5;
    }


}
