package pl.hypeapp.core.usecase.tvshow.toplist.gettoplist;

import com.google.common.collect.Ordering;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import pl.hypeapp.core.entity.database.TvShowLocal;
import pl.hypeapp.core.entity.database.TvShowTopListLocal;

import java.util.List;
import java.util.stream.Collectors;

public class GetTvShowTopListUseCase {

    private final GetTvShowTopList getTvShowTopList;

    public GetTvShowTopListUseCase(GetTvShowTopList getTvShowTopList) {
        this.getTvShowTopList = getTvShowTopList;
    }

    public Page<TvShowLocal> getTopList(Pageable pageableRequest) {
        Page<TvShowTopListLocal> topList = getTvShowTopList.getTopList(pageableRequest);
        if (pageableRequest.getPageNumber() >= topList.getTotalPages()) {
            throw new ResourceNotFoundException();
        }
        List<String> topListIds = getTopListIds(topList);
        List<TvShowLocal> tvShowTopList = getTvShowTopList.getTopListTvShows(topListIds);
        orderTvShowsById(topListIds, tvShowTopList);
        return new PageImpl<>(tvShowTopList, pageableRequest, topList.getTotalElements());
    }

    private List<String> getTopListIds(Page<TvShowTopListLocal> topList) {
        return topList.getContent().stream()
                .map(TvShowTopListLocal::getTvShowApiId)
                .collect(Collectors.toList());
    }

    private void orderTvShowsById(List<String> topListIds, List<TvShowLocal> tvShowToList) {
        Ordering<String> idOrdering = Ordering.explicit(topListIds);
        tvShowToList.sort((o1, o2) -> idOrdering.compare(o1.getTvShowApiId(), o2.getTvShowApiId()));
    }

}