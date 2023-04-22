package com.bootcamp.Assignment.services;

import com.bootcamp.Assignment.models.User;
import com.bootcamp.Assignment.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.UUID;

@Service

public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EmailService emailService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public String setPassLogin(String email, String password) {
        User existingUser = userRepository.findByEmail(email);
        if (existingUser != null) {
            return "User already exists";
        }
        User user = new User();
        user.setEmail(email);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        userRepository.save(user);
        return "Signup Successful";
    }

    public String passLogin(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            return "User does not exist";
        }
        if (!bCryptPasswordEncoder.matches(password, user.getPassword())) {
            return "Incorrect password";
        }
        String token = generateToken();
        userRepository.delete(user);
        user.setToken(token);
        userRepository.save(user);
        return "Login Successful with token: "+ token;
    }

    public String updatePass(String email, String password, String newPass) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            return "User does not exist";
        }
        if (!bCryptPasswordEncoder.matches(password, user.getPassword())) {
            return "Incorrect password";
        }
        userRepository.delete(user);
        user.setPassword(bCryptPasswordEncoder.encode(newPass));
        userRepository.save(user);
        return "Password Updated";
    }

    public String requestPasswordReset(String email) throws MessagingException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            return "User does not exist";
        }
        String resetToken = generateToken();
        user.setResetToken(resetToken);
        userRepository.save(user);

        String resetLink = "Your reset token is :" + resetToken;
        emailService.sendPasswordResetEmail(email, resetLink);
        return "Password reset token sent to " + email;
    }

    public String resetPassword(String email, String newPassword, String resetToken) {
        User user = userRepository.findByResetToken(resetToken);
        if (user==null) {
            return "Incorrect reset token for email "+ email;
        }
        userRepository.delete(user);
        user.setPassword(bCryptPasswordEncoder.encode(newPassword));
        user.setResetToken(null);
        userRepository.save(user);
        return "Password reset successfully";
    }

    private String generateToken() {
        return UUID.randomUUID().toString();
    }
}
