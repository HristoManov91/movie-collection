package com.example.moviecollectionbackend.model.binding;

import java.math.BigDecimal;
import java.util.List;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;

public class AddMovieBindingModel {

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

    public AddMovieBindingModel() {
    }

    @NotBlank(message = "Title cannot be empty string!")
    @Size(max = 50 , min = 2, message = "Length must be between 2 and 50 characters!")
    public String getTitle1() {
        return title1;
    }

    public AddMovieBindingModel setTitle1(String title1) {
        this.title1 = title1;
        return this;
    }

    @Size(max = 50 , min = 2, message = "Length must be between 2 and 50 characters!")
    public String getTitle2() {
        return title2;
    }

    public AddMovieBindingModel setTitle2(String title2) {
        this.title2 = title2;
        return this;
    }

    @NotEmpty(message = "You must select at least one genres")
    public List<@NotBlank String> getGenres() {
        return genres;
    }

    public AddMovieBindingModel setGenres(List<String> genres) {
        this.genres = genres;
        return this;
    }

    @Positive(message = "The value must be a positive")
    @Max(value = 300 , message = "The duration must be max 300 min")
    @Min(value = 30 , message = "The duration must be min 30 min")
    public Integer getDuration() {
        return duration;
    }

    public AddMovieBindingModel setDuration(Integer duration) {
        this.duration = duration;
        return this;
    }

    @Positive(message = "The value must be a positive")
    @Max(value = 2100 , message = "The year must be max 2100 min")
    @Min(value = 1950 , message = "The year must be min 1950 min")
    public Integer getYear() {
        return year;
    }

    public AddMovieBindingModel setYear(Integer year) {
        this.year = year;
        return this;
    }

    public String getImdbUrl() {
        return imdbUrl;
    }

    public AddMovieBindingModel setImdbUrl(String imdbUrl) {
        this.imdbUrl = imdbUrl;
        return this;
    }

    @NotBlank()
    public String getTrailerUrl() {
        return trailerUrl;
    }

    public AddMovieBindingModel setTrailerUrl(String trailerUrl) {
        this.trailerUrl = trailerUrl;
        return this;
    }

    @NotBlank
    public String getPosterUrl() {
        return posterUrl;
    }

    public AddMovieBindingModel setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
        return this;
    }

    @NotEmpty(message = "You must select at least one platforms")
    public List<@NotBlank String> getPlatforms() {
        return platforms;
    }

    public AddMovieBindingModel setPlatforms(List<String> platforms) {
        this.platforms = platforms;
        return this;
    }

    @NotNull
    public Boolean getBulgarianLanguage() {
        return bulgarianLanguage;
    }

    public AddMovieBindingModel setBulgarianLanguage(Boolean bulgarianLanguage) {
        this.bulgarianLanguage = bulgarianLanguage;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public AddMovieBindingModel setDescription(String description) {
        this.description = description;
        return this;
    }
}
