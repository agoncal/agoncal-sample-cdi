package org.agoncal.sample.injection.withoutcdi;

import javax.annotation.ManagedBean;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * @author Antonio Goncalves
 *         http://www.antoniogoncalves.org
 */
@Path("/items")
// to make injection work you either need a Stateless, Singleton or ManagedBean
//@Stateless
//@Singleton
@ManagedBean
public class ItemRestService {

    // ======================================
    // =             Attributes             =
    // ======================================

    @Resource
    private IsbnGenerator numberGenerator;

    @EJB
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
}