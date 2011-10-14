package org.yamkazu.springdata;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EmpRepository extends JpaRepository<Emp, Long> {

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

    List<Emp> findByUseNamedQuery(Long id);

    @Query("select e from Emp e where e.id > ?1")
    List<Emp> findByUseQueryAnnotation(Long id);

    @Query("select e from Emp e where e.id > :id")
    List<Emp> findByUseQueryAnnotationWithParam(@Param("id") Long id);

    @Query("update Emp e set e.name = :name where e.id = :id")
    @Modifying
    //    @Modifying(clearAutomatically = false)
    int setName(@Param("id") Long id, @Param("name") String name);

    @Query("delete from Emp e where e.id = :id")
    int deleteById(@Param("id") Long id);

}
