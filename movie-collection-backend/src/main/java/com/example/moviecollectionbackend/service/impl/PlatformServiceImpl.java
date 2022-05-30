package com.example.moviecollectionbackend.service.impl;

import com.example.moviecollectionbackend.repository.PlatformRepository;
import com.example.moviecollectionbackend.service.PlatformService;
import org.springframework.stereotype.Service;

@Service
public class PlatformServiceImpl implements PlatformService {

    private final PlatformRepository platformRepository;

    public PlatformServiceImpl(PlatformRepository platformRepository) {
        this.platformRepository = platformRepository;
    }
}
