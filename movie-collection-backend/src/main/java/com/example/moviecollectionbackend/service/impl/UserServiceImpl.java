//package com.example.moviecollectionbackend.service.impl;
//
//import com.example.moviecollectionbackend.model.dto.UserRegisterDto;
//import com.example.moviecollectionbackend.model.entity.UserEntity;
//import com.example.moviecollectionbackend.model.entity.UserRoleEntity;
//import com.example.moviecollectionbackend.model.entity.enums.UserRole;
//import com.example.moviecollectionbackend.repository.UserRepository;
//import com.example.moviecollectionbackend.repository.UserRoleRepository;
//import com.example.moviecollectionbackend.service.UserService;
//import java.util.List;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//@Service
//public class UserServiceImpl implements UserService {
//
//    private final UserRepository userRepository;
//    private final UserRoleRepository userRoleRepository;
//    private final PasswordEncoder passwordEncoder;
//
//    public UserServiceImpl(UserRepository userRepository, UserRoleRepository userRoleRepository,
//        PasswordEncoder passwordEncoder) {
//        this.userRepository = userRepository;
//        this.userRoleRepository = userRoleRepository;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    @Override
//    public void init() {
//        if (userRoleRepository.count() == 0) {
//            UserRoleEntity userRole = new UserRoleEntity().setRole(UserRole.USER);
//            userRoleRepository.save(userRole);
//        }
//
//        UserEntity user =
//            new UserEntity()
//                .setUsername("hristo")
////                .setPassword(passwordEncoder.encode("topsecret"))
//                .setPassword("top")
//                .setRoles(userRoleRepository.findAll());
//
//        userRepository.save(user);
//    }
//
//    @Override
//    public boolean registerUser(UserRegisterDto userRegisterDto) {
//        Boolean existUsername = userRepository.existsUserEntityByUsername(userRegisterDto.getUsername());
//
//        if (existUsername) {
//            //ToDo error username exist
//            return false;
//        } else {
//            UserEntity user =
//                new UserEntity()
//                    .setUsername(userRegisterDto.getUsername())
//                    .setPassword(passwordEncoder.encode(userRegisterDto.getPassword()))
//                    .setRoles(List.of(userRoleRepository.findByRole(UserRole.USER.name())));
//
//            userRepository.save(user);
//
//            return true;
//        }
//    }
//}
