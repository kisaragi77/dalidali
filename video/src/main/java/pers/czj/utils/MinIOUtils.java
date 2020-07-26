package pers.czj.utils;


import io.minio.MinioClient;
import io.minio.errors.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.xmlpull.v1.XmlPullParserException;

import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * 创建在 2020/7/13 14:38
 */
@Component
public class MinIOUtils {

    private static final Logger log = LoggerFactory.getLogger(MinIOUtils.class);

    @Autowired
    private MinioClient minioClient;


    @Value("${minio.bucket-name}")
    private String bucketName;

    @Value("${minio.download-url}")
    private String url;

    /**
     * @author czj
     * 上传资源到minio中
     * @date 2020/7/16 14:32
     * @param [fileName, inputStream]
     * @return java.lang.String
     */
    public String uploadFile(String fileName, InputStream inputStream){
        log.debug("将上传的视频名:{}",fileName);
        try {
            minioClient.putObject(bucketName,fileName,inputStream,"video/mp4");
        } catch (InvalidBucketNameException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InsufficientDataException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoResponseException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (ErrorResponseException e) {
            e.printStackTrace();
        } catch (InternalException e) {
            e.printStackTrace();
        } catch (InvalidArgumentException e) {
            e.printStackTrace();
        }
        return url+bucketName+"/"+fileName;
    }

    /**
     * @author czj
     * 将文件保存到本地
     * @date 2020/7/23 19:08
     * @param [fileName]
     * @return
     */
    public void saveLocalTemp(String fileName,String destPath){
        try(BufferedInputStream bufferedInputStream = new BufferedInputStream(minioClient.getObject(bucketName,fileName));
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(new File(destPath,fileName)))
        ){
            byte [] bytes = new byte[1024];
            int len = -1;
            while((len = bufferedInputStream.read(bytes))!=-1){
                bufferedOutputStream.write(bytes,0,len);
            }

        } catch (InvalidBucketNameException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InsufficientDataException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoResponseException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (ErrorResponseException e) {
            e.printStackTrace();
        } catch (InternalException e) {
            e.printStackTrace();
        } catch (InvalidArgumentException e) {
            e.printStackTrace();
        }
    }
}
