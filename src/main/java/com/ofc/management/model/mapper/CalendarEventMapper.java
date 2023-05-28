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
        calendarEventDTO.setStart(concert.getDate());
        //TODO: add place to concerts
        //calendarEventDTO.setPlace(concert.getPlace());
        calendarEventDTO.setType("C");

        return calendarEventDTO;
    }

    public CalendarEventDTO fromRehersalToCalendarEventDTO(Rehersal rehersal) {
        CalendarEventDTO calendarEventDTO = new CalendarEventDTO();

        calendarEventDTO.setId(rehersal.getId());
        String title = "Ensayo de " + rehersal.getConcert().getTitle();
        calendarEventDTO.setTitle(title);
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
