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
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }
}
