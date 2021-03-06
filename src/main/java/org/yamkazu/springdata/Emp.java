package org.yamkazu.springdata;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Emp {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @ManyToOne
    private Dept dept;

    public Long getId() {
        return id;
    }

    public Dept getDept() {
        return dept;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Emp [id=" + id + ", name=" + name + ", dept=" + dept + "]";
    }

}
