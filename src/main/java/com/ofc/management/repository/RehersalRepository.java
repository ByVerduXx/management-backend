package com.ofc.management.repository;

import com.ofc.management.model.Rehersal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface RehersalRepository extends JpaRepository<Rehersal, Integer> {
}
