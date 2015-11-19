package com.mk.taskfactory.biz.cps.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class CpsOrderSummaryCollectExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CpsOrderSummaryCollectExample() {
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        public Criteria andIdIsNull() {
            addCriterion("ID is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("ID is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("ID not between", value1, value2, "id");
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

        public Criteria andSumorderfirstIsNull() {
            addCriterion("sumOrderFirst is null");
            return (Criteria) this;
        }

        public Criteria andSumorderfirstIsNotNull() {
            addCriterion("sumOrderFirst is not null");
            return (Criteria) this;
        }

        public Criteria andSumorderfirstEqualTo(Integer value) {
            addCriterion("sumOrderFirst =", value, "sumorderfirst");
            return (Criteria) this;
        }

        public Criteria andSumorderfirstNotEqualTo(Integer value) {
            addCriterion("sumOrderFirst <>", value, "sumorderfirst");
            return (Criteria) this;
        }

        public Criteria andSumorderfirstGreaterThan(Integer value) {
            addCriterion("sumOrderFirst >", value, "sumorderfirst");
            return (Criteria) this;
        }

        public Criteria andSumorderfirstGreaterThanOrEqualTo(Integer value) {
            addCriterion("sumOrderFirst >=", value, "sumorderfirst");
            return (Criteria) this;
        }

        public Criteria andSumorderfirstLessThan(Integer value) {
            addCriterion("sumOrderFirst <", value, "sumorderfirst");
            return (Criteria) this;
        }

        public Criteria andSumorderfirstLessThanOrEqualTo(Integer value) {
            addCriterion("sumOrderFirst <=", value, "sumorderfirst");
            return (Criteria) this;
        }

        public Criteria andSumorderfirstIn(List<Integer> values) {
            addCriterion("sumOrderFirst in", values, "sumorderfirst");
            return (Criteria) this;
        }

        public Criteria andSumorderfirstNotIn(List<Integer> values) {
            addCriterion("sumOrderFirst not in", values, "sumorderfirst");
            return (Criteria) this;
        }

        public Criteria andSumorderfirstBetween(Integer value1, Integer value2) {
            addCriterion("sumOrderFirst between", value1, value2, "sumorderfirst");
            return (Criteria) this;
        }

        public Criteria andSumorderfirstNotBetween(Integer value1, Integer value2) {
            addCriterion("sumOrderFirst not between", value1, value2, "sumorderfirst");
            return (Criteria) this;
        }

        public Criteria andSumorderpricefirstIsNull() {
            addCriterion("sumOrderPriceFirst is null");
            return (Criteria) this;
        }

        public Criteria andSumorderpricefirstIsNotNull() {
            addCriterion("sumOrderPriceFirst is not null");
            return (Criteria) this;
        }

        public Criteria andSumorderpricefirstEqualTo(BigDecimal value) {
            addCriterion("sumOrderPriceFirst =", value, "sumorderpricefirst");
            return (Criteria) this;
        }

        public Criteria andSumorderpricefirstNotEqualTo(BigDecimal value) {
            addCriterion("sumOrderPriceFirst <>", value, "sumorderpricefirst");
            return (Criteria) this;
        }

        public Criteria andSumorderpricefirstGreaterThan(BigDecimal value) {
            addCriterion("sumOrderPriceFirst >", value, "sumorderpricefirst");
            return (Criteria) this;
        }

        public Criteria andSumorderpricefirstGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("sumOrderPriceFirst >=", value, "sumorderpricefirst");
            return (Criteria) this;
        }

        public Criteria andSumorderpricefirstLessThan(BigDecimal value) {
            addCriterion("sumOrderPriceFirst <", value, "sumorderpricefirst");
            return (Criteria) this;
        }

        public Criteria andSumorderpricefirstLessThanOrEqualTo(BigDecimal value) {
            addCriterion("sumOrderPriceFirst <=", value, "sumorderpricefirst");
            return (Criteria) this;
        }

        public Criteria andSumorderpricefirstIn(List<BigDecimal> values) {
            addCriterion("sumOrderPriceFirst in", values, "sumorderpricefirst");
            return (Criteria) this;
        }

        public Criteria andSumorderpricefirstNotIn(List<BigDecimal> values) {
            addCriterion("sumOrderPriceFirst not in", values, "sumorderpricefirst");
            return (Criteria) this;
        }

        public Criteria andSumorderpricefirstBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("sumOrderPriceFirst between", value1, value2, "sumorderpricefirst");
            return (Criteria) this;
        }

        public Criteria andSumorderpricefirstNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("sumOrderPriceFirst not between", value1, value2, "sumorderpricefirst");
            return (Criteria) this;
        }

        public Criteria andSumorderIsNull() {
            addCriterion("sumOrder is null");
            return (Criteria) this;
        }

        public Criteria andSumorderIsNotNull() {
            addCriterion("sumOrder is not null");
            return (Criteria) this;
        }

        public Criteria andSumorderEqualTo(Integer value) {
            addCriterion("sumOrder =", value, "sumorder");
            return (Criteria) this;
        }

        public Criteria andSumorderNotEqualTo(Integer value) {
            addCriterion("sumOrder <>", value, "sumorder");
            return (Criteria) this;
        }

        public Criteria andSumorderGreaterThan(Integer value) {
            addCriterion("sumOrder >", value, "sumorder");
            return (Criteria) this;
        }

        public Criteria andSumorderGreaterThanOrEqualTo(Integer value) {
            addCriterion("sumOrder >=", value, "sumorder");
            return (Criteria) this;
        }

        public Criteria andSumorderLessThan(Integer value) {
            addCriterion("sumOrder <", value, "sumorder");
            return (Criteria) this;
        }

        public Criteria andSumorderLessThanOrEqualTo(Integer value) {
            addCriterion("sumOrder <=", value, "sumorder");
            return (Criteria) this;
        }

        public Criteria andSumorderIn(List<Integer> values) {
            addCriterion("sumOrder in", values, "sumorder");
            return (Criteria) this;
        }

        public Criteria andSumorderNotIn(List<Integer> values) {
            addCriterion("sumOrder not in", values, "sumorder");
            return (Criteria) this;
        }

        public Criteria andSumorderBetween(Integer value1, Integer value2) {
            addCriterion("sumOrder between", value1, value2, "sumorder");
            return (Criteria) this;
        }

        public Criteria andSumorderNotBetween(Integer value1, Integer value2) {
            addCriterion("sumOrder not between", value1, value2, "sumorder");
            return (Criteria) this;
        }

        public Criteria andSumorderpriceIsNull() {
            addCriterion("sumOrderPrice is null");
            return (Criteria) this;
        }

        public Criteria andSumorderpriceIsNotNull() {
            addCriterion("sumOrderPrice is not null");
            return (Criteria) this;
        }

        public Criteria andSumorderpriceEqualTo(BigDecimal value) {
            addCriterion("sumOrderPrice =", value, "sumorderprice");
            return (Criteria) this;
        }

        public Criteria andSumorderpriceNotEqualTo(BigDecimal value) {
            addCriterion("sumOrderPrice <>", value, "sumorderprice");
            return (Criteria) this;
        }

        public Criteria andSumorderpriceGreaterThan(BigDecimal value) {
            addCriterion("sumOrderPrice >", value, "sumorderprice");
            return (Criteria) this;
        }

        public Criteria andSumorderpriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("sumOrderPrice >=", value, "sumorderprice");
            return (Criteria) this;
        }

        public Criteria andSumorderpriceLessThan(BigDecimal value) {
            addCriterion("sumOrderPrice <", value, "sumorderprice");
            return (Criteria) this;
        }

        public Criteria andSumorderpriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("sumOrderPrice <=", value, "sumorderprice");
            return (Criteria) this;
        }

        public Criteria andSumorderpriceIn(List<BigDecimal> values) {
            addCriterion("sumOrderPrice in", values, "sumorderprice");
            return (Criteria) this;
        }

        public Criteria andSumorderpriceNotIn(List<BigDecimal> values) {
            addCriterion("sumOrderPrice not in", values, "sumorderprice");
            return (Criteria) this;
        }

        public Criteria andSumorderpriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("sumOrderPrice between", value1, value2, "sumorderprice");
            return (Criteria) this;
        }

        public Criteria andSumorderpriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("sumOrderPrice not between", value1, value2, "sumorderprice");
            return (Criteria) this;
        }

        public Criteria andDeductratefirstIsNull() {
            addCriterion("deductRateFirst is null");
            return (Criteria) this;
        }

        public Criteria andDeductratefirstIsNotNull() {
            addCriterion("deductRateFirst is not null");
            return (Criteria) this;
        }

        public Criteria andDeductratefirstEqualTo(Double value) {
            addCriterion("deductRateFirst =", value, "deductratefirst");
            return (Criteria) this;
        }

        public Criteria andDeductratefirstNotEqualTo(Double value) {
            addCriterion("deductRateFirst <>", value, "deductratefirst");
            return (Criteria) this;
        }

        public Criteria andDeductratefirstGreaterThan(Double value) {
            addCriterion("deductRateFirst >", value, "deductratefirst");
            return (Criteria) this;
        }

        public Criteria andDeductratefirstGreaterThanOrEqualTo(Double value) {
            addCriterion("deductRateFirst >=", value, "deductratefirst");
            return (Criteria) this;
        }

        public Criteria andDeductratefirstLessThan(Double value) {
            addCriterion("deductRateFirst <", value, "deductratefirst");
            return (Criteria) this;
        }

        public Criteria andDeductratefirstLessThanOrEqualTo(Double value) {
            addCriterion("deductRateFirst <=", value, "deductratefirst");
            return (Criteria) this;
        }

        public Criteria andDeductratefirstIn(List<Double> values) {
            addCriterion("deductRateFirst in", values, "deductratefirst");
            return (Criteria) this;
        }

        public Criteria andDeductratefirstNotIn(List<Double> values) {
            addCriterion("deductRateFirst not in", values, "deductratefirst");
            return (Criteria) this;
        }

        public Criteria andDeductratefirstBetween(Double value1, Double value2) {
            addCriterion("deductRateFirst between", value1, value2, "deductratefirst");
            return (Criteria) this;
        }

        public Criteria andDeductratefirstNotBetween(Double value1, Double value2) {
            addCriterion("deductRateFirst not between", value1, value2, "deductratefirst");
            return (Criteria) this;
        }

        public Criteria andCpsratefirstIsNull() {
            addCriterion("cpsRateFirst is null");
            return (Criteria) this;
        }

        public Criteria andCpsratefirstIsNotNull() {
            addCriterion("cpsRateFirst is not null");
            return (Criteria) this;
        }

        public Criteria andCpsratefirstEqualTo(Double value) {
            addCriterion("cpsRateFirst =", value, "cpsratefirst");
            return (Criteria) this;
        }

        public Criteria andCpsratefirstNotEqualTo(Double value) {
            addCriterion("cpsRateFirst <>", value, "cpsratefirst");
            return (Criteria) this;
        }

        public Criteria andCpsratefirstGreaterThan(Double value) {
            addCriterion("cpsRateFirst >", value, "cpsratefirst");
            return (Criteria) this;
        }

        public Criteria andCpsratefirstGreaterThanOrEqualTo(Double value) {
            addCriterion("cpsRateFirst >=", value, "cpsratefirst");
            return (Criteria) this;
        }

        public Criteria andCpsratefirstLessThan(Double value) {
            addCriterion("cpsRateFirst <", value, "cpsratefirst");
            return (Criteria) this;
        }

        public Criteria andCpsratefirstLessThanOrEqualTo(Double value) {
            addCriterion("cpsRateFirst <=", value, "cpsratefirst");
            return (Criteria) this;
        }

        public Criteria andCpsratefirstIn(List<Double> values) {
            addCriterion("cpsRateFirst in", values, "cpsratefirst");
            return (Criteria) this;
        }

        public Criteria andCpsratefirstNotIn(List<Double> values) {
            addCriterion("cpsRateFirst not in", values, "cpsratefirst");
            return (Criteria) this;
        }

        public Criteria andCpsratefirstBetween(Double value1, Double value2) {
            addCriterion("cpsRateFirst between", value1, value2, "cpsratefirst");
            return (Criteria) this;
        }

        public Criteria andCpsratefirstNotBetween(Double value1, Double value2) {
            addCriterion("cpsRateFirst not between", value1, value2, "cpsratefirst");
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

        public Criteria andRuleupdatetimeIsNull() {
            addCriterion("ruleUpdateTime is null");
            return (Criteria) this;
        }

        public Criteria andRuleupdatetimeIsNotNull() {
            addCriterion("ruleUpdateTime is not null");
            return (Criteria) this;
        }

        public Criteria andRuleupdatetimeEqualTo(Date value) {
            addCriterion("ruleUpdateTime =", value, "ruleupdatetime");
            return (Criteria) this;
        }

        public Criteria andRuleupdatetimeNotEqualTo(Date value) {
            addCriterion("ruleUpdateTime <>", value, "ruleupdatetime");
            return (Criteria) this;
        }

        public Criteria andRuleupdatetimeGreaterThan(Date value) {
            addCriterion("ruleUpdateTime >", value, "ruleupdatetime");
            return (Criteria) this;
        }

        public Criteria andRuleupdatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("ruleUpdateTime >=", value, "ruleupdatetime");
            return (Criteria) this;
        }

        public Criteria andRuleupdatetimeLessThan(Date value) {
            addCriterion("ruleUpdateTime <", value, "ruleupdatetime");
            return (Criteria) this;
        }

        public Criteria andRuleupdatetimeLessThanOrEqualTo(Date value) {
            addCriterion("ruleUpdateTime <=", value, "ruleupdatetime");
            return (Criteria) this;
        }

        public Criteria andRuleupdatetimeIn(List<Date> values) {
            addCriterion("ruleUpdateTime in", values, "ruleupdatetime");
            return (Criteria) this;
        }

        public Criteria andRuleupdatetimeNotIn(List<Date> values) {
            addCriterion("ruleUpdateTime not in", values, "ruleupdatetime");
            return (Criteria) this;
        }

        public Criteria andRuleupdatetimeBetween(Date value1, Date value2) {
            addCriterion("ruleUpdateTime between", value1, value2, "ruleupdatetime");
            return (Criteria) this;
        }

        public Criteria andRuleupdatetimeNotBetween(Date value1, Date value2) {
            addCriterion("ruleUpdateTime not between", value1, value2, "ruleupdatetime");
            return (Criteria) this;
        }

        public Criteria andRuleupdatebyIsNull() {
            addCriterion("ruleUpdateBy is null");
            return (Criteria) this;
        }

        public Criteria andRuleupdatebyIsNotNull() {
            addCriterion("ruleUpdateBy is not null");
            return (Criteria) this;
        }

        public Criteria andRuleupdatebyEqualTo(String value) {
            addCriterion("ruleUpdateBy =", value, "ruleupdateby");
            return (Criteria) this;
        }

        public Criteria andRuleupdatebyNotEqualTo(String value) {
            addCriterion("ruleUpdateBy <>", value, "ruleupdateby");
            return (Criteria) this;
        }

        public Criteria andRuleupdatebyGreaterThan(String value) {
            addCriterion("ruleUpdateBy >", value, "ruleupdateby");
            return (Criteria) this;
        }

        public Criteria andRuleupdatebyGreaterThanOrEqualTo(String value) {
            addCriterion("ruleUpdateBy >=", value, "ruleupdateby");
            return (Criteria) this;
        }

        public Criteria andRuleupdatebyLessThan(String value) {
            addCriterion("ruleUpdateBy <", value, "ruleupdateby");
            return (Criteria) this;
        }

        public Criteria andRuleupdatebyLessThanOrEqualTo(String value) {
            addCriterion("ruleUpdateBy <=", value, "ruleupdateby");
            return (Criteria) this;
        }

        public Criteria andRuleupdatebyLike(String value) {
            addCriterion("ruleUpdateBy like", value, "ruleupdateby");
            return (Criteria) this;
        }

        public Criteria andRuleupdatebyNotLike(String value) {
            addCriterion("ruleUpdateBy not like", value, "ruleupdateby");
            return (Criteria) this;
        }

        public Criteria andRuleupdatebyIn(List<String> values) {
            addCriterion("ruleUpdateBy in", values, "ruleupdateby");
            return (Criteria) this;
        }

        public Criteria andRuleupdatebyNotIn(List<String> values) {
            addCriterion("ruleUpdateBy not in", values, "ruleupdateby");
            return (Criteria) this;
        }

        public Criteria andRuleupdatebyBetween(String value1, String value2) {
            addCriterion("ruleUpdateBy between", value1, value2, "ruleupdateby");
            return (Criteria) this;
        }

        public Criteria andRuleupdatebyNotBetween(String value1, String value2) {
            addCriterion("ruleUpdateBy not between", value1, value2, "ruleupdateby");
            return (Criteria) this;
        }

        public Criteria andCpsrateIsNull() {
            addCriterion("cpsRate is null");
            return (Criteria) this;
        }

        public Criteria andCpsrateIsNotNull() {
            addCriterion("cpsRate is not null");
            return (Criteria) this;
        }

        public Criteria andCpsrateEqualTo(Double value) {
            addCriterion("cpsRate =", value, "cpsrate");
            return (Criteria) this;
        }

        public Criteria andCpsrateNotEqualTo(Double value) {
            addCriterion("cpsRate <>", value, "cpsrate");
            return (Criteria) this;
        }

        public Criteria andCpsrateGreaterThan(Double value) {
            addCriterion("cpsRate >", value, "cpsrate");
            return (Criteria) this;
        }

        public Criteria andCpsrateGreaterThanOrEqualTo(Double value) {
            addCriterion("cpsRate >=", value, "cpsrate");
            return (Criteria) this;
        }

        public Criteria andCpsrateLessThan(Double value) {
            addCriterion("cpsRate <", value, "cpsrate");
            return (Criteria) this;
        }

        public Criteria andCpsrateLessThanOrEqualTo(Double value) {
            addCriterion("cpsRate <=", value, "cpsrate");
            return (Criteria) this;
        }

        public Criteria andCpsrateIn(List<Double> values) {
            addCriterion("cpsRate in", values, "cpsrate");
            return (Criteria) this;
        }

        public Criteria andCpsrateNotIn(List<Double> values) {
            addCriterion("cpsRate not in", values, "cpsrate");
            return (Criteria) this;
        }

        public Criteria andCpsrateBetween(Double value1, Double value2) {
            addCriterion("cpsRate between", value1, value2, "cpsrate");
            return (Criteria) this;
        }

        public Criteria andCpsrateNotBetween(Double value1, Double value2) {
            addCriterion("cpsRate not between", value1, value2, "cpsrate");
            return (Criteria) this;
        }

        public Criteria andDeductrateIsNull() {
            addCriterion("deductRate is null");
            return (Criteria) this;
        }

        public Criteria andDeductrateIsNotNull() {
            addCriterion("deductRate is not null");
            return (Criteria) this;
        }

        public Criteria andDeductrateEqualTo(Double value) {
            addCriterion("deductRate =", value, "deductrate");
            return (Criteria) this;
        }

        public Criteria andDeductrateNotEqualTo(Double value) {
            addCriterion("deductRate <>", value, "deductrate");
            return (Criteria) this;
        }

        public Criteria andDeductrateGreaterThan(Double value) {
            addCriterion("deductRate >", value, "deductrate");
            return (Criteria) this;
        }

        public Criteria andDeductrateGreaterThanOrEqualTo(Double value) {
            addCriterion("deductRate >=", value, "deductrate");
            return (Criteria) this;
        }

        public Criteria andDeductrateLessThan(Double value) {
            addCriterion("deductRate <", value, "deductrate");
            return (Criteria) this;
        }

        public Criteria andDeductrateLessThanOrEqualTo(Double value) {
            addCriterion("deductRate <=", value, "deductrate");
            return (Criteria) this;
        }

        public Criteria andDeductrateIn(List<Double> values) {
            addCriterion("deductRate in", values, "deductrate");
            return (Criteria) this;
        }

        public Criteria andDeductrateNotIn(List<Double> values) {
            addCriterion("deductRate not in", values, "deductrate");
            return (Criteria) this;
        }

        public Criteria andDeductrateBetween(Double value1, Double value2) {
            addCriterion("deductRate between", value1, value2, "deductrate");
            return (Criteria) this;
        }

        public Criteria andDeductrateNotBetween(Double value1, Double value2) {
            addCriterion("deductRate not between", value1, value2, "deductrate");
            return (Criteria) this;
        }

        public Criteria andOrderdateIsNull() {
            addCriterion("orderDate is null");
            return (Criteria) this;
        }

        public Criteria andOrderdateIsNotNull() {
            addCriterion("orderDate is not null");
            return (Criteria) this;
        }

        public Criteria andOrderdateEqualTo(Date value) {
            addCriterionForJDBCDate("orderDate =", value, "orderdate");
            return (Criteria) this;
        }

        public Criteria andOrderdateNotEqualTo(Date value) {
            addCriterionForJDBCDate("orderDate <>", value, "orderdate");
            return (Criteria) this;
        }

        public Criteria andOrderdateGreaterThan(Date value) {
            addCriterionForJDBCDate("orderDate >", value, "orderdate");
            return (Criteria) this;
        }

        public Criteria andOrderdateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("orderDate >=", value, "orderdate");
            return (Criteria) this;
        }

        public Criteria andOrderdateLessThan(Date value) {
            addCriterionForJDBCDate("orderDate <", value, "orderdate");
            return (Criteria) this;
        }

        public Criteria andOrderdateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("orderDate <=", value, "orderdate");
            return (Criteria) this;
        }

        public Criteria andOrderdateIn(List<Date> values) {
            addCriterionForJDBCDate("orderDate in", values, "orderdate");
            return (Criteria) this;
        }

        public Criteria andOrderdateNotIn(List<Date> values) {
            addCriterionForJDBCDate("orderDate not in", values, "orderdate");
            return (Criteria) this;
        }

        public Criteria andOrderdateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("orderDate between", value1, value2, "orderdate");
            return (Criteria) this;
        }

        public Criteria andOrderdateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("orderDate not between", value1, value2, "orderdate");
            return (Criteria) this;
        }

        public Criteria andSumorderpriceratefirstIsNull() {
            addCriterion("sumOrderPriceRateFirst is null");
            return (Criteria) this;
        }

        public Criteria andSumorderpriceratefirstIsNotNull() {
            addCriterion("sumOrderPriceRateFirst is not null");
            return (Criteria) this;
        }

        public Criteria andSumorderpriceratefirstEqualTo(BigDecimal value) {
            addCriterion("sumOrderPriceRateFirst =", value, "sumorderpriceratefirst");
            return (Criteria) this;
        }

        public Criteria andSumorderpriceratefirstNotEqualTo(BigDecimal value) {
            addCriterion("sumOrderPriceRateFirst <>", value, "sumorderpriceratefirst");
            return (Criteria) this;
        }

        public Criteria andSumorderpriceratefirstGreaterThan(BigDecimal value) {
            addCriterion("sumOrderPriceRateFirst >", value, "sumorderpriceratefirst");
            return (Criteria) this;
        }

        public Criteria andSumorderpriceratefirstGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("sumOrderPriceRateFirst >=", value, "sumorderpriceratefirst");
            return (Criteria) this;
        }

        public Criteria andSumorderpriceratefirstLessThan(BigDecimal value) {
            addCriterion("sumOrderPriceRateFirst <", value, "sumorderpriceratefirst");
            return (Criteria) this;
        }

        public Criteria andSumorderpriceratefirstLessThanOrEqualTo(BigDecimal value) {
            addCriterion("sumOrderPriceRateFirst <=", value, "sumorderpriceratefirst");
            return (Criteria) this;
        }

        public Criteria andSumorderpriceratefirstIn(List<BigDecimal> values) {
            addCriterion("sumOrderPriceRateFirst in", values, "sumorderpriceratefirst");
            return (Criteria) this;
        }

        public Criteria andSumorderpriceratefirstNotIn(List<BigDecimal> values) {
            addCriterion("sumOrderPriceRateFirst not in", values, "sumorderpriceratefirst");
            return (Criteria) this;
        }

        public Criteria andSumorderpriceratefirstBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("sumOrderPriceRateFirst between", value1, value2, "sumorderpriceratefirst");
            return (Criteria) this;
        }

        public Criteria andSumorderpriceratefirstNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("sumOrderPriceRateFirst not between", value1, value2, "sumorderpriceratefirst");
            return (Criteria) this;
        }

        public Criteria andSumorderpricerateIsNull() {
            addCriterion("sumOrderPriceRate is null");
            return (Criteria) this;
        }

        public Criteria andSumorderpricerateIsNotNull() {
            addCriterion("sumOrderPriceRate is not null");
            return (Criteria) this;
        }

        public Criteria andSumorderpricerateEqualTo(BigDecimal value) {
            addCriterion("sumOrderPriceRate =", value, "sumorderpricerate");
            return (Criteria) this;
        }

        public Criteria andSumorderpricerateNotEqualTo(BigDecimal value) {
            addCriterion("sumOrderPriceRate <>", value, "sumorderpricerate");
            return (Criteria) this;
        }

        public Criteria andSumorderpricerateGreaterThan(BigDecimal value) {
            addCriterion("sumOrderPriceRate >", value, "sumorderpricerate");
            return (Criteria) this;
        }

        public Criteria andSumorderpricerateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("sumOrderPriceRate >=", value, "sumorderpricerate");
            return (Criteria) this;
        }

        public Criteria andSumorderpricerateLessThan(BigDecimal value) {
            addCriterion("sumOrderPriceRate <", value, "sumorderpricerate");
            return (Criteria) this;
        }

        public Criteria andSumorderpricerateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("sumOrderPriceRate <=", value, "sumorderpricerate");
            return (Criteria) this;
        }

        public Criteria andSumorderpricerateIn(List<BigDecimal> values) {
            addCriterion("sumOrderPriceRate in", values, "sumorderpricerate");
            return (Criteria) this;
        }

        public Criteria andSumorderpricerateNotIn(List<BigDecimal> values) {
            addCriterion("sumOrderPriceRate not in", values, "sumorderpricerate");
            return (Criteria) this;
        }

        public Criteria andSumorderpricerateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("sumOrderPriceRate between", value1, value2, "sumorderpricerate");
            return (Criteria) this;
        }

        public Criteria andSumorderpricerateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("sumOrderPriceRate not between", value1, value2, "sumorderpricerate");
            return (Criteria) this;
        }

        public Criteria andTotalpriceIsNull() {
            addCriterion("totalPrice is null");
            return (Criteria) this;
        }

        public Criteria andTotalpriceIsNotNull() {
            addCriterion("totalPrice is not null");
            return (Criteria) this;
        }

        public Criteria andTotalpriceEqualTo(BigDecimal value) {
            addCriterion("totalPrice =", value, "totalprice");
            return (Criteria) this;
        }

        public Criteria andTotalpriceNotEqualTo(BigDecimal value) {
            addCriterion("totalPrice <>", value, "totalprice");
            return (Criteria) this;
        }

        public Criteria andTotalpriceGreaterThan(BigDecimal value) {
            addCriterion("totalPrice >", value, "totalprice");
            return (Criteria) this;
        }

        public Criteria andTotalpriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("totalPrice >=", value, "totalprice");
            return (Criteria) this;
        }

        public Criteria andTotalpriceLessThan(BigDecimal value) {
            addCriterion("totalPrice <", value, "totalprice");
            return (Criteria) this;
        }

        public Criteria andTotalpriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("totalPrice <=", value, "totalprice");
            return (Criteria) this;
        }

        public Criteria andTotalpriceIn(List<BigDecimal> values) {
            addCriterion("totalPrice in", values, "totalprice");
            return (Criteria) this;
        }

        public Criteria andTotalpriceNotIn(List<BigDecimal> values) {
            addCriterion("totalPrice not in", values, "totalprice");
            return (Criteria) this;
        }

        public Criteria andTotalpriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("totalPrice between", value1, value2, "totalprice");
            return (Criteria) this;
        }

        public Criteria andTotalpriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("totalPrice not between", value1, value2, "totalprice");
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