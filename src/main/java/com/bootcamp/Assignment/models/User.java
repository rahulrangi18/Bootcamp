package com.bootcamp.Assignment.models;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.beans.Transient;
import java.util.UUID;


@Data
@Entity
@Table(name = "bootcamp_users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(unique = true)
    private String email;

    private String password;

    private String newPassword;

    private String token;

    @Column(name = "reset_token")
    private String resetToken;
}


