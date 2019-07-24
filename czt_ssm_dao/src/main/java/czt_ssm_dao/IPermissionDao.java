package czt_ssm_dao;

import czt_ssm_domain.Permission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IPermissionDao {
    @Select("select * from permission where id in (select permissionId from role_permission where roleId=#{rid})")
    public List<Permission> findPermission(String rid) throws Exception;


    //查找所有的资源
    @Select("select * from permission")
    public List<Permission> findAll() throws Exception;


    @Insert("insert into permission(permissionName,url) values(#{permissionName},#{url})")
    void save(Permission permission) throws Exception;
}
