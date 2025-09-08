package com.example;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // (1) Marks this interface as a Spring component
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
}
