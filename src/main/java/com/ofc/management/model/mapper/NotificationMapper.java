package com.ofc.management.model.mapper;

import com.ofc.management.model.Notification;
import com.ofc.management.model.dto.NotificationResponseDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NotificationMapper {

    public NotificationResponseDTO toNotificationResponseDTO(Notification notification) {
        NotificationResponseDTO notificationResponseDTO = new NotificationResponseDTO();

        notificationResponseDTO.setId(notification.getId());
        notificationResponseDTO.setTitle(notification.getTitle());
        notificationResponseDTO.setContent(notification.getContent());
        notificationResponseDTO.setDate(notification.getDate());

        return notificationResponseDTO;
    }

    public List<NotificationResponseDTO> toNotificationResponseDTOs(List<Notification> notifications) {
        return notifications.stream().map(this::toNotificationResponseDTO).toList();
    }
}
