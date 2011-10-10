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
public class EmpRepositoryTest {

    @Inject
    EmpRepository repository;

    @Test
    public void test() {
        Emp emp = new Emp();
        emp.setName("HomuHomu");
        repository.save(emp);

        List<Emp> findAll = repository.findAll();

        for (Emp e : findAll) {
            System.out.println(e.getId() + ":" + e.getName());
        }
    }

}
