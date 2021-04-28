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
import vu.lt.entities.Role;
import vu.lt.entities.Show;
import vu.lt.persistence.RolesDAO;
import vu.lt.persistence.ShowsDAO;

@Model
public class RolesForShow implements Serializable {

    @Inject
    private ShowsDAO showDAO;

    @Inject
    private RolesDAO rolesDAO;

    @Getter @Setter
    private Role roleToCreate = new Role();

    @Getter @Setter
    private Show show;

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer teamId = Integer.parseInt(requestParameters.get("showId"));
        this.show = showDAO.findOne(teamId);
    }

    @Transactional
    public String createRole() {
        roleToCreate.setShow(this.show);
        rolesDAO.persist(roleToCreate);
        return "/roles?faces-redirect=true&showId=" + this.show.getId();
    }

    @Transactional
    public String deleteRole(Role role){
        this.rolesDAO.delete(role);
        return "/roles?faces-redirect=true&showId=" + this.show.getId();
    }
    @Transactional
    public String renameShow(){
        this.showDAO.update(show);
        return "/roles?faces-redirect=true&showId=" + this.show.getId();
    }
}