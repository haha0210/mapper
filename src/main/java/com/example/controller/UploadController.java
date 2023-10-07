package com.example.controller;

import com.example.pojo.Result;
import com.example.utils.AliOSSUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@Slf4j
public class UploadController {
    @Autowired
    private AliOSSUtils aliOSSUtils;
//    本地存储文件
//    @PostMapping("/upload")
//    public Result upload(String username, Integer age, MultipartFile image) throws IOException {
//        log.info("文件上传：{},{},{}",username,age,image);
//
//        //1-------------存储在本地文件夹
//        //获取原始文件名
//        String originalFilename = image.getOriginalFilename();
//        //构建新的文件名
//        //一个随机的UUID字符串拼接上后缀名  ，subString为截取函数，lastIndextOf 找到最后一个.
//        String newFileName = UUID.randomUUID().toString() +
//                originalFilename.substring(originalFilename.lastIndexOf("."));
//        image.transferTo(new File("D:/images/"+newFileName));
//
//        return Result.success();
//    }

    //云端存储文件
    @PostMapping("/upload")
    public Result upload(MultipartFile image) throws IOException {
        log.info("上传文件的文件名：{}",image.getOriginalFilename());
        String url = aliOSSUtils.upload(image);
        log.info("文件的url: {}",url);
        return Result.success(url);
    }
}
