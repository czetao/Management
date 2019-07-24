package czt_ssm_controler;


import czt_ssm_domain.Permission;
import czt_ssm_domain.Role;
import czt_ssm_service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/role")
public class roleController {

    @Autowired
    private IRoleService roleService;

    //根据角色id添加资源信息
    @RequestMapping("/findRoleByIdAndAllPermission.do")
    public ModelAndView findRoleByIdAndAllPermission(@RequestParam(name ="id" ,required = true) String rid) throws Exception{
        ModelAndView mv = new ModelAndView();
        //        //1.查询角色
        Role byid = roleService.findByid(rid);
        mv.addObject("role",byid);
        //2.查询添加资源
        List<Permission> permissions = roleService.findRoleByIdAndAllPermission(rid);
        mv.addObject("permissionList",permissions);

        mv.setViewName("role-permission-add");
        return mv;

    }

    //添加权限
    @RequestMapping("/addPermissionToRole.do")
    public String addPermissionToRole(@RequestParam(name = "roleId",required = true ) String rid,@RequestParam(name = "ids",required = true) String[] pid) throws Exception{
        roleService.addPermissionToRole(rid,pid);
        return "redirect:findAll.do";
    }
    //查询所有角色
    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception{
        ModelAndView mv = new ModelAndView();
        List<Role> ro = roleService.findAll();
        mv.addObject("roleList",ro);
        mv.setViewName("role-list");

        return mv;
    }


    //角色添加
    @RequestMapping("/save.do")
    public String save(Role role) throws Exception{

        roleService.save(role);

        return "redirect:findAll.do";
    }


}
