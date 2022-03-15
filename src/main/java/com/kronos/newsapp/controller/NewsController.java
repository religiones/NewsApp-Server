package com.kronos.newsapp.controller;

import org.json.JSONException;
import org.json.JSONObject;
import com.kronos.newsapp.model.News;
import com.kronos.newsapp.repository.NewsReponsitory;
import com.kronos.newsapp.tool.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.security.MessageDigest;
import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.List;


@RestController
public class NewsController {

    @Autowired
    private NewsReponsitory newsReponsitory;

    @RequestMapping("/api/getNews")
    public List<News> getNews(){
        List<News> newsList;
        newsList = newsReponsitory.findAll();
        return newsList;
    }

    @RequestMapping("/api/addNews")
    public Code addNews(@RequestParam(value = "news") String newsStr, @RequestParam(value = "newsPic") MultipartFile uploadPic) throws IOException, JSONException {
        JSONObject jsonObject = new JSONObject(newsStr);
        String title = jsonObject.getString("title")+new Date().toString();
        //将新闻标题和时间转为hash值存储为主键
        try {
            //MessageDigest 类为应用程序提供信息摘要算法的功能，如 MD5 或 SHA 算法。
            //信息摘要是安全的单向哈希函数，它接收 任意大小的数据，并输出固定长度的哈希值。
            //MessageDigest 对象开始被初始化。
            MessageDigest mDigest = MessageDigest.getInstance("MD5");
            //通过使用 update 方法处理数据
            mDigest.update(title.getBytes());
            //调用 digest 方法之一完成哈希计算同时将Byte数组转换成16进制
            String Id = bytesToHexString(mDigest.digest());

            //构建图片Url
            String c = System.getProperty("user.dir");
            File fileMkdir = new File(c+"\\","images");
            if(!fileMkdir.exists()){
                fileMkdir.mkdirs();
            }
            //图片转hash
            byte[] picByte = uploadPic.getBytes();
            mDigest.update(picByte);
            String picName = bytesToHexString(mDigest.digest());
            String picType = uploadPic.getContentType().split("/")[1];
            uploadPic.transferTo(new File(fileMkdir,picName+"."+picType));
            String picUrl = "http://localhost:8080/images/"+picName+"."+picType;    // 采用url加密处理中文字符

            //存入数据库
            News news = new News();
            news.setId(Id);
            news.setTitle(jsonObject.getString("title"));
            news.setCtime(jsonObject.getString("ctime"));
            news.setDescription(jsonObject.getString("description"));
            news.setSource(jsonObject.getString("source"));
            news.setPicUrl(picUrl);

            newsReponsitory.save(news);
            return Code.OK;

        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return Code.FAILED;
        }

    }

    // ID字符串加密
    private static String bytesToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        //MD5加密后bytes长度16转换成32位16进制字符串
        for (int i = 0; i < bytes.length; i++) {
            /**
             * 在32位的电脑中数字都是以32格式存放的，如果是一个byte(8位)类型的数字，
             * 他的高24位里面都是随机数字，低8位才是实际的数据。
             * java.lang.Integer.toHexString() 方法的参数是int(32位)类型.
             * 如果输入一个byte(8位)类型的数字，这个方法会把这个数字的高24为也看作有效位，
             * 这就必然导致错误，使用& 0XFF操作，可以把高24位置0以避免这样错误.
             *
             * 0xFF = 1111 1111　 低8位为1，高位都为0
             * 故 &0xFF 可将数字的高位都置为0，低8位不变
             *
             * */
            String hex = Integer.toHexString(0xFF & bytes[i]);
            if (hex.length() == 1) {
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
    }
}
