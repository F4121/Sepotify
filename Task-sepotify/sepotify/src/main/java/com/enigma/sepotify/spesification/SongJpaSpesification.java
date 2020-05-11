package com.enigma.sepotify.spesification;

import com.enigma.sepotify.entity.Artist;
import com.enigma.sepotify.entity.Genre;
import com.enigma.sepotify.entity.Song;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Collection;

public class SongJpaSpesification {
    public static Specification<Song> findByCriterias(Song searchForm){
        return new Specification<Song>() {
            @Override
            public Predicate toPredicate(Root<Song> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                final Collection<Predicate> predicates = new ArrayList<>();

                Join<Song, Artist> songArtistJoin = root.join("artist",JoinType.INNER);

                if (searchForm != null) {
                    if (!StringUtils.isEmpty(searchForm.getTitle())) {
                        final Predicate genreNamePredicate = criteriaBuilder
                                .like(criteriaBuilder
                                        .lower(root.get("title")),"%"+searchForm.getTitle().toLowerCase()+"%");
                        predicates.add(genreNamePredicate);
                    }
                }

                if (!StringUtils.isEmpty(searchForm.getArtistname())) {
                    final Predicate artistNamePredicate = criteriaBuilder
                            .like(criteriaBuilder
                                    .lower(songArtistJoin.get("name")),"%"+searchForm.getArtistname().toLowerCase()+"%");
                    predicates.add(artistNamePredicate);
                }
                criteriaQuery.distinct(true);
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }
}
