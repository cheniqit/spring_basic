package com.mk.taskfactory.biz.mapper.crawer;


import com.mk.taskfactory.model.crawer.GdRoomPic;

import java.util.List;

public interface GdRoomPicMapper {
    public List<GdRoomPic> qureyByPramas(GdRoomPic bean);
    public GdRoomPic getByPramas(GdRoomPic bean);
    public Integer save(GdRoomPic bean);
    public Integer delete(Integer id);
    public Integer updateById(GdRoomPic bean);
    public Integer count(GdRoomPic bean);
}