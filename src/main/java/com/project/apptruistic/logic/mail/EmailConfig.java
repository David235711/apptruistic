package com.project.apptruistic.logic.mail;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
@ConfigurationProperties("spring")
public class EmailConfig {

    private MailSetting mailsetting;

    public MailSetting getMailSetting() {
        return mailsetting;
    }

    public void setMailSetting(MailSetting mailSetting) {
        this.mailsetting = mailSetting;
    }

    @Bean
    public JavaMailSender getMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        mailSender.setHost(mailsetting.getHost());
        mailSender.setPort(mailsetting.getPort());
        mailSender.setUsername(mailsetting.getUsername());
        mailSender.setPassword(mailsetting.getPassword());

        Properties javaMailProperties = mailSender.getJavaMailProperties();
        ;
        javaMailProperties.put("mail.smtp.starttls.enable", "true");
        javaMailProperties.put("mail.smtp.auth", "true");
        javaMailProperties.put("mail.transport.protocol", "smtp");
        javaMailProperties.put("mail.debug", "true");
        return mailSender;
    }
}
