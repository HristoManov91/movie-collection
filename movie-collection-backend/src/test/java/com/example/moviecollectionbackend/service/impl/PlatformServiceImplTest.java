package com.example.moviecollectionbackend.service.impl;

import static org.mockito.Mockito.when;

import com.example.moviecollectionbackend.model.entity.PlatformEntity;
import com.example.moviecollectionbackend.repository.PlatformRepository;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PlatformServiceImplTest {

    @Mock
    private PlatformRepository mockPlatformRepo;

    private PlatformServiceImpl testPlatformService;
    private PlatformEntity hbo, disney, netflix;

    @BeforeEach
    void setUp() {
        hbo = new PlatformEntity().setPlatform("HBOMAX");
        disney = new PlatformEntity().setPlatform("DISNEY+");
        netflix = new PlatformEntity().setPlatform("NETFLIX");

        testPlatformService = new PlatformServiceImpl(mockPlatformRepo);
    }

    @AfterEach
    void tearDown() {
        mockPlatformRepo.deleteAll();
    }

    @Test
    void testFindAllByNames_ReturnListFromPlatformEntities() {

        when(mockPlatformRepo.findByPlatform("HBOMAX")).thenReturn(Optional.of(hbo));
        when(mockPlatformRepo.findByPlatform("DISNEY+")).thenReturn(Optional.of(disney));
        when(mockPlatformRepo.findByPlatform("NETFLIX")).thenReturn(Optional.of(netflix));

        List<PlatformEntity> platforms = testPlatformService.findAllByNames(List.of("HBOMAX", "DISNEY+", "NETFLIX"));

        Assertions.assertEquals(3 , platforms.size());
        Assertions.assertEquals(hbo.getPlatform() , platforms.get(0).getPlatform());
        Assertions.assertEquals(disney.getPlatform() , platforms.get(1).getPlatform());
        Assertions.assertEquals(netflix.getPlatform() , platforms.get(2).getPlatform());
    }

    @Test
    void testFindAllByNamesOnlyOneExist_ReturnListFromOne(){

        when(mockPlatformRepo.findByPlatform("INVALID")).thenReturn(Optional.empty());
        when(mockPlatformRepo.findByPlatform("invalid2")).thenReturn(Optional.empty());
        when(mockPlatformRepo.findByPlatform("NETFLIX")).thenReturn(Optional.of(netflix));

        List<PlatformEntity> platforms = testPlatformService.findAllByNames(List.of("INVALID", "invalid2", "NETFLIX"));

        Assertions.assertEquals(1 , platforms.size());
        Assertions.assertEquals(netflix.getPlatform() , platforms.get(0).getPlatform());
    }

    @Test
    void testFindAllByMovieId_ReturnListFromStrings(){

        when(mockPlatformRepo.findAllByMovieId(1L)).thenReturn(List.of(hbo , netflix));

        List<String> platformsNames = testPlatformService.findAllByMovieId(1L);

        Assertions.assertEquals(2 , platformsNames.size());
        Assertions.assertEquals(hbo.getPlatform() , platformsNames.get(0));
        Assertions.assertEquals(netflix.getPlatform() , platformsNames.get(1));
    }

    @Test
    void testFindAllPlatformsNames_ReturnListFromStrings(){

        when(mockPlatformRepo.findAll()).thenReturn(List.of(hbo , disney , netflix));

        List<PlatformEntity> platforms = mockPlatformRepo.findAll();

        Assertions.assertEquals(3 , platforms.size());
        Assertions.assertEquals(hbo.getPlatform() , platforms.get(0).getPlatform());
        Assertions.assertEquals(disney.getPlatform() , platforms.get(1).getPlatform());
        Assertions.assertEquals(netflix.getPlatform() , platforms.get(2).getPlatform());
    }
}