package com.ys.blog.util;

import org.springframework.util.DigestUtils;

public class MD5 {

    public static void main(String[] args) {
        String base = "abcd";
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        System.out.println(md5);
    }
}
