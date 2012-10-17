package org.agoncal.sample.cdi.bootstrapping.se;

import javax.inject.Inject;

/**
 * @author: Antonio Goncalves
 */
public class Hello {

    @Inject
    World world;

    public String sayHelloWorld() {
        return "Hello " + world.sayWorld();
    }
}
