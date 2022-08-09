package com.example.moviecollectionbackend.service.impl;

import static org.mockito.Mockito.when;

import com.example.moviecollectionbackend.model.entity.GenreEntity;
import com.example.moviecollectionbackend.repository.GenreRepository;
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
class GenreServiceImplTest {

    @Mock
    private GenreRepository mockGenreRepo;

    private GenreServiceImpl testGenreService;
    private GenreEntity action, comedy, fantasy;

    @BeforeEach
    void setUp() {
        action = new GenreEntity().setGenre("ACTION");
        comedy = new GenreEntity().setGenre("COMEDY");
        fantasy = new GenreEntity().setGenre("FANTASY");

        testGenreService = new GenreServiceImpl(mockGenreRepo);
    }

    @AfterEach
    void tearDown() {
        mockGenreRepo.deleteAll();
    }

    @Test
    void testFindAllByNames_ReturnList() {
        when(mockGenreRepo.findByGenre("ACTION")).thenReturn(Optional.of(action));
        when(mockGenreRepo.findByGenre("COMEDY")).thenReturn(Optional.of(comedy));
        when(mockGenreRepo.findByGenre("FANTASY")).thenReturn(Optional.of(fantasy));

        List<GenreEntity> genres = testGenreService.findAllByNames(List.of("ACTION", "COMEDY", "FANTASY"));

        Assertions.assertEquals(3, genres.size());
        Assertions.assertEquals(action.getGenre(), genres.get(0).getGenre());
        Assertions.assertEquals(comedy.getGenre(), genres.get(1).getGenre());
        Assertions.assertEquals(fantasy.getGenre(), genres.get(2).getGenre());
    }

    @Test
    void testFindAllByNamesDoNotExistAndOneExist_ReturnListWithOneElement() {
        when(mockGenreRepo.findByGenre("DRAMA")).thenReturn(Optional.empty());
        when(mockGenreRepo.findByGenre("ANIME")).thenReturn(Optional.empty());
        when(mockGenreRepo.findByGenre("ACTION")).thenReturn(Optional.of(action));

        List<GenreEntity> genres = testGenreService.findAllByNames(List.of("DRAMA", "ANIME", "ACTION"));

        Assertions.assertEquals(1, genres.size());
        Assertions.assertEquals(action.getGenre(), genres.get(0).getGenre());
    }

    @Test
    void testFindAllByMovieId_ReturnListFromStrings() {
        when(mockGenreRepo.findAllByMovieId(1L)).thenReturn(List.of(action, comedy));

        List<String> genresNames = testGenreService.findAllByMovieId(1L);

        Assertions.assertEquals(2, genresNames.size());
        Assertions.assertEquals("ACTION", genresNames.get(0));
        Assertions.assertEquals("COMEDY", genresNames.get(1));
    }

    @Test
    void testFindAllGenres_ReturnListFromGenresEntities() {
        when(mockGenreRepo.findAll()).thenReturn(List.of(action, comedy, fantasy));

        List<String> genres = testGenreService.findAllGenres();

        Assertions.assertEquals(3, genres.size());
        Assertions.assertEquals(action.getGenre(), genres.get(0));
        Assertions.assertEquals(comedy.getGenre(), genres.get(1));
        Assertions.assertEquals(fantasy.getGenre(), genres.get(2));
    }
}