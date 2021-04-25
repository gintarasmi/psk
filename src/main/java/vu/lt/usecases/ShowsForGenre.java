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
import vu.lt.entities.Genre;
import vu.lt.entities.Role;
import vu.lt.entities.Show;
import vu.lt.persistence.GenresDAO;
import vu.lt.persistence.RolesDAO;
import vu.lt.persistence.ShowsDAO;

@Model
public class ShowsForGenre implements Serializable {

    @Inject
    private ShowsDAO showDAO;

    @Inject
    private GenresDAO genresDAO;

    @Getter @Setter
    private Genre genre;

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer teamId = Integer.parseInt(requestParameters.get("genreId"));
        this.genre = genresDAO.findOne(teamId);
    }

}