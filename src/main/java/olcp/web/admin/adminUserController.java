package olcp.web.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import olcp.entity.user;
import olcp.service.userService;

import java.util.List;

@RestController
@RequestMapping("/admin/user")
public class adminUserController {
    @Autowired
    private userService userservice;

    /**
     * 获取所有用户列表
     *
     * @param page
     * @return
     */
    @RequestMapping("/list")
    public List<user> findAllUser(@RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "pageSize", defaultValue = "6") int pageSize) {
        Pageable pageable = new PageRequest(page, pageSize, null);
        List<user> users = userservice.findAll(pageable).getContent();
        return users;
    }

    @RequestMapping("/getTotal")
    public Integer geTotal() {
        Pageable pageable = new PageRequest(0, 6, null);
        int total = (int) userservice.findAll(pageable).getTotalElements();
        return total;
    }

    @RequestMapping("/delete")
    public Boolean delete(int userid) {
        userservice.delByUserid(userid);
        return true;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/update")
    public Boolean update(String userPhone,String userEmail,String userNickName,
                          String userJob,
                          String userCity,
                          String userSex,
                          String userSignature) {
        // 更新前先查询
        List<user> user = userservice.findByusernickname(userNickName);
        for(int i=0;i<user.size();i++) {
            user.get(i).setUserphone(userPhone);
            user.get(i).setUseremail(userEmail);
            user.get(i).setUsernickname(userNickName);
            user.get(i).setUserjob(userJob);
            user.get(i).setUsercity(userCity);
            user.get(i).setUsersex(userSex);
            user.get(i).setUsersignature(userSignature);
            userservice.update(user);
        }
        return true;
    }
}