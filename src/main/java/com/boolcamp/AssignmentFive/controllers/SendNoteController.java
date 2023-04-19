package com.boolcamp.AssignmentFive.controllers;

import com.boolcamp.AssignmentFive.models.Email;
import com.boolcamp.AssignmentFive.services.SendNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

@RestController
public class SendNoteController {

    private final SendNoteService sendNoteService;

    @Autowired
    public SendNoteController(SendNoteService sendNoteService) {
        this.sendNoteService = sendNoteService;
    }

    @PostMapping("/EmailNote")
    public ResponseEntity<String> sendNoteEmail(@RequestBody Email email) {
        try {
            sendNoteService.sendNoteEmail(email);
            return ResponseEntity.ok("Note email sent successfully");
        } catch (MessagingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error sending note email: " + e.getMessage());
        }
    }
}
