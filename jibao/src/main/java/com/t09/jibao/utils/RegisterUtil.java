package com.t09.jibao.utils;

import java.util.Random;

public class RegisterUtil {
    public static String generateEmailCaptcha() {
        StringBuilder captcha = new StringBuilder();
        Random random = new Random();
        int numbers = 6;
        for (int i = 0; i < numbers; i++) {
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            if ("char".equalsIgnoreCase(charOrNum)) {
                int choice = random.nextInt(2) % 2 == 0 ? 65 : 97;
                captcha.append((char) (choice + random.nextInt(26)));
            } else {
                captcha.append(random.nextInt(10));
            }
        }
        return captcha.toString().toLowerCase();
    }
}
