package com.bootcamp.Assignment.services;

import com.bootcamp.Assignment.models.EmailObject;
import com.bootcamp.Assignment.repositories.NotesRepository;
import com.bootcamp.Assignment.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.UUID;

@Service
public class SendNoteService {
    @Autowired
    private EmailService emailService;
    @Autowired
    private NotesRepository notesRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    public String sendNoteEmail(String email,String note_id) throws MessagingException {
        String subject = "Bootcamp Group 2 share a note with you";
        String body = "Group-2 Bootcamp shared this Note :" + note_id;

        emailService.sendEmail(email, subject, body);
        EmailObject emailObject=new EmailObject();
        emailObject.setEmail(email);
        emailObject.setNote_id(bCryptPasswordEncoder.encode(note_id));
        notesRepository.save(emailObject);
        return "Email Sent Successfully to "+ email;
    }
}