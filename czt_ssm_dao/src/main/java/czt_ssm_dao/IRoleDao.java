package czt_ssm_dao;

import czt_ssm_domain.Permission;
import czt_ssm_domain.Role;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IRoleDao {

    //通过用户id查询出所有的角色
    @Select("select * from role where id in (select roleId from users_role where userId=#{uid})")
    @Results({
            @Result(id=true , property = "id" ,column = "id"),
            @Result( property = "roleName" ,column = "roleName"),
            @Result( property = "roleDesc" ,column = "roleDesc"),
            @Result(property = "permissions" ,column = "id",javaType = java.util.List.class,many = @Many(select = "czt_ssm_dao.IPermissionDao.findPermission"))
    })
     List<Role> findRole(String uid) throws Exception;

    //查询所有角色
    @Select("select * from role")
     List<Role> findAll() throws Exception;


    //添加用户角色
    @Insert("insert into role(roleName,roleDesc) values(#{roleName},#{roleDesc})")
    void save(Role role) throws Exception;

    //根据id查询角色
    @Select("select * from role where id=#{rid}")
    Role findByid(String rid);

    //
    @Select("select * from permission where id not in (select permissionId from role_permission where roleId = #{rid})")
    List<Permission> findRoleByIdAndAllPermission(String rid);

    @Insert("insert into role_permission values(#{pid},#{rid})")
    void addPermissionToRole(@Param("rid") String rid, @Param("pid") String pid);
}
