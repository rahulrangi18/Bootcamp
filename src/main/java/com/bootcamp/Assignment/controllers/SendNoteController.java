package com.bootcamp.Assignment.controllers;

import com.bootcamp.Assignment.services.SendNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

@RestController
public class SendNoteController {
    @Autowired
    private SendNoteService sendNoteService;

    @PostMapping("/EmailNote")
    public ResponseEntity<String> sendNoteEmail(@RequestParam String email, @RequestParam String note_id) {
        try {
            String response = sendNoteService.sendNoteEmail(email,note_id);
            System.out.println(response);
            return ResponseEntity.ok(response);
        } catch (MessagingException e) {
            return ResponseEntity.ok("Unable to Send Email to "+email);
        }
    }
}