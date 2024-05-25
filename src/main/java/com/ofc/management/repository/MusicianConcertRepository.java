package com.ofc.management.repository;

import com.ofc.management.model.MusicianConcert;
import com.ofc.management.model.MusicianConcertPK;
import com.ofc.management.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MusicianConcertRepository extends JpaRepository<MusicianConcert, MusicianConcertPK> {
    List<MusicianConcert> findAllByUser(User user);
}
