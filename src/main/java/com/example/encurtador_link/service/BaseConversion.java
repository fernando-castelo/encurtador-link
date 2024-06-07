package com.example.encurtador_link.service;

import org.springframework.stereotype.Service;

@Service
public class BaseConversion {
    private static final String BASE62 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public String encode(long input) {
        StringBuilder sb = new StringBuilder();
        while (input > 0) {
            sb.append(BASE62.charAt((int) (input % 62)));
            input /= 62;
        }
        return sb.reverse().toString();
    }

    public Long decode(String str) {
        long num = 0;
        for (int i = 0; i < str.length(); i++) {
            num = num * 62 + BASE62.indexOf(str.charAt(i));
        }
        return num;
    }

}
