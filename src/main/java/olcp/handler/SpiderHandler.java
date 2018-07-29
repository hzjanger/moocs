package olcp.handler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.google.gson.Gson;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.FastDateFormat;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import olcp.common.HttpClientUtils;
import olcp.constant.SysConstant;
/**
 * 爬虫调度处理器
 */
@Component
public class SpiderHandler {
    private static final Logger logger = LoggerFactory.getLogger(SpiderHandler.class);
    private static String HTTPS_PROTOCOL = "https:";
    public void spiderData() {
        logger.info("爬虫开始....");
        Date startDate = new Date();
        // 使用现线程池提交任务
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        //引入countDownLatch进行线程同步，使主线程等待线程池的所有任务结束，便于计时
        CountDownLatch countDownLatch = new CountDownLatch(100);
        for(int i=1;i<=30;i++) {
            String url = "https://www.imooc.com/course/list?sort=pop?page="+i;
            String html = HttpClientUtils.sendGet1(url, null);
            if (!StringUtils.isBlank(html)) {
                Document document = Jsoup.parse(html);
                Elements elements = document.select("div[class=clearfix]").select("div[class=course-card-container]");
                List<String> cid = new ArrayList<>();
                for (Element element : elements) {
                    String id = element.select("a").attr("href");
                    String temp[] = id.replaceAll("\\\\", "/").split("/");
                    cid.add(temp[temp.length - 1]);
                }
                for (int j = 0; j < cid.size(); j++) {
                    String hr = cid.get(j);
                    executorService.submit(() -> {
                        grib(hr);
                        countDownLatch.countDown();
                    });
                }
            }
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
        Date endDate = new Date();
        FastDateFormat fdf = FastDateFormat.getInstance(SysConstant.DEFAULT_DATE_FORMAT);
        logger.info("爬虫结束....");
        logger.info("[开始时间:" + fdf.format(startDate) + ",结束时间:" + fdf.format(endDate) + ",耗时:"
                + (endDate.getTime() - startDate.getTime()) + "ms]");

    }

    //存取数据
    public static void store(String coursesummary,String coursetype , String coursetime,String coursedifficulty,String courseoverallrating,String coursestudypeople,String coursename,String coursetailnum,String teacherpicturepath,String teachername,String teacherjob,String qacommentnum,String user_evaluationnum,String coursenotes,String learncontent,String label,String chapter) {

        String driverClassName = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/online_learning_platform?useUnicode=true&characterEncoding=utf-8&useSSL=true&zeroDateTimeBehavior=convertToNull";
        String username = "root";
        String password = "19981028";
        Connection conn = null;
        // 加载驱动类
        try {
            Class.forName(driverClassName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            conn = DriverManager.getConnection(url, username, password);
            // 创建sql语句模板
            String sql = "INSERT into coursehot(coursesummary, coursetype , coursetime,coursedifficulty,courseoverallrating,coursestudypeople,coursename,coursetailnum,teacherpicturepath,teachername,teacherjob,qacommentnum,user_evaluationnum,coursenotes,learncontent,label,chapter) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            // 创建一个声明对象
            PreparedStatement pst = conn.prepareStatement(sql);
            // 用循环将数据添加到sql模板中
            pst.setString(1, coursesummary);
            pst.setString(2, coursetype);
            pst.setString(3, coursetime);
            pst.setString(4, coursedifficulty);
            pst.setString(5, courseoverallrating);
            pst.setString(6, coursestudypeople);
            pst.setString(7, coursename);
            pst.setString(8, coursetailnum);
            pst.setString(9, teacherpicturepath);
            pst.setString(10, teachername);
            pst.setString(11, teacherjob);
            pst.setString(12, qacommentnum);
            pst.setString(13, user_evaluationnum);
            pst.setString(14, coursenotes);
            pst.setString(15, learncontent);
            pst.setString(16, label);
            pst.setString(17, chapter);
            pst.addBatch();
            // 将sql语句发送到mysql上
            int[] res = pst.executeBatch();
            System.out.println(res);
            pst.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void grib(String id)
    {
        List<String> a=new ArrayList<>();
        List<String> b=new ArrayList<>();
        List<String> c=new ArrayList<>();
        List<String> d=new ArrayList<>();
        List<String> e=new ArrayList<>();
        List<String> f=new ArrayList<>();
        List<String> g=new ArrayList<>();
        List<String> h=new ArrayList<>();
        List<String> q=new ArrayList<>();
        StringBuilder chapter=new StringBuilder();
        List<String> direction=new ArrayList<>();
        List<String> direction1=new ArrayList<>();
        LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
        List<String> map1=new ArrayList<>();
        String curl="https://www.imooc.com/learn/"+id;
        String burl="https://www.imooc.com/course/AjaxCourseMembers?ids="+id;
        String durl="https://www.imooc.com/course/ajaxskillcourse?cid="+id;
        String html = HttpClientUtils.sendGet1(curl,null);
        String html1 = HttpClientUtils.sendGet1(burl,null);
        String html2 = HttpClientUtils.sendGet1(durl,null);
        Document document = Jsoup.parse(html);
        String courseSummary=document.select("div[class=course-description course-wrap]").text();
        String coursetype[]=document.select("div[class=path]").select("a").text().split(" ");
        direction = document.select("div[class=path]").select("a").eachAttr("href");
        for(int i=1;i<coursetype.length-1;i++)
        {
            q.add(coursetype[i]);
        }
        for(int i=1;i<direction.size()-1;i++)
        {
            String temp=direction.get(i);
            direction1.add(temp.substring(temp.indexOf("=")+1,temp.length()));
        }
        for(int i=0;i<direction1.size();i++)
        {
            map.put("name",q.get(i));
            map.put("link",direction1.get(i));
            String jsoncoursetype = new Gson().toJson(map);
            map1.add(jsoncoursetype);
        }
        map.clear();
        String temp[]=document.select("div[class=static-item l]").select("span[class=meta-value]").text().split(" ");
        String courseTime=temp[1];
        String courseDifficulty=temp[0];
        String courseOverallRating=document.select("div[class=static-item l score-btn]").select("span[class=meta-value]").text().split(" ")[0];
        String courseStudyPeople=null;
        String courseName=document.select("div[class=hd clearfix]").select("h2[class=l]").text();
        String regexStr = "\"numbers\":\"([0-9]+)\"";
        //获取Pattern对象
        Pattern pattern = Pattern.compile(regexStr);
        // 定义一个matcher用来做匹配
        Matcher matcher = pattern.matcher(html1);
        if (matcher.find()) {
            courseStudyPeople=matcher.group();
        }
        String teacherpicturepath=HTTPS_PROTOCOL+document.select("div[class=teacher-info l]").select("a").select("img").attr("src");
        String teachername=document.select("div[class=teacher-info l]").select("span[class=tit]").select("a").text();
        String teacherjob=document.select("div[class=teacher-info l]").select("span[class=job]").text();
        String qacommentnum=document.select("ul[class=course-menu]").select("li").select("span").text().split(" ")[0];
        String user_evaluationnum=document.select("ul[class=course-menu]").select("li").select("span").text().split(" ")[1];
        String str=document.select("div[class=course-info-tip]").select("dl").text();
        String coursenotes=JSON.toJSONString(splitData1(str,"须","老").trim().split(" "));
        String learncontent=JSON.toJSONString(splitData(str,"么",str.substring(str.length()-1)).trim().split(" "));
        // String label=JSON.toJSONString(document.select("div[class=js-all-attention all-attention]").select("a").text().split(" "));
        String label=null;
        String course_chapter_name[]=document.select("div[class=course-chapters]").select("h3").text().split(" ");
        String course_chapter_introduction[]=document.select("div[class=course-chapters]").select("div[class=chapter-description]").text().split(" ");
        String course_directory[]=document.select("ul[class=video]").select("li").select("a").text().replaceAll("开始学习","").split("\\)");
        for(int i=0;i<course_chapter_name.length;i++)
            e.add(course_chapter_name[i].trim());
        for(int i=0;i<e.size()-1;i+=2)
        {
            g.add(e.get(i)+" "+e.get(i+1));
        }
        for(int i=0;i<course_chapter_introduction.length;i++)
            f.add(course_chapter_introduction[i].trim());
        for(int i=0;i<course_directory.length;i++)
        {
            course_directory[i]=course_directory[i]+")";
            course_directory[i]=course_directory[i].replaceAll("\\s*", "");
            d.add(course_directory[i]);
        }
        d.remove(d.size()-1);
        for(int i=0;i<g.size();i++) {
            if (i == 0)
                chapter.append("[{" + "course_chapter_name:" + "\"" + g.get(i) + "\"" + ",");
            else chapter.append("{" + "course_chapter_name:" + "\"" + g.get(i) + "\"" + ",");
            chapter.append("course_chapter_introduction:" + "\"" + f.get(i) + "\"" + ",");
            chapter.append("directory:[");
            if (i != g.size() - 1) {
                for (int j = 0; j < d.size(); j++) {
                    if (d.get(j).substring(0, 1).equals(String.valueOf(i + 1))) {
                        if (j == 0) {
                            chapter.append("{" + "course_directory:" + "\"" + d.get(j) + "\"" + "," + "course_status:\"radio-button\"" + "," + "link:" + "\"" + id + "-" + d.get(j).substring(0, 3) + "\"" + "}");
                        } else if (j != d.size() - 1) {
                            chapter.append(",{" + "course_directory:" + "\"" + d.get(j) + "\"" + "," + "course_status:\"radio-button\"" + "," + "link:" + "\"" + id + "-" + d.get(j).substring(0, 3) + "\"" + "}");
                        } else {
                            chapter.append(",{" + "course_directory:" + "\"" + d.get(j) + "\"" + "," + "course_status:\"radio-button\"" + "," + "link:" + "\"" + id + "-" + d.get(j).substring(0, 3) + "\"" + "}]},");
                        }
                    }
                }
            }
            else {
                for (int j = 0; j < d.size(); j++) {
                    if (d.get(j).substring(0, 1).equals(String.valueOf(i + 1))) {
                        if (j == 0) {
                            chapter.append("{" + "course_directory:" + "\"" + d.get(j) + "\"" + "," + "course_status:\"radio-button\"" + "," + "link:" + "\"" + id + "-" + d.get(j).substring(0, 3) + "\"" + "}");
                        } else if (j != d.size() - 1) {
                            chapter.append(",{" + "course_directory:" + "\"" + d.get(j) + "\"" + "," + "course_status:\"radio-button\"" + "," + "link:" + "\"" + id + "-" + d.get(j).substring(0, 3) + "\"" + "}");
                        } else {
                            chapter.append(",{" + "course_directory:" + "\"" + d.get(j) + "\"" + "," + "course_status:\"radio-button\"" + "," + "link:" + "\"" + id + "-" + d.get(j).substring(0, 3) + "\"" + "}]}]");
                        }
                    }
                }
            }
        }
        store(courseSummary,JSON.toJSONString(map1) , courseTime,courseDifficulty,courseOverallRating,courseStudyPeople.split(":")[1].replace("\"", ""),courseName,id,teacherpicturepath,teachername,teacherjob,qacommentnum,user_evaluationnum,coursenotes,learncontent,label,chapter.toString());
    }

    public String splitData(String str, String strStart, String strEnd) {
        String tempStr;
        tempStr = str.substring(str.indexOf(strStart) + 2, str.lastIndexOf(strEnd)+1);
        return tempStr;
    }
    public String splitData1(String str, String strStart, String strEnd) {
        String tempStr;
        tempStr = str.substring(str.indexOf(strStart) + 2, str.lastIndexOf(strEnd));
        return tempStr;
    }
//    public String unicodeToString(String str) {
//        Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");
//        Matcher matcher = pattern.matcher(str);
//        char ch;
//        while (matcher.find()) {
//            ch = (char) Integer.parseInt(matcher.group(2), 16);
//            str = str.replace(matcher.group(1), ch+"" );
//        }
//        return str;
//    }
}