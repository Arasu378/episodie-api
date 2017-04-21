package pl.hypeapp.episodie.dataproviders.database.toplist;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.hypeapp.core.entity.database.TvShowTopListLocal;

public interface TvShowTopListRepository extends JpaRepository<TvShowTopListLocal, Integer> {
}