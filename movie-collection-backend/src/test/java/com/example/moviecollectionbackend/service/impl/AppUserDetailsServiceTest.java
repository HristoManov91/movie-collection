package com.example.moviecollectionbackend.service.impl;


import static org.mockito.Mockito.when;

import com.example.moviecollectionbackend.model.entity.UserEntity;
import com.example.moviecollectionbackend.model.entity.UserRoleEntity;
import com.example.moviecollectionbackend.model.entity.enums.UserRole;
import com.example.moviecollectionbackend.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@ExtendWith(MockitoExtension.class)
class AppUserDetailsServiceTest {

    @Mock
    private UserRepository mockUserRepo;

    private AppUserDetailsService serviceToTest;

    @BeforeEach
    void setUp() {
        serviceToTest = new AppUserDetailsService(mockUserRepo);
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void testLoadUserByUsername_UserExist() {

        //arrange
        UserEntity testUser =
            new UserEntity()
                .setUsername("hristo")
                .setPassword("123456")
                .setRoles(List.of(new UserRoleEntity().setRole(UserRole.USER)));

        when(mockUserRepo.findByUsername(testUser.getUsername())).thenReturn(Optional.of(testUser));

        //act
        UserDetails userDetails = serviceToTest.loadUserByUsername(testUser.getUsername());

        //assert
        Assertions.assertEquals(testUser.getUsername(), userDetails.getUsername());
        Assertions.assertEquals(testUser.getPassword(), userDetails.getPassword());
        Assertions.assertEquals(1, testUser.getRoles().size());
        Assertions.assertEquals("USER", testUser.getRoles().get(0).getRole().name());

    }

    @Test
    void testLoadUserByUsername_UserDoesNotExist() {

        Assertions.assertThrows(UsernameNotFoundException.class, () -> serviceToTest.loadUserByUsername("non_exist"));
    }
}