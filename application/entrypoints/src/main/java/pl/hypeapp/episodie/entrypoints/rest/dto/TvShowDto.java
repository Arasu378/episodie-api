package pl.hypeapp.episodie.entrypoints.rest.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

import java.util.List;

@Data
@Builder
@Setter(AccessLevel.NONE)
public class TvShowDto {

    private String tvShowApiId;

    private String imdbId;

    private String name;

    private String status;

    private Integer runtime;

    private Integer fullRuntime;

    private String premiered;

    private String summary;

    private String imageMedium;

    private String imageOriginal;

    private List<SeasonDto> seasons;

    private List<EpisodeDto> episodes;

}