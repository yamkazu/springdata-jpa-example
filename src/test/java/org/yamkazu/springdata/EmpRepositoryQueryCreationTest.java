package org.yamkazu.springdata;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/META-INF/spring/applicationContext.xml" })
@Transactional
public class EmpRepositoryQueryCreationTest {

    @Inject
    EmpRepository repository;

    @SuppressWarnings("unused")
    @Test
    public void 検索メソッドを定義してみる() throws Exception {
        Dept dept = new Dept();
        dept.setId(1L);

        List<Emp> emps = repository.findByDept(dept);
        emps = repository.readByDept(dept);
        emps = repository.getByDept(dept);
    }

    @Test
    public void testName() throws Exception {
        Dept dept = new Dept();
        dept.setId(1L);

        //        List<Emp> emps = repository.findByDeptAndIdGreaterThan(dept, 4L);

        List<Emp> emps = repository.findByDept_NameLike("S%");
        for (Emp emp : emps) {
            System.out.println(emp);
        }
    }

}
