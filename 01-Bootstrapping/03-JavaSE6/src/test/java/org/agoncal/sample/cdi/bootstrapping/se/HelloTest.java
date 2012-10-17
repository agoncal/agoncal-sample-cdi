package org.agoncal.sample.cdi.bootstrapping.se;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class HelloTest {

    @Test
    public void shouldDisplayHelloWorld() {
        WeldContainer weld = new Weld().initialize();
        Hello hello = weld.instance().select(Hello.class).get();
        assertEquals("should say Hello World !!!", "Hello World !!!", hello.sayHelloWorld());
    }
}
