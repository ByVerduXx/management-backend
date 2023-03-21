package com.ofc.management.repository;

import com.ofc.management.model.MusicianConcert;
import com.ofc.management.model.MusicianConcertPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MusicianConcertRepository extends JpaRepository<MusicianConcert, MusicianConcertPK> {
}
