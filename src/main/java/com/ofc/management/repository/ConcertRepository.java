package com.ofc.management.repository;

import com.ofc.management.model.Concert;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ConcertRepository extends JpaRepository<Concert, Integer> {
    Optional<List<Concert>> findByTitle(String title);
}
