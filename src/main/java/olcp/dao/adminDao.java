package olcp.dao;

import olcp.entity.admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface adminDao extends JpaRepository<admin, Integer>{
    admin findByNameAndPassword(@Param("name") String name, @Param
            ("password") String password);
    admin findByid(Integer id);
}
