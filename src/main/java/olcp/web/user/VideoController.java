package olcp.web.user;
import com.alibaba.fastjson.JSON;
import olcp.entity.*;
import olcp.service.*;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

@RestController
@RequestMapping(value="/video" ,method = RequestMethod.GET)
//本地视频库　/home/zq/Desktop/LocalVideoLibrary/videoname.mp4
public class VideoController {
    @RequestMapping("/play")
    public void getVideo(HttpServletResponse response, @RequestParam(value = "path") String path) throws IOException {
//        BufferedInputStream input = new BufferedInputStream(new FileInputStream(new File(path)));
        try {
            File file = new File(path);
            FileInputStream input = new FileInputStream(file);
            int i = input.available();
            byte[] bytes;
            OutputStream output = null;
            if (i != 0) {
                bytes = new byte[i];
                input.read(bytes);
                response.setContentType("application/video");
                output = response.getOutputStream();
                output.write(bytes);
            }
            output.flush();
            output.close();
            input.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
//下载视频到本地
//    @RequestMapping("/download")
//    public ResponseEntity<byte[]> download(HttpServletResponse response, @RequestParam(value="path") String path) throws IOException {
//        File file = new File(path);
//        //封装http头部信息
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
//        headers.setContentDispositionFormData("attachment",path);
//        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),
//                headers, HttpStatus.CREATED);
//    }