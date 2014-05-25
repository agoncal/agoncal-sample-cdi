package org.agoncal.sample.cdi.alternatives.database.cdi;


import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Stereotype;
import java.lang.annotation.*;

@Stereotype
@Alternative
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD})
@Documented
public @interface ProdDatabase {
}