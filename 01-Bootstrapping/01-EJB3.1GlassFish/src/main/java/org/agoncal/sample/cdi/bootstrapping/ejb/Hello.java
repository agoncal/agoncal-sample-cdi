package org.agoncal.sample.cdi.bootstrapping.ejb;

import javax.inject.Inject;

/**
 * @author Antonio Goncalves
 *         http://www.antoniogoncalves.org
 */
public class Hello {

    // ======================================
    // =             Attributes             =
    // ======================================

    @Inject
    World world;

    // ======================================
    // =          Business methods          =
    // ======================================

    public String sayHelloWorld() {
        return "Hello " + world.sayWorld();
    }
}
