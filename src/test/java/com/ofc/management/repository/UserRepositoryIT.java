package com.ofc.management.repository;

import com.ofc.management.model.Instrument;
import com.ofc.management.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryIT {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @BeforeEach
    void setup() {
        testEntityManager.getEntityManager().createQuery("delete from User").executeUpdate();
    }

    @Test
    void shouldSaveUser() {
        Instrument instrument = new Instrument("Violin");
        instrument = testEntityManager.persist(instrument);
        User user = new User("Daniel", "Verduras Gallego", "daniel.verduras", "mypassword", instrument);
        List<User> users = List.of(new User("Daniel", "Verduras Gallego", "daniel.verduras", "mypassword", instrument));
        userRepository.save(user);
        List<User> userDB = testEntityManager.getEntityManager().createQuery("from User").getResultList();
        assertEquals(users, userDB);
    }

    @Test
    void shouldSaveAdminUser() {
        User user = new User("Daniel", "Verduras Gallego", "daniel.verduras", "mypassword", "CEO");
        List<User> admins = List.of(new User("Daniel", "Verduras Gallego", "daniel.verduras", "mypassword", "CEO"));
        userRepository.save(user);
        List<User> usersDB = testEntityManager.getEntityManager().createQuery("from User").getResultList();
        assertEquals(admins, usersDB);
    }

    @Test
    void shouldFindAllUsers() {
        Instrument instrument = new Instrument("Violin");
        instrument = testEntityManager.persist(instrument);
        testEntityManager.persist(new User("Pedro", "Lopez", "pedro.lopez", "mypassword1", instrument));
        testEntityManager.persist(new User("Daniel", "Verduras Gallego", "daniel.verduras", "mypassword", "CEO"));
        List<User> friends = userRepository.findAll();
        assertEquals(2, friends.size());
    }

    @Test
    void shouldFindUserByUsername() {
        Instrument instrument = new Instrument("Violin");
        instrument = testEntityManager.persist(instrument);
        testEntityManager.persist(new User("Pedro", "Lopez", "pedro.lopez", "mypassword1", instrument));
        testEntityManager.persist(new User("Daniel", "Verduras Gallego", "daniel.verduras", "mypassword", "CEO"));
        User user = userRepository.findFirstByUsername("daniel.verduras").get();
        assertEquals("Daniel", user.getName());

    }

    @Test
    void shouldFindUserById() {
        User user = new User("Daniel", "Verduras Gallego", "daniel.verduras", "mypassword", "CEO");
        testEntityManager.persist(user);
        User userDB = userRepository.findUserById(user.getId()).get();
        assertEquals(user, userDB);
    }

    @Test
    void shouldDeleteUser() {
        User user = new User("Daniel", "Verduras Gallego", "daniel.verduras", "mypassword", "CEO");
        testEntityManager.persist(user);
        userRepository.delete(user);
        List<User> users = userRepository.findAll();
        assertEquals(0, users.size());
    }

    @Test
    void shouldUpdateUser() {
        User user = new User("Daniel", "Verduras Gallego", "daniel.verduras", "mypassword", "CEO");
        testEntityManager.persist(user);
        user.setName("Manolo");
        userRepository.save(user);
        User userDB = userRepository.findUserById(user.getId()).get();
        assertEquals("Manolo", userDB.getName());
    }
}
