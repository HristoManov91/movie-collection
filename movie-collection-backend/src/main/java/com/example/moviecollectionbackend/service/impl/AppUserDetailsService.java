package com.example.moviecollectionbackend.service.impl;

import com.example.moviecollectionbackend.model.entity.UserEntity;
import com.example.moviecollectionbackend.model.entity.UserRoleEntity;
import com.example.moviecollectionbackend.model.user.AppUserDetails;
import com.example.moviecollectionbackend.repository.UserRepository;
import java.util.Optional;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class AppUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public AppUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).map(this::mapUE)
            .orElseThrow(() -> new UsernameNotFoundException("User with this " + username + " username not found!"));
    }

    private UserDetails mapUE(UserEntity userEntity) {

        return new AppUserDetails(
            userEntity.getId(),
            userEntity.getUsername(),
            userEntity.getPassword(),
            userEntity.
                getRoles().
                stream().
                map(this::mapGA).
                toList()
        );
    }

    private GrantedAuthority mapGA(UserRoleEntity userRole) {
        return new SimpleGrantedAuthority("ROLE_" +
            userRole.
                getRole().name());
    }
}
