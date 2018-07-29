package olcp.web.admin;
import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import olcp.entity.*;
import olcp.entity.pojo.ResultBean;
import olcp.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Component
@RequestMapping("/admin/course")
public class adminCourseController {
    @Autowired
    @Reference
    private coursenew1Service courseService1;
    @Autowired
    @Reference
    private coursenewService courseService;
    @Autowired
    @Reference
    private coursehot1Service courseService2;
    @Autowired
    @Reference
    private coursehotService courseService3;
    @Autowired
    @Reference
    private courseclassificationService courseclassificationservice;
    //增加课程信息

    //删除课程信息
    @RequestMapping("/delete")
    public Boolean delete(int courseid) {
        courseService1.delBycourseid1(courseid);
        courseService2.delBycourseid2(courseid);
        return true;
    }
    //批量删除课程信息
    @RequestMapping("/batch_deletion")
    public Boolean delete(int[] courseids) {
        for(int id:courseids)
        {
            courseService1.delBycourseid1(id);
            courseService2.delBycourseid2(id);
        }
        return true;
    }
    //查找课程信息

    //修改课程信息
}