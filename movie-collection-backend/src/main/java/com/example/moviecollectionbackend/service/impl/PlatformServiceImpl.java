package com.example.moviecollectionbackend.service.impl;

import com.example.moviecollectionbackend.model.entity.PlatformEntity;
import com.example.moviecollectionbackend.repository.PlatformRepository;
import com.example.moviecollectionbackend.service.PlatformService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class PlatformServiceImpl implements PlatformService {

    private final PlatformRepository platformRepository;

    public PlatformServiceImpl(PlatformRepository platformRepository) {
        this.platformRepository = platformRepository;
    }

    @Override
    public List<String> findAllByMovieId(Long movieId) {

        List<PlatformEntity> allByMovieId = platformRepository.findAllByMovieId(movieId);
        return allByMovieId.stream().map(PlatformEntity::getPlatform).collect(Collectors.toList());
    }

    @Override
    public List<PlatformEntity> findAllByNames(List<String> platforms) {
        return platforms
            .stream()
            .map(platformRepository::findByPlatform)
            .filter(Optional::isPresent)
            .map(Optional::get)
            .collect(Collectors.toList());

    }

    @Override
    public List<String> findAllPlatformsNames() {
        return platformRepository.findAll().stream().map(PlatformEntity::getPlatform).collect(Collectors.toList());
    }
}
