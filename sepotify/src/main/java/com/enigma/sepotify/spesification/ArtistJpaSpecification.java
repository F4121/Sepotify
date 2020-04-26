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
                if (searchForm != null) {
                    if (!StringUtils.isEmpty(searchForm.getName())) {
                        final Predicate artistNamePredicate = criteriaBuilder
                                .like(criteriaBuilder
                                        .lower(root.get("name")),"%"+searchForm.getName().toLowerCase()+"%");
                        predicates.add(artistNamePredicate);
                    }
//                    if (!StringUtils.isEmpty(searchForm.getOriginPlace())) {
//                        final Predicate artistOriginPlacePredicate = criteriaBuilder
//                                .like(criteriaBuilder
//                                        .lower(root.get("originPlace")),"%"+searchForm.getOriginPlace().toLowerCase()+"%");
//                        predicates.add(artistOriginPlacePredicate);
//                    }
//                    if (!StringUtils.isEmpty(searchForm.getType())) {
//                        final Predicate artistTypePredicate = criteriaBuilder
//                                .like(criteriaBuilder
//                                        .lower(root.get("type")),"%"+searchForm.getType().toLowerCase()+"%");
//                        predicates.add(artistTypePredicate);
//                    }
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }

//    public static Specification<Artist> findBySongTitle(Artist searchForm){
//        return new Specification<Artist>() {
//            @Override
//            public Predicate toPredicate(Root<Artist> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
//                List<Predicate> predicates = new ArrayList<>();
//                Join<Artist, Song> artistSongJoin = root.join("songs",JoinType.INNER);
//                if (!StringUtils.isEmpty(searchForm.getName())){
//                    final Predicate artistNamePredicate = criteriaBuilder
//                            .like(criteriaBuilder
//                                    .lower(root.get("name")),"%"+ searchForm.getName().toLowerCase()+"%");
//                    predicates.add(artistNamePredicate);
//                }
//                if (!StringUtils.isEmpty(searchForm.getSongs())){
//                    final Predicate songTitlePredicate = criteriaBuilder
//                            .like(criteriaBuilder
//                                    .lower(artistSongJoin.get("title")),"%"+ searchForm.getSongs().get(0).getTitle()  +"%");
//                    predicates.add(songTitlePredicate);
//                }
//
//                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
//            }
//        };
//
//    }
}
