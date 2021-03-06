package pl.hypeapp.episodie.dataproviders.database.mostpopular;

import org.junit.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import pl.hypeapp.episodie.core.entity.database.TvShowLocal;
import pl.hypeapp.episodie.core.entity.database.TvShowMostPopularLocal;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class MostPopularDatabaseProviderTest {

    private TvShowMostPopularRepository tvShowMostPopularRepository = mock(TvShowMostPopularRepository.class);

    private MostPopularDatabaseProvider mostPopularDatabaseProvider = new MostPopularDatabaseProvider(tvShowMostPopularRepository);

    @Test
    public void shouldInsertTvShowToTopList() throws Exception {
        TvShowMostPopularLocal tvShowMostPopularLocal = new TvShowMostPopularLocal(1, "12");

        when(tvShowMostPopularRepository.save(tvShowMostPopularLocal)).thenReturn(tvShowMostPopularLocal);

        mostPopularDatabaseProvider.insert(tvShowMostPopularLocal);

        verify(tvShowMostPopularRepository, times(1)).save(tvShowMostPopularLocal);
    }

    @Test
    public void shouldReturnsTopList() throws Exception {
        Pageable pageable = new PageRequest(0, 3);

        mostPopularDatabaseProvider.getMostPopular(pageable);

        verify(tvShowMostPopularRepository, times(1)).findAll(pageable);
    }

    @Test
    public void shouldReturnsTvShowsTopList() throws Exception {
        List<String> topListIds = new ArrayList<>();
        topListIds.add("12");
        topListIds.add("13");

        List<TvShowLocal> tvShows = new ArrayList<>();
        tvShows.add(new TvShowLocal());
        tvShows.add(new TvShowLocal());

        when(tvShowMostPopularRepository.getTopListTvShows(topListIds)).thenReturn(tvShows);

        mostPopularDatabaseProvider.getMostPopularTvShows(topListIds);

        verify(tvShowMostPopularRepository, times(1)).getTopListTvShows(topListIds);
    }

}