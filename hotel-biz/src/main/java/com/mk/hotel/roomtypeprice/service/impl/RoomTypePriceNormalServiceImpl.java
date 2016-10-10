package com.mk.hotel.roomtypeprice.service.impl;

import com.mk.hotel.roomtypeprice.RoomTypePriceNormalService;
import com.mk.hotel.roomtypeprice.dto.RoomTypePriceNormalDto;
import com.mk.hotel.roomtypeprice.mapper.RoomTypePriceNormalMapper;
import com.mk.hotel.roomtypeprice.model.RoomTypePriceNormal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yanbaobin on 2016/10/9 15:51.
 */
@Service
public class RoomTypePriceNormalServiceImpl implements RoomTypePriceNormalService {
	
	@Autowired
	private RoomTypePriceNormalMapper roomTypePriceNormalMapper;
	
	private final Logger logger = LoggerFactory.getLogger(RoomTypePriceNormalServiceImpl.class);
	
	@Override
	public int batchInsert(List<RoomTypePriceNormalDto> roomTypePriceNormalDtoList) {
		if (null != roomTypePriceNormalDtoList) {
			List<RoomTypePriceNormal> list = new ArrayList<RoomTypePriceNormal>();
			for (RoomTypePriceNormalDto dto : roomTypePriceNormalDtoList) {
				list.add(toModel(dto));
			}
			return roomTypePriceNormalMapper.batchInsert(list);
		}
		return 0;
	}

	@Override
	public int saveOrUpdate(RoomTypePriceNormalDto dto) {
		if (null != dto) {
			if (null == dto.getId()) {
				RoomTypePriceNormal model = toModel(dto);
				int result = roomTypePriceNormalMapper.insert(model);
				dto.setId(model.getId());
				return result;
			}
			return roomTypePriceNormalMapper.updateByPrimaryKey(toModel(dto));
		}
		return 0;
	}

	@Override
	public RoomTypePriceNormalDto selectById(Long id) {
		if (null != id) {
			RoomTypePriceNormal model = roomTypePriceNormalMapper.selectByPrimaryKey(id);
			return toDto(model);
		}
		return null;
	}

	/**
	 * dto转model
	 * @param dto
	 * @return
	 */
	public RoomTypePriceNormal toModel(RoomTypePriceNormalDto dto) {
		if (null != dto) {
			RoomTypePriceNormal model = new RoomTypePriceNormal();
			model.setId(dto.getId());
			model.setRoomTypeId(dto.getRoomTypeId());
			model.setCreateBy(dto.getCreateBy());
			model.setCreateDate(dto.getCreateDate());
			model.setIsValid(dto.getIsValid());
			model.setUpdateBy(dto.getUpdateBy());
			model.setUpdateDate(dto.getUpdateDate());
			
			model.setMonMarketPrice(dto.getMonMarketPrice());
			model.setMonSalePrice(dto.getMonSalePrice());
			model.setMonSettlePrice(dto.getMonSettlePrice());
			
			model.setTueMarketPrice(dto.getTueMarketPrice());
			model.setTueSalePrice(dto.getTueSalePrice());
			model.setTueSettlePrice(dto.getTueSettlePrice());

			model.setWedMarketPrice(dto.getWedMarketPrice());
			model.setWedSalePrice(dto.getWedSalePrice());
			model.setWedSettlePrice(dto.getWedSettlePrice());

			model.setThuMarketPrice(dto.getThuMarketPrice());
			model.setThuSalePrice(dto.getThuSalePrice());
			model.setThuSettlePrice(dto.getThuSettlePrice());
			
			model.setFriMarketPrice(dto.getFriMarketPrice());
			model.setFriSalePrice(dto.getFriSalePrice());
			model.setFriSettlePrice(dto.getFriSettlePrice());

			model.setSatMarketPrice(dto.getSatMarketPrice());
			model.setSatSalePrice(dto.getSatSalePrice());
			model.setSatSettlePrice(dto.getSatSettlePrice());

			model.setSunMarketPrice(dto.getSunMarketPrice());
			model.setSunSalePrice(dto.getSunSalePrice());
			model.setSunSettlePrice(dto.getSunSettlePrice());
			
			return model;
		}
		return null;
	}

	/**
	 * model转dto
	 * @param model
	 * @return
	 */
	public RoomTypePriceNormalDto toDto(RoomTypePriceNormal model) {
		if (null != model) {
			RoomTypePriceNormalDto dto = new RoomTypePriceNormalDto();
			dto.setId(model.getId());
			dto.setRoomTypeId(model.getRoomTypeId());
			dto.setCreateBy(model.getCreateBy());
			dto.setCreateDate(model.getCreateDate());
			dto.setIsValid(model.getIsValid());
			dto.setUpdateBy(model.getUpdateBy());
			dto.setUpdateDate(model.getUpdateDate());

			dto.setMonMarketPrice(model.getMonMarketPrice());
			dto.setMonSalePrice(model.getMonSalePrice());
			dto.setMonSettlePrice(model.getMonSettlePrice());

			dto.setTueMarketPrice(model.getTueMarketPrice());
			dto.setTueSalePrice(model.getTueSalePrice());
			dto.setTueSettlePrice(model.getTueSettlePrice());

			dto.setWedMarketPrice(model.getWedMarketPrice());
			dto.setWedSalePrice(model.getWedSalePrice());
			dto.setWedSettlePrice(model.getWedSettlePrice());

			dto.setThuMarketPrice(model.getThuMarketPrice());
			dto.setThuSalePrice(model.getThuSalePrice());
			dto.setThuSettlePrice(model.getThuSettlePrice());

			dto.setFriMarketPrice(model.getFriMarketPrice());
			dto.setFriSalePrice(model.getFriSalePrice());
			dto.setFriSettlePrice(model.getFriSettlePrice());

			dto.setSatMarketPrice(model.getSatMarketPrice());
			dto.setSatSalePrice(model.getSatSalePrice());
			dto.setSatSettlePrice(model.getSatSettlePrice());

			dto.setSunMarketPrice(model.getSunMarketPrice());
			dto.setSunSalePrice(model.getSunSalePrice());
			dto.setSunSettlePrice(model.getSunSettlePrice());

			return dto;
		}
		return null;
	}
}
