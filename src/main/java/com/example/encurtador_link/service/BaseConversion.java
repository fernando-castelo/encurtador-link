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

}
