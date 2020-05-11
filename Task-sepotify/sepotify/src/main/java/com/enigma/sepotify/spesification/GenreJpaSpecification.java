package com.enigma.sepotify.spesification;

import com.enigma.sepotify.entity.Genre;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collection;

public class GenreJpaSpecification {
    public static Specification<Genre> findByCriterias(Genre searchForm){
        return new Specification<Genre>() {
            @Override
            public Predicate toPredicate(Root<Genre> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                final Collection<Predicate> predicates = new ArrayList<>();

                if (searchForm != null) {
                    if (!StringUtils.isEmpty(searchForm.getName())) {
                        final Predicate genreNamePredicate = criteriaBuilder
                                .like(criteriaBuilder
                                        .lower(root.get("name")),"%"+searchForm.getName().toLowerCase()+"%");
                        predicates.add(genreNamePredicate);
                    }
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }
}
