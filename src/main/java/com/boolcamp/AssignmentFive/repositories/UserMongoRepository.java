package com.boolcamp.AssignmentFive.repositories;

import com.boolcamp.AssignmentFive.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface UserMongoRepository extends MongoRepository<User, String> {
    User findByEmail(String email);

    User findByResetToken(String email);
}
