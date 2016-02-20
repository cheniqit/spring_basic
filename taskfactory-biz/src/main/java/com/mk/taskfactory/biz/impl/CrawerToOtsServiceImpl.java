package com.mk.taskfactory.biz.impl;

import com.dianping.cat.Cat;
import com.dianping.cat.message.Event;
import com.mk.taskfactory.api.CrawerToOtsService;
import com.mk.taskfactory.api.crawer.CrawerCommentImgService;
import com.mk.taskfactory.api.crawer.CrawerHotelImageService;
import com.mk.taskfactory.api.dtos.crawer.TExCommentImgDto;
import com.mk.taskfactory.api.dtos.crawer.TExHotelImageDto;
import com.mk.taskfactory.api.ots.CityService;
import com.mk.taskfactory.api.ots.OtsCommentImgService;
import com.mk.taskfactory.api.ots.OtsHotelImageService;
import com.mk.taskfactory.biz.utils.DateUtils;
import com.mk.taskfactory.biz.utils.PicUtils;
import com.mk.taskfactory.biz.utils.QiniuUtils;
import com.mk.taskfactory.common.Constants;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class CrawerToOtsServiceImpl implements CrawerToOtsService {
    private static Logger logger = LoggerFactory.getLogger(CrawerToOtsServiceImpl.class);

    @Autowired
    private CrawerCommentImgService crawerCommentImgService;

    @Autowired
    private OtsCommentImgService otsCommentImgService;

    @Autowired
    private CrawerHotelImageService crawerHotelImageService;

    @Autowired
    private OtsHotelImageService otsHotelImageService;

    private static ExecutorService pool = Executors.newFixedThreadPool(70);


    public Map<String,Object> commentImg(){
        Map<String,Object> resultMap=new HashMap<String,Object>();
        Cat.logEvent("commentImg","同步commentImg信息到Ots",Event.SUCCESS,
                "beginTime=" + DateUtils.format_yMdHms(new Date()));
        logger.info(String.format("\n====================commentImg begin time={}====================\n"),DateUtils.format_yMdHms(new Date()));
        TExCommentImgDto commentImgDto = new TExCommentImgDto();
        Integer count = crawerCommentImgService.count(commentImgDto);
        if (count==null||count<=0){
            resultMap.put("message","CommentImg count is 0");
            resultMap.put("SUCCESS", false);
            return resultMap;
        }
        int pageSize=1000;
        int pageCount=count/pageSize;
        logger.info(String.format("\n====================size={}&pageSize={}&pageCount={}====================\n")
                ,count,pageSize,pageCount);
        for (int i=0;i<=pageCount;i++){
            logger.info(String.format("\n====================pageIndex={}====================\n")
                    ,i*pageSize);
            commentImgDto.setPageSize(pageSize);
            commentImgDto.setPageIndex(i*pageSize);
            List<TExCommentImgDto> commentImgList = crawerCommentImgService.qureyByPramas(commentImgDto);
            if (CollectionUtils.isEmpty(commentImgList)){
                logger.info(String.format("\n====================commentImgList is empty====================\n"));
                continue;
            }

            for (final TExCommentImgDto commentImg:commentImgList){
                TExCommentImgDto otsCommentImg = new TExCommentImgDto();
                otsCommentImg.setId(commentImg.getId());
                otsCommentImg = otsCommentImgService.getByPramas(otsCommentImg);
                if (otsCommentImg == null || StringUtils.isEmpty(otsCommentImg.getUrl())) {
                    pool.execute(new Runnable() {
                        @Override
                        public void run() {
                            saveCommentImg(commentImg);
                        }
                    });
                }

            }

        }
        Cat.logEvent("commentImg", "同步commentImg信息到Ots", Event.SUCCESS,
                "endTime=" + DateUtils.format_yMdHms(new Date())
        );
        logger.info(String.format("\n====================commentImg  endTime={}====================\n")
                , DateUtils.format_yMdHms(new Date()));
        resultMap.put("message","执行结束");
        resultMap.put("SUCCESS", true);
        return resultMap;
    }
    public void saveCommentImg(TExCommentImgDto commentImg) {
        TExCommentImgDto saveBean = new TExCommentImgDto();
        BeanUtils.copyProperties(commentImg, saveBean);
        //上传7牛
        String bigImgKey = null;
        //uuid 文件名
        UUID uuid = UUID.randomUUID();
        String strUuid = uuid.toString();
        logger.info(
                String.format("\n========picture.upload:hotelSourceId:%s========\n",
                        commentImg.getHotelSourceId()));
        try {
            BufferedImage bigImg = PicUtils.cutPic(commentImg.getUrl());
            String bigFileName = strUuid +bigImg.getWidth()+ ".jpg";
            ByteArrayOutputStream bao = new ByteArrayOutputStream();
            ImageIO.write(bigImg, "jpg", bao);
            //上传切图
            bigImgKey = QiniuUtils.uploadAndTry(bao.toByteArray(), bigFileName, Constants.QINIU_BUCKET);
            if (StringUtils.isNotEmpty(bigImgKey)){
                saveBean.setUrl(Constants.QINIU_DOWNLOAD_ADDRESS+"/"+bigFileName);

            }else {
                saveBean.setUrl(null);
            }
            BufferedImage smallImg = PicUtils.getImageFromNetByUrl(commentImg.getSmallUrl());
            String smallFileName = strUuid +smallImg.getWidth()+ ".jpg";
            ByteArrayOutputStream smallBao = new ByteArrayOutputStream();
            ImageIO.write(smallImg, "jpg", smallBao);
            String smallImgKey = QiniuUtils.uploadAndTry(smallBao.toByteArray(), smallFileName, Constants.QINIU_BUCKET);
            if (StringUtils.isNotEmpty(smallImgKey)){
                saveBean.setSmallUrl(Constants.QINIU_DOWNLOAD_ADDRESS+"/"+smallFileName);
            }else {
                saveBean.setSmallUrl(null);
            }
            saveBean.setCreateTime(new Date());
            saveBean.setUpdateTime(null);
            otsCommentImgService.save(saveBean);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public Map<String,Object> hotelImage(){
        Map<String,Object> resultMap=new HashMap<String,Object>();
        Cat.logEvent("hotelImage","同步hotelImage信息到Ots",Event.SUCCESS,
                "beginTime=" + DateUtils.format_yMdHms(new Date()));
        logger.info(String.format("\n====================hotelImage begin time={}====================\n"),DateUtils.format_yMdHms(new Date()));
        TExHotelImageDto hotelImageDto = new TExHotelImageDto();
        Integer count = crawerHotelImageService.count(hotelImageDto);
        if (count==null||count<=0){
            resultMap.put("message","CommentImg count is 0");
            resultMap.put("SUCCESS", false);
            return resultMap;
        }
        int pageSize=1000;
        int pageCount=count/pageSize;
        logger.info(String.format("\n====================size={}&pageSize={}&pageCount={}====================\n")
                ,count,pageSize,pageCount);
        for (int i=180;i<=pageCount;i++){
            logger.info(String.format("\n====================pageIndex={}====================\n")
                    ,i*pageSize);
            hotelImageDto.setPageSize(pageSize);
            hotelImageDto.setPageIndex(i*pageSize);
            List<TExHotelImageDto> hotelImageList = crawerHotelImageService.qureyByPramas(hotelImageDto);
            if (CollectionUtils.isEmpty(hotelImageList)){
                logger.info(String.format("\n====================commentImgList is empty====================\n"));
                continue;
            }

            for (final TExHotelImageDto hotelImage:hotelImageList){
                TExHotelImageDto otsHotelImg = new TExHotelImageDto();
                otsHotelImg.setId(hotelImage.getId());
                otsHotelImg = otsHotelImageService.getByPramas(otsHotelImg);
                if (otsHotelImg == null || StringUtils.isEmpty(otsHotelImg.getBig())) {
                    pool.execute(new Runnable() {
                        @Override
                        public void run() {
                            saveHotelImage(hotelImage);
                        }
                    });
                }

            }

        }
        Cat.logEvent("hotelImage", "同步hotelImage信息到Ots", Event.SUCCESS,
                "endTime=" + DateUtils.format_yMdHms(new Date())
        );
        logger.info(String.format("\n====================commentImg  endTime={}====================\n")
                , DateUtils.format_yMdHms(new Date()));
        resultMap.put("message","执行结束");
        resultMap.put("SUCCESS", true);
        return resultMap;
    }
    public void saveHotelImage(TExHotelImageDto hotelImage){
        TExHotelImageDto saveBean = new TExHotelImageDto();
        BeanUtils.copyProperties(hotelImage, saveBean);
        //上传7牛
        String bigImgKey = null;
        //uuid 文件名
        UUID uuid = UUID.randomUUID();
        String strUuid = uuid.toString();
        logger.info(
                String.format("\n========picture.upload:hotelSourceId:%s========\n",
                        hotelImage.getHotelSourceId()));
        try {
            BufferedImage bigImg = PicUtils.cutPic(hotelImage.getBig());
            String bigFileName = strUuid +bigImg.getWidth()+ ".jpg";
            ByteArrayOutputStream bao = new ByteArrayOutputStream();
            ImageIO.write(bigImg, "jpg", bao);
            //上传切图
            bigImgKey = QiniuUtils.uploadAndTry(bao.toByteArray(), bigFileName, Constants.QINIU_BUCKET);
            if (StringUtils.isNotEmpty(bigImgKey)){
                saveBean.setBig(Constants.QINIU_DOWNLOAD_ADDRESS+"/"+bigFileName);

            }else {
                saveBean.setBig(null);
            }
            BufferedImage smallImg = PicUtils.getImageFromNetByUrl(hotelImage.getUrl());
            String smallFileName = strUuid +smallImg.getWidth()+ ".jpg";
            ByteArrayOutputStream smallBao = new ByteArrayOutputStream();
            ImageIO.write(smallImg, "jpg", smallBao);
            String smallImgKey = QiniuUtils.uploadAndTry(smallBao.toByteArray(), smallFileName, Constants.QINIU_BUCKET);
            if (StringUtils.isNotEmpty(smallImgKey)){
                saveBean.setUrl(Constants.QINIU_DOWNLOAD_ADDRESS+"/"+smallFileName);
            }else {
                saveBean.setUrl(null);
            }
            saveBean.setCreateTime(new Date());
            saveBean.setUpdateTime(null);
            otsHotelImageService.save(saveBean);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void saveCommentImg(Long id) {
        if (id==null){
            return;
        }
        TExCommentImgDto commentImgDto = new TExCommentImgDto();
        commentImgDto.setId(id);
        commentImgDto = crawerCommentImgService.getByPramas(commentImgDto);
        if (commentImgDto==null|| StringUtils.isEmpty(commentImgDto.getHotelSourceId())){
            return;
        }
        TExCommentImgDto otsCommentImg = new TExCommentImgDto();
        otsCommentImg.setId(commentImgDto.getId());
        otsCommentImg = otsCommentImgService.getByPramas(otsCommentImg);
        if (otsCommentImg == null || otsCommentImg.getSrc()==null) {
            return;
        }
        saveCommentImg(commentImgDto);
    }
    public void saveHotelImage(Long id) {
        if (id==null){
            return;
        }
        TExHotelImageDto hotelImageDto = new TExHotelImageDto();
        hotelImageDto.setId(id);
        hotelImageDto = crawerHotelImageService.getByPramas(hotelImageDto);
        if (hotelImageDto==null|| StringUtils.isEmpty(hotelImageDto.getHotelSourceId())){
            return;
        }
        TExHotelImageDto otsHotelImg = new TExHotelImageDto();
        otsHotelImg.setId(hotelImageDto.getId());
        otsHotelImg = otsHotelImageService.getByPramas(otsHotelImg);
        if (otsHotelImg == null || otsHotelImg.getSrc()==null) {
            return;
        }
        saveHotelImage(hotelImageDto);
    }
}
