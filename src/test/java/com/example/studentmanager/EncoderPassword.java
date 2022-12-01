package com.example.studentmanager;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncoderPassword {
    public static void main(String[]args){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "hoang123";
        String encoderPassword = encoder.encode(rawPassword);
        System.out.println(encoderPassword);
    }
}
