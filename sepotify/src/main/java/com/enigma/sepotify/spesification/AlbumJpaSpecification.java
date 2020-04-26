package com.enigma.sepotify.spesification;

import com.enigma.sepotify.entity.Album;
import com.enigma.sepotify.entity.Artist;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collection;

public class AlbumJpaSpecification {
    public static Specification<Album> findByCriterias(Album searchForm){
        return new Specification<Album>() {
            @Override
            public Predicate toPredicate(Root<Album> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                final Collection<Predicate> predicates = new ArrayList<>();

                if (searchForm != null) {
                    if (!StringUtils.isEmpty(searchForm.getTitle())) {
                        final Predicate albumNamePredicate = criteriaBuilder
                                .like(criteriaBuilder
                                        .lower(root.get("title")),"%"+searchForm.getTitle().toLowerCase()+"%");
                        predicates.add(albumNamePredicate);
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
}
