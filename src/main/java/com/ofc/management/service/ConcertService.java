package com.ofc.management.service;

import com.ofc.management.model.Concert;
import com.ofc.management.model.MusicianConcert;
import com.ofc.management.model.MusicianConcertPK;
import com.ofc.management.model.dto.ConcertRequestDTO;
import com.ofc.management.model.dto.ConcertResponseDTO;
import com.ofc.management.model.dto.MusicianConcertRequestDTO;
import com.ofc.management.model.dto.MusicianConcertResponseDTO;
import com.ofc.management.model.mapper.ConcertMapper;
import com.ofc.management.model.mapper.MusicianConcertMapper;
import com.ofc.management.repository.ConcertRepository;
import com.ofc.management.repository.MusicianConcertRepository;
import com.ofc.management.repository.UserRepository;
import com.ofc.management.service.exception.ConcertDoesNotExist;
import com.ofc.management.service.exception.MusicianConcertDoesNotExist;
import com.ofc.management.service.exception.TitleCannotBeVoid;
import com.ofc.management.service.exception.UserDoesNotExist;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConcertService {

    private final ConcertRepository concertRepository;
    private final ConcertMapper concertMapper;
    private final MusicianConcertMapper musicianConcertMapper;
    private final MusicianConcertRepository musicianConcertRepository;
    private final UserRepository userRepository;

    public ConcertResponseDTO createConcert(ConcertRequestDTO concertRequestDTO) {
        Concert concert = concertMapper.toConcert(concertRequestDTO);
        if (concert.getTitle() == null || concert.getTitle().equals("")) {
            throw new TitleCannotBeVoid();
        }
        concertRepository.save(concert);
        return concertMapper.toConcertResponseDTO(concert);
    }

    public List<ConcertResponseDTO> findAll() {
        return concertMapper.toConcertResponseDTOs(concertRepository.findAll());
    }

    public void deleteConcert(Integer id) {
        Concert concert = concertRepository.findById(id).orElseThrow(ConcertDoesNotExist::new);
        concertRepository.delete(concert);
    }

    public ConcertResponseDTO updateConcert(Integer id, ConcertRequestDTO concertRequestDTO) {

        if (concertRequestDTO.getTitle() == null || concertRequestDTO.getTitle().equals("")) {
            throw new TitleCannotBeVoid();
        }

        Concert concert = concertRepository.findById(id).orElseThrow(ConcertDoesNotExist::new);

        concert.setTitle(concertRequestDTO.getTitle());
        concert.setDescription(concertRequestDTO.getDescription());
        concert.setDate(concertRequestDTO.getDate());
        concert.setSoundcheck(concertRequestDTO.getSoundcheck());
        concert.setScores(concertRequestDTO.getScores());
        concertRepository.save(concert);
        return concertMapper.toConcertResponseDTO(concert);
    }

    public ConcertResponseDTO findById(Integer id) {
        return concertMapper.toConcertResponseDTO(concertRepository.findById(id).orElseThrow(ConcertDoesNotExist::new));
    }

    @Transactional
    public List<MusicianConcertResponseDTO> addMusicians(Integer concertId, List<MusicianConcertRequestDTO> musicianConcertRequestDTOs) {
        List<MusicianConcert> musicianConcerts = musicianConcertMapper.toMusicianConcerts(musicianConcertRequestDTOs);
        musicianConcerts.forEach(musicianConcert -> {
                musicianConcert.setConcert(concertRepository.findById(concertId).orElseThrow(ConcertDoesNotExist::new));
                musicianConcert.setUser(userRepository.findById(musicianConcert.getId().getMusicianId()).orElseThrow(UserDoesNotExist::new));
                musicianConcertRepository.save(musicianConcert);
        });

        return musicianConcertMapper.toMusicianConcertResponseDTOs(musicianConcerts);
    }

    public void deleteMusician(Integer concertId, Integer musicianId) {
        MusicianConcert musicianConcert = musicianConcertRepository.findById(new MusicianConcertPK(musicianId, concertId)).orElseThrow(MusicianConcertDoesNotExist::new);
        musicianConcertRepository.delete(musicianConcert);
    }
}
