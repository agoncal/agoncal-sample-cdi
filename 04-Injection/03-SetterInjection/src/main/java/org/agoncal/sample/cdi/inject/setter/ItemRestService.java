package org.agoncal.sample.cdi.inject.setter;

import javax.annotation.ManagedBean;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * @author Antonio Goncalves
 *         http://www.antoniogoncalves.org
 */
@Path("/items")
@ManagedBean
public class ItemRestService {

    // ======================================
    // =             Attributes             =
    // ======================================

    private NumberGenerator numberGenerator;
    private ItemEJB itemEJB;

    // ======================================
    // =          Business methods          =
    // ======================================

    @GET
    @Path("/{bookKey}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Book getBook(@PathParam("bookKey") Long id) {
        return itemEJB.getBook(id);
    }

    @GET
    @Path("/isbn")
    @Produces(MediaType.TEXT_PLAIN)
    public String getISBN() {
        return numberGenerator.generateNumber();
    }

    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Book createBook(Book book) {
        book.setIsbn(numberGenerator.generateNumber());
        itemEJB.createBook(book);
        return book;
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Book> findAllBooks() {
        return itemEJB.findAllBooks();
    }

    // ======================================
    // =          Getters & Setters         =
    // ======================================

    @Inject
    public void setNumberGenerator(@EightDigits NumberGenerator numberGenerator) {
        this.numberGenerator = numberGenerator;
    }

    @Inject
    public void setItemEJB(ItemEJB itemEJB) {
        this.itemEJB = itemEJB;
    }
}