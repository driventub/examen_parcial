package org.example.config;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

@ApplicationScoped
public class EntityManagerProducer {

    private EntityManagerFactory  emf;

    @PostConstruct
    public void init() {
        System.out.println("***init");
        emf = Persistence.createEntityManagerFactory("myPU");
    }

    @Produces
    public EntityManager em() {
        System.out.println("***em");
        return emf.createEntityManager();
    }
}
