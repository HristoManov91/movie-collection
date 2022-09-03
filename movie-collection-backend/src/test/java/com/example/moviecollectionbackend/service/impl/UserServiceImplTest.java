package com.example.moviecollectionbackend.service.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.example.moviecollectionbackend.config.SecurityConfig;
import com.example.moviecollectionbackend.exception.UserNotFoundException;
import com.example.moviecollectionbackend.model.entity.UserEntity;
import com.example.moviecollectionbackend.model.entity.UserRoleEntity;
import com.example.moviecollectionbackend.model.entity.enums.UserRole;
import com.example.moviecollectionbackend.repository.UserRepository;
import com.example.moviecollectionbackend.repository.UserRoleRepository;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository mockUserRepo;

    @Mock
    private UserRoleRepository mockUserRoleRepo;

    @Mock
    private PasswordEncoder passwordEncoder;

    private UserServiceImpl testUserService;
    private UserRoleEntity testUserRole;
    private UserEntity testUserEntity;

    @BeforeEach
    void setUp() {
        testUserRole = new UserRoleEntity().setRole(UserRole.USER);
        testUserEntity = new UserEntity().setUsername("TestUser").setPassword("testpass").setRoles(List.of(testUserRole));

//        when(mockUserRoleRepo.findAll()).thenReturn(List.of(testUserRole));
//        when((mockUserRoleRepo.findByRole(UserRole.USER))).thenReturn(Optional.of(testUserRole));
//        when(passwordEncoder.encode(any())).thenAnswer(invocationOnMock -> "hash" + invocationOnMock.<String>getArgument(0));

        testUserService = new UserServiceImpl(mockUserRepo, mockUserRoleRepo , passwordEncoder);
    }

    @AfterEach
    void tearDown() {
        mockUserRepo.deleteAll();
        mockUserRoleRepo.deleteAll();
    }

    @Test
    void testFindUserByExistId_ReturnUserEntity(){
        when(mockUserRepo.findById(1L)).thenReturn(Optional.of(testUserEntity));

        UserEntity user = testUserService.findById(1L);

        Assertions.assertEquals(testUserEntity.getUsername() , user.getUsername());
        Assertions.assertEquals(testUserEntity.getPassword() , user.getPassword());
        Assertions.assertEquals(testUserEntity.getRoles().size() , user.getRoles().size());
        Assertions.assertEquals(testUserEntity.getRoles().get(0).getRole() , user.getRoles().get(0).getRole());
    }

    @Test
    void testFindUserBuNotExistId_ThrowUserNotFoundException(){
        Long invalidId = 2L;

        Exception exception = Assertions.assertThrows(UserNotFoundException.class, () -> {
            testUserService.findById(invalidId);
        });

        String expectedMessage = "User with this id " + invalidId + " not found!";

        Assertions.assertEquals(expectedMessage , exception.getMessage());
    }
}