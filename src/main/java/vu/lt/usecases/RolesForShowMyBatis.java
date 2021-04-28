package vu.lt.usecases;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import vu.lt.mybatis.model.Role;
import vu.lt.mybatis.model.Show;
import vu.lt.mybatis.dao.RoleMapper;
import vu.lt.mybatis.dao.ShowMapper;
import vu.lt.persistence.RolesDAO;
import vu.lt.persistence.ShowsDAO;

@Model
public class RolesForShowMyBatis{

    @Inject
    private RoleMapper rolesMapper;

    @Inject
    private ShowMapper showMapper;

    @Getter @Setter
    private Role roleToCreate = new Role();

    @Getter @Setter
    private Show show;

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer teamId = Integer.parseInt(requestParameters.get("showId"));
        this.show = showMapper.selectByPrimaryKey(teamId);
    }

    @Transactional
    public String createRole() {
        roleToCreate.setShowId(this.show.getId());
        rolesMapper.insert(roleToCreate);
        return "/mybatis/roles?faces-redirect=true&showId=" + this.show.getId();
    }

    @Transactional
    public String deleteRole(Role role){
        this.rolesMapper.deleteByPrimaryKey(role.getId());
        return "/mybatis/roles?faces-redirect=true&showId=" + this.show.getId();
    }

    @Transactional
    public List<Role> getRoles(Integer id){
       return showMapper.selectAllRoles(id);
    }
}