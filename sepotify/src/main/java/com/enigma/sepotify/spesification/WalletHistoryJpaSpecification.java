package com.enigma.sepotify.spesification;

import com.enigma.sepotify.entity.Wallet;
import com.enigma.sepotify.entity.WalletHistory;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Collection;

public class WalletHistoryJpaSpecification {
    public static Specification<WalletHistory> findByCriterias(WalletHistory searchForm){
        return new Specification<WalletHistory>() {
            @Override
            public Predicate toPredicate(Root<WalletHistory> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                final Collection<Predicate> predicates = new ArrayList<>();

                Join<WalletHistory, Wallet> walllet = root.join("wallet",JoinType.INNER);

                if (searchForm != null) {
                    if (!StringUtils.isEmpty(searchForm.getWalletId())) {
                        final Predicate genreNamePredicate = criteriaBuilder
                                .like(criteriaBuilder
                                        .lower(walllet.get("id")),"%"+searchForm.getWalletId().toLowerCase()+"%");
                        predicates.add(genreNamePredicate);
                    }
                }

                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }
}
