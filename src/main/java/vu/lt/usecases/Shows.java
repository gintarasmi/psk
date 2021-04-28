package vu.lt.usecases;

import lombok.Getter;
import lombok.Setter;
import vu.lt.entities.Role;
import vu.lt.entities.Show;
import vu.lt.persistence.ShowsDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model

public class Shows {
    @Inject
    private ShowsDAO showsDAO;

    @Getter @Setter
    private Show showToCreate = new Show();

    @Getter
    private List<Show> allShows;

    @PostConstruct
    public void init(){
        loadAllShows();
    }

    @Transactional
    public String createShow(){
        this.showsDAO.persist(showToCreate);
        return "/index?faces-redirect=true";
    }
    @Transactional
    public String deleteShow(Show showToDelete){
        this.showsDAO.delete(showToDelete);
        return "/index?faces-redirect=true";
    }

    private void loadAllShows(){
        this.allShows = showsDAO.loadAll();
    }
}
