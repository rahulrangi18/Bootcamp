package com.boolcamp.AssignmentFive.controllers;
import com.boolcamp.AssignmentFive.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
        if (!result.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }
        return ResponseEntity.ok("Password updated successfully");
    }
}
