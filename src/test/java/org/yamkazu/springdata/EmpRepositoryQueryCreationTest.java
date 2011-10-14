package org.yamkazu.springdata;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/META-INF/spring/applicationContext.xml" })
@Transactional
public class EmpRepositoryQueryCreationTest {

    @Inject
    EmpRepository repository;

    @Test
    public void 検索メソッドを定義してみる() throws Exception {
        Dept dept = new Dept();
        dept.setId(1L);

        // プレフィックスの違うだけで全部同じ処理
        List<Emp> emps = repository.findByDept(dept);
        assertThat(emps.size(), is(not(equalTo(0))));

        emps = repository.readByDept(dept);
        assertThat(emps.size(), is(not(equalTo(0))));

        emps = repository.getByDept(dept);
        assertThat(emps.size(), is(not(equalTo(0))));

        emps = repository.dept(dept);
        assertThat(emps.size(), is(not(equalTo(0))));
    }

    @Test
    public void プロパティの指定() throws Exception {
        Dept dept = new Dept();
        dept.setId(1L);

        List<Emp> emps = repository.findByDeptAndIdGreaterThan(dept, 4L);
        assertThat(emps.size(), is(not(equalTo(0))));
    }

    @Test
    public void プロパティの指定_Pageable_Sort() throws Exception {
        Dept dept = new Dept();
        dept.setId(1L);

        Page<Emp> pageEmps = repository.findByDeptAndIdGreaterThan(dept, 4L, new PageRequest(0, 3));
        assertThat(pageEmps.getNumberOfElements(), is(not(equalTo(0))));

        List<Emp> sortedEmps = repository.findByDeptAndIdGreaterThan(dept, 3L, new Sort("name"));
        assertThat(sortedEmps.size(), is(not(equalTo(0))));
    }

    @Test
    public void プロパティをたぐる() throws Exception {
        List<Emp> emps = repository.findByDeptNameLike("S%");
        assertThat(emps.size(), is(not(equalTo(0))));
    }

    @Test
    public void プロパティをアンスコで指定() throws Exception {
        List<Emp> emps = repository.findByDept_NameLike("S%");
        assertThat(emps.size(), is(not(equalTo(0))));
    }

}
