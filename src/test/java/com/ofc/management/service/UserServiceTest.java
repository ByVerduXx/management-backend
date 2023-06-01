package com.ofc.management.service;

import com.ofc.management.model.mapper.UserMapper;
import com.ofc.management.repository.InstrumentRepository;
import com.ofc.management.repository.UserRepository;
import com.ofc.management.security.JWTService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.mockito.Mockito.mock;

public class UserServiceTest {

    private final UserRepository userRepository = mock(UserRepository.class);
    private final InstrumentRepository instrumentRepository = mock(InstrumentRepository.class);
    private final UserMapper userMapper = mock(UserMapper.class);
    private final BCryptPasswordEncoder bCryptPasswordEncoder = mock(BCryptPasswordEncoder.class);
    private final JWTService jwtService = mock(JWTService.class);
    //private final UserService userService = new UserService(userRepository, instrumentRepository, userMapper, bCryptPasswordEncoder, jwtService);
}
