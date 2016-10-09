package com.mk.hotel.roomtypestock.model;

import com.mk.hotel.common.bean.PageBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RoomTypeStockNormalExample extends PageBean {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public RoomTypeStockNormalExample() {
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

        public Criteria andRoomTypeIdIsNull() {
            addCriterion("room_type_id is null");
            return (Criteria) this;
        }

        public Criteria andRoomTypeIdIsNotNull() {
            addCriterion("room_type_id is not null");
            return (Criteria) this;
        }

        public Criteria andRoomTypeIdEqualTo(Long value) {
            addCriterion("room_type_id =", value, "roomTypeId");
            return (Criteria) this;
        }

        public Criteria andRoomTypeIdNotEqualTo(Long value) {
            addCriterion("room_type_id <>", value, "roomTypeId");
            return (Criteria) this;
        }

        public Criteria andRoomTypeIdGreaterThan(Long value) {
            addCriterion("room_type_id >", value, "roomTypeId");
            return (Criteria) this;
        }

        public Criteria andRoomTypeIdGreaterThanOrEqualTo(Long value) {
            addCriterion("room_type_id >=", value, "roomTypeId");
            return (Criteria) this;
        }

        public Criteria andRoomTypeIdLessThan(Long value) {
            addCriterion("room_type_id <", value, "roomTypeId");
            return (Criteria) this;
        }

        public Criteria andRoomTypeIdLessThanOrEqualTo(Long value) {
            addCriterion("room_type_id <=", value, "roomTypeId");
            return (Criteria) this;
        }

        public Criteria andRoomTypeIdIn(List<Long> values) {
            addCriterion("room_type_id in", values, "roomTypeId");
            return (Criteria) this;
        }

        public Criteria andRoomTypeIdNotIn(List<Long> values) {
            addCriterion("room_type_id not in", values, "roomTypeId");
            return (Criteria) this;
        }

        public Criteria andRoomTypeIdBetween(Long value1, Long value2) {
            addCriterion("room_type_id between", value1, value2, "roomTypeId");
            return (Criteria) this;
        }

        public Criteria andRoomTypeIdNotBetween(Long value1, Long value2) {
            addCriterion("room_type_id not between", value1, value2, "roomTypeId");
            return (Criteria) this;
        }

        public Criteria andMonTotalNumberIsNull() {
            addCriterion("mon_total_number is null");
            return (Criteria) this;
        }

        public Criteria andMonTotalNumberIsNotNull() {
            addCriterion("mon_total_number is not null");
            return (Criteria) this;
        }

        public Criteria andMonTotalNumberEqualTo(Long value) {
            addCriterion("mon_total_number =", value, "monTotalNumber");
            return (Criteria) this;
        }

        public Criteria andMonTotalNumberNotEqualTo(Long value) {
            addCriterion("mon_total_number <>", value, "monTotalNumber");
            return (Criteria) this;
        }

        public Criteria andMonTotalNumberGreaterThan(Long value) {
            addCriterion("mon_total_number >", value, "monTotalNumber");
            return (Criteria) this;
        }

        public Criteria andMonTotalNumberGreaterThanOrEqualTo(Long value) {
            addCriterion("mon_total_number >=", value, "monTotalNumber");
            return (Criteria) this;
        }

        public Criteria andMonTotalNumberLessThan(Long value) {
            addCriterion("mon_total_number <", value, "monTotalNumber");
            return (Criteria) this;
        }

        public Criteria andMonTotalNumberLessThanOrEqualTo(Long value) {
            addCriterion("mon_total_number <=", value, "monTotalNumber");
            return (Criteria) this;
        }

        public Criteria andMonTotalNumberIn(List<Long> values) {
            addCriterion("mon_total_number in", values, "monTotalNumber");
            return (Criteria) this;
        }

        public Criteria andMonTotalNumberNotIn(List<Long> values) {
            addCriterion("mon_total_number not in", values, "monTotalNumber");
            return (Criteria) this;
        }

        public Criteria andMonTotalNumberBetween(Long value1, Long value2) {
            addCriterion("mon_total_number between", value1, value2, "monTotalNumber");
            return (Criteria) this;
        }

        public Criteria andMonTotalNumberNotBetween(Long value1, Long value2) {
            addCriterion("mon_total_number not between", value1, value2, "monTotalNumber");
            return (Criteria) this;
        }

        public Criteria andTueTotalNumberIsNull() {
            addCriterion("tue_total_number is null");
            return (Criteria) this;
        }

        public Criteria andTueTotalNumberIsNotNull() {
            addCriterion("tue_total_number is not null");
            return (Criteria) this;
        }

        public Criteria andTueTotalNumberEqualTo(Long value) {
            addCriterion("tue_total_number =", value, "tueTotalNumber");
            return (Criteria) this;
        }

        public Criteria andTueTotalNumberNotEqualTo(Long value) {
            addCriterion("tue_total_number <>", value, "tueTotalNumber");
            return (Criteria) this;
        }

        public Criteria andTueTotalNumberGreaterThan(Long value) {
            addCriterion("tue_total_number >", value, "tueTotalNumber");
            return (Criteria) this;
        }

        public Criteria andTueTotalNumberGreaterThanOrEqualTo(Long value) {
            addCriterion("tue_total_number >=", value, "tueTotalNumber");
            return (Criteria) this;
        }

        public Criteria andTueTotalNumberLessThan(Long value) {
            addCriterion("tue_total_number <", value, "tueTotalNumber");
            return (Criteria) this;
        }

        public Criteria andTueTotalNumberLessThanOrEqualTo(Long value) {
            addCriterion("tue_total_number <=", value, "tueTotalNumber");
            return (Criteria) this;
        }

        public Criteria andTueTotalNumberIn(List<Long> values) {
            addCriterion("tue_total_number in", values, "tueTotalNumber");
            return (Criteria) this;
        }

        public Criteria andTueTotalNumberNotIn(List<Long> values) {
            addCriterion("tue_total_number not in", values, "tueTotalNumber");
            return (Criteria) this;
        }

        public Criteria andTueTotalNumberBetween(Long value1, Long value2) {
            addCriterion("tue_total_number between", value1, value2, "tueTotalNumber");
            return (Criteria) this;
        }

        public Criteria andTueTotalNumberNotBetween(Long value1, Long value2) {
            addCriterion("tue_total_number not between", value1, value2, "tueTotalNumber");
            return (Criteria) this;
        }

        public Criteria andWedTotalNumberIsNull() {
            addCriterion("wed_total_number is null");
            return (Criteria) this;
        }

        public Criteria andWedTotalNumberIsNotNull() {
            addCriterion("wed_total_number is not null");
            return (Criteria) this;
        }

        public Criteria andWedTotalNumberEqualTo(Long value) {
            addCriterion("wed_total_number =", value, "wedTotalNumber");
            return (Criteria) this;
        }

        public Criteria andWedTotalNumberNotEqualTo(Long value) {
            addCriterion("wed_total_number <>", value, "wedTotalNumber");
            return (Criteria) this;
        }

        public Criteria andWedTotalNumberGreaterThan(Long value) {
            addCriterion("wed_total_number >", value, "wedTotalNumber");
            return (Criteria) this;
        }

        public Criteria andWedTotalNumberGreaterThanOrEqualTo(Long value) {
            addCriterion("wed_total_number >=", value, "wedTotalNumber");
            return (Criteria) this;
        }

        public Criteria andWedTotalNumberLessThan(Long value) {
            addCriterion("wed_total_number <", value, "wedTotalNumber");
            return (Criteria) this;
        }

        public Criteria andWedTotalNumberLessThanOrEqualTo(Long value) {
            addCriterion("wed_total_number <=", value, "wedTotalNumber");
            return (Criteria) this;
        }

        public Criteria andWedTotalNumberIn(List<Long> values) {
            addCriterion("wed_total_number in", values, "wedTotalNumber");
            return (Criteria) this;
        }

        public Criteria andWedTotalNumberNotIn(List<Long> values) {
            addCriterion("wed_total_number not in", values, "wedTotalNumber");
            return (Criteria) this;
        }

        public Criteria andWedTotalNumberBetween(Long value1, Long value2) {
            addCriterion("wed_total_number between", value1, value2, "wedTotalNumber");
            return (Criteria) this;
        }

        public Criteria andWedTotalNumberNotBetween(Long value1, Long value2) {
            addCriterion("wed_total_number not between", value1, value2, "wedTotalNumber");
            return (Criteria) this;
        }

        public Criteria andThuTotalNumberIsNull() {
            addCriterion("thu_total_number is null");
            return (Criteria) this;
        }

        public Criteria andThuTotalNumberIsNotNull() {
            addCriterion("thu_total_number is not null");
            return (Criteria) this;
        }

        public Criteria andThuTotalNumberEqualTo(Long value) {
            addCriterion("thu_total_number =", value, "thuTotalNumber");
            return (Criteria) this;
        }

        public Criteria andThuTotalNumberNotEqualTo(Long value) {
            addCriterion("thu_total_number <>", value, "thuTotalNumber");
            return (Criteria) this;
        }

        public Criteria andThuTotalNumberGreaterThan(Long value) {
            addCriterion("thu_total_number >", value, "thuTotalNumber");
            return (Criteria) this;
        }

        public Criteria andThuTotalNumberGreaterThanOrEqualTo(Long value) {
            addCriterion("thu_total_number >=", value, "thuTotalNumber");
            return (Criteria) this;
        }

        public Criteria andThuTotalNumberLessThan(Long value) {
            addCriterion("thu_total_number <", value, "thuTotalNumber");
            return (Criteria) this;
        }

        public Criteria andThuTotalNumberLessThanOrEqualTo(Long value) {
            addCriterion("thu_total_number <=", value, "thuTotalNumber");
            return (Criteria) this;
        }

        public Criteria andThuTotalNumberIn(List<Long> values) {
            addCriterion("thu_total_number in", values, "thuTotalNumber");
            return (Criteria) this;
        }

        public Criteria andThuTotalNumberNotIn(List<Long> values) {
            addCriterion("thu_total_number not in", values, "thuTotalNumber");
            return (Criteria) this;
        }

        public Criteria andThuTotalNumberBetween(Long value1, Long value2) {
            addCriterion("thu_total_number between", value1, value2, "thuTotalNumber");
            return (Criteria) this;
        }

        public Criteria andThuTotalNumberNotBetween(Long value1, Long value2) {
            addCriterion("thu_total_number not between", value1, value2, "thuTotalNumber");
            return (Criteria) this;
        }

        public Criteria andFriTotalNumberIsNull() {
            addCriterion("fri_total_number is null");
            return (Criteria) this;
        }

        public Criteria andFriTotalNumberIsNotNull() {
            addCriterion("fri_total_number is not null");
            return (Criteria) this;
        }

        public Criteria andFriTotalNumberEqualTo(Long value) {
            addCriterion("fri_total_number =", value, "friTotalNumber");
            return (Criteria) this;
        }

        public Criteria andFriTotalNumberNotEqualTo(Long value) {
            addCriterion("fri_total_number <>", value, "friTotalNumber");
            return (Criteria) this;
        }

        public Criteria andFriTotalNumberGreaterThan(Long value) {
            addCriterion("fri_total_number >", value, "friTotalNumber");
            return (Criteria) this;
        }

        public Criteria andFriTotalNumberGreaterThanOrEqualTo(Long value) {
            addCriterion("fri_total_number >=", value, "friTotalNumber");
            return (Criteria) this;
        }

        public Criteria andFriTotalNumberLessThan(Long value) {
            addCriterion("fri_total_number <", value, "friTotalNumber");
            return (Criteria) this;
        }

        public Criteria andFriTotalNumberLessThanOrEqualTo(Long value) {
            addCriterion("fri_total_number <=", value, "friTotalNumber");
            return (Criteria) this;
        }

        public Criteria andFriTotalNumberIn(List<Long> values) {
            addCriterion("fri_total_number in", values, "friTotalNumber");
            return (Criteria) this;
        }

        public Criteria andFriTotalNumberNotIn(List<Long> values) {
            addCriterion("fri_total_number not in", values, "friTotalNumber");
            return (Criteria) this;
        }

        public Criteria andFriTotalNumberBetween(Long value1, Long value2) {
            addCriterion("fri_total_number between", value1, value2, "friTotalNumber");
            return (Criteria) this;
        }

        public Criteria andFriTotalNumberNotBetween(Long value1, Long value2) {
            addCriterion("fri_total_number not between", value1, value2, "friTotalNumber");
            return (Criteria) this;
        }

        public Criteria andSatTotalNumberIsNull() {
            addCriterion("sat_total_number is null");
            return (Criteria) this;
        }

        public Criteria andSatTotalNumberIsNotNull() {
            addCriterion("sat_total_number is not null");
            return (Criteria) this;
        }

        public Criteria andSatTotalNumberEqualTo(Long value) {
            addCriterion("sat_total_number =", value, "satTotalNumber");
            return (Criteria) this;
        }

        public Criteria andSatTotalNumberNotEqualTo(Long value) {
            addCriterion("sat_total_number <>", value, "satTotalNumber");
            return (Criteria) this;
        }

        public Criteria andSatTotalNumberGreaterThan(Long value) {
            addCriterion("sat_total_number >", value, "satTotalNumber");
            return (Criteria) this;
        }

        public Criteria andSatTotalNumberGreaterThanOrEqualTo(Long value) {
            addCriterion("sat_total_number >=", value, "satTotalNumber");
            return (Criteria) this;
        }

        public Criteria andSatTotalNumberLessThan(Long value) {
            addCriterion("sat_total_number <", value, "satTotalNumber");
            return (Criteria) this;
        }

        public Criteria andSatTotalNumberLessThanOrEqualTo(Long value) {
            addCriterion("sat_total_number <=", value, "satTotalNumber");
            return (Criteria) this;
        }

        public Criteria andSatTotalNumberIn(List<Long> values) {
            addCriterion("sat_total_number in", values, "satTotalNumber");
            return (Criteria) this;
        }

        public Criteria andSatTotalNumberNotIn(List<Long> values) {
            addCriterion("sat_total_number not in", values, "satTotalNumber");
            return (Criteria) this;
        }

        public Criteria andSatTotalNumberBetween(Long value1, Long value2) {
            addCriterion("sat_total_number between", value1, value2, "satTotalNumber");
            return (Criteria) this;
        }

        public Criteria andSatTotalNumberNotBetween(Long value1, Long value2) {
            addCriterion("sat_total_number not between", value1, value2, "satTotalNumber");
            return (Criteria) this;
        }

        public Criteria andSunTotalNumberIsNull() {
            addCriterion("sun_total_number is null");
            return (Criteria) this;
        }

        public Criteria andSunTotalNumberIsNotNull() {
            addCriterion("sun_total_number is not null");
            return (Criteria) this;
        }

        public Criteria andSunTotalNumberEqualTo(Long value) {
            addCriterion("sun_total_number =", value, "sunTotalNumber");
            return (Criteria) this;
        }

        public Criteria andSunTotalNumberNotEqualTo(Long value) {
            addCriterion("sun_total_number <>", value, "sunTotalNumber");
            return (Criteria) this;
        }

        public Criteria andSunTotalNumberGreaterThan(Long value) {
            addCriterion("sun_total_number >", value, "sunTotalNumber");
            return (Criteria) this;
        }

        public Criteria andSunTotalNumberGreaterThanOrEqualTo(Long value) {
            addCriterion("sun_total_number >=", value, "sunTotalNumber");
            return (Criteria) this;
        }

        public Criteria andSunTotalNumberLessThan(Long value) {
            addCriterion("sun_total_number <", value, "sunTotalNumber");
            return (Criteria) this;
        }

        public Criteria andSunTotalNumberLessThanOrEqualTo(Long value) {
            addCriterion("sun_total_number <=", value, "sunTotalNumber");
            return (Criteria) this;
        }

        public Criteria andSunTotalNumberIn(List<Long> values) {
            addCriterion("sun_total_number in", values, "sunTotalNumber");
            return (Criteria) this;
        }

        public Criteria andSunTotalNumberNotIn(List<Long> values) {
            addCriterion("sun_total_number not in", values, "sunTotalNumber");
            return (Criteria) this;
        }

        public Criteria andSunTotalNumberBetween(Long value1, Long value2) {
            addCriterion("sun_total_number between", value1, value2, "sunTotalNumber");
            return (Criteria) this;
        }

        public Criteria andSunTotalNumberNotBetween(Long value1, Long value2) {
            addCriterion("sun_total_number not between", value1, value2, "sunTotalNumber");
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