package org.yamkazu.springdata;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Dept.class)
public abstract class Dept_ {

    public static volatile SingularAttribute<Dept, Long> id;
    public static volatile SingularAttribute<Dept, String> name;

}
