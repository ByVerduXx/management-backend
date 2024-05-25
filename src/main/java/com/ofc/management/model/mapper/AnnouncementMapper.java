package com.ofc.management.model.mapper;

import com.ofc.management.model.Announcement;
import com.ofc.management.model.dto.AnnouncementRequestDTO;
import com.ofc.management.model.dto.AnnouncementResponseDTO;
import com.ofc.management.service.exception.TitleCannotBeVoid;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class AnnouncementMapper {

    public Announcement toAnnouncement(AnnouncementRequestDTO announcementRequestDTO) {
        Announcement announcement = new Announcement();

        if (announcementRequestDTO.getTitle() == null || announcementRequestDTO.getTitle().equals("")) {
            throw new TitleCannotBeVoid();
        }

        announcement.setTitle(announcementRequestDTO.getTitle());
        announcement.setContent(announcementRequestDTO.getContent());
        announcement.setDate(LocalDateTime.now());

        return announcement;
    }

    public AnnouncementResponseDTO toAnnouncementResponseDTO(Announcement announcement) {
        AnnouncementResponseDTO announcementResponseDTO = new AnnouncementResponseDTO();

        announcementResponseDTO.setId(announcement.getId());
        announcementResponseDTO.setTitle(announcement.getTitle());
        announcementResponseDTO.setContent(announcement.getContent());
        announcementResponseDTO.setDate(announcement.getDate());
        announcementResponseDTO.setUsername(announcement.getUser().getUsername());

        return announcementResponseDTO;
    }

    public List<AnnouncementResponseDTO> toAnnouncementResponseDTOs(List<Announcement> announcements) {
        return announcements.stream().map(this::toAnnouncementResponseDTO).toList();
    }
}
