package com.mk.taskfactory.biz.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by kangxiaolong on 2016/01/27.
 */
public class PicUtils {
    //图片剪切
    public static BufferedImage cutPic(BufferedImage sourceImg) throws IOException {
        //tmp dir
//        File tmpDir = new File("/Users/kangxiaolong/Desktop/tmp");
//        if (!tmpDir.exists()) {
//            tmpDir.mkdirs();
//        }
//        ImageIO.setCacheDirectory(tmpDir);

        //
        Integer cutWidth = sourceImg.getWidth();
        Integer cutHeight = sourceImg.getHeight()-26;
        BufferedImage sub = sourceImg.getSubimage(0,0,cutWidth,cutHeight);

        //缩放
        //BufferedImage tag = new BufferedImage(defaultWidth,defaultHeight, BufferedImage.TYPE_INT_RGB);
        //tag.getGraphics().drawImage(sub, 0, 0, defaultWidth, defaultHeight, null);
        //保存新图片
			//ImageIO.write(sub, "jpg", new File("/Users/kangxiaolong/Desktop/tmp/test.jpg"));
        return sub;
    }

    public static void main(String[] args) {
        try{
            String url = "http://himg1.qunarzz.com/imgs/201407/20/8q41fFkdfXhzmk2OZ720.jpg";
            cutPic(getImageFromNetByUrl(url));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    /**
     * 剪切远端图片
     * @param strUrl 剪切远端图片
     * @return
     */
    public static BufferedImage cutPic(String strUrl) throws IOException {
        return cutPic(getImageFromNetByUrl(strUrl));
    }
    /**
     * 根据地址获得数据的字节流
     * @param strUrl 网络连接地址
     * @return
     */
    public static BufferedImage getImageFromNetByUrl(String strUrl){
        try {
            URL url = new URL(strUrl);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5 * 1000);
            InputStream inStream = conn.getInputStream();//通过输入流获取图片数据
            BufferedImage sourceImg = ImageIO.read(inStream);
            return sourceImg;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
