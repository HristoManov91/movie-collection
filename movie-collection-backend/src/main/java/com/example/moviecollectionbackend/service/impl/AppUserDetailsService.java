//package com.example.moviecollectionbackend.service.impl;
//
//import com.example.moviecollectionbackend.model.entity.UserEntity;
//import com.example.moviecollectionbackend.model.entity.UserRoleEntity;
//import com.example.moviecollectionbackend.repository.UserRepository;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//
//public class AppUserDetailsService implements UserDetailsService {
//
//    private UserRepository userRepository;
//
//    public AppUserDetailsService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//        return userRepository
//            .findByUsername(username)
//            .map(this::mapUD)
//            .orElseThrow(() -> new UsernameNotFoundException("User with this username " + username + " not found!"));
//
//    }
//
//    private UserDetails mapUD(UserEntity userEntity) {
//
//        return User.builder()
//            .username(userEntity.getUsername())
//            .password(userEntity.getPassword())
//            .authorities(userEntity
//                .getRoles()
//                .stream()
//                .map(this::mapGA)
//                .toList())
//            .build();
//    }
//
//    private GrantedAuthority mapGA(UserRoleEntity userRole) {
//        return new SimpleGrantedAuthority("ROLE_" + userRole.getRole().name());
//    }
//}
