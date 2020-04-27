package com.enigma.sepotify.spesification;

import com.enigma.sepotify.entity.Genre;
import com.enigma.sepotify.entity.Song;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collection;

public class SongJpaSpesification {
    public static Specification<Song> findByCriterias(Song searchForm){
        return new Specification<Song>() {
            @Override
            public Predicate toPredicate(Root<Song> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                final Collection<Predicate> predicates = new ArrayList<>();
                if (searchForm != null) {
                    if (!StringUtils.isEmpty(searchForm.getTitle())) {
                        final Predicate genreNamePredicate = criteriaBuilder
                                .like(criteriaBuilder
                                        .lower(root.get("title")),"%"+searchForm.getTitle().toLowerCase()+"%");
                        predicates.add(genreNamePredicate);
                    }
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }
}
