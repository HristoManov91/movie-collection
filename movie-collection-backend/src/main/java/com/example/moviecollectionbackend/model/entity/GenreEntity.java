package com.example.moviecollectionbackend.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "genres")
public class GenreEntity extends BaseEntity {

    @Column
    private String genre;

    public GenreEntity() {
    }

    public String getGenre() {
        return genre;
    }

    public GenreEntity setGenre(String genre) {
        this.genre = genre;
        return this;
    }
}
