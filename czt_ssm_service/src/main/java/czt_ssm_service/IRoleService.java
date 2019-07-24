package czt_ssm_service;

import czt_ssm_domain.Permission;
import czt_ssm_domain.Role;

import java.util.List;

public interface IRoleService {

    public List<Role> findAll() throws Exception;

    void save(Role role) throws Exception;

    Role findByid(String rid);

    List<Permission> findRoleByIdAndAllPermission(String rid);

    void addPermissionToRole(String rid,String[] pids);
}
