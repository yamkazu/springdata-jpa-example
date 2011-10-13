package org.yamkazu.springdata;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpRepository extends JpaRepository<Emp, Long> {

    Emp findByName(String name);

    List<Emp> findByDept(Dept dept);

    List<Emp> readByDept(Dept dept);

    List<Emp> getByDept(Dept dept);

    List<Emp> dept(Dept dept);

    Page<Emp> findByDept(Dept dept, Pageable pageable);

    //    List<Emp> findByDept(Dept dept, Pageable pageable);

    List<Emp> findByDeptAndIdGreaterThan(Dept dept, Long id);

    //    Page<Emp> findByDeptAndIdGreaterThan(Pageable pageable, Dept dept, Long id);

    List<Emp> findByDeptAndIdGreaterThan(Dept dept, Long id, Sort sort);

    List<Emp> findByDept_NameLike(String name);

}
