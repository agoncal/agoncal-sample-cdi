package org.agoncal.cdi.inject.jmslistener;

import javax.annotation.Resource;
import javax.jms.*;

/**
 * @author Antonio Goncalves
 *         http://www.antoniogoncalves.org
 */
public class JMSListener implements MessageListener {

    // ======================================
    // =             Attributes             =
    // ======================================

    @Resource(lookup = "jms/OrderConnectionFactory")
    private static QueueConnectionFactory orderConnectionFactory;
    @Resource(lookup = "jms/OrderQueue")
    private static Queue orderQueue;

    // ======================================
    // =          Business methods          =
    // ======================================

    public static void main(String[] args) {
        try {
            QueueConnection qcon = orderConnectionFactory.createQueueConnection();
            QueueSession session = qcon.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageConsumer receiver = session.createReceiver(orderQueue);
            receiver.setMessageListener(new JMSListener());
            System.out.println("\nListening...\n");
            qcon.start();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onMessage(Message message) {
        try {
            System.out.println("### Message received: " + ((TextMessage) message).getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}