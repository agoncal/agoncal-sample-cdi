package org.agoncal.cdi.inject.producedispose;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class MainEJBTest {

    // ======================================
    // =             Attributes             =
    // ======================================
    private static EJBContainer ec;
    private static Context ctx;

    // ======================================
    // =          Lifecycle Methods         =
    // ======================================

    @BeforeClass
    public static void initContainer() throws Exception {
        Map<String, Object> properties = new HashMap<String, Object>();
        properties.put(EJBContainer.MODULES, new File("target/classes"));
        ec = EJBContainer.createEJBContainer(properties);
        ctx = ec.getContext();
    }

    @AfterClass
    public static void closeContainer() throws Exception {
        if (ec != null)
            ec.close();
    }

    // ======================================
    // =              Unit tests            =
    // ======================================

    @Test @Ignore("TODO With Java EE 7 we should use the @JMSxxxDefinition annotation to define a factory and destinations")
    public void shouldCreateABook() throws Exception {

        // Looks up for the EJB
        ItemEJB itemEJB = (ItemEJB) ctx.lookup("java:global/classes/ItemEJB");

        // Counts books and scifi books in the database
        int nbBooks = itemEJB.findAllBooks().size();
        int nbScifiBooks = itemEJB.findAllScifiBooks().size();

        // Creates a new scifi book
        Book book = new Book();
        book.setTitle("The Hitchhiker's Guide to the Galaxy");
        book.setPrice(12.5F);
        book.setDescription("Science fiction comedy book");
        book.setNbOfPage(354);
        book.setIllustrations(false);
        book.addTag("scifi");

        // Persists the book to the database
        book = itemEJB.createBook(book);
        assertNotNull("ID should not be null", book.getId());

        // Checks the ISBN starts with 13
        assertTrue(book.getIsbn().startsWith("13-"));

        // Checks that there is an extra book in the database
        assertEquals("Should have an extra book", itemEJB.findAllBooks().size(), nbBooks + 1);
        assertEquals("Should have an extra scifi book", itemEJB.findAllScifiBooks().size(), nbScifiBooks + 1);

        // Creates a new book
        book = new Book();
        book.setTitle("Java EE 6");
        book.setPrice(48.5F);
        book.setDescription("More Java EE 6 books sold than Harry Potter");
        book.setNbOfPage(456);
        book.setIllustrations(true);

        // Persists the book to the database
        book = itemEJB.createBook(book);
        assertNotNull("ID should not be null", book.getId());

        // Checks the ISBN starts with 13
        assertTrue(book.getIsbn().startsWith("13-"));

        // Checks that there is an extra book in the database
        assertEquals("Should have an extra book", itemEJB.findAllBooks().size(), nbBooks + 2);
        assertEquals("Should have the same amount of scifi books", itemEJB.findAllScifiBooks().size(), nbScifiBooks + 1);
    }
}
