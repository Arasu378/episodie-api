package pl.hypeapp.episodie.core.usecase.tvshow.toplist.gettoplist;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.hypeapp.episodie.core.entity.database.TvShowLocal;
import pl.hypeapp.episodie.core.entity.database.TvShowTopListLocal;

import java.util.List;

public interface GetTvShowTopList {

    Page<TvShowTopListLocal> getTopList(Pageable pageableRequest);

    List<TvShowLocal> getTopListTvShows(List<String> topListIds);

}
