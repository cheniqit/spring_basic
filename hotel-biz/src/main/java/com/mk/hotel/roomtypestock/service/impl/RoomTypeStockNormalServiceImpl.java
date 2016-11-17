package com.mk.hotel.roomtypestock.service.impl;

import com.mk.hotel.roomtypestock.RoomTypeStockNormalService;
import com.mk.hotel.roomtypestock.dto.RoomTypeStockNormalDto;
import com.mk.hotel.roomtypestock.mapper.RoomTypeStockNormalMapper;
import com.mk.hotel.roomtypestock.model.RoomTypeStockNormal;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yanbaobin on 2016/10/9 15:51.
 */
@Service
public class RoomTypeStockNormalServiceImpl implements RoomTypeStockNormalService {

	@Autowired
	private RoomTypeStockNormalMapper roomTypeStockNormalMapper;

	@Override
	public int batchInsert(List<RoomTypeStockNormalDto> roomTypeStockNormalDtoList) {
		if (null != roomTypeStockNormalDtoList) {
			List<RoomTypeStockNormal> list = new ArrayList<RoomTypeStockNormal>();
			for (RoomTypeStockNormalDto dto : roomTypeStockNormalDtoList) {
				list.add(toModel(dto));
			}
			return roomTypeStockNormalMapper.batchInsert(list);
		}
		return 0;
	}

	@Override
	public int saveOrUpdate(RoomTypeStockNormalDto dto) {
		if (null != dto) {
			if (null == dto.getId()) {
				RoomTypeStockNormal model = toModel(dto);
				int result = roomTypeStockNormalMapper.insert(model);
				dto.setId(model.getId());
				return result;
			}
			return roomTypeStockNormalMapper.updateByPrimaryKey(toModel(dto));
		}
		return 0;
	}

	@Override
	public RoomTypeStockNormalDto selectById(Long id) {
		if (null != id) {
			RoomTypeStockNormal model = roomTypeStockNormalMapper.selectByPrimaryKey(id);
			return toDto(model);
		}
		return null;
	}

	/**
	 * dto转model
	 * @param dto
	 * @return
	 */
	public RoomTypeStockNormal toModel(RoomTypeStockNormalDto dto) {
		if (null != dto) {
			RoomTypeStockNormal model = new RoomTypeStockNormal();
			BeanUtils.copyProperties(dto, model);
			return model;
		}
		return null;
	}

	/**
	 * model转dto
	 * @param model
	 * @return
	 */
	public RoomTypeStockNormalDto toDto(RoomTypeStockNormal model) {
		if (null != model) {
			RoomTypeStockNormalDto dto = new RoomTypeStockNormalDto();
			BeanUtils.copyProperties(model, dto);
			return dto;
		}
		return null;
	}
}
