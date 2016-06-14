package com.mk.hotel.roomtype.model;

import com.mk.hotel.common.bean.PageBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RoomTypeExample extends PageBean{
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public RoomTypeExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andFangIdIsNull() {
            addCriterion("fang_id is null");
            return (Criteria) this;
        }

        public Criteria andFangIdIsNotNull() {
            addCriterion("fang_id is not null");
            return (Criteria) this;
        }

        public Criteria andFangIdEqualTo(Long value) {
            addCriterion("fang_id =", value, "fangId");
            return (Criteria) this;
        }

        public Criteria andFangIdNotEqualTo(Long value) {
            addCriterion("fang_id <>", value, "fangId");
            return (Criteria) this;
        }

        public Criteria andFangIdGreaterThan(Long value) {
            addCriterion("fang_id >", value, "fangId");
            return (Criteria) this;
        }

        public Criteria andFangIdGreaterThanOrEqualTo(Long value) {
            addCriterion("fang_id >=", value, "fangId");
            return (Criteria) this;
        }

        public Criteria andFangIdLessThan(Long value) {
            addCriterion("fang_id <", value, "fangId");
            return (Criteria) this;
        }

        public Criteria andFangIdLessThanOrEqualTo(Long value) {
            addCriterion("fang_id <=", value, "fangId");
            return (Criteria) this;
        }

        public Criteria andFangIdIn(List<Long> values) {
            addCriterion("fang_id in", values, "fangId");
            return (Criteria) this;
        }

        public Criteria andFangIdNotIn(List<Long> values) {
            addCriterion("fang_id not in", values, "fangId");
            return (Criteria) this;
        }

        public Criteria andFangIdBetween(Long value1, Long value2) {
            addCriterion("fang_id between", value1, value2, "fangId");
            return (Criteria) this;
        }

        public Criteria andFangIdNotBetween(Long value1, Long value2) {
            addCriterion("fang_id not between", value1, value2, "fangId");
            return (Criteria) this;
        }

        public Criteria andHotelIdIsNull() {
            addCriterion("hotel_id is null");
            return (Criteria) this;
        }

        public Criteria andHotelIdIsNotNull() {
            addCriterion("hotel_id is not null");
            return (Criteria) this;
        }

        public Criteria andHotelIdEqualTo(Long value) {
            addCriterion("hotel_id =", value, "hotelId");
            return (Criteria) this;
        }

        public Criteria andHotelIdNotEqualTo(Long value) {
            addCriterion("hotel_id <>", value, "hotelId");
            return (Criteria) this;
        }

        public Criteria andHotelIdGreaterThan(Long value) {
            addCriterion("hotel_id >", value, "hotelId");
            return (Criteria) this;
        }

        public Criteria andHotelIdGreaterThanOrEqualTo(Long value) {
            addCriterion("hotel_id >=", value, "hotelId");
            return (Criteria) this;
        }

        public Criteria andHotelIdLessThan(Long value) {
            addCriterion("hotel_id <", value, "hotelId");
            return (Criteria) this;
        }

        public Criteria andHotelIdLessThanOrEqualTo(Long value) {
            addCriterion("hotel_id <=", value, "hotelId");
            return (Criteria) this;
        }

        public Criteria andHotelIdIn(List<Long> values) {
            addCriterion("hotel_id in", values, "hotelId");
            return (Criteria) this;
        }

        public Criteria andHotelIdNotIn(List<Long> values) {
            addCriterion("hotel_id not in", values, "hotelId");
            return (Criteria) this;
        }

        public Criteria andHotelIdBetween(Long value1, Long value2) {
            addCriterion("hotel_id between", value1, value2, "hotelId");
            return (Criteria) this;
        }

        public Criteria andHotelIdNotBetween(Long value1, Long value2) {
            addCriterion("hotel_id not between", value1, value2, "hotelId");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andAreaIsNull() {
            addCriterion("area is null");
            return (Criteria) this;
        }

        public Criteria andAreaIsNotNull() {
            addCriterion("area is not null");
            return (Criteria) this;
        }

        public Criteria andAreaEqualTo(Short value) {
            addCriterion("area =", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaNotEqualTo(Short value) {
            addCriterion("area <>", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaGreaterThan(Short value) {
            addCriterion("area >", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaGreaterThanOrEqualTo(Short value) {
            addCriterion("area >=", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaLessThan(Short value) {
            addCriterion("area <", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaLessThanOrEqualTo(Short value) {
            addCriterion("area <=", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaIn(List<Short> values) {
            addCriterion("area in", values, "area");
            return (Criteria) this;
        }

        public Criteria andAreaNotIn(List<Short> values) {
            addCriterion("area not in", values, "area");
            return (Criteria) this;
        }

        public Criteria andAreaBetween(Short value1, Short value2) {
            addCriterion("area between", value1, value2, "area");
            return (Criteria) this;
        }

        public Criteria andAreaNotBetween(Short value1, Short value2) {
            addCriterion("area not between", value1, value2, "area");
            return (Criteria) this;
        }

        public Criteria andBedTypeIsNull() {
            addCriterion("bed_type is null");
            return (Criteria) this;
        }

        public Criteria andBedTypeIsNotNull() {
            addCriterion("bed_type is not null");
            return (Criteria) this;
        }

        public Criteria andBedTypeEqualTo(Short value) {
            addCriterion("bed_type =", value, "bedType");
            return (Criteria) this;
        }

        public Criteria andBedTypeNotEqualTo(Short value) {
            addCriterion("bed_type <>", value, "bedType");
            return (Criteria) this;
        }

        public Criteria andBedTypeGreaterThan(Short value) {
            addCriterion("bed_type >", value, "bedType");
            return (Criteria) this;
        }

        public Criteria andBedTypeGreaterThanOrEqualTo(Short value) {
            addCriterion("bed_type >=", value, "bedType");
            return (Criteria) this;
        }

        public Criteria andBedTypeLessThan(Short value) {
            addCriterion("bed_type <", value, "bedType");
            return (Criteria) this;
        }

        public Criteria andBedTypeLessThanOrEqualTo(Short value) {
            addCriterion("bed_type <=", value, "bedType");
            return (Criteria) this;
        }

        public Criteria andBedTypeIn(List<Short> values) {
            addCriterion("bed_type in", values, "bedType");
            return (Criteria) this;
        }

        public Criteria andBedTypeNotIn(List<Short> values) {
            addCriterion("bed_type not in", values, "bedType");
            return (Criteria) this;
        }

        public Criteria andBedTypeBetween(Short value1, Short value2) {
            addCriterion("bed_type between", value1, value2, "bedType");
            return (Criteria) this;
        }

        public Criteria andBedTypeNotBetween(Short value1, Short value2) {
            addCriterion("bed_type not between", value1, value2, "bedType");
            return (Criteria) this;
        }

        public Criteria andBedSizeIsNull() {
            addCriterion("bed_size is null");
            return (Criteria) this;
        }

        public Criteria andBedSizeIsNotNull() {
            addCriterion("bed_size is not null");
            return (Criteria) this;
        }

        public Criteria andBedSizeEqualTo(String value) {
            addCriterion("bed_size =", value, "bedSize");
            return (Criteria) this;
        }

        public Criteria andBedSizeNotEqualTo(String value) {
            addCriterion("bed_size <>", value, "bedSize");
            return (Criteria) this;
        }

        public Criteria andBedSizeGreaterThan(String value) {
            addCriterion("bed_size >", value, "bedSize");
            return (Criteria) this;
        }

        public Criteria andBedSizeGreaterThanOrEqualTo(String value) {
            addCriterion("bed_size >=", value, "bedSize");
            return (Criteria) this;
        }

        public Criteria andBedSizeLessThan(String value) {
            addCriterion("bed_size <", value, "bedSize");
            return (Criteria) this;
        }

        public Criteria andBedSizeLessThanOrEqualTo(String value) {
            addCriterion("bed_size <=", value, "bedSize");
            return (Criteria) this;
        }

        public Criteria andBedSizeLike(String value) {
            addCriterion("bed_size like", value, "bedSize");
            return (Criteria) this;
        }

        public Criteria andBedSizeNotLike(String value) {
            addCriterion("bed_size not like", value, "bedSize");
            return (Criteria) this;
        }

        public Criteria andBedSizeIn(List<String> values) {
            addCriterion("bed_size in", values, "bedSize");
            return (Criteria) this;
        }

        public Criteria andBedSizeNotIn(List<String> values) {
            addCriterion("bed_size not in", values, "bedSize");
            return (Criteria) this;
        }

        public Criteria andBedSizeBetween(String value1, String value2) {
            addCriterion("bed_size between", value1, value2, "bedSize");
            return (Criteria) this;
        }

        public Criteria andBedSizeNotBetween(String value1, String value2) {
            addCriterion("bed_size not between", value1, value2, "bedSize");
            return (Criteria) this;
        }

        public Criteria andRoomNumIsNull() {
            addCriterion("room_num is null");
            return (Criteria) this;
        }

        public Criteria andRoomNumIsNotNull() {
            addCriterion("room_num is not null");
            return (Criteria) this;
        }

        public Criteria andRoomNumEqualTo(Short value) {
            addCriterion("room_num =", value, "roomNum");
            return (Criteria) this;
        }

        public Criteria andRoomNumNotEqualTo(Short value) {
            addCriterion("room_num <>", value, "roomNum");
            return (Criteria) this;
        }

        public Criteria andRoomNumGreaterThan(Short value) {
            addCriterion("room_num >", value, "roomNum");
            return (Criteria) this;
        }

        public Criteria andRoomNumGreaterThanOrEqualTo(Short value) {
            addCriterion("room_num >=", value, "roomNum");
            return (Criteria) this;
        }

        public Criteria andRoomNumLessThan(Short value) {
            addCriterion("room_num <", value, "roomNum");
            return (Criteria) this;
        }

        public Criteria andRoomNumLessThanOrEqualTo(Short value) {
            addCriterion("room_num <=", value, "roomNum");
            return (Criteria) this;
        }

        public Criteria andRoomNumIn(List<Short> values) {
            addCriterion("room_num in", values, "roomNum");
            return (Criteria) this;
        }

        public Criteria andRoomNumNotIn(List<Short> values) {
            addCriterion("room_num not in", values, "roomNum");
            return (Criteria) this;
        }

        public Criteria andRoomNumBetween(Short value1, Short value2) {
            addCriterion("room_num between", value1, value2, "roomNum");
            return (Criteria) this;
        }

        public Criteria andRoomNumNotBetween(Short value1, Short value2) {
            addCriterion("room_num not between", value1, value2, "roomNum");
            return (Criteria) this;
        }

        public Criteria andPrepayIsNull() {
            addCriterion("prepay is null");
            return (Criteria) this;
        }

        public Criteria andPrepayIsNotNull() {
            addCriterion("prepay is not null");
            return (Criteria) this;
        }

        public Criteria andPrepayEqualTo(Short value) {
            addCriterion("prepay =", value, "prepay");
            return (Criteria) this;
        }

        public Criteria andPrepayNotEqualTo(Short value) {
            addCriterion("prepay <>", value, "prepay");
            return (Criteria) this;
        }

        public Criteria andPrepayGreaterThan(Short value) {
            addCriterion("prepay >", value, "prepay");
            return (Criteria) this;
        }

        public Criteria andPrepayGreaterThanOrEqualTo(Short value) {
            addCriterion("prepay >=", value, "prepay");
            return (Criteria) this;
        }

        public Criteria andPrepayLessThan(Short value) {
            addCriterion("prepay <", value, "prepay");
            return (Criteria) this;
        }

        public Criteria andPrepayLessThanOrEqualTo(Short value) {
            addCriterion("prepay <=", value, "prepay");
            return (Criteria) this;
        }

        public Criteria andPrepayIn(List<Short> values) {
            addCriterion("prepay in", values, "prepay");
            return (Criteria) this;
        }

        public Criteria andPrepayNotIn(List<Short> values) {
            addCriterion("prepay not in", values, "prepay");
            return (Criteria) this;
        }

        public Criteria andPrepayBetween(Short value1, Short value2) {
            addCriterion("prepay between", value1, value2, "prepay");
            return (Criteria) this;
        }

        public Criteria andPrepayNotBetween(Short value1, Short value2) {
            addCriterion("prepay not between", value1, value2, "prepay");
            return (Criteria) this;
        }

        public Criteria andBreakfastIsNull() {
            addCriterion("breakfast is null");
            return (Criteria) this;
        }

        public Criteria andBreakfastIsNotNull() {
            addCriterion("breakfast is not null");
            return (Criteria) this;
        }

        public Criteria andBreakfastEqualTo(Short value) {
            addCriterion("breakfast =", value, "breakfast");
            return (Criteria) this;
        }

        public Criteria andBreakfastNotEqualTo(Short value) {
            addCriterion("breakfast <>", value, "breakfast");
            return (Criteria) this;
        }

        public Criteria andBreakfastGreaterThan(Short value) {
            addCriterion("breakfast >", value, "breakfast");
            return (Criteria) this;
        }

        public Criteria andBreakfastGreaterThanOrEqualTo(Short value) {
            addCriterion("breakfast >=", value, "breakfast");
            return (Criteria) this;
        }

        public Criteria andBreakfastLessThan(Short value) {
            addCriterion("breakfast <", value, "breakfast");
            return (Criteria) this;
        }

        public Criteria andBreakfastLessThanOrEqualTo(Short value) {
            addCriterion("breakfast <=", value, "breakfast");
            return (Criteria) this;
        }

        public Criteria andBreakfastIn(List<Short> values) {
            addCriterion("breakfast in", values, "breakfast");
            return (Criteria) this;
        }

        public Criteria andBreakfastNotIn(List<Short> values) {
            addCriterion("breakfast not in", values, "breakfast");
            return (Criteria) this;
        }

        public Criteria andBreakfastBetween(Short value1, Short value2) {
            addCriterion("breakfast between", value1, value2, "breakfast");
            return (Criteria) this;
        }

        public Criteria andBreakfastNotBetween(Short value1, Short value2) {
            addCriterion("breakfast not between", value1, value2, "breakfast");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Short value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Short value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Short value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Short value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Short value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Short value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Short> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Short> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Short value1, Short value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Short value1, Short value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andRefundIsNull() {
            addCriterion("refund is null");
            return (Criteria) this;
        }

        public Criteria andRefundIsNotNull() {
            addCriterion("refund is not null");
            return (Criteria) this;
        }

        public Criteria andRefundEqualTo(Short value) {
            addCriterion("refund =", value, "refund");
            return (Criteria) this;
        }

        public Criteria andRefundNotEqualTo(Short value) {
            addCriterion("refund <>", value, "refund");
            return (Criteria) this;
        }

        public Criteria andRefundGreaterThan(Short value) {
            addCriterion("refund >", value, "refund");
            return (Criteria) this;
        }

        public Criteria andRefundGreaterThanOrEqualTo(Short value) {
            addCriterion("refund >=", value, "refund");
            return (Criteria) this;
        }

        public Criteria andRefundLessThan(Short value) {
            addCriterion("refund <", value, "refund");
            return (Criteria) this;
        }

        public Criteria andRefundLessThanOrEqualTo(Short value) {
            addCriterion("refund <=", value, "refund");
            return (Criteria) this;
        }

        public Criteria andRefundIn(List<Short> values) {
            addCriterion("refund in", values, "refund");
            return (Criteria) this;
        }

        public Criteria andRefundNotIn(List<Short> values) {
            addCriterion("refund not in", values, "refund");
            return (Criteria) this;
        }

        public Criteria andRefundBetween(Short value1, Short value2) {
            addCriterion("refund between", value1, value2, "refund");
            return (Criteria) this;
        }

        public Criteria andRefundNotBetween(Short value1, Short value2) {
            addCriterion("refund not between", value1, value2, "refund");
            return (Criteria) this;
        }

        public Criteria andMaxRoomNumIsNull() {
            addCriterion("max_room_num is null");
            return (Criteria) this;
        }

        public Criteria andMaxRoomNumIsNotNull() {
            addCriterion("max_room_num is not null");
            return (Criteria) this;
        }

        public Criteria andMaxRoomNumEqualTo(Short value) {
            addCriterion("max_room_num =", value, "maxRoomNum");
            return (Criteria) this;
        }

        public Criteria andMaxRoomNumNotEqualTo(Short value) {
            addCriterion("max_room_num <>", value, "maxRoomNum");
            return (Criteria) this;
        }

        public Criteria andMaxRoomNumGreaterThan(Short value) {
            addCriterion("max_room_num >", value, "maxRoomNum");
            return (Criteria) this;
        }

        public Criteria andMaxRoomNumGreaterThanOrEqualTo(Short value) {
            addCriterion("max_room_num >=", value, "maxRoomNum");
            return (Criteria) this;
        }

        public Criteria andMaxRoomNumLessThan(Short value) {
            addCriterion("max_room_num <", value, "maxRoomNum");
            return (Criteria) this;
        }

        public Criteria andMaxRoomNumLessThanOrEqualTo(Short value) {
            addCriterion("max_room_num <=", value, "maxRoomNum");
            return (Criteria) this;
        }

        public Criteria andMaxRoomNumIn(List<Short> values) {
            addCriterion("max_room_num in", values, "maxRoomNum");
            return (Criteria) this;
        }

        public Criteria andMaxRoomNumNotIn(List<Short> values) {
            addCriterion("max_room_num not in", values, "maxRoomNum");
            return (Criteria) this;
        }

        public Criteria andMaxRoomNumBetween(Short value1, Short value2) {
            addCriterion("max_room_num between", value1, value2, "maxRoomNum");
            return (Criteria) this;
        }

        public Criteria andMaxRoomNumNotBetween(Short value1, Short value2) {
            addCriterion("max_room_num not between", value1, value2, "maxRoomNum");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNull() {
            addCriterion("create_date is null");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNotNull() {
            addCriterion("create_date is not null");
            return (Criteria) this;
        }

        public Criteria andCreateDateEqualTo(Date value) {
            addCriterion("create_date =", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotEqualTo(Date value) {
            addCriterion("create_date <>", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThan(Date value) {
            addCriterion("create_date >", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("create_date >=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThan(Date value) {
            addCriterion("create_date <", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThanOrEqualTo(Date value) {
            addCriterion("create_date <=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateIn(List<Date> values) {
            addCriterion("create_date in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotIn(List<Date> values) {
            addCriterion("create_date not in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateBetween(Date value1, Date value2) {
            addCriterion("create_date between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotBetween(Date value1, Date value2) {
            addCriterion("create_date not between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateByIsNull() {
            addCriterion("create_by is null");
            return (Criteria) this;
        }

        public Criteria andCreateByIsNotNull() {
            addCriterion("create_by is not null");
            return (Criteria) this;
        }

        public Criteria andCreateByEqualTo(String value) {
            addCriterion("create_by =", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotEqualTo(String value) {
            addCriterion("create_by <>", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByGreaterThan(String value) {
            addCriterion("create_by >", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByGreaterThanOrEqualTo(String value) {
            addCriterion("create_by >=", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLessThan(String value) {
            addCriterion("create_by <", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLessThanOrEqualTo(String value) {
            addCriterion("create_by <=", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLike(String value) {
            addCriterion("create_by like", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotLike(String value) {
            addCriterion("create_by not like", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByIn(List<String> values) {
            addCriterion("create_by in", values, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotIn(List<String> values) {
            addCriterion("create_by not in", values, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByBetween(String value1, String value2) {
            addCriterion("create_by between", value1, value2, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotBetween(String value1, String value2) {
            addCriterion("create_by not between", value1, value2, "createBy");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIsNull() {
            addCriterion("update_date is null");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIsNotNull() {
            addCriterion("update_date is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateDateEqualTo(Date value) {
            addCriterion("update_date =", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotEqualTo(Date value) {
            addCriterion("update_date <>", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateGreaterThan(Date value) {
            addCriterion("update_date >", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("update_date >=", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateLessThan(Date value) {
            addCriterion("update_date <", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateLessThanOrEqualTo(Date value) {
            addCriterion("update_date <=", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIn(List<Date> values) {
            addCriterion("update_date in", values, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotIn(List<Date> values) {
            addCriterion("update_date not in", values, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateBetween(Date value1, Date value2) {
            addCriterion("update_date between", value1, value2, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotBetween(Date value1, Date value2) {
            addCriterion("update_date not between", value1, value2, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateByIsNull() {
            addCriterion("update_by is null");
            return (Criteria) this;
        }

        public Criteria andUpdateByIsNotNull() {
            addCriterion("update_by is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateByEqualTo(String value) {
            addCriterion("update_by =", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotEqualTo(String value) {
            addCriterion("update_by <>", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByGreaterThan(String value) {
            addCriterion("update_by >", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByGreaterThanOrEqualTo(String value) {
            addCriterion("update_by >=", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLessThan(String value) {
            addCriterion("update_by <", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLessThanOrEqualTo(String value) {
            addCriterion("update_by <=", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLike(String value) {
            addCriterion("update_by like", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotLike(String value) {
            addCriterion("update_by not like", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByIn(List<String> values) {
            addCriterion("update_by in", values, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotIn(List<String> values) {
            addCriterion("update_by not in", values, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByBetween(String value1, String value2) {
            addCriterion("update_by between", value1, value2, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotBetween(String value1, String value2) {
            addCriterion("update_by not between", value1, value2, "updateBy");
            return (Criteria) this;
        }

        public Criteria andIsValidIsNull() {
            addCriterion("is_valid is null");
            return (Criteria) this;
        }

        public Criteria andIsValidIsNotNull() {
            addCriterion("is_valid is not null");
            return (Criteria) this;
        }

        public Criteria andIsValidEqualTo(String value) {
            addCriterion("is_valid =", value, "isValid");
            return (Criteria) this;
        }

        public Criteria andIsValidNotEqualTo(String value) {
            addCriterion("is_valid <>", value, "isValid");
            return (Criteria) this;
        }

        public Criteria andIsValidGreaterThan(String value) {
            addCriterion("is_valid >", value, "isValid");
            return (Criteria) this;
        }

        public Criteria andIsValidGreaterThanOrEqualTo(String value) {
            addCriterion("is_valid >=", value, "isValid");
            return (Criteria) this;
        }

        public Criteria andIsValidLessThan(String value) {
            addCriterion("is_valid <", value, "isValid");
            return (Criteria) this;
        }

        public Criteria andIsValidLessThanOrEqualTo(String value) {
            addCriterion("is_valid <=", value, "isValid");
            return (Criteria) this;
        }

        public Criteria andIsValidLike(String value) {
            addCriterion("is_valid like", value, "isValid");
            return (Criteria) this;
        }

        public Criteria andIsValidNotLike(String value) {
            addCriterion("is_valid not like", value, "isValid");
            return (Criteria) this;
        }

        public Criteria andIsValidIn(List<String> values) {
            addCriterion("is_valid in", values, "isValid");
            return (Criteria) this;
        }

        public Criteria andIsValidNotIn(List<String> values) {
            addCriterion("is_valid not in", values, "isValid");
            return (Criteria) this;
        }

        public Criteria andIsValidBetween(String value1, String value2) {
            addCriterion("is_valid between", value1, value2, "isValid");
            return (Criteria) this;
        }

        public Criteria andIsValidNotBetween(String value1, String value2) {
            addCriterion("is_valid not between", value1, value2, "isValid");
            return (Criteria) this;
        }

        public Criteria andPicsSignIsNull() {
            addCriterion("pics_sign is null");
            return (Criteria) this;
        }

        public Criteria andPicsSignIsNotNull() {
            addCriterion("pics_sign is not null");
            return (Criteria) this;
        }

        public Criteria andPicsSignEqualTo(String value) {
            addCriterion("pics_sign =", value, "picsSign");
            return (Criteria) this;
        }

        public Criteria andPicsSignNotEqualTo(String value) {
            addCriterion("pics_sign <>", value, "picsSign");
            return (Criteria) this;
        }

        public Criteria andPicsSignGreaterThan(String value) {
            addCriterion("pics_sign >", value, "picsSign");
            return (Criteria) this;
        }

        public Criteria andPicsSignGreaterThanOrEqualTo(String value) {
            addCriterion("pics_sign >=", value, "picsSign");
            return (Criteria) this;
        }

        public Criteria andPicsSignLessThan(String value) {
            addCriterion("pics_sign <", value, "picsSign");
            return (Criteria) this;
        }

        public Criteria andPicsSignLessThanOrEqualTo(String value) {
            addCriterion("pics_sign <=", value, "picsSign");
            return (Criteria) this;
        }

        public Criteria andPicsSignLike(String value) {
            addCriterion("pics_sign like", value, "picsSign");
            return (Criteria) this;
        }

        public Criteria andPicsSignNotLike(String value) {
            addCriterion("pics_sign not like", value, "picsSign");
            return (Criteria) this;
        }

        public Criteria andPicsSignIn(List<String> values) {
            addCriterion("pics_sign in", values, "picsSign");
            return (Criteria) this;
        }

        public Criteria andPicsSignNotIn(List<String> values) {
            addCriterion("pics_sign not in", values, "picsSign");
            return (Criteria) this;
        }

        public Criteria andPicsSignBetween(String value1, String value2) {
            addCriterion("pics_sign between", value1, value2, "picsSign");
            return (Criteria) this;
        }

        public Criteria andPicsSignNotBetween(String value1, String value2) {
            addCriterion("pics_sign not between", value1, value2, "picsSign");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}