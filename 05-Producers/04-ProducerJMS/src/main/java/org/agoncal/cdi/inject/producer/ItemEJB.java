package org.agoncal.cdi.inject.producer;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.*;
import javax.persistence.EntityManager;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.util.List;

/**
 * @author Antonio Goncalves
 *         http://www.antoniogoncalves.org
 */
@Stateless
public class ItemEJB {

    // ======================================
    // =             Attributes             =
    // ======================================

    @Inject
    @BookStoreDatabase
    private EntityManager em;

    @Inject
    @ThirteenDigits
    private NumberGenerator numberGenerator;

    @Inject
    @Order
    private QueueConnectionFactory orderConnectionFactory;

    @Inject
    @Order
    private Queue orderQueue;

    // ======================================
    // =          Business methods          =
    // ======================================

    public Book getBook(Long id) {
        return em.find(Book.class, id);
    }

    public Book createBook(Book book) {
        book.setIsbn(numberGenerator.generateNumber());
        em.persist(book);
        sendOrder(book);
        return book;
    }

    public List<Book> findAllBooks() {
        return em.createNamedQuery("findAllBooks", Book.class).getResultList();
    }

    public List<Book> findAllScifiBooks() {
        return em.createNamedQuery("findAllScifiBooks", Book.class).getResultList();
    }

    private void sendOrder(Book book) {
        try {
            QueueConnection qcon = orderConnectionFactory.createQueueConnection();
            QueueSession session = qcon.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
            QueueSender sender = session.createSender(orderQueue);
            TextMessage message = session.createTextMessage();
            message.setText(marshall(book));
            sender.send(message);
            session.close();
            qcon.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String marshall(Book book) throws JAXBException {
        StringWriter writer = new StringWriter();
        JAXBContext jaxbContext = JAXBContext.newInstance(Book.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.marshal(book, writer);
        return writer.toString();
    }
}