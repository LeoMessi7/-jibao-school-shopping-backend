package com.t09.jibao.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.io.*;
import java.util.Random;

public class Utils {

    @Autowired
    private static JavaMailSenderImpl mailSender;

    @Value("${spring.mail.username}")
    private static String from;


    public static byte[] getBytesByStream(InputStream inputStream) {
        byte[] bytes = new byte[1024];
        int b;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            while ((b = inputStream.read(bytes)) != -1) {
                byteArrayOutputStream.write(bytes, 0, b);
            }
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String generateEmailCaptcha() {
        String captcha = "";
        Random random = new Random();
        int numbers = 6;
        for (int i = 0; i < numbers; i++) {
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            if ("char".equalsIgnoreCase(charOrNum)) {
                int choice = random.nextInt(2) % 2 == 0 ? 65 : 97;
                captcha += (char) (choice + random.nextInt(26));
            } else if ("num".equalsIgnoreCase(charOrNum)) {
                captcha += String.valueOf(random.nextInt(10));
            }
        }
        return captcha.toLowerCase();
    }


    public static String readHTMLFile(String path) throws IOException {
        FileReader fileReader = new FileReader(path);
        BufferedReader reader = new BufferedReader(fileReader);
        StringBuilder html = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            html.append(line + '\n');
        }
        reader.close();
        System.out.println(html);
        return html.toString();
    }


}
