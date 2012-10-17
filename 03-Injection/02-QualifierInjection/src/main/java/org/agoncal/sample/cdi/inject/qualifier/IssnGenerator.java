package org.agoncal.sample.cdi.inject.qualifier;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Random;
import java.util.logging.Logger;

/**
 * @author Antonio Goncalves
 *         http://www.antoniogoncalves.org
 */
@EightDigits
public class IssnGenerator implements NumberGenerator {

    // ======================================
    // =             Attributes             =
    // ======================================

    private Logger logger = Logger.getLogger(IsbnGenerator.class.getName());

    // ======================================
    // =          Lifecycle methods         =
    // ======================================

    @PostConstruct
    private void init() {
        logger.info("=> IssnGenerator PostConstruct");
        logger.info("================");
    }

    @PreDestroy
    private void release() {
        logger.info("================");
        logger.info("=> IssnGenerator PreDestroy");
    }

    // ======================================
    // =          Business methods          =
    // ======================================

    @Override
    public String generateNumber() {
        return "8-" + Math.abs(new Random().nextInt());
    }
}