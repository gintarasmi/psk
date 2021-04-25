package vu.lt.persistence;

import vu.lt.entities.Show;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class ShowsDAO {
    @Inject
    private EntityManager em;

    public List<Show> loadAll() {
        return em.createNamedQuery("Show.findAll", Show.class).getResultList();
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void persist(Show show){
        this.em.persist(show);
    }
    public void delete(Show show){ this.em.remove(show); }

    public Show findOne(Integer id) { return em.find(Show.class, id); }
}
