package com.boolcamp.AssignmentFive.controllers;
import com.boolcamp.AssignmentFive.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/SetPassLogin")
    public ResponseEntity<String> setPassLogin(@RequestParam String email, @RequestParam String password) {
        String result = userService.setPassLogin(email, password);
        if (!result.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }
        return ResponseEntity.ok("User created successfully");
    }

    @PostMapping("/PassLogin")
    public ResponseEntity<String> passLogin(@RequestParam String email, @RequestParam String password) {
        String token = userService.passLogin(email, password);
        if (token.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Incorrect email or password");
        }
        return ResponseEntity.ok(token);
    }

    @PostMapping("/UpdatePass")
    public ResponseEntity<String> updatePass(@RequestParam String email, @RequestParam String password, @RequestParam String newPass) {
        String result = userService.updatePass(email, password, newPass);
        return ResponseEntity.ok(result);
    }
<<<<<<< HEAD
=======

>>>>>>> origin/main
    @PostMapping("/ResetPass")
    public ResponseEntity<?> requestPasswordReset(@RequestParam String email) {
        try {
            String response = userService.requestPasswordReset(email);
            return ResponseEntity.ok(Map.of("success", true, "error", response));
        } catch (MessagingException e) {
            return ResponseEntity.ok(Map.of("success", false, "error", e.getMessage()));
        }
    }

    @PostMapping("/ResetLink")
<<<<<<< HEAD
    public ResponseEntity<?> resetPassword(@RequestParam String email,@RequestParam String newPass, @RequestParam String token) {
        String response = userService.resetPassword(email,newPass, token);
=======
    public ResponseEntity<?> resetPassword(@RequestParam String token, @RequestParam String password) {
        String response = userService.resetPassword(token, password);
>>>>>>> origin/main
        return ResponseEntity.ok(Map.of("success", true, "error", response));
    }
}
