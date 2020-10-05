package pers.czj.utils;

import okhttp3.*;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 创建在 2020/5/17 21:46
 */
public class HttpUtils {

    //*用户标志
    private static final String DEFAULT_USER_AGENT="Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.138 Safari/537.36";
    /**
     * 请求url路径解析视频路径在哪里
     * @param url
     */
    public static HtmlUtils.Resource findVideoBaseUrl(String url){
        //构建基本请求
        OkHttpClient client =new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .addHeader("User-Agent",DEFAULT_USER_AGENT)
                .build();

        try {
            Response response = client.newCall(request).execute();
            ResponseBody responseBody = response.body();
            return HtmlUtils.resolver(responseBody.string());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 开始下载
     * @param videoUrl
     */
    public static void download(String videoUrl, Callback callback){
        OkHttpClient client = new OkHttpClient().newBuilder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(60,TimeUnit.SECONDS)
                .build();
        Request request = new Request.Builder()
                .url(videoUrl)
                .addHeader("Connection","keep-alive")
                .addHeader("User-Agent",DEFAULT_USER_AGENT)
                .addHeader("Accept","*/*")
                .addHeader("Origin","https://www.bilibili.com")
                .addHeader("Sec-Fetch-Site","cross-site")
                .addHeader("Sec-Fetch-Mode","cors")
                .addHeader("Sec-Fetch-Dest","empty")
                .addHeader("Referer","https://www.bilibili.com")
                .addHeader("Accept-Encoding","identity")
                .addHeader("Accept-Language","zh-CN,zh;q=0.9")
                .build();
        System.out.println("开始下载");
        client.newCall(request).enqueue(callback);
        System.out.println("下载中");
    }

}