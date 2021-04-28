package vu.lt.usecases;

import lombok.Getter;
import lombok.Setter;
import vu.lt.mybatis.dao.GenreMapper;
import vu.lt.mybatis.dao.ShowGenreMapper;
import vu.lt.mybatis.dao.ShowMapper;
import vu.lt.mybatis.model.Genre;
import vu.lt.mybatis.model.Show;
import vu.lt.mybatis.model.ShowGenre;


import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class GenresMyBatis {
    @Inject
    private GenreMapper genreMapper;

    @Inject
    private ShowMapper showMapper;

    @Inject
    private ShowGenreMapper showGenreMapper;

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
        this.allGenres = genreMapper.selectAll();
    }

    @Transactional
    public String createGenre(){
        this.genreMapper.insert(genreToCreate);
        return "/mybatis/genres?faces-redirect=true";
    }

    @Transactional
    public String addShowToGenre(Integer showId, Integer genreId){
        Show show = this.showMapper.selectByPrimaryKey(showId);
        if(show == null) return "false";
        Genre genre = this.genreMapper.selectByPrimaryKey(genreId);
        ShowGenre showGenre = new ShowGenre();
        showGenre.setGenreId(genreId);
        showGenre.setShowId(showId);
        showGenreMapper.insert(showGenre);
        return "/mybatis/genres?faces-redirect=true";
    }

    @Transactional
    public String deleteGenre(Genre genreToDelete){
        this.genreMapper.deleteByPrimaryKey(genreToDelete.getId());
        return "/mybatis/genres?faces-redirect=true";
    }
}
