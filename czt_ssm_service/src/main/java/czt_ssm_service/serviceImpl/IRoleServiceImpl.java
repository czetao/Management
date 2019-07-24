package czt_ssm_service.serviceImpl;

import czt_ssm_dao.IRoleDao;
import czt_ssm_domain.Permission;
import czt_ssm_domain.Role;
import czt_ssm_service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class IRoleServiceImpl implements IRoleService {

    @Autowired
    private IRoleDao roleDao;


    @Override
    public List<Role> findAll() throws Exception {
        return  roleDao.findAll();
    }

    @Override
    public void save(Role role) throws Exception {
        roleDao.save(role);
    }

    @Override
    public Role findByid(String rid) {
        roleDao.findByid(rid);
        return null;
    }

    @Override
    public List<Permission> findRoleByIdAndAllPermission(String rid) {
        roleDao.findRoleByIdAndAllPermission(rid);
        return null;
    }

    @Override
    public void addPermissionToRole(String rid, String[] pids) {
        for (String pid :pids
             ) {
            roleDao.addPermissionToRole(rid,pid);
        }
    }
}
