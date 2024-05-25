package com.ofc.management.repository;

import com.ofc.management.model.Concert;
import com.ofc.management.model.Rehersal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RehersalRepository extends JpaRepository<Rehersal, Integer> {
}
