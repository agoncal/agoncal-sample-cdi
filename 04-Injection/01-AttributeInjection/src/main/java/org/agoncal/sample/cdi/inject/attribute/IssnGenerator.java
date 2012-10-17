package org.agoncal.sample.cdi.inject.attribute;


import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Random;

/**
 * @author Antonio Goncalves
 *         http://www.antoniogoncalves.org
 */
@EightDigits
public class IssnGenerator implements NumberGenerator {

    // ======================================
    // =          Lifecycle methods         =
    // ======================================

    @PostConstruct
    private void init() {
        System.out.println("\n=> IssnGenerator PostConstruct");
        System.out.println("================");
    }

    @PreDestroy
    private void release() {
        System.out.println("================");
        System.out.println("=> IssnGenerator PreDestroy");
    }

    // ======================================
    // =          Business methods          =
    // ======================================

    public String generateNumber() {
        return "8-" + Math.abs(new Random().nextInt());
    }
}