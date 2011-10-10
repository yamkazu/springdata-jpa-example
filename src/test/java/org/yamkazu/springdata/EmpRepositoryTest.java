package org.yamkazu.springdata;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

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
    public void はじめてのSpringDataJpa() {
        Emp emp = new Emp();
        emp.setName("HomuHomu");
        repository.save(emp);

        Emp e = repository.findByName("HomuHomu");
        assertThat(e, is(notNullValue()));
        assertThat(e.getName(), is(equalTo("HomuHomu")));
    }

}
