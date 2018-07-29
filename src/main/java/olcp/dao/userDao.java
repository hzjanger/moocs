package olcp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import olcp.entity.user;

import java.util.List;
public interface userDao extends JpaRepository<user, Integer> {
    /**
     * 根据id查询用户
     * @param userid
     * @return
     */
    List<user> findByuserid(String userid);
    /**
     * 根据用户名，密码查询用户
     * @param usernickname
     * @param userpassword
     * @return
     */
    user findByUsernicknameAndUserpassword(String usernickname, String userpassword);

    /**
     * 根据用户名查询用户
     * @param usernickname
     * @return
     */
    List<user> findByusernickname(String usernickname);
    /**
     * 根据用户邮箱或者手机号查询用户
     * @param useremail
     * @return userphone
     */
    List<user> findByUseremailOrUserphone(String useremail,String userphone);
    /**
     * 根据密码查询用户
     * @param userpassword
     * @return
     */
    List<user> findByuserpassword(String userpassword);
}
