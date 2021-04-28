package vu.lt.persistence;

import vu.lt.entities.Genre;
import vu.lt.entities.Role;
import vu.lt.entities.Show;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@ApplicationScoped
public class RolesDAO {
    @Inject
    private EntityManager em;

    public List<Role> loadAll() {
        return em.createNamedQuery("Role.findAll", Role.class).getResultList();
    }

    public void persist(Role role){
        this.em.persist(role);
    }
    public void delete(Role role){ this.em.remove(role); }
    public void update(Role role){ this.em.merge(role); }

    public Role findOne(Integer id) { return em.find(Role.class, id); }
}