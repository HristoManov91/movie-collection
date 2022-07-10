package com.example.moviecollectionbackend.model.dto;

import java.util.List;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;

public class EditMovieDTO {

    private Long movieId;
    private String title1;
    private String title2;
    private List<String> genres;
    private Integer duration;
    private Integer year;
    private String imdbUrl;
    private String trailerUrl;
    private String posterUrl;
    private List<String> platforms;
    private Boolean bulgarianLanguage;
    private String description;

    public EditMovieDTO() {
    }

    public Long getMovieId() {
        return movieId;
    }

    public EditMovieDTO setMovieId(Long movieId) {
        this.movieId = movieId;
        return this;
    }

    @NotBlank(message = "Title is required and cannot be empty string!")
    @Size(max = 40 , min = 2, message = "Title length must be between 2 and 40 characters!")
    public String getTitle1() {
        return title1;
    }

    public EditMovieDTO setTitle1(String title1) {
        this.title1 = title1;
        return this;
    }

    @Size(max = 40 , min = 2, message = "Second title length must be between 2 and 40 characters!")
    public String getTitle2() {
        return title2;
    }

    public EditMovieDTO setTitle2(String title2) {
        this.title2 = title2;
        return this;
    }

    @NotEmpty(message = "You must select at least one Genres!")
    @Size(max = 4 , message = "You can select up to 4 Genres!")
    public List<@NotBlank String> getGenres() {
        return genres;
    }

    public EditMovieDTO setGenres(List<String> genres) {
        this.genres = genres;
        return this;
    }

    @NotNull(message = "Field Duration is required!")
    @Positive(message = "The value of the Duration field must be a positive number!")
    @Max(value = 300 , message = "The value of the Duration field must be max 300 min!")
    @Min(value = 30 , message = "The value of the Duration field must be min 30 min!")
    public Integer getDuration() {
        return duration;
    }

    public EditMovieDTO setDuration(Integer duration) {
        this.duration = duration;
        return this;
    }

    @NotNull(message = "Field Year is required!")
    @Positive(message = "The value of the Year field must be a positive number!")
    @Max(value = 2100 , message = "The value of the Year field must be max 2100!")
    @Min(value = 1950 , message = "The value of the Year field must be min 1950!")
    public Integer getYear() {
        return year;
    }

    public EditMovieDTO setYear(Integer year) {
        this.year = year;
        return this;
    }

    @NotBlank(message = "IMDbURL cannot be empty string!")
    @Length(max = 254 , message = "The length of the IMDbURL field must be max 254 characters!")
    public String getImdbUrl() {
        return imdbUrl;
    }

    public EditMovieDTO setImdbUrl(String imdbUrl) {
        this.imdbUrl = imdbUrl;
        return this;
    }

    @NotBlank(message = "TrailerURL cannot be empty string!")
    @Length(max = 254 , message = "The length of the TrailerURL field must be max 254 characters!")
    public String getTrailerUrl() {
        return trailerUrl;
    }

    public EditMovieDTO setTrailerUrl(String trailerUrl) {
        this.trailerUrl = trailerUrl;
        return this;
    }

    @NotBlank(message = "PosterURL cannot be empty string!")
    @Length(max = 254 , message = "The length of the PosterURL field must be max 254 characters!")
    public String getPosterUrl() {
        return posterUrl;
    }

    public EditMovieDTO setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
        return this;
    }

    @NotEmpty(message = "You must select at least one Platforms")
    public List<@NotBlank String> getPlatforms() {
        return platforms;
    }

    public EditMovieDTO setPlatforms(List<String> platforms) {
        this.platforms = platforms;
        return this;
    }

    @NotNull(message = "You need to choose a audio language!")
    public Boolean getBulgarianLanguage() {
        return bulgarianLanguage;
    }

    public EditMovieDTO setBulgarianLanguage(Boolean bulgarianLanguage) {
        this.bulgarianLanguage = bulgarianLanguage;
        return this;
    }

    @Length(max = 254 , message = "The length of the Description field must be max 254 characters!")
    public String getDescription() {
        return description;
    }

    public EditMovieDTO setDescription(String description) {
        this.description = description;
        return this;
    }
}
