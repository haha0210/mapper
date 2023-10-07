package com.example.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSBuilder;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Component  //交给AIOC容器管理
public class AliOSSUtils {
    // 变量分别为 AccessKey 连接的地址，id，密码
    //初始值在application配置文件中，使用Value注释赋值
//    private String endpoint = "https://oss-cn-hangzhou.aliyuncs.com";    //服务器连接点的地址
//    private String accessKeyId = "LTAI5tRER5TDuPUSwvzMvcNs";   //AccessKey id
//    private String accessKeySecret = "Z3SLvAV3s5jZwyc1X6wg5ia7khV8EQ"; //AccessKey 密码
//    private String bucketName = "hahagg12";

    @Value("${aliyun.oss.endpoint}")
    private String endpoint ;    //服务器连接点的地址
    @Value("${aliyun.oss.accessKeyId}")
    private String accessKeyId ;   //AccessKey id
    @Value("${aliyun.oss.accessKeySecret}")
    private String accessKeySecret ; //AccessKey 密码
    @Value("${aliyun.oss.bucketName}")
    private String bucketName ;

    public String upload(MultipartFile file) throws IOException{
        //获取文件上传的输入流
        InputStream inputStream = file.getInputStream();

        //UUID随机生成文件名，避免上传的文件名相同
        //随机生成的UUID拼接上原始文件的后缀名
        String orginalFilename = file.getOriginalFilename();
        String fileName = UUID.randomUUID().toString()
                + orginalFilename.substring(orginalFilename.lastIndexOf("."));

        //文件上传到OSS
        OSS ossClient = new OSSClientBuilder().build(endpoint,accessKeyId,accessKeySecret);
        ossClient.putObject(bucketName,fileName,inputStream);

        //文件访问路径
        //例如 ： https://hahahagg12.oss-cn-hangzhou.aliyuncs.com/ filename(UUID随机生成的文件名)
        String url = endpoint.split("//")[0] + "//" + bucketName + "."
                   + endpoint.split("//")[1] + "/" + fileName;

        // 关闭ossClient
        ossClient.shutdown();

        return url;// 把上传到oss的路径返回
    }
}
