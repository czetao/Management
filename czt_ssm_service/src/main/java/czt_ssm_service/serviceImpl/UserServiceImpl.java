package czt_ssm_service.serviceImpl;

import czt_ssm_dao.IUserDao;
import czt_ssm_domain.Role;
import czt_ssm_domain.UserInfo;
import czt_ssm_service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserDao userDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    //用户登录 获取用户的登录角色，是否可以登录，
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserInfo userInfo = null;
        try {
            userInfo = userDao.findByUsername(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //处理自己的用户对象，封装成UserDetails
        //User user = new User(userInfo.getUsername(),"{noop}"+userInfo.getPassword(),getAuthority(userInfo.getRoles()));
       //用户状态 控制用户登录
        User user = new User(userInfo.getUsername(),userInfo.getPassword(),userInfo.getStatus()==0?false : true,true,true,true,getAuthority(userInfo.getRoles()));
        return user;
    }
        //获取用户的角色信息
    public List<SimpleGrantedAuthority> getAuthority(List<Role> roles){
        List<SimpleGrantedAuthority> list = new ArrayList<>();
        for(Role role : roles){
            list.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
        }

        return list;

    }

    @Override
    public List<UserInfo> findAll() throws Exception {

        List<UserInfo> l = userDao.findAll();

        return l;
    }

    @Override
    public void save(UserInfo userInfo) throws Exception {

        //在这个地方将密码加密 后再 调用dao
       userInfo.setPassword(bCryptPasswordEncoder.encode(userInfo.getPassword()));
        userDao.save(userInfo);
    }

    @Override
    public UserInfo findByid(int id) throws Exception {

        UserInfo userInfo = userDao.findByid(id);

        return userInfo;
    }

    @Override
    public List<Role> findUserByIdAndAllRole(String id) throws Exception {
        return userDao.findUserByIdAndAllRole(id);
    }

    @Override
    public void addRoletoUser(String uid, String[] rids) {
        for (String rid :rids
             ) {
            userDao.addRoletuUser(uid,rid);
        }
    }
}
