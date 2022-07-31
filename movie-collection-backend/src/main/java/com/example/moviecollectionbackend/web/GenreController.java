package com.example.moviecollectionbackend.web;

import com.example.moviecollectionbackend.service.GenreService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:8080/")
@RequestMapping("/genres")
public class GenreController {

    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping("/all")
//    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<String>> getAllGenres () {
        return new ResponseEntity<>(genreService.findAllGenres() , HttpStatus.OK);
    }
}
