package com.mk.taskfactory.biz.impl;

import com.dianping.cat.Cat;
import com.dianping.cat.message.Event;
import com.mk.taskfactory.api.GdHotelPicToOtsService;
import com.mk.taskfactory.biz.mapper.crawer.GdHotelPhotosMapper;
import com.mk.taskfactory.biz.mapper.crawer.GdRoomPicMapper;
import com.mk.taskfactory.biz.mapper.ots.OtsGdHotelPhotosMapper;
import com.mk.taskfactory.biz.mapper.ots.OtsGdRoomPicMapper;
import com.mk.taskfactory.biz.utils.DateUtils;
import com.mk.taskfactory.biz.utils.PicUtils;
import com.mk.taskfactory.biz.utils.QiniuUtils;
import com.mk.taskfactory.common.Constants;
import com.mk.taskfactory.model.crawer.GdHotelPhotos;
import com.mk.taskfactory.model.crawer.GdRoomPic;
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
import java.util.concurrent.*;

@Service
public class GdHotelPicToOtsServiceImpl implements GdHotelPicToOtsService {
    private static Logger logger = LoggerFactory.getLogger(GdHotelPicToOtsServiceImpl.class);

    @Autowired
    private GdHotelPhotosMapper gdHotelPhotosMapper;

    @Autowired
    private GdRoomPicMapper gdRoomPicMapper;

    @Autowired
    private OtsGdHotelPhotosMapper otsGdHotelPhotosMapper;

    @Autowired
    private OtsGdRoomPicMapper otsGdRoomPicMapper;

    private static ExecutorService pool = Executors.newFixedThreadPool(16);


    public Map<String,Object> hotelPhotos(Integer start){
        Map<String,Object> resultMap=new HashMap<String,Object>();
        Cat.logEvent("hotelPhotos","hotelPhotos to 7niu",Event.SUCCESS,
                "beginTime=" + DateUtils.format_yMdHms(new Date()));
        logger.info(String.format("\n====================hotelPhotos begin time={}====================\n"),DateUtils.format_yMdHms(new Date()));
        GdHotelPhotos gdHotelPhotos = new GdHotelPhotos();
        Integer count = gdHotelPhotosMapper.count(gdHotelPhotos);
        if (count==null||count<=0){
            resultMap.put("message","gd_hotel_photos count is 0");
            resultMap.put("SUCCESS", false);
            return resultMap;
        }
        int pageSize=1000;
        int pageCount=count/pageSize;
        if(start==null){
            start=0;
        }
        logger.info(String.format("\n====================size={}&pageSize={}&pageCount={}====================\n")
                ,count,pageSize,pageCount);
        for (int i=start;i<=pageCount;i++){
            logger.info(String.format("\n====================pageIndex={}====================\n")
                    ,i*pageSize);
            gdHotelPhotos.setPageSize(pageSize);
            gdHotelPhotos.setPageIndex(i*pageSize);
            List<GdHotelPhotos> hotelPhotosList = gdHotelPhotosMapper.qureyByPramas(gdHotelPhotos);
            if (CollectionUtils.isEmpty(hotelPhotosList)){
                logger.info(String.format("\n====================hotelPhotosList is empty====================\n"));
                continue;
            }

            for (final GdHotelPhotos hotelPhotos:hotelPhotosList){
                GdHotelPhotos checkBean = new GdHotelPhotos();
                checkBean.setId(hotelPhotos.getId());
                checkBean = otsGdHotelPhotosMapper.getByPramas(checkBean);
                if (checkBean == null || StringUtils.isEmpty(checkBean.getUrl())) {
                    try{
                        try{
                            pool.execute(new Runnable() {
                                @Override
                                public void run() {
                                    logger.info(String.format("\n========id={}&hotelSourceId={}========\n"),
                        hotelPhotos.getId(),hotelPhotos.getHotelSourceId());
                                    saveHotelPhotos(hotelPhotos);
                                }
                            });
                        }catch (RejectedExecutionException e){

                                TimeUnit.SECONDS.sleep(1);


                        }
                    }catch (InterruptedException e1){
                    }

                }

            }

        }
        Cat.logEvent("hotelPhotos", "hotelPhotos to 7niu", Event.SUCCESS,
                "endTime=" + DateUtils.format_yMdHms(new Date())
        );
        logger.info(String.format("\n====================hotelPhotos  endTime={}====================\n")
                , DateUtils.format_yMdHms(new Date()));
        resultMap.put("message","执行结束");
        resultMap.put("SUCCESS", true);
        return resultMap;
    }
    public void saveHotelPhotos(GdHotelPhotos hotelPhotos) {
        GdHotelPhotos saveBean = new GdHotelPhotos();
        BeanUtils.copyProperties(hotelPhotos, saveBean);
        //上传7牛
        String bigImgKey = null;
        //uuid 文件名
        UUID uuid = UUID.randomUUID();
        String strUuid = uuid.toString();
        try {
            BufferedImage bigImg = PicUtils.getImageFromNetByUrl(hotelPhotos.getUrl());
            String bigFileName = strUuid +bigImg.getWidth()+ ".jpg";
            ByteArrayOutputStream bao = new ByteArrayOutputStream();
            ImageIO.write(bigImg, "jpg", bao);
            //上传切图
            bigImgKey = QiniuUtils.uploadAndTry(bao.toByteArray(), bigFileName, Constants.QINIU_BUCKET);
            bao.close();
            if (StringUtils.isNotEmpty(bigImgKey)){
                saveBean.setUrl(Constants.QINIU_DOWNLOAD_ADDRESS+"/"+bigFileName);

            }else {
                saveBean.setUrl(null);
            }
            saveBean.setCreateTime(new Date());
            saveBean.setUpdateTime(null);
            otsGdHotelPhotosMapper.save(saveBean);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public Map<String,Object> roomPic(Integer start){
        Map<String,Object> resultMap=new HashMap<String,Object>();
        Cat.logEvent("roomPic","roomPic to 7niu",Event.SUCCESS,
                "beginTime=" + DateUtils.format_yMdHms(new Date()));
        logger.info(String.format("\n====================roomPic begin time={}====================\n"),DateUtils.format_yMdHms(new Date()));
        GdRoomPic roomPic = new GdRoomPic();
        Integer count = gdRoomPicMapper.count(roomPic);
        if (count==null||count<=0){
            resultMap.put("message","roomPic count is 0");
            resultMap.put("SUCCESS", false);
            return resultMap;
        }
        int pageSize=1000;
        int pageCount=count/pageSize;
        if(start==null){
            start=0;
        }

        logger.info(String.format("\n====================size={}&pageSize={}&pageCount={}====================\n")
                ,count,pageSize,pageCount);
        for (int i=start;i<=pageCount;i++){
            logger.info(String.format("\n====================pageIndex={}====================\n")
                    ,i*pageSize);
            roomPic.setPageSize(pageSize);
            roomPic.setPageIndex(i*pageSize);
            List<GdRoomPic> roomPicList = gdRoomPicMapper.qureyByPramas(roomPic);
            if (CollectionUtils.isEmpty(roomPicList)){
                logger.info(String.format("\n====================roomPicList is empty====================\n"));
                continue;
            }

            for (final GdRoomPic gdRoomPic:roomPicList){
                GdRoomPic checkBean = new GdRoomPic();
                checkBean.setId(gdRoomPic.getId());
                checkBean = otsGdRoomPicMapper.getByPramas(checkBean);
                if (checkBean == null || StringUtils.isEmpty(checkBean.getUrl())) {
                    pool.execute(new Runnable() {
                        @Override
                        public void run() {
                            logger.info(String.format("\n====================id={}====================\n"),gdRoomPic.getId());
                            saveRoomPic(gdRoomPic);
                        }
                    });
                }

            }

        }
        Cat.logEvent("roomPic", "roomPic to 7niu", Event.SUCCESS,
                "endTime=" + DateUtils.format_yMdHms(new Date())
        );
        logger.info(String.format("\n====================roomPic  endTime={}====================\n")
                , DateUtils.format_yMdHms(new Date()));
        resultMap.put("message","执行结束");
        resultMap.put("SUCCESS", true);
        return resultMap;
    }

    public void saveRoomPic(GdRoomPic roomPic){
        GdRoomPic saveBean = new GdRoomPic();
        BeanUtils.copyProperties(roomPic, saveBean);
        //上传7牛
        String bigImgKey = null;
        //uuid 文件名
        UUID uuid = UUID.randomUUID();
        String strUuid = uuid.toString();
        try {
            BufferedImage smallImg = PicUtils.getImageFromNetByUrl(roomPic.getUrl());
            String smallFileName = strUuid +smallImg.getWidth()+ ".jpg";
            ByteArrayOutputStream smallBao = new ByteArrayOutputStream();
            ImageIO.write(smallImg, "jpg", smallBao);
            String smallImgKey = QiniuUtils.uploadAndTry(smallBao.toByteArray(), smallFileName, Constants.QINIU_BUCKET);
            smallBao.close();
            if (StringUtils.isNotEmpty(smallImgKey)){
                saveBean.setUrl(Constants.QINIU_DOWNLOAD_ADDRESS+"/"+smallFileName);
            }else {
                saveBean.setUrl(null);
            }
            otsGdRoomPicMapper.save(saveBean);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
