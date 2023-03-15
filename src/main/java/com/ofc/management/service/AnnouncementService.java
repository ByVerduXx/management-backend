package com.ofc.management.service;

import com.ofc.management.model.Announcement;
import com.ofc.management.model.dto.AnnouncementRequestDTO;
import com.ofc.management.model.dto.AnnouncementResponseDTO;
import com.ofc.management.model.mapper.AnnouncementMapper;
import com.ofc.management.repository.AnnouncementRepository;
import com.ofc.management.repository.UserRepository;
import com.ofc.management.security.JWTService;
import com.ofc.management.service.exception.UserDoesNotExist;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnnouncementService {

    private final AnnouncementRepository announcementRepository;

    private final UserRepository userRepository;

    private final AnnouncementMapper announcementMapper;

    private final JWTService jwtService;

    public AnnouncementResponseDTO createAnnouncement(AnnouncementRequestDTO announcementRequestDTO, String token) {
        Announcement announcement = announcementMapper.toAnnouncement(announcementRequestDTO);
        announcement.setUser(userRepository.findFirstByUsername(jwtService.extractUsername(token)).orElseThrow(UserDoesNotExist::new));
        announcementRepository.save(announcement);
        return announcementMapper.toAnnouncementResponseDTO(announcement);
    }
}
