package vu.lt.usecases;

import lombok.Getter;
import lombok.Setter;
import vu.lt.entities.Genre;
import vu.lt.entities.Show;
import vu.lt.persistence.GenresDAO;
import vu.lt.persistence.ShowsDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class Genres {
    @Inject
    private GenresDAO genresDAO;

    @Inject
    private ShowsDAO showsDAO;

    @Getter @Setter
    private Genre genreToCreate = new Genre();

    @Getter @Setter
    private List<Genre> allGenres;

    @Getter @Setter
    private List<Show> showsByGenres;

    @PostConstruct
    public void init(){
        loadGenres();
    }

    public void loadGenres(){
        this.allGenres = genresDAO.loadAll();
    }

    @Transactional
    public String createGenre(){
        this.genresDAO.persist(genreToCreate);
        return "genres?faces-redirect=true";
    }

    @Transactional
    public String addShowToGenre(Integer showId, Integer genreId){
        Show show = this.showsDAO.findOne(showId);
        if(show == null) return "false";
        Genre genre = this.genresDAO.findOne(genreId);
        if(genre == null) return "false";
        genre.addShow(show);
        genresDAO.persist(genre);
        return "genres?faces-redirect=true";
    }

    @Transactional
    public String deleteGenre(Genre genreToDelete){
        this.genresDAO.delete(genreToDelete);
        return "genres?faces-redirect=true";
    }
}
