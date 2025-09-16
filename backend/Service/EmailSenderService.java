package com.example.email.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {

    @Autowired
    private JavaMailSender mailSender;

    private static final String FROM_ADDRESS = "eyagr4444@gmail.com";

    public void envoyer(String to, String subject, String text, byte[] file, String fileName) throws MessagingException {
        if (file != null && fileName != null) {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text, true);
            helper.setFrom(FROM_ADDRESS);
            helper.addAttachment(fileName, new ByteArrayResource(file));
            mailSender.send(message);
        } else {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);
            message.setFrom(FROM_ADDRESS);
            mailSender.send(message);
        }
    }
}
