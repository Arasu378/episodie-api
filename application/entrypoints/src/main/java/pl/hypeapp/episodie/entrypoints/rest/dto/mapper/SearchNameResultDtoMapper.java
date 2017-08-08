package pl.hypeapp.episodie.entrypoints.rest.dto.mapper;

import pl.hypeapp.episodie.core.entity.database.TvShowLocal;
import pl.hypeapp.episodie.entrypoints.rest.dto.SearchNameResultDto;

import java.util.function.Function;

public class SearchNameResultDtoMapper {

    public Function<TvShowLocal, SearchNameResultDto> tvShowLocalToSearchNameDto = tvShowLocal -> SearchNameResultDto.builder()
            .tvShowApiId(tvShowLocal.getTvShowApiId())
            .name(tvShowLocal.getName())
            .fullRuntime(tvShowLocal.getFullRuntime())
            .build();

}