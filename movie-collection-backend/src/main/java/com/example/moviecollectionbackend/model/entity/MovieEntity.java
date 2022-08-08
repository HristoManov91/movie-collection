package com.example.moviecollectionbackend.model.entity;

import java.math.BigDecimal;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.Table;

@NamedEntityGraph(
    name = "movie-card",
    attributeNodes = {
        @NamedAttributeNode("genres"),
    }
)
@Entity
@Table(name = "movies")
public class MovieEntity extends BaseEntity {

    @Column(nullable = false, length = 50)
    private String title1;

    @Column(length = 50)
    private String title2;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    private List<GenreEntity> genres;

    @Column(nullable = false)
    private Integer duration;

    @Column(nullable = false)
    private Integer year;

    @Column
    private BigDecimal rating;

    @Column(nullable = false)
    private String imdbUrl;

    @Column(nullable = false)
    private String trailerUrl;

    @Column(nullable = false)
    private String posterUrl;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    private List<PlatformEntity> platforms;

    @Column
    private Boolean bulgarianLanguage;

    @Column
    private String description;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    private UserEntity user;

    public MovieEntity() {
    }

    public String getTitle1() {
        return title1;
    }

    public MovieEntity setTitle1(String title1) {
        this.title1 = title1;
        return this;
    }

    public String getTitle2() {
        return title2;
    }

    public MovieEntity setTitle2(String title2) {
        this.title2 = title2;
        return this;
    }

    public List<GenreEntity> getGenres() {
        return genres;
    }

    public MovieEntity setGenres(List<GenreEntity> genres) {
        this.genres = genres;
        return this;
    }

    public Integer getDuration() {
        return duration;
    }

    public MovieEntity setDuration(Integer duration) {
        this.duration = duration;
        return this;
    }

    public Integer getYear() {
        return year;
    }

    public MovieEntity setYear(Integer year) {
        this.year = year;
        return this;
    }

    public BigDecimal getRating() {
        return rating;
    }

    public MovieEntity setRating(BigDecimal rating) {
        this.rating = rating;
        return this;
    }

    public String getImdbUrl() {
        return imdbUrl;
    }

    public MovieEntity setImdbUrl(String ibdbUrl) {
        this.imdbUrl = ibdbUrl;
        return this;
    }

    public String getTrailerUrl() {
        return trailerUrl;
    }

    public MovieEntity setTrailerUrl(String trailerUrl) {
        this.trailerUrl = trailerUrl;
        return this;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public MovieEntity setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
        return this;
    }

    public List<PlatformEntity> getPlatforms() {
        return platforms;
    }

    public MovieEntity setPlatforms(List<PlatformEntity> platforms) {
        this.platforms = platforms;
        return this;
    }

    public Boolean getBulgarianLanguage() {
        return bulgarianLanguage;
    }

    public MovieEntity setBulgarianLanguage(Boolean bulgarianLanguage) {
        this.bulgarianLanguage = bulgarianLanguage;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public MovieEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public UserEntity getUser() {
        return user;
    }

    public MovieEntity setUser(UserEntity user) {
        this.user = user;
        return this;
    }

    //    public UserEntity getUser() {
//        return user;
//    }
//
//    public MovieEntity setUser(UserEntity user) {
//        this.user = user;
//        return this;
//    }
}
