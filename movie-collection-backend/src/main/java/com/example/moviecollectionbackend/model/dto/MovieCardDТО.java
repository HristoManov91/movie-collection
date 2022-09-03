package com.example.moviecollectionbackend.model.dto;

import java.math.BigDecimal;

public class MovieCardDТО {

    private Long id;
    private String title1;
    private String title2;
    private String trailerUrl;
    private Integer duration;
    private BigDecimal rating;
    private String posterUrl;

    public MovieCardDТО() {
    }

    public Long getId() {
        return id;
    }

    public MovieCardDТО setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle1() {
        return title1;
    }

    public MovieCardDТО setTitle1(String title1) {
        this.title1 = title1;
        return this;
    }

    public String getTitle2() {
        return title2;
    }

    public MovieCardDТО setTitle2(String title2) {
        this.title2 = title2;
        return this;
    }

    public String getTrailerUrl() {
        return trailerUrl;
    }

    public MovieCardDТО setTrailerUrl(String trailerUrl) {
        this.trailerUrl = trailerUrl;
        return this;
    }

    public Integer getDuration() {
        return duration;
    }

    public MovieCardDТО setDuration(Integer duration) {
        this.duration = duration;
        return this;
    }

    public BigDecimal getRating() {
        return rating;
    }

    public MovieCardDТО setRating(BigDecimal rating) {
        this.rating = rating;
        return this;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public MovieCardDТО setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
        return this;
    }
}
