package com.boolcamp.AssignmentFive.services;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.Properties;

@Service
public class EmailService {
    private final String SMTP_SERVER = "smtp.gmail.com";
    private final String SMTP_PORT = "587";
    private final String EMAIL_ADDRESS = "email@gmail.com";
    private final String EMAIL_PASSWORD = "password";

    public void sendPasswordResetEmail(String recipientEmail, String resetLink) throws MessagingException {
        String subject = "Password reset link";
        String body = "Please click the link below to reset your password:\n\n" + resetLink;

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", SMTP_SERVER);
        props.put("mail.smtp.port", SMTP_PORT);

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(EMAIL_ADDRESS, EMAIL_PASSWORD);
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(EMAIL_ADDRESS));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
        message.setSubject(subject);
        message.setText(body);

        Transport.send(message);
    }
}
