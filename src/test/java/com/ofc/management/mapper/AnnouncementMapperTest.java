package com.ofc.management.mapper;

import com.ofc.management.model.Announcement;
import com.ofc.management.model.User;
import com.ofc.management.model.dto.AnnouncementRequestDTO;
import com.ofc.management.model.dto.AnnouncementResponseDTO;
import com.ofc.management.model.mapper.AnnouncementMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class AnnouncementMapperTest {

    private final AnnouncementMapper announcementMapper = new AnnouncementMapper();

    private final User DUMMY_USER = new User(1, "name", "lastName", "username", "password", null, "role");
    private final Announcement DUMMY_ANNOUNCEMENT = new Announcement(1, "Announcement", "Description", LocalDateTime.of(2023,7,21,13,0), DUMMY_USER);
    private final AnnouncementRequestDTO DUMMY_ANNOUNCEMENT_REQUEST_DTO = new AnnouncementRequestDTO("Announcement", "Description");
    private final AnnouncementResponseDTO DUMMY_ANNOUNCEMENT_RESPONSE_DTO = new AnnouncementResponseDTO(1, "Announcement", "Description", LocalDateTime.of(2023,7,21,13,0), "username");

    @Test
    @DisplayName("Test toAnnouncement method")
    void testToAnnouncement() {

        Announcement announcement = announcementMapper.toAnnouncement(DUMMY_ANNOUNCEMENT_REQUEST_DTO);

        assertEquals(DUMMY_ANNOUNCEMENT.getTitle(), announcement.getTitle());
        assertEquals(DUMMY_ANNOUNCEMENT.getContent(), announcement.getContent());
        assertNotNull(announcement.getDate());
    }

    @Test
    @DisplayName("Test toAnnouncementResponseDTO method")
    void testToAnnouncementResponseDTO() {

        AnnouncementResponseDTO announcementResponseDTO = announcementMapper.toAnnouncementResponseDTO(DUMMY_ANNOUNCEMENT);

        assertEquals(DUMMY_ANNOUNCEMENT_RESPONSE_DTO, announcementResponseDTO);
    }

    @Test
    @DisplayName("Test toAnnouncementResponseDTOs method")
    void testToAnnouncementResponseDTOs() {

        List<Announcement> announcements = List.of(DUMMY_ANNOUNCEMENT);

        List<AnnouncementResponseDTO> announcementResponseDTOs = announcementMapper.toAnnouncementResponseDTOs(announcements);

        assertEquals(1, announcementResponseDTOs.size());
        assertEquals(DUMMY_ANNOUNCEMENT_RESPONSE_DTO, announcementResponseDTOs.get(0));
    }


}
