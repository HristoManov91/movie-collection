package com.example.moviecollectionbackend.model.dto;

import java.math.BigDecimal;
import java.util.List;

public class MovieDetailsDto {

    private String title1;
    private String title2;
    private List<String> genres;
    private Integer duration;
    private Integer year;
    private BigDecimal rating;
    private String imdbUrl;
    private String trailerUrl;
    private String posterUrl;
    private List<String> platforms;
    private Boolean bulgarianLanguage;
    private String description;

    public MovieDetailsDto() {
    }

    public String getTitle1() {
        return title1;
    }

    public MovieDetailsDto setTitle1(String title1) {
        this.title1 = title1;
        return this;
    }

    public String getTitle2() {
        return title2;
    }

    public MovieDetailsDto setTitle2(String title2) {
        this.title2 = title2;
        return this;
    }

    public List<String> getGenres() {
        return genres;
    }

    public MovieDetailsDto setGenres(List<String> genres) {
        this.genres = genres;
        return this;
    }

    public Integer getDuration() {
        return duration;
    }

    public MovieDetailsDto setDuration(Integer duration) {
        this.duration = duration;
        return this;
    }

    public Integer getYear() {
        return year;
    }

    public MovieDetailsDto setYear(Integer year) {
        this.year = year;
        return this;
    }

    public BigDecimal getRating() {
        return rating;
    }

    public MovieDetailsDto setRating(BigDecimal rating) {
        this.rating = rating;
        return this;
    }

    public String getImdbUrl() {
        return imdbUrl;
    }

    public MovieDetailsDto setImdbUrl(String imdbUrl) {
        this.imdbUrl = imdbUrl;
        return this;
    }

    public String getTrailerUrl() {
        return trailerUrl;
    }

    public MovieDetailsDto setTrailerUrl(String trailerUrl) {
        this.trailerUrl = trailerUrl;
        return this;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public MovieDetailsDto setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
        return this;
    }

    public List<String> getPlatforms() {
        return platforms;
    }

    public MovieDetailsDto setPlatforms(List<String> platforms) {
        this.platforms = platforms;
        return this;
    }

    public Boolean getBulgarianLanguage() {
        return bulgarianLanguage;
    }

    public MovieDetailsDto setBulgarianLanguage(Boolean bulgarianLanguage) {
        this.bulgarianLanguage = bulgarianLanguage;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public MovieDetailsDto setDescription(String description) {
        this.description = description;
        return this;
    }
}
