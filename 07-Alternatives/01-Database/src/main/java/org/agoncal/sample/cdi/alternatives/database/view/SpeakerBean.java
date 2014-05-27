package org.agoncal.sample.cdi.alternatives.database.view;

import org.agoncal.sample.cdi.alternatives.database.model.Speaker;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Backing bean for Speaker entities.
 * <p/>
 * This class provides CRUD functionality for all Speaker entities. It focuses
 * purely on Java EE 6 standards (e.g. <tt>&#64;ConversationScoped</tt> for
 * state management, <tt>PersistenceContext</tt> for persistence,
 * <tt>CriteriaBuilder</tt> for searches) rather than introducing a CRUD framework or
 * custom base class.
 */

@Named
@Transactional
@ConversationScoped
public class SpeakerBean implements Serializable {

    private static final long serialVersionUID = 1L;

	/*
     * Support creating and retrieving Speaker entities
	 */

    private java.lang.Long id;

    public java.lang.Long getId() {
        return this.id;
    }

    public void setId(java.lang.Long id) {
        this.id = id;
    }

    private Speaker speaker;

    public Speaker getSpeaker() {
        return this.speaker;
    }

    @Inject
    private Conversation conversation;

    @Inject
    private EntityManager entityManager;

    public String create() {

        this.conversation.begin();
        return "create?faces-redirect=true";
    }

    public void retrieve() {

        if (FacesContext.getCurrentInstance().isPostback()) {
            return;
        }

        if (this.conversation.isTransient()) {
            this.conversation.begin();
        }

        if (this.id == null) {
            this.speaker = this.example;
        } else {
            this.speaker = findById(getId());
        }
    }

    public Speaker findById(java.lang.Long id) {

        return this.entityManager.find(Speaker.class, id);
    }

	/*
     * Support updating and deleting Speaker entities
	 */

    public String update() {
        this.conversation.end();

        try {
            if (this.id == null) {
                this.entityManager.persist(this.speaker);
                return "search?faces-redirect=true";
            } else {
                this.entityManager.merge(this.speaker);
                return "view?faces-redirect=true&id=" + this.speaker.getId();
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
            return null;
        }
    }

    public String delete() {
        this.conversation.end();

        try {
            Speaker deletableEntity = findById(getId());

            this.entityManager.remove(deletableEntity);
            this.entityManager.flush();
            return "search?faces-redirect=true";
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
            return null;
        }
    }

	/*
	 * Support searching Speaker entities with pagination
	 */

    private int page;
    private long count;
    private List<Speaker> pageItems;

    private Speaker example = new Speaker();

    public int getPage() {
        return this.page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return 10;
    }

    public Speaker getExample() {
        return this.example;
    }

    public void setExample(Speaker example) {
        this.example = example;
    }

    public void search() {
        this.page = 0;
    }

    public void paginate() {

        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();

        // Populate this.count

        CriteriaQuery<Long> countCriteria = builder.createQuery(Long.class);
        Root<Speaker> root = countCriteria.from(Speaker.class);
        countCriteria = countCriteria.select(builder.count(root)).where(
                getSearchPredicates(root));
        this.count = this.entityManager.createQuery(countCriteria)
                .getSingleResult();

        // Populate this.pageItems

        CriteriaQuery<Speaker> criteria = builder.createQuery(Speaker.class);
        root = criteria.from(Speaker.class);
        TypedQuery<Speaker> query = this.entityManager.createQuery(criteria
                .select(root).where(getSearchPredicates(root)));
        query.setFirstResult(this.page * getPageSize()).setMaxResults(
                getPageSize());
        this.pageItems = query.getResultList();
    }

    private Predicate[] getSearchPredicates(Root<Speaker> root) {

        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        List<Predicate> predicatesList = new ArrayList<Predicate>();

        String firstname = this.example.getFirstname();
        if (firstname != null && !"".equals(firstname)) {
            predicatesList.add(builder.like(builder.lower(root.<String>get("firstname")), '%' + firstname.toLowerCase() + '%'));
        }
        String surname = this.example.getSurname();
        if (surname != null && !"".equals(surname)) {
            predicatesList.add(builder.like(builder.lower(root.<String>get("surname")), '%' + surname.toLowerCase() + '%'));
        }
        String bio = this.example.getBio();
        if (bio != null && !"".equals(bio)) {
            predicatesList.add(builder.like(builder.lower(root.<String>get("bio")), '%' + bio.toLowerCase() + '%'));
        }
        String twitter = this.example.getTwitter();
        if (twitter != null && !"".equals(twitter)) {
            predicatesList.add(builder.like(builder.lower(root.<String>get("twitter")), '%' + twitter.toLowerCase() + '%'));
        }

        return predicatesList.toArray(new Predicate[predicatesList.size()]);
    }

    public List<Speaker> getPageItems() {
        return this.pageItems;
    }

    public long getCount() {
        return this.count;
    }

	/*
	 * Support listing and POSTing back Speaker entities (e.g. from inside an
	 * HtmlSelectOneMenu)
	 */

    public List<Speaker> getAll() {

        CriteriaQuery<Speaker> criteria = this.entityManager
                .getCriteriaBuilder().createQuery(Speaker.class);
        return this.entityManager.createQuery(
                criteria.select(criteria.from(Speaker.class))).getResultList();
    }

    private Speaker add = new Speaker();

    public Speaker getAdd() {
        return this.add;
    }

    public Speaker getAdded() {
        Speaker added = this.add;
        this.add = new Speaker();
        return added;
    }
}