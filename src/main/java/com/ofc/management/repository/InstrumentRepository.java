package com.ofc.management.repository;

import com.ofc.management.model.Instrument;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InstrumentRepository extends JpaRepository<Instrument, Integer> {
    Optional<Instrument> findFirstByName(String name);
}
