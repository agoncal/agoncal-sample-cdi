package org.agoncal.sample.cdi.inject.setter;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author Antonio Goncalves
 *         http://www.antoniogoncalves.org
 */
@WebServlet(urlPatterns = "/itemServlet")
public class ItemServlet extends HttpServlet {

    // ======================================
    // =             Attributes             =
    // ======================================

    private NumberGenerator numberGenerator;
    private ItemEJB itemEJB;

    // ======================================
    // =          Business methods          =
    // ======================================

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Creates an instance of book
        Book book = new Book("title", 999.99F, "description", 666, false, "english", "scifi", numberGenerator.generateNumber());
        itemEJB.createBook(book);

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<HTML>");
        out.println("<HEAD><TITLE>Display all books</TITLE></HEAD>");
        out.println("<BODY>");

        out.println("<h1>====== An ISBN number</h1>");
        out.println("==> " + numberGenerator.generateNumber() + "<br/>");

        out.println("<h1>====== All books</h1>");
        List<Book> books = itemEJB.findAllBooks();
        for (Book b : books) {
            out.println("==> " + b + "<br/>");
        }

        out.println("<h1>====== All Scifi books</h1>");
        books = itemEJB.findAllScifiBooks();
        for (Book b : books) {
            out.println("==> " + b + "<br/>");
        }
        out.println("</BODY>");
        out.println("</HTML>");
        out.close();
    }

    // ======================================
    // =          Getters & Setters         =
    // ======================================

    @Inject
    public void setNumberGenerator(@ThirteenDigits NumberGenerator numberGenerator) {
        this.numberGenerator = numberGenerator;
    }

    @Inject
    public void setItemEJB(ItemEJB itemEJB) {
        this.itemEJB = itemEJB;
    }
}
