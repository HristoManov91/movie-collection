package com.example.moviecollectionbackend.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "genres")
public class GenreEntity extends BaseEntity {

    private String genre;

    public GenreEntity() {
    }

    @Column
    public String getGenre() {
        return genre;
    }

    public GenreEntity setGenre(String genre) {
        this.genre = genre;
        return this;
    }
}
