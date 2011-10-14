package org.yamkazu.springdata;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Emp.class)
public abstract class Emp_ {

    public static volatile SingularAttribute<Emp, Long> id;
    public static volatile SingularAttribute<Emp, String> name;
    public static volatile SingularAttribute<Emp, Dept> dept;

}
