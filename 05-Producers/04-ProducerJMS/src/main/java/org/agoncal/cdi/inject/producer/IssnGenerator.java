package org.agoncal.cdi.inject.producer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;
import java.util.Random;

/**
 * @author Antonio Goncalves
 *         http://www.antoniogoncalves.org
 */
@EightDigits
public class IssnGenerator implements NumberGenerator {

    // ======================================
    // =             Attributes             =
    // ======================================

    @Inject
    @EightDigits
    private String prefix;

    @Inject
    @EightDigits
    private int postfix;

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
        return prefix + "-" + Math.abs(new Random().nextInt()) + "-" + postfix;
    }
}