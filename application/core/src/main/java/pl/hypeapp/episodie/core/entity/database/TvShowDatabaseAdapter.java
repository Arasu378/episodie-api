package pl.hypeapp.episodie.core.entity.database;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import pl.hypeapp.episodie.core.entity.TvShowEntity;
import pl.hypeapp.episodie.core.entity.api.tvmaze.EpisodeRemote;
import pl.hypeapp.episodie.core.entity.api.tvmaze.SeasonRemote;
import pl.hypeapp.episodie.core.entity.api.tvmaze.TvShowRemote;

import java.util.List;
import java.util.stream.Collectors;

public class TvShowDatabaseAdapter implements TvShowEntity<SeasonLocal, EpisodeLocal> {

    private TvShowRemote tvShowRemote;

    public TvShowDatabaseAdapter(TvShowRemote tvShowRemote) {
        this.tvShowRemote = tvShowRemote;
    }

    @Override
    public String getTvShowApiId() {
        return this.tvShowRemote.getTvShowApiId();
    }

    @Override
    public String getImdbId() {
        return this.tvShowRemote.getImdbId();
    }

    @Override
    public String getName() {
        return this.tvShowRemote.getName();
    }

    @Override
    public String getStatus() {
        return this.tvShowRemote.getStatus();
    }

    @Override
    public String getOfficialSite() {
        return this.tvShowRemote.getOfficialSite();
    }

    @Override
    public Integer getRuntime() {
        return this.tvShowRemote.getRuntime();
    }

    @Override
    public Integer getFullRuntime() {
        return this.tvShowRemote.getFullRuntime();
    }

    @Override
    public String getPremiered() {
        return this.tvShowRemote.getPremiered();
    }

    @Override
    public String getNetworkName() {
        return this.tvShowRemote.getNetworkName();
    }

    @Override
    public String getGenre() {
        return this.tvShowRemote.getGenre();
    }

    @Override
    public String getSummary() {
        return escapeHtmlTags(this.tvShowRemote.getSummary());
    }

    @Override
    public String getImageMedium() {
        return this.tvShowRemote.getImageMedium();
    }

    @Override
    public String getImageOriginal() {
        return this.tvShowRemote.getImageOriginal();
    }

    @Override
    public Integer getUpdated() {
        return this.tvShowRemote.getUpdated();
    }

    public TvShowLocal apply() {
        return TvShowLocal.builder()
                .tvShowApiId(getTvShowApiId())
                .imdbId(getImdbId())
                .name(getName())
                .status(getStatus())
                .officialSite(getOfficialSite())
                .runtime(getRuntime())
                .fullRuntime(getFullRuntime())
                .premiered(getPremiered())
                .networkName(getNetworkName())
                .genre(getGenre())
                .summary(getSummary())
                .imageMedium(getImageMedium())
                .imageOriginal(getImageOriginal())
                .updated(getUpdated())
                .seasons(getSeasons())
                .episodes(getEpisodes())
                .build();
    }

    @Override
    public List<SeasonLocal> getSeasons() {
        return tvShowRemote.getSeasons()
                .stream()
                .map(this::convertToSeasonLocal)
                .collect(Collectors.toList());
    }

    @Override
    public List<EpisodeLocal> getEpisodes() {
        return tvShowRemote.getEpisodes()
                .stream()
                .map(this::convertToEpisodeLocal)
                .collect(Collectors.toList());
    }


    private SeasonLocal convertToSeasonLocal(SeasonRemote seasonRemote) {
        return SeasonLocal.builder()
                .seasonApiId(seasonRemote.getSeasonApiId())
                .runtime(calculateSeasonRuntime(seasonRemote.getSeasonNumber()))
                .url(seasonRemote.getUrl())
                .seasonNumber(seasonRemote.getSeasonNumber())
                .episodeOrder(seasonRemote.getEpisodeOrder())
                .premiereDate(seasonRemote.getPremiereDate())
                .endDate(seasonRemote.getEndDate())
                .summary(escapeHtmlTags(seasonRemote.getSummary()))
                .imageMedium(seasonRemote.getImageMedium())
                .imageOriginal(seasonRemote.getImageOriginal())
                .build();
    }

    private Integer calculateSeasonRuntime(Integer seasonNumber) {
        return tvShowRemote.getEpisodes().stream()
                .filter(episodeRemote -> episodeRemote.getSeasonNumber().equals(seasonNumber))
                .mapToInt(EpisodeRemote::getRuntime)
                .sum();
    }

    private EpisodeLocal convertToEpisodeLocal(EpisodeRemote episodeRemote) {
        return EpisodeLocal.builder()
                .episodeApiId(episodeRemote.getEpisodeApiId())
                .url(episodeRemote.getUrl())
                .name(episodeRemote.getName())
                .seasonNumber(episodeRemote.getSeasonNumber())
                .episodeNumber(episodeRemote.getEpisodeNumber())
                .premiereDate(episodeRemote.getPremiereDate())
                .runtime(episodeRemote.getRuntime())
                .imageMedium(episodeRemote.getImageMedium())
                .imageOriginal(episodeRemote.getImageOriginal())
                .summary(escapeHtmlTags(episodeRemote.getSummary()))
                .build();
    }

    private String escapeHtmlTags(String input) {
        if (input != null) {
            return Jsoup.clean(input, Whitelist.none());
        } else {
            return null;
        }
    }

}
