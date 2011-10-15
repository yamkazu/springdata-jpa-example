package org.yamkazu.springdata;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Dept {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    public static Dept of(Long id) {
        Dept dept = new Dept();
        dept.setId(id);
        return dept;
    }

    public Long getId() {
        return id;
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
        return "Dept [id=" + id + ", name=" + name + "]";
    }

}
