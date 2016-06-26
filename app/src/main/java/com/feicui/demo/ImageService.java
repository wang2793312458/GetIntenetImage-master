package com.feicui.demo;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by AAAAA on 2016/6/20.
 */
public class ImageService {
    public static byte[] getImages(String path)throws IOException{

        URL url=new URL(path);
        HttpURLConnection connection= (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");//用GET方法获取图片
        connection.setConnectTimeout(5*1000);//设置等待超时时间为5秒
        connection.disconnect();
        InputStream inputStream=connection.getInputStream();//用connection通过输入流获取图片
        byte[]data=StreamTool.read(inputStream);//获取图片的二进制数据
        return data;
    }
}
