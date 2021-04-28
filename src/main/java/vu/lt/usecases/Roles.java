package vu.lt.usecases;

import lombok.Getter;
import lombok.Setter;
import vu.lt.entities.Genre;
import vu.lt.entities.Role;
import vu.lt.persistence.RolesDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Map;

@Model
public class Roles {
    @Inject
    private RolesDAO rolesDAO;

    @Getter @Setter
    private Role role;

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer roleId = Integer.parseInt(requestParameters.get("roleId"));
        this.role = rolesDAO.findOne(roleId);
    }
    @Transactional
    public String renameRole(){
        this.rolesDAO.update(role);
        return "/role?faces-redirect=true&roleId=" + this.role.getId();
    }
}
