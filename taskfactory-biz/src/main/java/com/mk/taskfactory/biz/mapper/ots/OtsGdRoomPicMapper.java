package com.mk.taskfactory.biz.mapper.ots;


import com.mk.taskfactory.model.crawer.GdRoomPic;

import java.util.List;

public interface OtsGdRoomPicMapper {
    public List<GdRoomPic> qureyByPramas(GdRoomPic bean);
    public GdRoomPic getByPramas(GdRoomPic bean);
    public Integer save(GdRoomPic bean);
    public Integer delete(Integer id);
    public Integer updateById(GdRoomPic bean);
    public Integer count(GdRoomPic bean);
}