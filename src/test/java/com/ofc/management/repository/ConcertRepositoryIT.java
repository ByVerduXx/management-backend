package com.ofc.management.repository;

import com.ofc.management.model.Concert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class ConcertRepositoryIT {

    @Autowired
    private ConcertRepository concertRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @BeforeEach
    void setup() {
        testEntityManager.getEntityManager().createQuery("delete from concerts").executeUpdate();
    }

    @Test
    void shouldSaveConcert() {
        Concert concert = new Concert("Concierto de Navidad", "Concierto navidad 2029", LocalDateTime.of(2029, 12, 25, 20, 0), LocalDateTime.of(2029, 12, 25, 19, 0), "https://www.youtube.com/watch?v=1");
        concertRepository.save(concert);
        Concert concertDB = testEntityManager.getEntityManager().createQuery("from concerts", Concert.class).getSingleResult();
        assertEquals(concert, concertDB);
    }

    @Test
    void shouldFindAllConcerts() {
        testEntityManager.persist(new Concert("Concierto de Navidad", "Concierto navidad 2029", LocalDateTime.of(2029, 12, 25, 20, 0), LocalDateTime.of(2029, 12, 25, 19, 0), "https://www.youtube.com/watch?v=1"));
        testEntityManager.persist(new Concert("Concierto de Navidad", "Concierto navidad 2029", LocalDateTime.of(2029, 12, 25, 20, 0), LocalDateTime.of(2029, 12, 25, 19, 0), "https://www.youtube.com/watch?v=1"));
        List<Concert> concerts = concertRepository.findAll();
        assertEquals(2, concerts.size());
    }

    @Test
    void shouldFindConcertByTitle() {
        testEntityManager.persist(new Concert("Concierto de Navidad", "Concierto navidad 2029", LocalDateTime.of(2029, 12, 25, 20, 0), LocalDateTime.of(2029, 12, 25, 19, 0), "https://www.youtube.com/watch?v=1"));
        testEntityManager.persist(new Concert("Concierto de Navidad", "Concierto navidad 2029", LocalDateTime.of(2029, 12, 25, 20, 0), LocalDateTime.of(2029, 12, 25, 19, 0), "https://www.youtube.com/watch?v=1"));
        List<Concert> concerts = concertRepository.findByTitle("Concierto de Navidad");
        assertEquals(2, concerts.size());
    }

    @Test
    void shouldFindConcertById() {
        Concert concert = new Concert("Concierto de Navidad", "Concierto navidad 2029", LocalDateTime.of(2029, 12, 25, 20, 0), LocalDateTime.of(2029, 12, 25, 19, 0), "https://www.youtube.com/watch?v=1");
        concert = testEntityManager.persist(concert);
        Concert concertDB = concertRepository.findById(concert.getId()).get();
        assertEquals(concert, concertDB);
    }

    @Test
    void shouldDeleteConcert() {
        Concert concert = new Concert("Concierto de Navidad", "Concierto navidad 2029", LocalDateTime.of(2029, 12, 25, 20, 0), LocalDateTime.of(2029, 12, 25, 19, 0), "https://www.youtube.com/watch?v=1");
        concert = testEntityManager.persist(concert);
        concertRepository.delete(concert);
        List<Concert> concerts = concertRepository.findAll();
        assertEquals(0, concerts.size());
    }

    @Test
    void shouldUpdateConcert() {
        Concert concert = new Concert("Concierto de Navidad", "Concierto navidad 2029", LocalDateTime.of(2029, 12, 25, 20, 0), LocalDateTime.of(2029, 12, 25, 19, 0), "https://www.youtube.com/watch?v=1");
        concert = testEntityManager.persist(concert);
        concert.setTitle("Concierto de Navidad 2029");
        concertRepository.save(concert);
        Concert concertDB = testEntityManager.getEntityManager().createQuery("from concerts", Concert.class).getSingleResult();
        assertEquals(concert, concertDB);
    }
}
