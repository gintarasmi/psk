package vu.lt.usecases;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import vu.lt.mybatis.dao.GenreMapper;
import vu.lt.mybatis.dao.ShowMapper;
import vu.lt.mybatis.model.Genre;


@Model
public class ShowsForGenreMyBatis {

    @Inject
    private ShowMapper showMapper;

    @Inject
    private GenreMapper genreMapper;

    @Getter @Setter
    private Genre genre;

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer teamId = Integer.parseInt(requestParameters.get("genreId"));
        this.genre = genreMapper.selectByPrimaryKey(teamId);
    }
    @Transactional
    public String renameGenre(){
        this.genreMapper.updateByPrimaryKey(genre);
        return "/mybatis/genre?faces-redirect=true&genreId=" + this.genre.getId();
    }

}