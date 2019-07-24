
package czt_ssm_controler;

import czt_ssm_domain.Role;
import czt_ssm_domain.UserInfo;
import czt_ssm_service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
@Controller
@RequestMapping("/user")
public class usersController {

   @Autowired
   private IUserService userService;

   //添加用户角色
   @RequestMapping("/addRoleToUser.do")
   public String addRoleToUser(@RequestParam(name = "userId",required = true) String uid,@RequestParam(name = "ids",required = true) String[] rids) throws Exception{

      userService.addRoletoUser(uid ,rids );
      return "redirect:findAll.do";
   }


   //查询用户以及用户可以添加的角色
   @RequestMapping("/findUserByIdAndAllRole.do")
   public ModelAndView findUserByIdAndAllRole(@RequestParam(name = "id",required = true)String id) throws Exception{
      ModelAndView mv = new ModelAndView();
      int tid = Integer.parseInt(id);

      //1.根据id查询用户
      UserInfo user = userService.findByid(tid);
      mv.addObject("user",user);

      //2.查询用户可以添加的角色
      List<Role> role = userService.findUserByIdAndAllRole(id);
      mv.addObject("roleList",role);

      mv.setViewName("user-role-add");
      return mv;
   }


   //根据指定id 查询用户详细信息
   @RequestMapping("/findById.do")
   public ModelAndView findByid(String id) throws Exception{

      ModelAndView mv = new ModelAndView();
      int tid = Integer.parseInt(id);

      UserInfo byid = userService.findByid(tid);
      mv.addObject("user",byid);
      mv.setViewName("user-show");

      return mv;
   }


   //用户查询
   @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception{
       ModelAndView mv = new ModelAndView();
       List<UserInfo> list = userService.findAll();
       //添加到页面的值
       mv.addObject("userList",list);
      //跳转页面
       mv.setViewName("user-list");
       return mv;
   }

   //用户添加
   @RequestMapping("/save.do")
   public String save(UserInfo userInfo) throws Exception{

      userService.save(userInfo);
      return "redirect:findAll.do";
   }

}
