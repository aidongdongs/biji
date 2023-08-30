package com.regin.controller;

import com.regin.common.R;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.UUID;

/**
 * 文件的上传和下载
 */
@RestController
@RequestMapping("/common")
public class CommonController {
    //reggie:
    //  image:
    //    path:
    //存储图片的具体路劲
    @Value("${reggie.image.path}")
    private String imagePath;

    /**
     * 文件的上传
     * @param file 上传过来的文件流
     * @return r结果集
     */
    @PostMapping("/upload")
    public R<String> upload(MultipartFile file) throws IOException {
//        file.transferTo(new File(imagePath+"hello2.jpg"));
        //获取原始文件名，拿到后缀名，生成随机名字进行拼接
        String filename = file.getOriginalFilename();
        //将原始名字的后缀名取出
        filename = filename.substring(filename.lastIndexOf("."));
        //使用uuid和原始文件后缀名，进行生成新的文件名
        String newFileName = UUID.randomUUID().toString()+filename;
        File newFile = new File(imagePath,newFileName);
        if (newFile.exists()){
            newFile.mkdirs();
        }
        file.transferTo(newFile);
        return R.success(newFileName);

    }

    /**\
     * 下载文件操作
     * @param name 需要加载的文件名称
     * @param response 将文件数据写出
     */
    @GetMapping("/download")
    public void download(String name, HttpServletResponse response){
        FileInputStream fileInputStream  =null;
        ServletOutputStream outputStream=null;
        try {
             fileInputStream  = new FileInputStream(new File(imagePath+name));
             outputStream = response.getOutputStream();
             response.setContentType("image/jpeg");
            byte []  bytes = new byte[1024];
            int lens = 0;
            while ((lens =fileInputStream.read(bytes))!=-1){
                outputStream.write(bytes,0,lens);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }



    }
}
