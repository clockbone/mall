package com.clockbone.security;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.DigestUtils;

import java.io.UnsupportedEncodingException;

/**
 * Created by qinjun on 2016/2/22.
 */
public class PasswordEncoderServiceImpl implements PasswordEncoder {

    @Override
    public String encode(CharSequence rawPassword) {
        try {
            // MD5加密密码
            return DigestUtils.md5DigestAsHex(rawPassword.toString().getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encode(rawPassword).equals(encodedPassword);
    }
}
