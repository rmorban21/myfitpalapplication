package com.decadev.utils;

public class UsernameValidator {
    public static boolean isValidUsername(String username) {
        if (username == null || username.length() < 6 || username.length() > 12) {
            return false;
        }
        return username.matches("[a-zA-Z0-9]+");
    }
}
