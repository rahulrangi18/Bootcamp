package com.boolcamp.AssignmentFive.services;

import com.boolcamp.AssignmentFive.models.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

@Service
public class SendNoteService {

    private final EmailService emailService;

    @Autowired
    public SendNoteService(EmailService emailService) {
        this.emailService = emailService;
    }

    public void sendNoteEmail(Email email) throws MessagingException {
        String recipientEmail = email.getEmail();
        int noteId = email.getNote_id();
        String subject = "Note #" + noteId + " shared with you";
        String body = "Group-2 Bootcamp shared this Note #" + noteId;

        emailService.sendEmail(recipientEmail, subject, body);
    }
}
