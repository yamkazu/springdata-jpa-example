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
public class EmpRepositoryCustomTest {

    @Inject
    EmpRepository repository;

    @Test
    public void 独自実装のメソッドを使う() throws Exception {
        String echo = repository.echo("homuhomu");
        assertThat(echo, is("homuhomu"));
    }

}
