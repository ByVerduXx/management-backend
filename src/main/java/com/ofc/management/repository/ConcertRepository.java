package com.ofc.management.repository;

import com.ofc.management.model.Concert;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConcertRepository extends JpaRepository<Concert, Integer> {
    List<Concert> findByTitle(String title);
}
