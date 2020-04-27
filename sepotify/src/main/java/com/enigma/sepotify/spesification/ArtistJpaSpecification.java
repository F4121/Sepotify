package com.enigma.sepotify.spesification;

import com.enigma.sepotify.entity.Artist;
import com.enigma.sepotify.entity.Song;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ArtistJpaSpecification {
    public static Specification<Artist> findByCriterias(Artist searchForm){
        return new Specification<Artist>() {
            @Override
            public Predicate toPredicate(Root<Artist> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                final Collection<Predicate> predicates = new ArrayList<>();
                Join<Artist, Song> artistSongJoin = root.join("songs",JoinType.INNER);

                if (searchForm != null) {
                    if (!StringUtils.isEmpty(searchForm.getName())) {
                        final Predicate artistNamePredicate = criteriaBuilder
                                .like(criteriaBuilder
                                        .lower(root.get("name")),"%"+searchForm.getName().toLowerCase()+"%");
                        predicates.add(artistNamePredicate);
                    }
                    if (!StringUtils.isEmpty(searchForm.getTitle())){
                        final Predicate songTitlePredicate = criteriaBuilder
                                .like(criteriaBuilder
                                        .lower(artistSongJoin.get("title")),"%"+ searchForm.getTitle()  +"%");
                        predicates.add(songTitlePredicate);
                    }
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }
}
