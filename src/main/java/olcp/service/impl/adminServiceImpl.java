package olcp.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import olcp.dao.adminDao;
import olcp.entity.admin;
import olcp.service.adminService;
import olcp.service.exception.LoginException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class adminServiceImpl implements adminService{
    @Autowired
    private adminDao adminDao1;

    @Override
    public admin findByid(int id) {
        return adminDao1.getOne(id);
    }

    @Override
    public Page<admin> findAll(Pageable pageable) {
        return adminDao1.findAll(pageable);
    }

    @Override
    public List<admin> findAllExample(Example<admin> example) {
        return adminDao1.findAll(example);
    }

    @Override
    public void update(admin adminUser) {
        adminDao1.save(adminUser);
    }

    @Override
    public int create(admin adminUser) {
        admin adminUser1 = adminDao1.save(adminUser);
        return adminUser1.getId();
    }

    @Override
    public void delById(int id) {
        adminDao1.delete(id);
    }

    @Override
    public admin checkLogin(HttpServletRequest request, String name, String password) {
        admin adminUser =adminDao1.findByNameAndPassword(name, password);
        if (adminUser != null) {
            request.getSession().setAttribute("login_user", adminUser);
        } else {
            throw new LoginException("用户名或密码错误");
        }
        return adminUser;
    }
}
