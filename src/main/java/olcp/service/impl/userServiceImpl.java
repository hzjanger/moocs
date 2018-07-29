package olcp.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import olcp.dao.userDao;
import olcp.entity.user;
import olcp.service.userService;

import java.util.List;

@Service
public class userServiceImpl implements userService{
    @Autowired
    private userDao userDao;

    @Override
    public user findByuserid(int userid) {
        return userDao.getOne(userid);
    }

    @Override
    public Page<user> findAll(Pageable pageable) {
        return userDao.findAll(pageable);
    }

    @Override
    public List<user> findAllExample(Example<user> example) {
        return userDao.findAll(example);
    }

    @Override
    public void update(List<user> user) {
        userDao.save(user);
    }

    @Override
    public void save(user user) {
        userDao.save(user);
    }

    @Override
    public int create(user user) {
        return userDao.save(user).getUserid();
    }

    @Override
    public void delByUserid(int userid) {
        userDao.delete(userid);
    }

    /**
     * 根据用户名查询
     *
     * @param userNickName
     * @return
     */
    @Override
    public List<user> findByusernickname(String userNickName) {
        return userDao.findByusernickname(userNickName);
    }

    /**
     * 检查登录
     *
     * @param userNickName
     * @param userPassword
     * @return
     */
    @Override
    public user checkLogin(String userNickName, String userPassword) {
        return userDao.findByUsernicknameAndUserpassword(userNickName, userPassword);
    }
    @Override
    public List<user> findByUseremailOrUserphone(String userEmail,String userPhone)
    {
        return userDao.findByUseremailOrUserphone(userEmail,userPhone);
    }
    @Override
    public List<user> findByuserpassword(String userpassword)
    {
        return userDao.findByuserpassword(userpassword);
    }
}
