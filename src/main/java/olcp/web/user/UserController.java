package olcp.web.user;

import olcp.service.RedisService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import olcp.entity.*;
import olcp.service.userService;
import olcp.service.exception.LoginException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
@RequestMapping("/user")
public class UserController {
    @Autowired
    private RedisService redisService;
    @Autowired
    private userService userService;
    /**
     * 登录
     *
     * @param userNickName
     * @param userPassword
     */
    @RequestMapping(method = RequestMethod.POST,value = "/login")
    public Boolean login(String userNickName,
                      String userPassword,
                      HttpServletRequest request,
                      HttpServletResponse response) throws IOException {
        user user = userService.checkLogin(userNickName, userPassword);
        if (user != null) {
            //登录成功
            request.getSession().setAttribute("user", user);
            return true;
        } else {
            return false;
        }
    }

    /**
     * 注册
     */
    @RequestMapping("/register")
    public void register(String userPhone,String userEmail,String userNickName,
                         String userJob,
                         String userCity,
                         String userSex,
                         String userSignature,
                         HttpServletResponse response) throws IOException {
        List<user> user = userService.findByusernickname(userNickName);
        for(int i=0;i<user.size();i++) {
            user.get(i).setUserphone(userPhone);
            user.get(i).setUseremail(userEmail);
            user.get(i).setUsernickname(userNickName);
            user.get(i).setUserjob(userJob);
            user.get(i).setUsercity(userCity);
            user.get(i).setUsersex(userSex);
            user.get(i).setUsersignature(userSignature);
            userService.update(user);
            redisService.set(user.get(i).getUserid()+"", user.get(i));
        }
    }

    /**
     * 登出
     */
    @RequestMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().removeAttribute("user");
    }

    /**
     * 验证用户名是否唯一
     * @param userNickName
     * @return
     */
    @RequestMapping("/checkuserNickName")
    public Boolean checkuserNickName(String userNickName){
        List<user> users = userService.findByusernickname(userNickName);
        if (users==null||users.isEmpty()){
            return true;
        }
        return false;
    }

    /**
     * 验证密码是否唯一
     * @param userPassword
     * @return
     */
    @RequestMapping("/checkuserPassword")
    public Boolean checkuserPassword(String userPassword){
        List<user> users = userService.findByuserpassword(userPassword);
        if (users==null||users.isEmpty()){
            return true;
        }
        return false;
    }

    /**
     * 验证手机号或者邮箱是否唯一
     * @param userEmail
     * @param userPhone
     * @return
     */
    @RequestMapping("/checkuserEmailOruserPhone")
    public Boolean checkuserEmailOruserPhone(String userEmail,String userPhone){
        List<user> users = userService.findByUseremailOrUserphone(userEmail,userPhone);
        if (users==null||users.isEmpty()){
            return true;
        }
        return false;
    }

    /**
     * 如发生错误 转到这页面
     *
     * @param response
     * @param request
     * @return
     */
    @RequestMapping("/error")
    public String error(HttpServletResponse response, HttpServletRequest request) {
        return "error";
    }
}