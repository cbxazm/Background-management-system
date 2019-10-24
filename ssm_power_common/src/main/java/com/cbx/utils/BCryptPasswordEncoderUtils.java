package com.cbx.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptPasswordEncoderUtils {
    private static BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
        public static String encodePassword(String password){

                           return bCryptPasswordEncoder.encode(password);
        }

    public static void main(String[] args) {
          String password="234";
          //每次加密后的密码都不一样
        String s = encodePassword(password);//$2a$10$jGSRMAdf3hJ7Pi0CwqKVLeBRXloP8c2S4OcA2rOniSb7ZB3eOIv4S
        System.out.println(s);
    }
}
