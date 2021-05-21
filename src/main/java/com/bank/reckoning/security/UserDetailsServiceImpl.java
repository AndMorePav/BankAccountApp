package com.bank.reckoning.security;

import com.bank.reckoning.repository.UserRepository;
import com.bank.reckoning.security.model.SecurityUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return SecurityUser.fromUser(userRepository.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException(String.format("User with %s not found", username))));
    }
}
