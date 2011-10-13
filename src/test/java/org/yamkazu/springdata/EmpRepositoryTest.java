package org.yamkazu.springdata;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.springframework.data.domain.Sort.Direction.*;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.repository.query.parser.OrderBySource;
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

    @Test
    public void findAll() throws Exception {
        List<Emp> all = repository.findAll();
        assertThat(all.size(), is(not(0)));
    }

    @Test
    public void findAll_初めてのソート() throws Exception {
        // 一番シンプルなやり方
        List<Emp> emps = repository.findAll(new Sort(ASC, "name"));
        assertThat(emps.size(), is(not(equalTo(0))));
        for (int i = 0; i < emps.size() - 1; i++) {
            if (emps.get(i).getName().compareTo(emps.get(i + 1).getName()) > 0) {
                fail();
            }
        }

        // 上の指定と同じ
        emps = repository.findAll(new Sort(new Order(ASC, "name")));
        assertThat(emps.size(), is(not(equalTo(0))));
    }

    @Test
    public void findAll_sort_OrderBySourceを使う() throws Exception {
        List<Emp> emps = repository.findAll(new OrderBySource("NameAsc").toSort());
        assertThat(emps.size(), is(not(equalTo(0))));
    }

    @Test
    public void findAll_sort_関連クラスのプロパティを指定() throws Exception {
        List<Emp> emps = repository.findAll(new Sort(new Order(DESC, "dept.id"), new Order(ASC, "name")));
        assertThat(emps.size(), is(not(equalTo(0))));

        // OrderBySourceを使用した例、上と同じ意味
        emps = repository.findAll(new OrderBySource("DeptIdDescNameAsc", Emp.class).toSort());
        assertThat(emps.size(), is(not(equalTo(0))));
    }

    @SuppressWarnings("unused")
    @Test
    public void findAll_pageable() throws Exception {
        Page<Emp> page = repository.findAll(new PageRequest(3, 3));

        List<Emp> emps = page.getContent(); // 取得した要素をListで取得
        int number = page.getNumber(); // ページ番号を取得
        int numberOfElements = page.getNumberOfElements(); // 取得したページの要素数
        int size = page.getSize(); // 指定したページの要素数
        long totalElements = page.getTotalElements(); // 全件の数を取得
        int totalPages = page.getTotalPages(); // ページ数を取得

        // ソートを指定する
        page = repository.findAll(new PageRequest(0, 4, ASC, "dept.id", "name"));
        assertThat(page.getSize(), is(4));

        // OrderBySourceを使用してソートを指定する
        page = repository.findAll(new PageRequest(0, 5, new OrderBySource("NameDesc").toSort()));
        assertThat(page.getSize(), is(5));
    }
}
