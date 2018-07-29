//package olcp.service;
//
//import olcp.dao.adminDao;
//import olcp.entity.admin;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//
//
//public class CustomUserService implements UserDetailsService {
//    @Autowired
//    adminDao adminDao;
//    @Override
//    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
//        admin adminuser = adminDao.findByName(name);
//        if (adminuser == null) {
//            throw new UsernameNotFoundException("用户名不存在");
//        }
//        System.out.println("username:"+adminuser.getUsername()+";password:"+adminuser.getPassword());
//        return adminuser;
//    }
//}
