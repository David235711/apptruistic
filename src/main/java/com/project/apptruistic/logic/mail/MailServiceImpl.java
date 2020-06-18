package com.project.apptruistic.logic.mail;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.stereotype.Service;


@Service
public class MailServiceImpl {


    private final JavaMailSender mailSender;

    public MailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendVerification(String userName, String userEmail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(userEmail);
        message.setSubject("Verification email");
        String mailBody = "Hello " + userName + " , Thank you for your registration";
        message.setText(mailBody);

        this.mailSender.send(message);
    }
}