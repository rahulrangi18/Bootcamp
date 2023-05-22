package com.bootcamp.Assignment.repositories;

import com.bootcamp.Assignment.models.EmailObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotesRepository extends JpaRepository<EmailObject,String> {

}
