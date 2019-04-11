package com.methods;

import java.util.Random;

public class Helper {

    public static String generateRandomUserLogin(){

        Random login = new Random();
        String alphabet = "1234567890";
        String userlogin = "";
        for (int i = 0; i < 8; i++) userlogin += login.nextInt(alphabet.length());
        userlogin = userlogin + "@test.com";
        return userlogin;
    }
}
