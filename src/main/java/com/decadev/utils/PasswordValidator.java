package com.decadev.utils;

import java.util.regex.Pattern;

public class PasswordValidator {
    public static boolean isValidPassword(String password) {
        // Use regex to validate password
        String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!$*])(?=\\S+$).{6,15}$";
        Pattern pattern = Pattern.compile(passwordRegex);
        return pattern.matcher(password).matches();
    }
}
