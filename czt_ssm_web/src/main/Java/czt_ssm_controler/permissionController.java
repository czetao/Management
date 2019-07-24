package czt_ssm_controler;

import czt_ssm_domain.Permission;
import czt_ssm_service.IpermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/permission")
public class permissionController {

    @Autowired
    private IpermissionService ipermissionService;

    //查询所有权限
    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception{
        ModelAndView mv = new ModelAndView();
        List<Permission> permissions = ipermissionService.findAll();
        mv.addObject("permissionList",permissions);
        mv.setViewName("permission-list");
        return mv;
    }

    //权限添加操作
    @RequestMapping("/save.do")
    public String save(Permission permission) throws Exception{
        ipermissionService.save(permission);
        return "redirect:findAll.do";
    }
}
