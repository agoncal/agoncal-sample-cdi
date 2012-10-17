package org.agoncal.cdi.inject.producedispose;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class DatabaseProducer {

    @Produces
    @PersistenceContext(unitName = "cdiPU")
    @BookStoreDatabase
    private EntityManager em;
}
