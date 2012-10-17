package org.agoncal.cdi.inject.producer;

import javax.annotation.Resource;
import javax.enterprise.inject.Produces;
import javax.jms.Queue;
import javax.jms.QueueConnectionFactory;

/**
 * @author Antonio Goncalves
 *         http://www.antoniogoncalves.org
 */
public class JMSResourceProducer {

    @Produces
    @Order
    @Resource(name = "jms/OrderConnectionFactory")
    private QueueConnectionFactory orderConnectionFactory;
    @Produces
    @Order
    @Resource(name = "jms/OrderQueue")
    private Queue orderQueue;
}
