package com.game.utils;

import com.game.entity.Player;
import com.game.entity.Profession;
import com.game.entity.Race;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PlayerSort {

    public List<Predicate> predicates = new ArrayList<Predicate>();

    public static Specification<Player> findPlayerByName(final String name) {
        return (r, cq, cb) -> cb.like(r.get("name"), "%" + name + "%");
    }

    public static Specification<Player> findPlayerByTitle(final String title) {
        return (r, cq, cb) -> cb.like(r.get("title"), "%" + title + "%");
    }

    public static Specification<Player> findPlayerByRace(final Race race) {
        if (race == null)
            return null;
        return (r, cq, cb) -> cb.equal(r.get("race"), race);
    }

    public static Specification<Player> findPlayerByProfession(final Profession profession) {
        if (profession == null)
            return null;
        return (r, cq, cb) -> cb.equal(r.get("profession"), profession);
    }

    public static Specification<Player> findPlayerByStatus(final Boolean banned) {
        if (banned == null)
            return null;
        return (r, cq, cb) -> cb.equal(r.get("banned"), banned);
    }

    public static Specification<Player> findPlayerByDate(final Long after, final Long before) {
        return new Specification<Player>() {
            @Override
            public Predicate toPredicate(Root<Player> root,
                                         CriteriaQuery<?> criteriaQuery,
                                         CriteriaBuilder criteriaBuilder) {

                Predicate greater;
                Predicate lesser;

                if (after == null && before != null) {
                    lesser = criteriaBuilder.lessThanOrEqualTo(root.get("birthday"), new Date(before));
                    return lesser;
                }
                if (after != null && before == null) {
                    greater = criteriaBuilder.greaterThanOrEqualTo(root.get("birthday"), new Date(after));
                    return greater;
                }

                if (after != null && before != null) {
                    greater = criteriaBuilder.greaterThanOrEqualTo(root.get("birthday"), new Date(after));
                    lesser = criteriaBuilder.lessThanOrEqualTo(root.get("birthday"), new Date(before));
                    return criteriaBuilder.and(greater, lesser);
                }

                return null;
            }
        };
    }

    public static Specification<Player> findPlayerByExp(final Integer experienceMin, final Integer experienceMax) {
        return new Specification<Player>() {
            @Override
            public Predicate toPredicate(Root<Player> root,
                                         CriteriaQuery<?> criteriaQuery,
                                         CriteriaBuilder criteriaBuilder) {

                Predicate lesser;
                Predicate greater;

                if (experienceMin == null && experienceMax != null) {
                    lesser = criteriaBuilder.lessThanOrEqualTo(root.get("experience"), experienceMax);
                    return lesser;
                }
                if (experienceMin != null && experienceMax == null) {
                    greater = criteriaBuilder.greaterThanOrEqualTo(root.get("experience"), experienceMin);
                    return greater;
                }
                if (experienceMax != null && experienceMin != null) {
                    greater = criteriaBuilder.greaterThanOrEqualTo(root.get("experience"), experienceMin);
                    lesser = criteriaBuilder.lessThanOrEqualTo(root.get("experience"), experienceMax);
                    return criteriaBuilder.and(greater, lesser);
                }

                return null;
            }
        };
    }

    public static Specification<Player> findPlayerByMinLevel(final Integer levelMin) {
        return new Specification<Player>() {
            @Override
            public Predicate toPredicate(Root<Player> root,
                                         CriteriaQuery<?> criteriaQuery,
                                         CriteriaBuilder criteriaBuilder) {

                if (levelMin == null)
                    return null;

                Predicate greater = criteriaBuilder.greaterThanOrEqualTo(root.get("level"), levelMin);
                return greater;
            }
        };
    }

    public static Specification<Player> findPlayerByMaxLevel(final Integer maxLevel) {
        return new Specification<Player>() {
            @Override
            public Predicate toPredicate(Root<Player> root,
                                         CriteriaQuery<?> criteriaQuery,
                                         CriteriaBuilder criteriaBuilder) {

                if (maxLevel == null)
                    return null;

                Predicate lesser = criteriaBuilder.lessThanOrEqualTo(root.get("level"), maxLevel);
                return lesser;
            }
        };
    }
}