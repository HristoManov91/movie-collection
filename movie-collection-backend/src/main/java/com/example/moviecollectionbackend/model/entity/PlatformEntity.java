package com.example.moviecollectionbackend.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "platforms")
public class PlatformEntity extends BaseEntity{

    @Column
    private String platform;

    public PlatformEntity() {
    }

    public String getPlatform() {
        return platform;
    }

    public PlatformEntity setPlatform(String platform) {
        this.platform = platform;
        return this;
    }
}
