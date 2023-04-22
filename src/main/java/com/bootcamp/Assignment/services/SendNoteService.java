package com.bootcamp.Assignment.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

@Service
public class SendNoteService {
    @Autowired
    private EmailService emailService;

    public String sendNoteEmail(String email,String note_id) throws MessagingException {
        String subject = "Note #" + note_id + " shared with you";
        String body = "Group-2 Bootcamp shared this Note #" + note_id;

        emailService.sendEmail(email, subject, body);
        return "Email Sent Successfully to "+ email;
    }
}