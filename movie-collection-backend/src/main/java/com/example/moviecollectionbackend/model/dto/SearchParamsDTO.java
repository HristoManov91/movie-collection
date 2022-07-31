package com.example.moviecollectionbackend.model.dto;

import java.math.BigDecimal;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SearchParamsDTO {

    @NotNull(message = "Min duration is required!")
    @Min(value = 30, message = "Duration must be between 30 and 300 minutes!")
    private Integer minDuration;

    @NotNull(message = "Max duration is required!")
    @Max(value = 300, message = "Duration must be between 30 and 300 minutes!")
    private Integer maxDuration;

    @NotNull(message = "Min rating is required!")
    @DecimalMin(value = "0", message = "Rating must be between 0 and 10!")
    private BigDecimal minRating;

    @NotNull(message = "Max rating is required!")
    @DecimalMax(value = "10", message = "Rating must be between 0 and 10!")
    private BigDecimal maxRating;

    @NotNull(message = "Search text must not be null!")
    @Size(max = 40, message = "Max characters in Search text is 40!")
    private String searchText;

    @NotNull(message = "Genres must not be null!")
    @Size(max = 15, message = "You can select max 15 genres!")
    private String[] genres;

    public SearchParamsDTO() {
    }

    public Integer getMinDuration() {
        return minDuration;
    }

    public SearchParamsDTO setMinDuration(Integer minDuration) {
        this.minDuration = minDuration;
        return this;
    }

    public Integer getMaxDuration() {
        return maxDuration;
    }

    public SearchParamsDTO setMaxDuration(Integer maxDuration) {
        this.maxDuration = maxDuration;
        return this;
    }

    public BigDecimal getMinRating() {
        return minRating;
    }

    public SearchParamsDTO setMinRating(BigDecimal minRating) {
        this.minRating = minRating;
        return this;
    }

    public BigDecimal getMaxRating() {
        return maxRating;
    }

    public SearchParamsDTO setMaxRating(BigDecimal maxRating) {
        this.maxRating = maxRating;
        return this;
    }

    public String getSearchText() {
        return searchText;
    }

    public SearchParamsDTO setSearchText(String searchText) {
        this.searchText = searchText;
        return this;
    }

    public String[] getGenres() {
        return genres;
    }

    public SearchParamsDTO setGenres(String[] genres) {
        this.genres = genres;
        return this;
    }
}
