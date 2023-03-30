package com.ofc.management.mapper;

import com.ofc.management.model.Notification;
import com.ofc.management.model.dto.NotificationResponseDTO;
import com.ofc.management.model.mapper.NotificationMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NotificationMapperTest {

    private final NotificationMapper notificationMapper = new NotificationMapper();

    private final Notification DUMMY_NOTIFICATION = new Notification(1, "title", "content", LocalDateTime.of(2021, 7, 21, 1, 0), null, null);
    private final NotificationResponseDTO DUMMY_NOTIFICATION_RESPONSE_DTO = new NotificationResponseDTO(1, "title", "content", LocalDateTime.of(2021, 7, 21, 1, 0));

    @Test
    @DisplayName("Test toNotificationResponseDTO method")
    void testToNotificationResponseDTO() {

        NotificationResponseDTO notificationResponseDTO = notificationMapper.toNotificationResponseDTO(DUMMY_NOTIFICATION);

        assertEquals(DUMMY_NOTIFICATION_RESPONSE_DTO, notificationResponseDTO);
    }

    @Test
    @DisplayName("Test toNotificationResponseDTOs method")
    void testToNotificationResponseDTOs() {

        assertEquals(1, notificationMapper.toNotificationResponseDTOs(List.of(DUMMY_NOTIFICATION)).size());
        assertEquals(DUMMY_NOTIFICATION_RESPONSE_DTO, notificationMapper.toNotificationResponseDTOs(List.of(DUMMY_NOTIFICATION)).get(0));
    }
}
