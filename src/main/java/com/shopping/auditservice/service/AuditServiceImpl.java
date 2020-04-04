package com.shopping.auditservice.service;

import com.shopping.auditservice.controller.dto.RecoveryPasswordResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@Slf4j
public class AuditServiceImpl implements AuditService {

    @Value("${email.subject.welcome}")
    private String emailWelcomeSubject;

    @Value("${email.subject.recovery_password}")
    private String emailRecoverySubject;

    private String emailBody;

    private JavaMailSender sender;

    private MailContentBuilder mailContentBuilder;

    public AuditServiceImpl(JavaMailSender sender, MailContentBuilder mailContentBuilder) {
        this.sender = sender;
        this.mailContentBuilder = mailContentBuilder;
    }

    @Override
    public void sendWelcomeEmail(String username, String email) {

        emailBody = mailContentBuilder.build("Welcome " + username);
        MimeMessage message = sender.createMimeMessage();
        log.info("Mail sent successfully");
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(email);
            helper.setSubject(emailWelcomeSubject);
            helper.setText(emailBody,true);
            sender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

    @Override
    public RecoveryPasswordResponseDTO recoveryPassword(String username, String email) {

        MimeMessage message = sender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(email);
            helper.setSubject(emailWelcomeSubject);
            helper.setText("Hey welcome to shopping site");
            sender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return new RecoveryPasswordResponseDTO("123456789");
    }
}
