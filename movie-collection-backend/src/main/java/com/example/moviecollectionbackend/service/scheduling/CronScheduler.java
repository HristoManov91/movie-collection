package com.example.moviecollectionbackend.service.scheduling;

import com.example.moviecollectionbackend.exception.InvalidIMDbUrlException;
import com.example.moviecollectionbackend.service.MovieService;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CronScheduler {

    private final static Logger LOGGER = LoggerFactory.getLogger(CronScheduler.class);

    private final MovieService movieService;

    public CronScheduler(MovieService movieService) {
        this.movieService = movieService;
    }

    @Scheduled(cron = "* 0 23 * * *")
    public void imdbRating() throws URISyntaxException, InvalidIMDbUrlException {
        LOGGER.info("Start set IMDb ratings {}", LocalDateTime.now());

        movieService.updateRatings();

        LOGGER.info("Finish set IMDb ratings {}", LocalDateTime.now());
    }
}
