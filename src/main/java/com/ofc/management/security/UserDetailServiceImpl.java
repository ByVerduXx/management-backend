package com.ofc.management.security;

import com.ofc.management.model.User;
import com.ofc.management.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository
                .findFirstByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));

        System.out.println("User: " + user);

        return new UserDetailsImpl(user);
    }
}
