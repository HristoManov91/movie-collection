package com.example.moviecollectionbackend.model.mapper;

import com.example.moviecollectionbackend.model.dto.MovieDTO;
import com.example.moviecollectionbackend.model.entity.GenreEntity;
import com.example.moviecollectionbackend.model.entity.MovieEntity;
import com.example.moviecollectionbackend.model.entity.PlatformEntity;
import com.example.moviecollectionbackend.service.GenreService;
import com.example.moviecollectionbackend.service.MovieService;
import com.example.moviecollectionbackend.service.PlatformService;
import com.example.moviecollectionbackend.service.UserService;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring" , uses = {GenreService.class , PlatformService.class , UserService.class})
public interface MovieMapper {


    @Mapping(target = "id" , source = "movieId")
    @Mapping(target = "user" , source = "userId")
    MovieEntity mapToEntity(MovieDTO dto);

    @Mapping(target = "movieId" , source = "id")
    @Mapping(target = "userId" , source = "entity.user.id")
    @Mapping(target = "genres", source = "entity.genres", qualifiedByName = "GenresToStringCollection")
    @Mapping(target = "platforms", source = "entity.platforms", qualifiedByName = "PlatformsToStringCollection")
    MovieDTO mapToResource(MovieEntity entity);

    @Named("GenresToStringCollection")
    default List<String> convertGenres(List<GenreEntity> genres) {
        return genres.stream().map(GenreEntity::getGenre).toList();
    }

    @Named("PlatformsToStringCollection")
    default List<String> convertPlatforms(List<PlatformEntity> platforms){
        return platforms.stream().map(PlatformEntity::getPlatform).toList();
    }
}
