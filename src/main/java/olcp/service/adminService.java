package olcp.service;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpRequest;
import olcp.entity.admin;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
public interface adminService {
    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    admin findByid(int id);

    /**
     * 分页查询所有
     *
     * @param pageable
     * @return
     */
    Page<admin> findAll(Pageable pageable);

    /**
     * 按条件查询
     *
     * @param example
     * @return
     */
    List<admin> findAllExample(Example<admin> example);

    /**
     * 更新
     *
     * @param admin1
     * @return
     */
    void update(admin admin1);

    /**
     * 创建
     *
     * @param admin1
     * @return
     */
    int create(admin admin1);

    /**
     * 根据id删除
     *
     * @param id
     * @return
     */
    void delById(int id);

    /**
     * 检查登录
     * @param request
     * @param name
     * @param password
     * @return
     */
    admin checkLogin(HttpServletRequest request,String name, String password);
}
