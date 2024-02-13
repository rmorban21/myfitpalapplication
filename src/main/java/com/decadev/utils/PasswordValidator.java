package com.decadev.utils;

import java.util.regex.Pattern;

public class PasswordValidator {
    public static boolean isValidPassword(String password) {
        // regex to validate password
        String passwordRegex = "^(?=.*[0-9a-zA-Z!$*])\\S{6,15}$";
        Pattern pattern = Pattern.compile(passwordRegex);
        return pattern.matcher(password).matches();
    }
}
