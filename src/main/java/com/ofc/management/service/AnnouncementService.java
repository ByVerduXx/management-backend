package com.ofc.management.service;

import com.ofc.management.model.Announcement;
import com.ofc.management.model.dto.AnnouncementRequestDTO;
import com.ofc.management.model.dto.AnnouncementResponseDTO;
import com.ofc.management.model.mapper.AnnouncementMapper;
import com.ofc.management.repository.AnnouncementRepository;
import com.ofc.management.repository.UserRepository;
import com.ofc.management.security.JWTService;
import com.ofc.management.service.exception.AnnouncementDoesNotExist;
import com.ofc.management.service.exception.TitleCannotBeVoid;
import com.ofc.management.service.exception.UserDoesNotExist;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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

    public void deleteAnnouncement(Integer id) {
        Announcement announcement = announcementRepository.findById(id).orElseThrow(AnnouncementDoesNotExist::new);
        announcementRepository.delete(announcement);
    }

    public List<AnnouncementResponseDTO> findAll() {
        return announcementMapper.toAnnouncementResponseDTOs(announcementRepository.findAllByOrderByDateDesc());
    }

    public AnnouncementResponseDTO findAnnouncementById(Integer id) {
        return announcementMapper.toAnnouncementResponseDTO(announcementRepository.findById(id).orElseThrow(AnnouncementDoesNotExist::new));
    }

    public AnnouncementResponseDTO updateAnnouncement(Integer id, AnnouncementRequestDTO announcementRequestDTO) {

        if (announcementRequestDTO.getTitle() == null || announcementRequestDTO.getTitle().equals("")) {
            throw new TitleCannotBeVoid();
        }

        Announcement announcement = announcementRepository.findById(id).orElseThrow(AnnouncementDoesNotExist::new);
        announcement.setTitle(announcementRequestDTO.getTitle());
        announcement.setContent(announcementRequestDTO.getContent());
        announcement.setDate(LocalDateTime.now());
        announcementRepository.save(announcement);
        return announcementMapper.toAnnouncementResponseDTO(announcement);
    }
}
