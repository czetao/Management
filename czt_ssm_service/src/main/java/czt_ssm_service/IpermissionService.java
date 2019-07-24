package czt_ssm_service;

import czt_ssm_domain.Permission;

import java.util.List;

public interface IpermissionService {

    public List<Permission> findAll() throws Exception;

    void save(Permission permission) throws Exception;
}
