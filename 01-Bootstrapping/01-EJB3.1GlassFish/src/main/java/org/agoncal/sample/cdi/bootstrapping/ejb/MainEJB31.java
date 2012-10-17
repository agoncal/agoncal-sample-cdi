package org.agoncal.sample.cdi.bootstrapping.ejb;

import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * @author Antonio Goncalves
 *         http://www.antoniogoncalves.org
 */
@Stateless
public class MainEJB31 {

    // ======================================
    // =             Attributes             =
    // ======================================

    @Inject
    Hello hello;

    // ======================================
    // =          Business methods          =
    // ======================================

    public String saySomething() {
        return hello.sayHelloWorld();
    }
}
