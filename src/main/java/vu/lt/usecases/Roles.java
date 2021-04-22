package vu.lt.usecases;

import vu.lt.entities.Role;
import vu.lt.persistence.RolesDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Model
public class Roles implements Serializable {

    @Inject
    private RolesDAO rolesDAO;

    private Role roleToCreate = new Role();

    private List<Role> allRoles;
    @PostConstruct
    public void init(){
        loadRoles();
    }

    public void loadRoles() {
        this.allRoles = rolesDAO.loadAll();
    }

    public List<Role> getAllRoles(){
        return allRoles;
    }

    @Transactional
    public String createRole(){
        this.rolesDAO.persist(roleToCreate);
        return "success";
    }

    public Role getRoleToCreate() {
        return roleToCreate;
    }

    public void setRoleToCreate(Role roleToCreate) {
        this.roleToCreate = roleToCreate;
    }
}
