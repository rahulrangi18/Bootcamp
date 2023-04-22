package com.bootcamp.Assignment.repositories;

import com.bootcamp.Assignment.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    User findByResetToken(String resetToken);
}
