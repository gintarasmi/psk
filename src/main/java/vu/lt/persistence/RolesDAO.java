package vu.lt.persistence;

import vu.lt.entities.Role;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@ApplicationScoped
public class RolesDAO {
    @Inject
    private EntityManager em;

    public void persist(Role role){
        this.em.persist(role);
    }
}