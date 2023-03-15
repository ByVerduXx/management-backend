package com.ofc.management.repository;

import com.ofc.management.model.Announcement;
import com.ofc.management.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AnnouncementRepository extends JpaRepository<Announcement, Integer> {

    List<Announcement> findAllByUser(User user);
}
