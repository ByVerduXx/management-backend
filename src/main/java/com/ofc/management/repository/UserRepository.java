package com.ofc.management.repository;

import com.ofc.management.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findFirstByUsername(String s);

    User findUserById(Integer id);
}
