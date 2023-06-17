package com.ofc.management.model.mapper;

import com.ofc.management.model.Concert;
import com.ofc.management.model.Rehersal;
import com.ofc.management.model.dto.CalendarEventDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CalendarEventMapper {

    public CalendarEventDTO fromConcertToCalendarEventDTO(Concert concert) {
        CalendarEventDTO calendarEventDTO = new CalendarEventDTO();

        calendarEventDTO.setId(concert.getId());
        calendarEventDTO.setTitle(concert.getTitle());
        calendarEventDTO.setDescription(concert.getDescription());
        if (concert.getSoundcheck() != null) {
            calendarEventDTO.setStart(concert.getSoundcheck());
        } else {
            calendarEventDTO.setStart(concert.getDate());
        }
        calendarEventDTO.setPlace(concert.getPlace());
        calendarEventDTO.setType("C");

        return calendarEventDTO;
    }

    public CalendarEventDTO fromRehersalToCalendarEventDTO(Rehersal rehersal) {
        CalendarEventDTO calendarEventDTO = new CalendarEventDTO();

        calendarEventDTO.setId(rehersal.getConcert().getId());
        String title = "Ensayo de " + rehersal.getConcert().getTitle() + " - " + rehersal.getDate().getDayOfMonth() + "/" + rehersal.getDate().getMonthValue();
        calendarEventDTO.setTitle(title);
        String description = "Ensayo para el concierto del dia " + rehersal.getConcert().getDate().getDayOfMonth() + "/" + rehersal.getConcert().getDate().getMonthValue();
        calendarEventDTO.setDescription(description);
        calendarEventDTO.setStart(rehersal.getDate());
        calendarEventDTO.setPlace(rehersal.getPlace());
        calendarEventDTO.setType("R");

        return calendarEventDTO;
    }

    public List<CalendarEventDTO> fromConcertToCalendarEventDTOs(List<Concert> concerts) {
        return concerts.stream().map(this::fromConcertToCalendarEventDTO).toList();
    }

    public List<CalendarEventDTO> fromRehersalToCalendarEventDTOs(List<Rehersal> rehersals) {
        return rehersals.stream().map(this::fromRehersalToCalendarEventDTO).toList();
    }
}
