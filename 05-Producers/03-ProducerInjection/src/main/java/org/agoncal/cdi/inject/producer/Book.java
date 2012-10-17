package org.agoncal.cdi.inject.producer;

import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Antonio Goncalves
 *         http://www.antoniogoncalves.org
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "findAllBooks", query = "SELECT b FROM Book b ORDER BY b.id DESC"),
        @NamedQuery(name = "findAllScifiBooks", query = "SELECT b FROM Book b WHERE 'scifi' member of b.tags ORDER BY b.id DESC")
})
@XmlRootElement
public class Book {

    // ======================================
    // =             Attributes             =
    // ======================================
    @Id
    @GeneratedValue
    protected Long id;
    @Column(nullable = false)
    @Size(min = 5, max = 50, message = "Title should be between {min} and {max}")
    protected String title;
    protected Float price;
    @Column(length = 2000)
    protected String description;
    private String isbn;
    private Integer nbOfPage;
    private Boolean illustrations;
    private String contentLanguage;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "tags")
    private List<String> tags = new ArrayList<String>();

    // ======================================
    // =            Constructors            =
    // ======================================

    public Book() {
    }

    public Book(String isbn) {
        this.isbn = isbn;
    }

    public Book(String title, Float price, String description, Integer nbOfPage, Boolean illustrations, String contentLanguage, String tag, String isbn) {
        this.title = title;
        this.price = price;
        this.description = description;
        this.nbOfPage = nbOfPage;
        this.illustrations = illustrations;
        this.contentLanguage = contentLanguage;
        this.isbn = isbn;
        this.addTag(tag);
    }

    // ======================================
    // =          Getters & Setters         =
    // ======================================

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Integer getNbOfPage() {
        return nbOfPage;
    }

    public void setNbOfPage(Integer nbOfPage) {
        this.nbOfPage = nbOfPage;
    }

    public Boolean getIllustrations() {
        return illustrations;
    }

    public void setIllustrations(Boolean illustrations) {
        this.illustrations = illustrations;
    }

    public String getContentLanguage() {
        return contentLanguage;
    }

    public void setContentLanguage(String contentLanguage) {
        this.contentLanguage = contentLanguage;
    }

    public void addTag(String tag) {
        tags.add(tag);
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    // ======================================
    // =         hash, equals, toString     =
    // ======================================

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Book");
        sb.append("{id=").append(id);
        sb.append(", title='").append(title).append('\'');
        sb.append(", price=").append(price);
        sb.append(", description='").append(description).append('\'');
        sb.append(", isbn='").append(isbn).append('\'');
        sb.append(", nbOfPage=").append(nbOfPage);
        sb.append(", illustrations=").append(illustrations);
        sb.append(", contentLanguage='").append(contentLanguage).append('\'');
        sb.append(", tags=").append(tags);
        sb.append('}');
        return sb.toString();
    }
}