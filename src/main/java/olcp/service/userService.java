package olcp.service;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import olcp.entity.user;

import java.util.List;
public interface userService {
    /**
     * 根据userId查询
     *
     * @param userid
     * @return
     */
    user findByuserid(int userid);
    /**
     * 分页查询所有
     *
     * @param pageable
     * @return
     */
    Page<user> findAll(Pageable pageable);
    /**
     * 按条件查询
     *
     * @param example
     * @return
     */
    List<user> findAllExample(Example<user> example);
    /**
     * 更新
     *
     * @param user1
     * @return
     */
    void update(List<user> user1);

    /**
     * 创建
     *
     * @param user1
     * @return
     */
    int create(user user1);
    void save(user user1);

    /**
     * 根据userid删除
     *
     * @param userid
     * @return
     */
    void delByUserid(int userid);

    /**
     * 根据用户名查询
     * @param userNickName
     * @return
     */
    List<user> findByusernickname(String userNickName);
    /**
     * 检查登录
     * @param usernickname
     * @param userpassword
     * @return
     */
    user checkLogin(String usernickname,String userpassword);
    List<user> findByUseremailOrUserphone(String userEmail,String userPhone);
    List<user> findByuserpassword(String userPassword);
}
