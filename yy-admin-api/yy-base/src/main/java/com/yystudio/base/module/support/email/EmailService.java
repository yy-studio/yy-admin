package com.yystudio.base.module.support.email;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    // 从配置文件中读取
    @Value("${spring.mail.username}")
    private String fromEmail;

    public boolean sendVerificationCode(String toEmail, String code) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(toEmail);
            message.setSubject("【建工普法】APP验证码");
            message.setText("您的验证码为: " + code);
            message.setFrom(fromEmail);

            javaMailSender.send(message);
            return true;
        } catch (Exception e) {
            log.error("Failed to send email", e);
            return false;
        }
    }
}