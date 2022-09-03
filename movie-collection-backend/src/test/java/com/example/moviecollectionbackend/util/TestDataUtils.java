package com.example.moviecollectionbackend.util;

import com.example.moviecollectionbackend.model.dto.MovieDTO;
import com.example.moviecollectionbackend.model.entity.GenreEntity;
import com.example.moviecollectionbackend.model.entity.MovieEntity;
import com.example.moviecollectionbackend.model.entity.PlatformEntity;
import com.example.moviecollectionbackend.model.entity.UserEntity;
import com.example.moviecollectionbackend.model.entity.UserRoleEntity;
import com.example.moviecollectionbackend.model.entity.enums.UserRole;
import com.example.moviecollectionbackend.repository.GenreRepository;
import com.example.moviecollectionbackend.repository.MovieRepository;
import com.example.moviecollectionbackend.repository.PlatformRepository;
import com.example.moviecollectionbackend.repository.UserRepository;
import com.example.moviecollectionbackend.repository.UserRoleRepository;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class TestDataUtils {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final GenreRepository genreRepository;
    private final PlatformRepository platformRepository;
    private final MovieRepository movieRepository;

    public TestDataUtils(UserRepository userRepository, UserRoleRepository userRoleRepository,
        GenreRepository genreRepository, PlatformRepository platformRepository, MovieRepository movieRepository) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.genreRepository = genreRepository;
        this.platformRepository = platformRepository;
        this.movieRepository = movieRepository;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public void initRoles() {
        if (userRoleRepository.count() == 0) {
            UserRoleEntity adminRole = new UserRoleEntity().setRole(UserRole.ADMIN);
            UserRoleEntity userRole = new UserRoleEntity().setRole(UserRole.USER);

            userRoleRepository.save(adminRole);
            userRoleRepository.save(userRole);
        }
    }

    public GenreEntity createTestGenre(String genre) {
        GenreEntity testGenre = new GenreEntity().setGenre(genre);

        return genreRepository.save(testGenre);
    }

    public PlatformEntity createTestPlatform(String platform) {

        PlatformEntity testPlatform = new PlatformEntity().setPlatform(platform);

        return platformRepository.save(testPlatform);
    }

    public UserEntity createTestUserWithAllRoles(String username) {

        UserEntity testUser = new UserEntity().setUsername(username).setPassword("topsecret").setRoles(userRoleRepository.findAll());

        return userRepository.save(testUser);
    }

    public MovieEntity createTestMovie(UserEntity user, List<GenreEntity> genres, List<PlatformEntity> platforms) {
        MovieEntity movie = new MovieEntity()
            .setTitle1("TestTitle1")
            .setTitle2("TestTile2")
            .setDuration(100)
            .setYear(2010)
            .setDescription("TestDescription")
            .setRating(new BigDecimal("7.77"))
            .setTrailerUrl("https://www.youtube.com/watch?v=a8Gx8wiNbs8")
            .setImdbUrl("https://www.imdb.com/title/tt1630029/")
            .setBulgarianLanguage(false)
            .setPosterUrl("https://m.media-amazon.com/images/M/MV5BMWFmYmRiYzMtMTQ4YS00NjA5LTliYTgtMmM3OTc4OGY3MTFkXkEyXkFqcGdeQXVyODk4OTc3MTY@._V1_.jpg")
            .setGenres(genres)
            .setPlatforms(platforms)
            .setUser(user);

        return movieRepository.save(movie);
    }

    public MovieDTO createMovieDto() {

        return new MovieDTO()
            .setTitle1("Title1")
            .setTitle2("Tile2")
            .setDuration(120)
            .setYear(2020)
            .setDescription("Description")
            .setTrailerUrl("https://www.youtube.com/watch?v=a8Gx8wiNbs8")
            .setImdbUrl("https://www.imdb.com/title/tt1630029/")
            .setBulgarianLanguage(true)
            .setPosterUrl("https://m.media-amazon.com/images/M/MV5BMWFmYmRiYzMtMTQ4YS00NjA5LTliYTgtMmM3OTc4OGY3MTFkXkEyXkFqcGdeQXVyODk4OTc3MTY@._V1_.jpg")
            .setGenres(List.of("ACTION"))
            .setPlatforms(List.of("HBOMAX"));
    }

    public void cleanUpDatabase() {
        movieRepository.deleteAll();
        userRepository.deleteAll();
        userRoleRepository.deleteAll();
        genreRepository.deleteAll();
        platformRepository.deleteAll();
    }
}
