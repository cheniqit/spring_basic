package com.mk.taskfactory.biz.cps.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CpsOrderListExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CpsOrderListExample() {
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

        public Criteria andOrderidIsNull() {
            addCriterion("orderId is null");
            return (Criteria) this;
        }

        public Criteria andOrderidIsNotNull() {
            addCriterion("orderId is not null");
            return (Criteria) this;
        }

        public Criteria andOrderidEqualTo(Long value) {
            addCriterion("orderId =", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidNotEqualTo(Long value) {
            addCriterion("orderId <>", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidGreaterThan(Long value) {
            addCriterion("orderId >", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidGreaterThanOrEqualTo(Long value) {
            addCriterion("orderId >=", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidLessThan(Long value) {
            addCriterion("orderId <", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidLessThanOrEqualTo(Long value) {
            addCriterion("orderId <=", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidIn(List<Long> values) {
            addCriterion("orderId in", values, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidNotIn(List<Long> values) {
            addCriterion("orderId not in", values, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidBetween(Long value1, Long value2) {
            addCriterion("orderId between", value1, value2, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidNotBetween(Long value1, Long value2) {
            addCriterion("orderId not between", value1, value2, "orderid");
            return (Criteria) this;
        }

        public Criteria andChanneltypeIsNull() {
            addCriterion("channelType is null");
            return (Criteria) this;
        }

        public Criteria andChanneltypeIsNotNull() {
            addCriterion("channelType is not null");
            return (Criteria) this;
        }

        public Criteria andChanneltypeEqualTo(Integer value) {
            addCriterion("channelType =", value, "channeltype");
            return (Criteria) this;
        }

        public Criteria andChanneltypeNotEqualTo(Integer value) {
            addCriterion("channelType <>", value, "channeltype");
            return (Criteria) this;
        }

        public Criteria andChanneltypeGreaterThan(Integer value) {
            addCriterion("channelType >", value, "channeltype");
            return (Criteria) this;
        }

        public Criteria andChanneltypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("channelType >=", value, "channeltype");
            return (Criteria) this;
        }

        public Criteria andChanneltypeLessThan(Integer value) {
            addCriterion("channelType <", value, "channeltype");
            return (Criteria) this;
        }

        public Criteria andChanneltypeLessThanOrEqualTo(Integer value) {
            addCriterion("channelType <=", value, "channeltype");
            return (Criteria) this;
        }

        public Criteria andChanneltypeIn(List<Integer> values) {
            addCriterion("channelType in", values, "channeltype");
            return (Criteria) this;
        }

        public Criteria andChanneltypeNotIn(List<Integer> values) {
            addCriterion("channelType not in", values, "channeltype");
            return (Criteria) this;
        }

        public Criteria andChanneltypeBetween(Integer value1, Integer value2) {
            addCriterion("channelType between", value1, value2, "channeltype");
            return (Criteria) this;
        }

        public Criteria andChanneltypeNotBetween(Integer value1, Integer value2) {
            addCriterion("channelType not between", value1, value2, "channeltype");
            return (Criteria) this;
        }

        public Criteria andChannelcodeIsNull() {
            addCriterion("channelCode is null");
            return (Criteria) this;
        }

        public Criteria andChannelcodeIsNotNull() {
            addCriterion("channelCode is not null");
            return (Criteria) this;
        }

        public Criteria andChannelcodeEqualTo(String value) {
            addCriterion("channelCode =", value, "channelcode");
            return (Criteria) this;
        }

        public Criteria andChannelcodeNotEqualTo(String value) {
            addCriterion("channelCode <>", value, "channelcode");
            return (Criteria) this;
        }

        public Criteria andChannelcodeGreaterThan(String value) {
            addCriterion("channelCode >", value, "channelcode");
            return (Criteria) this;
        }

        public Criteria andChannelcodeGreaterThanOrEqualTo(String value) {
            addCriterion("channelCode >=", value, "channelcode");
            return (Criteria) this;
        }

        public Criteria andChannelcodeLessThan(String value) {
            addCriterion("channelCode <", value, "channelcode");
            return (Criteria) this;
        }

        public Criteria andChannelcodeLessThanOrEqualTo(String value) {
            addCriterion("channelCode <=", value, "channelcode");
            return (Criteria) this;
        }

        public Criteria andChannelcodeLike(String value) {
            addCriterion("channelCode like", value, "channelcode");
            return (Criteria) this;
        }

        public Criteria andChannelcodeNotLike(String value) {
            addCriterion("channelCode not like", value, "channelcode");
            return (Criteria) this;
        }

        public Criteria andChannelcodeIn(List<String> values) {
            addCriterion("channelCode in", values, "channelcode");
            return (Criteria) this;
        }

        public Criteria andChannelcodeNotIn(List<String> values) {
            addCriterion("channelCode not in", values, "channelcode");
            return (Criteria) this;
        }

        public Criteria andChannelcodeBetween(String value1, String value2) {
            addCriterion("channelCode between", value1, value2, "channelcode");
            return (Criteria) this;
        }

        public Criteria andChannelcodeNotBetween(String value1, String value2) {
            addCriterion("channelCode not between", value1, value2, "channelcode");
            return (Criteria) this;
        }

        public Criteria andChannelnameIsNull() {
            addCriterion("channelName is null");
            return (Criteria) this;
        }

        public Criteria andChannelnameIsNotNull() {
            addCriterion("channelName is not null");
            return (Criteria) this;
        }

        public Criteria andChannelnameEqualTo(String value) {
            addCriterion("channelName =", value, "channelname");
            return (Criteria) this;
        }

        public Criteria andChannelnameNotEqualTo(String value) {
            addCriterion("channelName <>", value, "channelname");
            return (Criteria) this;
        }

        public Criteria andChannelnameGreaterThan(String value) {
            addCriterion("channelName >", value, "channelname");
            return (Criteria) this;
        }

        public Criteria andChannelnameGreaterThanOrEqualTo(String value) {
            addCriterion("channelName >=", value, "channelname");
            return (Criteria) this;
        }

        public Criteria andChannelnameLessThan(String value) {
            addCriterion("channelName <", value, "channelname");
            return (Criteria) this;
        }

        public Criteria andChannelnameLessThanOrEqualTo(String value) {
            addCriterion("channelName <=", value, "channelname");
            return (Criteria) this;
        }

        public Criteria andChannelnameLike(String value) {
            addCriterion("channelName like", value, "channelname");
            return (Criteria) this;
        }

        public Criteria andChannelnameNotLike(String value) {
            addCriterion("channelName not like", value, "channelname");
            return (Criteria) this;
        }

        public Criteria andChannelnameIn(List<String> values) {
            addCriterion("channelName in", values, "channelname");
            return (Criteria) this;
        }

        public Criteria andChannelnameNotIn(List<String> values) {
            addCriterion("channelName not in", values, "channelname");
            return (Criteria) this;
        }

        public Criteria andChannelnameBetween(String value1, String value2) {
            addCriterion("channelName between", value1, value2, "channelname");
            return (Criteria) this;
        }

        public Criteria andChannelnameNotBetween(String value1, String value2) {
            addCriterion("channelName not between", value1, value2, "channelname");
            return (Criteria) this;
        }

        public Criteria andOrderpriceIsNull() {
            addCriterion("orderPrice is null");
            return (Criteria) this;
        }

        public Criteria andOrderpriceIsNotNull() {
            addCriterion("orderPrice is not null");
            return (Criteria) this;
        }

        public Criteria andOrderpriceEqualTo(Long value) {
            addCriterion("orderPrice =", value, "orderprice");
            return (Criteria) this;
        }

        public Criteria andOrderpriceNotEqualTo(Long value) {
            addCriterion("orderPrice <>", value, "orderprice");
            return (Criteria) this;
        }

        public Criteria andOrderpriceGreaterThan(Long value) {
            addCriterion("orderPrice >", value, "orderprice");
            return (Criteria) this;
        }

        public Criteria andOrderpriceGreaterThanOrEqualTo(Long value) {
            addCriterion("orderPrice >=", value, "orderprice");
            return (Criteria) this;
        }

        public Criteria andOrderpriceLessThan(Long value) {
            addCriterion("orderPrice <", value, "orderprice");
            return (Criteria) this;
        }

        public Criteria andOrderpriceLessThanOrEqualTo(Long value) {
            addCriterion("orderPrice <=", value, "orderprice");
            return (Criteria) this;
        }

        public Criteria andOrderpriceIn(List<Long> values) {
            addCriterion("orderPrice in", values, "orderprice");
            return (Criteria) this;
        }

        public Criteria andOrderpriceNotIn(List<Long> values) {
            addCriterion("orderPrice not in", values, "orderprice");
            return (Criteria) this;
        }

        public Criteria andOrderpriceBetween(Long value1, Long value2) {
            addCriterion("orderPrice between", value1, value2, "orderprice");
            return (Criteria) this;
        }

        public Criteria andOrderpriceNotBetween(Long value1, Long value2) {
            addCriterion("orderPrice not between", value1, value2, "orderprice");
            return (Criteria) this;
        }

        public Criteria andSummarydetailidIsNull() {
            addCriterion("summaryDetailId is null");
            return (Criteria) this;
        }

        public Criteria andSummarydetailidIsNotNull() {
            addCriterion("summaryDetailId is not null");
            return (Criteria) this;
        }

        public Criteria andSummarydetailidEqualTo(Long value) {
            addCriterion("summaryDetailId =", value, "summarydetailid");
            return (Criteria) this;
        }

        public Criteria andSummarydetailidNotEqualTo(Long value) {
            addCriterion("summaryDetailId <>", value, "summarydetailid");
            return (Criteria) this;
        }

        public Criteria andSummarydetailidGreaterThan(Long value) {
            addCriterion("summaryDetailId >", value, "summarydetailid");
            return (Criteria) this;
        }

        public Criteria andSummarydetailidGreaterThanOrEqualTo(Long value) {
            addCriterion("summaryDetailId >=", value, "summarydetailid");
            return (Criteria) this;
        }

        public Criteria andSummarydetailidLessThan(Long value) {
            addCriterion("summaryDetailId <", value, "summarydetailid");
            return (Criteria) this;
        }

        public Criteria andSummarydetailidLessThanOrEqualTo(Long value) {
            addCriterion("summaryDetailId <=", value, "summarydetailid");
            return (Criteria) this;
        }

        public Criteria andSummarydetailidIn(List<Long> values) {
            addCriterion("summaryDetailId in", values, "summarydetailid");
            return (Criteria) this;
        }

        public Criteria andSummarydetailidNotIn(List<Long> values) {
            addCriterion("summaryDetailId not in", values, "summarydetailid");
            return (Criteria) this;
        }

        public Criteria andSummarydetailidBetween(Long value1, Long value2) {
            addCriterion("summaryDetailId between", value1, value2, "summarydetailid");
            return (Criteria) this;
        }

        public Criteria andSummarydetailidNotBetween(Long value1, Long value2) {
            addCriterion("summaryDetailId not between", value1, value2, "summarydetailid");
            return (Criteria) this;
        }

        public Criteria andMidIsNull() {
            addCriterion("mid is null");
            return (Criteria) this;
        }

        public Criteria andMidIsNotNull() {
            addCriterion("mid is not null");
            return (Criteria) this;
        }

        public Criteria andMidEqualTo(Long value) {
            addCriterion("mid =", value, "mid");
            return (Criteria) this;
        }

        public Criteria andMidNotEqualTo(Long value) {
            addCriterion("mid <>", value, "mid");
            return (Criteria) this;
        }

        public Criteria andMidGreaterThan(Long value) {
            addCriterion("mid >", value, "mid");
            return (Criteria) this;
        }

        public Criteria andMidGreaterThanOrEqualTo(Long value) {
            addCriterion("mid >=", value, "mid");
            return (Criteria) this;
        }

        public Criteria andMidLessThan(Long value) {
            addCriterion("mid <", value, "mid");
            return (Criteria) this;
        }

        public Criteria andMidLessThanOrEqualTo(Long value) {
            addCriterion("mid <=", value, "mid");
            return (Criteria) this;
        }

        public Criteria andMidIn(List<Long> values) {
            addCriterion("mid in", values, "mid");
            return (Criteria) this;
        }

        public Criteria andMidNotIn(List<Long> values) {
            addCriterion("mid not in", values, "mid");
            return (Criteria) this;
        }

        public Criteria andMidBetween(Long value1, Long value2) {
            addCriterion("mid between", value1, value2, "mid");
            return (Criteria) this;
        }

        public Criteria andMidNotBetween(Long value1, Long value2) {
            addCriterion("mid not between", value1, value2, "mid");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNull() {
            addCriterion("createTime is null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNotNull() {
            addCriterion("createTime is not null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeEqualTo(Date value) {
            addCriterion("createTime =", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotEqualTo(Date value) {
            addCriterion("createTime <>", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThan(Date value) {
            addCriterion("createTime >", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("createTime >=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThan(Date value) {
            addCriterion("createTime <", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThanOrEqualTo(Date value) {
            addCriterion("createTime <=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIn(List<Date> values) {
            addCriterion("createTime in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotIn(List<Date> values) {
            addCriterion("createTime not in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeBetween(Date value1, Date value2) {
            addCriterion("createTime between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotBetween(Date value1, Date value2) {
            addCriterion("createTime not between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andCheckouttimeIsNull() {
            addCriterion("checkOutTime is null");
            return (Criteria) this;
        }

        public Criteria andCheckouttimeIsNotNull() {
            addCriterion("checkOutTime is not null");
            return (Criteria) this;
        }

        public Criteria andCheckouttimeEqualTo(Date value) {
            addCriterion("checkOutTime =", value, "checkouttime");
            return (Criteria) this;
        }

        public Criteria andCheckouttimeNotEqualTo(Date value) {
            addCriterion("checkOutTime <>", value, "checkouttime");
            return (Criteria) this;
        }

        public Criteria andCheckouttimeGreaterThan(Date value) {
            addCriterion("checkOutTime >", value, "checkouttime");
            return (Criteria) this;
        }

        public Criteria andCheckouttimeGreaterThanOrEqualTo(Date value) {
            addCriterion("checkOutTime >=", value, "checkouttime");
            return (Criteria) this;
        }

        public Criteria andCheckouttimeLessThan(Date value) {
            addCriterion("checkOutTime <", value, "checkouttime");
            return (Criteria) this;
        }

        public Criteria andCheckouttimeLessThanOrEqualTo(Date value) {
            addCriterion("checkOutTime <=", value, "checkouttime");
            return (Criteria) this;
        }

        public Criteria andCheckouttimeIn(List<Date> values) {
            addCriterion("checkOutTime in", values, "checkouttime");
            return (Criteria) this;
        }

        public Criteria andCheckouttimeNotIn(List<Date> values) {
            addCriterion("checkOutTime not in", values, "checkouttime");
            return (Criteria) this;
        }

        public Criteria andCheckouttimeBetween(Date value1, Date value2) {
            addCriterion("checkOutTime between", value1, value2, "checkouttime");
            return (Criteria) this;
        }

        public Criteria andCheckouttimeNotBetween(Date value1, Date value2) {
            addCriterion("checkOutTime not between", value1, value2, "checkouttime");
            return (Criteria) this;
        }

        public Criteria andHotelidIsNull() {
            addCriterion("hotelId is null");
            return (Criteria) this;
        }

        public Criteria andHotelidIsNotNull() {
            addCriterion("hotelId is not null");
            return (Criteria) this;
        }

        public Criteria andHotelidEqualTo(Long value) {
            addCriterion("hotelId =", value, "hotelid");
            return (Criteria) this;
        }

        public Criteria andHotelidNotEqualTo(Long value) {
            addCriterion("hotelId <>", value, "hotelid");
            return (Criteria) this;
        }

        public Criteria andHotelidGreaterThan(Long value) {
            addCriterion("hotelId >", value, "hotelid");
            return (Criteria) this;
        }

        public Criteria andHotelidGreaterThanOrEqualTo(Long value) {
            addCriterion("hotelId >=", value, "hotelid");
            return (Criteria) this;
        }

        public Criteria andHotelidLessThan(Long value) {
            addCriterion("hotelId <", value, "hotelid");
            return (Criteria) this;
        }

        public Criteria andHotelidLessThanOrEqualTo(Long value) {
            addCriterion("hotelId <=", value, "hotelid");
            return (Criteria) this;
        }

        public Criteria andHotelidIn(List<Long> values) {
            addCriterion("hotelId in", values, "hotelid");
            return (Criteria) this;
        }

        public Criteria andHotelidNotIn(List<Long> values) {
            addCriterion("hotelId not in", values, "hotelid");
            return (Criteria) this;
        }

        public Criteria andHotelidBetween(Long value1, Long value2) {
            addCriterion("hotelId between", value1, value2, "hotelid");
            return (Criteria) this;
        }

        public Criteria andHotelidNotBetween(Long value1, Long value2) {
            addCriterion("hotelId not between", value1, value2, "hotelid");
            return (Criteria) this;
        }

        public Criteria andIsfirstIsNull() {
            addCriterion("isFirst is null");
            return (Criteria) this;
        }

        public Criteria andIsfirstIsNotNull() {
            addCriterion("isFirst is not null");
            return (Criteria) this;
        }

        public Criteria andIsfirstEqualTo(String value) {
            addCriterion("isFirst =", value, "isfirst");
            return (Criteria) this;
        }

        public Criteria andIsfirstNotEqualTo(String value) {
            addCriterion("isFirst <>", value, "isfirst");
            return (Criteria) this;
        }

        public Criteria andIsfirstGreaterThan(String value) {
            addCriterion("isFirst >", value, "isfirst");
            return (Criteria) this;
        }

        public Criteria andIsfirstGreaterThanOrEqualTo(String value) {
            addCriterion("isFirst >=", value, "isfirst");
            return (Criteria) this;
        }

        public Criteria andIsfirstLessThan(String value) {
            addCriterion("isFirst <", value, "isfirst");
            return (Criteria) this;
        }

        public Criteria andIsfirstLessThanOrEqualTo(String value) {
            addCriterion("isFirst <=", value, "isfirst");
            return (Criteria) this;
        }

        public Criteria andIsfirstLike(String value) {
            addCriterion("isFirst like", value, "isfirst");
            return (Criteria) this;
        }

        public Criteria andIsfirstNotLike(String value) {
            addCriterion("isFirst not like", value, "isfirst");
            return (Criteria) this;
        }

        public Criteria andIsfirstIn(List<String> values) {
            addCriterion("isFirst in", values, "isfirst");
            return (Criteria) this;
        }

        public Criteria andIsfirstNotIn(List<String> values) {
            addCriterion("isFirst not in", values, "isfirst");
            return (Criteria) this;
        }

        public Criteria andIsfirstBetween(String value1, String value2) {
            addCriterion("isFirst between", value1, value2, "isfirst");
            return (Criteria) this;
        }

        public Criteria andIsfirstNotBetween(String value1, String value2) {
            addCriterion("isFirst not between", value1, value2, "isfirst");
            return (Criteria) this;
        }

        public Criteria andIsnewIsNull() {
            addCriterion("isNew is null");
            return (Criteria) this;
        }

        public Criteria andIsnewIsNotNull() {
            addCriterion("isNew is not null");
            return (Criteria) this;
        }

        public Criteria andIsnewEqualTo(String value) {
            addCriterion("isNew =", value, "isnew");
            return (Criteria) this;
        }

        public Criteria andIsnewNotEqualTo(String value) {
            addCriterion("isNew <>", value, "isnew");
            return (Criteria) this;
        }

        public Criteria andIsnewGreaterThan(String value) {
            addCriterion("isNew >", value, "isnew");
            return (Criteria) this;
        }

        public Criteria andIsnewGreaterThanOrEqualTo(String value) {
            addCriterion("isNew >=", value, "isnew");
            return (Criteria) this;
        }

        public Criteria andIsnewLessThan(String value) {
            addCriterion("isNew <", value, "isnew");
            return (Criteria) this;
        }

        public Criteria andIsnewLessThanOrEqualTo(String value) {
            addCriterion("isNew <=", value, "isnew");
            return (Criteria) this;
        }

        public Criteria andIsnewLike(String value) {
            addCriterion("isNew like", value, "isnew");
            return (Criteria) this;
        }

        public Criteria andIsnewNotLike(String value) {
            addCriterion("isNew not like", value, "isnew");
            return (Criteria) this;
        }

        public Criteria andIsnewIn(List<String> values) {
            addCriterion("isNew in", values, "isnew");
            return (Criteria) this;
        }

        public Criteria andIsnewNotIn(List<String> values) {
            addCriterion("isNew not in", values, "isnew");
            return (Criteria) this;
        }

        public Criteria andIsnewBetween(String value1, String value2) {
            addCriterion("isNew between", value1, value2, "isnew");
            return (Criteria) this;
        }

        public Criteria andIsnewNotBetween(String value1, String value2) {
            addCriterion("isNew not between", value1, value2, "isnew");
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