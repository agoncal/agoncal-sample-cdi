package org.agoncal.sample.injection.withcdi;

import javax.annotation.PostConstruct;
import javax.annotation.sql.DataSourceDefinition;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.util.logging.Logger;

/**
 * @author Antonio Goncalves
 *         http://www.antoniogoncalves.org
 */
@DataSourceDefinition(name = "java:global/jdbc/sampleCdiInjectionWithCDIDS",
        className = "org.apache.derby.jdbc.EmbeddedDriver",
        url = "jdbc:derby:memory:sampleCdiInjectionWithCDIDB;create=true;user=app;password=app"
)
@Singleton
@Startup
public class DatabasePopulator {

    // ======================================
    // =             Attributes             =
    // ======================================

    @EJB
    private ItemEJB itemEJB;

    private Logger logger = Logger.getLogger(DatabasePopulator.class.getName());

    // ======================================
    // =          Lifecycle Methods         =
    // ======================================

    @PostConstruct
    private void populateDB() {

        itemEJB.createBook(new Book("H2G2", 12.5f, "Best IT Scifi Book", 457, false, "English", "scifi", "1234-5678-91011"));
        itemEJB.createBook(new Book("Harry Potter and the Goblet of Fire", 19.79f, "Science fiction (Book 4)", 734, true, "English", "scifi", "1234-5678-91011"));
        itemEJB.createBook(new Book("The Elements of Style", 6.64f, "A masterpiece in the art of clear and concise writing.", 105, false, "English", "it", "1234-5678-91011"));
        itemEJB.createBook(new Book("The Difference Between God and Larry Ellison", 11.99f, "God Doesn't Think He's Larry Ellison", 420, false, "English", "scifi", "1234-5678-91011"));
        itemEJB.createBook(new Book("The Da Vinci Code", 17.79f, "Thriller", 454, false, "English", "thriller", "1234-5678-91011"));

        logger.info("######### Inserted " + itemEJB.findAllBooks().size() + " Books");
    }
}
