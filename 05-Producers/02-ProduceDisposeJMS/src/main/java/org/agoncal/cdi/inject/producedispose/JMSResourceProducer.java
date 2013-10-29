package org.agoncal.cdi.inject.producedispose;

import javax.annotation.Resource;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.jms.*;

/**
 * @author Antonio Goncalves
 *         http://www.antoniogoncalves.org
 */
public class JMSResourceProducer {

    @Resource(name = "jms/OrderConnectionFactory")
    private QueueConnectionFactory orderConnectionFactory;
    @Resource(name = "jms/OrderQueue")
    @Produces
    @Order
    private Queue orderQueue;

    // You can either have two methods refering each other (for producers & disposers)
    @Produces
    @Order
    public QueueConnection createOrderConnection() throws JMSException {
        return orderConnectionFactory.createQueueConnection();
    }

    @Produces
    @Order
    public QueueSession createOrderSession(@Order QueueConnection conn) throws JMSException {
        return conn.createQueueSession(true, Session.AUTO_ACKNOWLEDGE);
    }

    public void closeOrderSession(@Disposes @Order QueueConnection conn) throws JMSException {
        conn.close();
    }

    public void closeOrderSession(@Disposes @Order QueueSession session) throws JMSException {
        session.close();
    }

    // or a single one
//    @Produces @Order
//    public QueueSession createOrderSession() throws JMSException {
//        QueueConnection conn = orderConnectionFactory.createQueueConnection();
//        QueueSession session = conn.createQueueSession(true, Session.AUTO_ACKNOWLEDGE);
//        return session;
//    }
//
//    public void closeOrderSession(@Order QueueSession session) throws JMSException {
//        session.close();
//    }
}
