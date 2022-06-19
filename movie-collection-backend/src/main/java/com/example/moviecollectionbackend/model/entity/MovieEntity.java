package com.example.moviecollectionbackend.model.entity;

import java.math.BigDecimal;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@NamedEntityGraph(
    name="movie-card",
    attributeNodes = {
        @NamedAttributeNode("genres"),
    }
)
@Entity
@Table(name = "movies")
public class MovieEntity extends BaseEntity{

    private String title1;
    private String title2;
    private List<GenreEntity> genres;
    private Integer duration;
    private Integer year;
    private BigDecimal rating; //ToDo write method get from IMDb
    private String imdbUrl;
    private String trailerUrl;
    private String posterUrl;
    private List<PlatformEntity> platforms;
    private Boolean bulgarianLanguage;
    private String description;

    public MovieEntity() {
    }

    @Column(nullable = false , length = 50)
    public String getTitle1() {
        return title1;
    }

    public MovieEntity setTitle1(String title1) {
        this.title1 = title1;
        return this;
    }

    @Column(length = 50 , unique = true)
    public String getTitle2() {
        return title2;
    }

    public MovieEntity setTitle2(String title2) {
        this.title2 = title2;
        return this;
    }

    @ManyToMany(fetch = FetchType.LAZY , cascade = CascadeType.DETACH)
    public List<GenreEntity> getGenres() {
        return genres;
    }

    public MovieEntity setGenres(List<GenreEntity> genres) {
        this.genres = genres;
        return this;
    }

    @Column(nullable = false)
    public Integer getDuration() {
        return duration;
    }

    public MovieEntity setDuration(Integer duration) {
        this.duration = duration;
        return this;
    }

    @Column(nullable = false)
    public Integer getYear() {
        return year;
    }

    public MovieEntity setYear(Integer year) {
        this.year = year;
        return this;
    }

    @Column
    public BigDecimal getRating() {
        return rating;
    }

    public MovieEntity setRating(BigDecimal rating) {
        this.rating = rating;
        return this;
    }

    @Column(nullable = false , length = 1000)
    public String getImdbUrl() {
        return imdbUrl;
    }

    public MovieEntity setImdbUrl(String ibdbUrl) {
        this.imdbUrl = ibdbUrl;
        return this;
    }

    @Column(nullable = false , length = 1000)
    public String getTrailerUrl() {
        return trailerUrl;
    }

    public MovieEntity setTrailerUrl(String trailerUrl) {
        this.trailerUrl = trailerUrl;
        return this;
    }

    @Column(nullable = false , length = 1000)
    public String getPosterUrl() {
        return posterUrl;
    }

    public MovieEntity setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
        return this;
    }

    @ManyToMany(fetch = FetchType.LAZY , cascade = CascadeType.DETACH)
    public List<PlatformEntity> getPlatforms() {
        return platforms;
    }

    public MovieEntity setPlatforms(List<PlatformEntity> platforms) {
        this.platforms = platforms;
        return this;
    }

    @Column
    public Boolean getBulgarianLanguage() {
        return bulgarianLanguage;
    }

    public MovieEntity setBulgarianLanguage(Boolean bulgarianLanguage) {
        this.bulgarianLanguage = bulgarianLanguage;
        return this;
    }

    @Column
    public String getDescription() {
        return description;
    }

    public MovieEntity setDescription(String description) {
        this.description = description;
        return this;
    }
}
