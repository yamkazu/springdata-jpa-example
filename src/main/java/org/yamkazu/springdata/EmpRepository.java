package org.yamkazu.springdata;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

public interface EmpRepository extends JpaRepository<Emp, Long>, JpaSpecificationExecutor<Emp>, QueryDslPredicateExecutor<Emp>, EmpRepositoryCustom {

    Emp findByName(String name);

    List<Emp> findByDept(Dept dept);

    List<Emp> readByDept(Dept dept);

    List<Emp> getByDept(Dept dept);

    List<Emp> dept(Dept dept);

    List<Emp> findByDept(Dept dept, Pageable pageable);

    List<Emp> findByDeptAndIdGreaterThan(Dept dept, Long id);

    Page<Emp> findByDeptAndIdGreaterThan(Dept dept, Long id, Pageable pageable);

    List<Emp> findByDeptAndIdGreaterThan(Dept dept, Long id, Sort sort);

    List<Emp> findByDeptNameLike(String name);

    List<Emp> findByDept_NameLike(String name);

    public class EmpSpecifications {
        public static Specification<Emp> idLessThanOrEqualTo(final Long id) {
            return new Specification<Emp>() {
                @Override
                public Predicate toPredicate(Root<Emp> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                    return cb.lessThanOrEqualTo(root.get(Emp_.id), id);
                }
            };
        }

        public static Specification<Emp> hasDept(final Dept dept) {
            return new Specification<Emp>() {
                @Override
                public Predicate toPredicate(Root<Emp> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                    return cb.equal(root.get(Emp_.dept), dept);
                }
            };
        }
    }

}
