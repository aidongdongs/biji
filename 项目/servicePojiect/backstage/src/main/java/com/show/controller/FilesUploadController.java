package com.show.controller;

import com.show.ex.FileEmptyException;
import com.show.ex.FileSizeException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/files")
public class FilesUploadController {

    @Value("${upload.img.url}")
    private String url;

    /**
     * 上传文件接口
     * @param multipartFiles
     * @return
     * @throws IOException
     */
    @PostMapping("/upload")
    public ResultJSON filesUpload(@RequestParam("exec") MultipartFile multipartFiles) throws IOException {
        //判断文件是否为空
        if (multipartFiles.isEmpty()){
            throw new FileEmptyException("文件为空",ResultJSON.FIlE_UPLOAD_ERROR,null);
        }
        //文件大小是否正常
        if (multipartFiles.getSize()>1024*1024*10){
            throw new FileSizeException("文件大小异常",ResultJSON.FIlE_UPLOAD_ERROR,null);
        }
        //获取上传文件的文件格式
        String filename = multipartFiles.getOriginalFilename().substring(multipartFiles.getOriginalFilename().lastIndexOf("."),multipartFiles.getOriginalFilename().length());
        //生成一个新的文件名字
       filename = UUID.randomUUID().toString().toUpperCase()+filename;
        //使用新文件全名创建一个file对象 在创建好的目录下创建  名字+上床文件的后缀名
//        File dest = new File(dir,fileName);
        File file = new File(url);
        if (!file.exists()){
            file.mkdirs();
        }
        File dest = new File(file,filename);
        multipartFiles.transferTo(dest);
        return new ResultJSON("上传成功",111,filename);
    }

    /**
     * 文件回显
     * @param name
     * @param response
     */
        @GetMapping("/download")
    public void download(String name, HttpServletResponse response){
        FileInputStream fileInputStream  =null;
        ServletOutputStream outputStream=null;
        try {
            fileInputStream  = new FileInputStream(new File(url+name));
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
        }finally {
            try {
                if (fileInputStream!=null){
                    fileInputStream.close();
                }
                if (outputStream!=null){
                    outputStream.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
