package cn.qyd.blogroom.website.controller;

import cn.qyd.blogroom.website.ueditor.ActionEnter;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @Author qyd
 * @Date 19-3-22 上午10:45
 **/
@RestController
@RequestMapping("/ueditor")
public class UeditorController {

    @RequestMapping("/exec")
    @ApiOperation("test")
    public void getUEditorConfig( HttpServletRequest request,HttpServletResponse response){
//        response.setContentType("application/json");
//        String rootPath = "./blog-web-new/blogroom/static/ueditorUpload/";
        String rootPath = "/home/qiuyunduo/nginxRepository/ueditorUpload/";
        try {
            String exec = new ActionEnter(request, rootPath).exec();
            PrintWriter writer = response.getWriter();
            writer.write(exec);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        File file = new File("./20190322/1553239844504030200.jpg");
        System.out.println((new File("./").getAbsolutePath()));
        String absoluteFile = file.getAbsolutePath();
        System.out.println(absoluteFile);
        File parentPath = file.getParentFile();
        boolean mkdirs = parentPath.mkdirs();
        System.out.println(mkdirs);
    }
}