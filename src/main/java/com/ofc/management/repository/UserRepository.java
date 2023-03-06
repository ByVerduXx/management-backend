package com.ofc.management.repository;

import com.ofc.management.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findFirstByUsername(String s);

    Optional<User> findUserById(Integer id);
}
