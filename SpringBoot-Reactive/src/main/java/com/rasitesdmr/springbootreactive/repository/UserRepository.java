package com.rasitesdmr.springbootreactive.repository;

import com.rasitesdmr.springbootreactive.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
}
