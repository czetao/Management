package czt_ssm_service;

import czt_ssm_domain.Role;
import czt_ssm_domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends UserDetailsService {


    public List<UserInfo> findAll() throws Exception;


    public void save(UserInfo userInfo) throws Exception;

    public UserInfo findByid(int id) throws Exception;

    List<Role> findUserByIdAndAllRole(String id) throws Exception;

    void addRoletoUser(String uid, String[] rids);
}
