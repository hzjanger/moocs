package olcp.web.user;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import olcp.entity.*;
import olcp.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Component
@RequestMapping("/course")
public class Course1Controller {

    @Autowired
    @Reference
    private RedisService redisService;
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

    //从redis缓存获取某门课程信息
    @RequestMapping(value = "/getcoursefromredis", method = RequestMethod.GET)
    public coursenew getRedis(@RequestParam String key) {
        return (coursenew)redisService.get(key);
    }

    /**
     *　最新课程
     */
    @RequestMapping("/allnewcourses")
    public String getNewcourses(@RequestParam(defaultValue = "0",required = true) Integer page) {
        Pageable pageable = new PageRequest(page, 24);
        Page<coursenew1> coursenew = courseService1.findAll(pageable);
        return splitData(JSON.toJSONString(coursenew),"[","]");
    }
    /**
     *　最新课程详细页面
     */
    @RequestMapping("/allnewcourses/detailed_page")
    public coursenew getNewdetailedcourses(String coursetailnum) {
        coursenew coursenewdetailed = courseService.findBycoursetailnum(coursetailnum);
        return coursenewdetailed;
    }

    /**
     *　课程详细页面
     * 点击课程,进入学习页面
     */
    @RequestMapping("/detailed_page")
    public coursehot getdetailedcourses(String coursetailnum) {
        coursehot coursehotdetailed = courseService3.findBycoursetailnum(coursetailnum);
        redisService.set(coursetailnum+"", coursehotdetailed);
        return coursehotdetailed;
    }

    /**
     *　最热课程
     */
    @RequestMapping("/allhotcourses")
    public String getHotcourses(@RequestParam(defaultValue = "0",required = true) Integer page) {
        Pageable pageable = new PageRequest(page, 24);
        Page<coursehot1> coursehot = courseService2.findAll(pageable);
        redisService.set(page+"", coursehot);
        return splitData(JSON.toJSONString(coursehot),"[","]");
    }
    /**
     *　最热课程详细页面
     */
    @RequestMapping("/allhotcourses/detailed_page")
    public coursehot getHotdetailedcourses(String coursetailnum) {
        coursehot coursehotdetailed = courseService3.findBycoursetailnum(coursetailnum);
        return coursehotdetailed;
    }
    /**
     *　最新课程分类1
     */
    @RequestMapping("/allnewcourses/sortbylast1")
    public Page<courseclassification> getNewClassificationCourses1(String direction,String difficulty,@RequestParam(defaultValue = "0",required = true) Integer page) {
        Pageable pageable = new PageRequest(page, 24);
        Page<courseclassification> type = courseclassificationservice.findBydirectionAnddifficulty(direction,difficulty,pageable);
        return type;
    }

    /**
     *　最新课程分类2
     */
    @RequestMapping("/allnewcourses/sortbylast")
    public Page<courseclassification> getNewClassificationCourses2(String direction,@RequestParam(defaultValue = "0",required = true) Integer page) {
        Pageable pageable = new PageRequest(page, 24);
        Page<courseclassification> type = courseclassificationservice.findBydirection(direction,pageable);
        return type;
    }

    /**
     *　最热课程分类1
     */
    @RequestMapping("/allhotcourses/sortbypop1")
    public Page<courseclassification> getHotClassificationCourses1(String direction,String difficulty,@RequestParam(defaultValue = "0",required = true) Integer page) {
        Sort sort = new Sort(Sort.Direction.DESC, "people");
        Pageable pageable = new PageRequest(page, 24,sort);
        Page<courseclassification> type = courseclassificationservice.findBydirectionAnddifficulty(direction,difficulty,pageable);
        return type;
    }

    /**
     *　最热课程分类2
     */
    @RequestMapping("/allhotcourses/sortbypop")
    public Page<courseclassification> getHotClassificationCourses2(String direction,@RequestParam(defaultValue = "0",required = true) Integer page) {
        Sort sort = new Sort(Sort.Direction.DESC, "people");
        Pageable pageable = new PageRequest(page, 24,sort);
        Page<courseclassification> type = courseclassificationservice.findBydirection(direction,pageable);
        return type;
    }

    public String splitData(String str, String strStart, String strEnd) {
        String tempStr;
        tempStr = str.substring(str.indexOf(strStart)+1 , str.lastIndexOf(strEnd));
        return tempStr;
    }
}