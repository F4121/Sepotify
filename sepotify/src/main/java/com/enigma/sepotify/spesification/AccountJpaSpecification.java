package com.enigma.sepotify.spesification;

import com.enigma.sepotify.entity.Account;
import com.enigma.sepotify.entity.Profile;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Collection;

public class AccountJpaSpecification {
    public static Specification<Account> findByCriterias(Account searchForm){
        return new Specification<Account>() {
            @Override
            public Predicate toPredicate(Root<Account> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                final Collection<Predicate> predicates = new ArrayList<>();

                Join<Account, Profile> join = root.join("profile");

                if (searchForm != null) {
                    if (!StringUtils.isEmpty(searchForm.getProfile().getFirstName())) {
                        final Predicate firstNamePredicate = criteriaBuilder
                                .like(criteriaBuilder
                                        .lower(join.get("firstName")),"%"+searchForm.getProfile().getFirstName().toLowerCase()+"%");
                        predicates.add(firstNamePredicate);
                    }
                    if (!StringUtils.isEmpty(searchForm.getProfile().getMiddleName())) {
                        final Predicate middleNamePredicate = criteriaBuilder
                                .like(criteriaBuilder
                                        .lower(join.get("middleName")),"%"+searchForm.getProfile().getMiddleName().toLowerCase()+"%");
                        predicates.add(middleNamePredicate);
                    }
                    if (!StringUtils.isEmpty(searchForm.getProfile().getLastName())) {
                        final Predicate lastNamePredicate = criteriaBuilder
                                .like(criteriaBuilder
                                        .lower(join.get("lastName")),"%"+searchForm.getProfile().getLastName().toLowerCase()+"%");
                        predicates.add(lastNamePredicate);
                    }
                }
                return criteriaBuilder.or(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }
}
