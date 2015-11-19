package com.mk.taskfactory.biz.cps.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class CpsRateConfigExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CpsRateConfigExample() {
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
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andChannelidIsNull() {
            addCriterion("channelId is null");
            return (Criteria) this;
        }

        public Criteria andChannelidIsNotNull() {
            addCriterion("channelId is not null");
            return (Criteria) this;
        }

        public Criteria andChannelidEqualTo(Integer value) {
            addCriterion("channelId =", value, "channelid");
            return (Criteria) this;
        }

        public Criteria andChannelidNotEqualTo(Integer value) {
            addCriterion("channelId <>", value, "channelid");
            return (Criteria) this;
        }

        public Criteria andChannelidGreaterThan(Integer value) {
            addCriterion("channelId >", value, "channelid");
            return (Criteria) this;
        }

        public Criteria andChannelidGreaterThanOrEqualTo(Integer value) {
            addCriterion("channelId >=", value, "channelid");
            return (Criteria) this;
        }

        public Criteria andChannelidLessThan(Integer value) {
            addCriterion("channelId <", value, "channelid");
            return (Criteria) this;
        }

        public Criteria andChannelidLessThanOrEqualTo(Integer value) {
            addCriterion("channelId <=", value, "channelid");
            return (Criteria) this;
        }

        public Criteria andChannelidIn(List<Integer> values) {
            addCriterion("channelId in", values, "channelid");
            return (Criteria) this;
        }

        public Criteria andChannelidNotIn(List<Integer> values) {
            addCriterion("channelId not in", values, "channelid");
            return (Criteria) this;
        }

        public Criteria andChannelidBetween(Integer value1, Integer value2) {
            addCriterion("channelId between", value1, value2, "channelid");
            return (Criteria) this;
        }

        public Criteria andChannelidNotBetween(Integer value1, Integer value2) {
            addCriterion("channelId not between", value1, value2, "channelid");
            return (Criteria) this;
        }

        public Criteria andFirstcpsrateIsNull() {
            addCriterion("firstCpsRate is null");
            return (Criteria) this;
        }

        public Criteria andFirstcpsrateIsNotNull() {
            addCriterion("firstCpsRate is not null");
            return (Criteria) this;
        }

        public Criteria andFirstcpsrateEqualTo(BigDecimal value) {
            addCriterion("firstCpsRate =", value, "firstcpsrate");
            return (Criteria) this;
        }

        public Criteria andFirstcpsrateNotEqualTo(BigDecimal value) {
            addCriterion("firstCpsRate <>", value, "firstcpsrate");
            return (Criteria) this;
        }

        public Criteria andFirstcpsrateGreaterThan(BigDecimal value) {
            addCriterion("firstCpsRate >", value, "firstcpsrate");
            return (Criteria) this;
        }

        public Criteria andFirstcpsrateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("firstCpsRate >=", value, "firstcpsrate");
            return (Criteria) this;
        }

        public Criteria andFirstcpsrateLessThan(BigDecimal value) {
            addCriterion("firstCpsRate <", value, "firstcpsrate");
            return (Criteria) this;
        }

        public Criteria andFirstcpsrateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("firstCpsRate <=", value, "firstcpsrate");
            return (Criteria) this;
        }

        public Criteria andFirstcpsrateIn(List<BigDecimal> values) {
            addCriterion("firstCpsRate in", values, "firstcpsrate");
            return (Criteria) this;
        }

        public Criteria andFirstcpsrateNotIn(List<BigDecimal> values) {
            addCriterion("firstCpsRate not in", values, "firstcpsrate");
            return (Criteria) this;
        }

        public Criteria andFirstcpsrateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("firstCpsRate between", value1, value2, "firstcpsrate");
            return (Criteria) this;
        }

        public Criteria andFirstcpsrateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("firstCpsRate not between", value1, value2, "firstcpsrate");
            return (Criteria) this;
        }

        public Criteria andFirstdeductrateIsNull() {
            addCriterion("firstDeductRate is null");
            return (Criteria) this;
        }

        public Criteria andFirstdeductrateIsNotNull() {
            addCriterion("firstDeductRate is not null");
            return (Criteria) this;
        }

        public Criteria andFirstdeductrateEqualTo(BigDecimal value) {
            addCriterion("firstDeductRate =", value, "firstdeductrate");
            return (Criteria) this;
        }

        public Criteria andFirstdeductrateNotEqualTo(BigDecimal value) {
            addCriterion("firstDeductRate <>", value, "firstdeductrate");
            return (Criteria) this;
        }

        public Criteria andFirstdeductrateGreaterThan(BigDecimal value) {
            addCriterion("firstDeductRate >", value, "firstdeductrate");
            return (Criteria) this;
        }

        public Criteria andFirstdeductrateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("firstDeductRate >=", value, "firstdeductrate");
            return (Criteria) this;
        }

        public Criteria andFirstdeductrateLessThan(BigDecimal value) {
            addCriterion("firstDeductRate <", value, "firstdeductrate");
            return (Criteria) this;
        }

        public Criteria andFirstdeductrateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("firstDeductRate <=", value, "firstdeductrate");
            return (Criteria) this;
        }

        public Criteria andFirstdeductrateIn(List<BigDecimal> values) {
            addCriterion("firstDeductRate in", values, "firstdeductrate");
            return (Criteria) this;
        }

        public Criteria andFirstdeductrateNotIn(List<BigDecimal> values) {
            addCriterion("firstDeductRate not in", values, "firstdeductrate");
            return (Criteria) this;
        }

        public Criteria andFirstdeductrateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("firstDeductRate between", value1, value2, "firstdeductrate");
            return (Criteria) this;
        }

        public Criteria andFirstdeductrateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("firstDeductRate not between", value1, value2, "firstdeductrate");
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

        public Criteria andCpsrateEqualTo(BigDecimal value) {
            addCriterion("cpsRate =", value, "cpsrate");
            return (Criteria) this;
        }

        public Criteria andCpsrateNotEqualTo(BigDecimal value) {
            addCriterion("cpsRate <>", value, "cpsrate");
            return (Criteria) this;
        }

        public Criteria andCpsrateGreaterThan(BigDecimal value) {
            addCriterion("cpsRate >", value, "cpsrate");
            return (Criteria) this;
        }

        public Criteria andCpsrateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("cpsRate >=", value, "cpsrate");
            return (Criteria) this;
        }

        public Criteria andCpsrateLessThan(BigDecimal value) {
            addCriterion("cpsRate <", value, "cpsrate");
            return (Criteria) this;
        }

        public Criteria andCpsrateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("cpsRate <=", value, "cpsrate");
            return (Criteria) this;
        }

        public Criteria andCpsrateIn(List<BigDecimal> values) {
            addCriterion("cpsRate in", values, "cpsrate");
            return (Criteria) this;
        }

        public Criteria andCpsrateNotIn(List<BigDecimal> values) {
            addCriterion("cpsRate not in", values, "cpsrate");
            return (Criteria) this;
        }

        public Criteria andCpsrateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("cpsRate between", value1, value2, "cpsrate");
            return (Criteria) this;
        }

        public Criteria andCpsrateNotBetween(BigDecimal value1, BigDecimal value2) {
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

        public Criteria andDeductrateEqualTo(BigDecimal value) {
            addCriterion("deductRate =", value, "deductrate");
            return (Criteria) this;
        }

        public Criteria andDeductrateNotEqualTo(BigDecimal value) {
            addCriterion("deductRate <>", value, "deductrate");
            return (Criteria) this;
        }

        public Criteria andDeductrateGreaterThan(BigDecimal value) {
            addCriterion("deductRate >", value, "deductrate");
            return (Criteria) this;
        }

        public Criteria andDeductrateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("deductRate >=", value, "deductrate");
            return (Criteria) this;
        }

        public Criteria andDeductrateLessThan(BigDecimal value) {
            addCriterion("deductRate <", value, "deductrate");
            return (Criteria) this;
        }

        public Criteria andDeductrateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("deductRate <=", value, "deductrate");
            return (Criteria) this;
        }

        public Criteria andDeductrateIn(List<BigDecimal> values) {
            addCriterion("deductRate in", values, "deductrate");
            return (Criteria) this;
        }

        public Criteria andDeductrateNotIn(List<BigDecimal> values) {
            addCriterion("deductRate not in", values, "deductrate");
            return (Criteria) this;
        }

        public Criteria andDeductrateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("deductRate between", value1, value2, "deductrate");
            return (Criteria) this;
        }

        public Criteria andDeductrateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("deductRate not between", value1, value2, "deductrate");
            return (Criteria) this;
        }

        public Criteria andPaystartdateIsNull() {
            addCriterion("payStartDate is null");
            return (Criteria) this;
        }

        public Criteria andPaystartdateIsNotNull() {
            addCriterion("payStartDate is not null");
            return (Criteria) this;
        }

        public Criteria andPaystartdateEqualTo(Date value) {
            addCriterionForJDBCDate("payStartDate =", value, "paystartdate");
            return (Criteria) this;
        }

        public Criteria andPaystartdateNotEqualTo(Date value) {
            addCriterionForJDBCDate("payStartDate <>", value, "paystartdate");
            return (Criteria) this;
        }

        public Criteria andPaystartdateGreaterThan(Date value) {
            addCriterionForJDBCDate("payStartDate >", value, "paystartdate");
            return (Criteria) this;
        }

        public Criteria andPaystartdateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("payStartDate >=", value, "paystartdate");
            return (Criteria) this;
        }

        public Criteria andPaystartdateLessThan(Date value) {
            addCriterionForJDBCDate("payStartDate <", value, "paystartdate");
            return (Criteria) this;
        }

        public Criteria andPaystartdateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("payStartDate <=", value, "paystartdate");
            return (Criteria) this;
        }

        public Criteria andPaystartdateIn(List<Date> values) {
            addCriterionForJDBCDate("payStartDate in", values, "paystartdate");
            return (Criteria) this;
        }

        public Criteria andPaystartdateNotIn(List<Date> values) {
            addCriterionForJDBCDate("payStartDate not in", values, "paystartdate");
            return (Criteria) this;
        }

        public Criteria andPaystartdateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("payStartDate between", value1, value2, "paystartdate");
            return (Criteria) this;
        }

        public Criteria andPaystartdateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("payStartDate not between", value1, value2, "paystartdate");
            return (Criteria) this;
        }

        public Criteria andPayenddateIsNull() {
            addCriterion("payEndDate is null");
            return (Criteria) this;
        }

        public Criteria andPayenddateIsNotNull() {
            addCriterion("payEndDate is not null");
            return (Criteria) this;
        }

        public Criteria andPayenddateEqualTo(Date value) {
            addCriterionForJDBCDate("payEndDate =", value, "payenddate");
            return (Criteria) this;
        }

        public Criteria andPayenddateNotEqualTo(Date value) {
            addCriterionForJDBCDate("payEndDate <>", value, "payenddate");
            return (Criteria) this;
        }

        public Criteria andPayenddateGreaterThan(Date value) {
            addCriterionForJDBCDate("payEndDate >", value, "payenddate");
            return (Criteria) this;
        }

        public Criteria andPayenddateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("payEndDate >=", value, "payenddate");
            return (Criteria) this;
        }

        public Criteria andPayenddateLessThan(Date value) {
            addCriterionForJDBCDate("payEndDate <", value, "payenddate");
            return (Criteria) this;
        }

        public Criteria andPayenddateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("payEndDate <=", value, "payenddate");
            return (Criteria) this;
        }

        public Criteria andPayenddateIn(List<Date> values) {
            addCriterionForJDBCDate("payEndDate in", values, "payenddate");
            return (Criteria) this;
        }

        public Criteria andPayenddateNotIn(List<Date> values) {
            addCriterionForJDBCDate("payEndDate not in", values, "payenddate");
            return (Criteria) this;
        }

        public Criteria andPayenddateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("payEndDate between", value1, value2, "payenddate");
            return (Criteria) this;
        }

        public Criteria andPayenddateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("payEndDate not between", value1, value2, "payenddate");
            return (Criteria) this;
        }

        public Criteria andValidIsNull() {
            addCriterion("valid is null");
            return (Criteria) this;
        }

        public Criteria andValidIsNotNull() {
            addCriterion("valid is not null");
            return (Criteria) this;
        }

        public Criteria andValidEqualTo(String value) {
            addCriterion("valid =", value, "valid");
            return (Criteria) this;
        }

        public Criteria andValidNotEqualTo(String value) {
            addCriterion("valid <>", value, "valid");
            return (Criteria) this;
        }

        public Criteria andValidGreaterThan(String value) {
            addCriterion("valid >", value, "valid");
            return (Criteria) this;
        }

        public Criteria andValidGreaterThanOrEqualTo(String value) {
            addCriterion("valid >=", value, "valid");
            return (Criteria) this;
        }

        public Criteria andValidLessThan(String value) {
            addCriterion("valid <", value, "valid");
            return (Criteria) this;
        }

        public Criteria andValidLessThanOrEqualTo(String value) {
            addCriterion("valid <=", value, "valid");
            return (Criteria) this;
        }

        public Criteria andValidLike(String value) {
            addCriterion("valid like", value, "valid");
            return (Criteria) this;
        }

        public Criteria andValidNotLike(String value) {
            addCriterion("valid not like", value, "valid");
            return (Criteria) this;
        }

        public Criteria andValidIn(List<String> values) {
            addCriterion("valid in", values, "valid");
            return (Criteria) this;
        }

        public Criteria andValidNotIn(List<String> values) {
            addCriterion("valid not in", values, "valid");
            return (Criteria) this;
        }

        public Criteria andValidBetween(String value1, String value2) {
            addCriterion("valid between", value1, value2, "valid");
            return (Criteria) this;
        }

        public Criteria andValidNotBetween(String value1, String value2) {
            addCriterion("valid not between", value1, value2, "valid");
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

        public Criteria andCreatebyIsNull() {
            addCriterion("createBy is null");
            return (Criteria) this;
        }

        public Criteria andCreatebyIsNotNull() {
            addCriterion("createBy is not null");
            return (Criteria) this;
        }

        public Criteria andCreatebyEqualTo(String value) {
            addCriterion("createBy =", value, "createby");
            return (Criteria) this;
        }

        public Criteria andCreatebyNotEqualTo(String value) {
            addCriterion("createBy <>", value, "createby");
            return (Criteria) this;
        }

        public Criteria andCreatebyGreaterThan(String value) {
            addCriterion("createBy >", value, "createby");
            return (Criteria) this;
        }

        public Criteria andCreatebyGreaterThanOrEqualTo(String value) {
            addCriterion("createBy >=", value, "createby");
            return (Criteria) this;
        }

        public Criteria andCreatebyLessThan(String value) {
            addCriterion("createBy <", value, "createby");
            return (Criteria) this;
        }

        public Criteria andCreatebyLessThanOrEqualTo(String value) {
            addCriterion("createBy <=", value, "createby");
            return (Criteria) this;
        }

        public Criteria andCreatebyLike(String value) {
            addCriterion("createBy like", value, "createby");
            return (Criteria) this;
        }

        public Criteria andCreatebyNotLike(String value) {
            addCriterion("createBy not like", value, "createby");
            return (Criteria) this;
        }

        public Criteria andCreatebyIn(List<String> values) {
            addCriterion("createBy in", values, "createby");
            return (Criteria) this;
        }

        public Criteria andCreatebyNotIn(List<String> values) {
            addCriterion("createBy not in", values, "createby");
            return (Criteria) this;
        }

        public Criteria andCreatebyBetween(String value1, String value2) {
            addCriterion("createBy between", value1, value2, "createby");
            return (Criteria) this;
        }

        public Criteria andCreatebyNotBetween(String value1, String value2) {
            addCriterion("createBy not between", value1, value2, "createby");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIsNull() {
            addCriterion("updateTime is null");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIsNotNull() {
            addCriterion("updateTime is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeEqualTo(Date value) {
            addCriterion("updateTime =", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotEqualTo(Date value) {
            addCriterion("updateTime <>", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeGreaterThan(Date value) {
            addCriterion("updateTime >", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("updateTime >=", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeLessThan(Date value) {
            addCriterion("updateTime <", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeLessThanOrEqualTo(Date value) {
            addCriterion("updateTime <=", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIn(List<Date> values) {
            addCriterion("updateTime in", values, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotIn(List<Date> values) {
            addCriterion("updateTime not in", values, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeBetween(Date value1, Date value2) {
            addCriterion("updateTime between", value1, value2, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotBetween(Date value1, Date value2) {
            addCriterion("updateTime not between", value1, value2, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatebyIsNull() {
            addCriterion("updateBy is null");
            return (Criteria) this;
        }

        public Criteria andUpdatebyIsNotNull() {
            addCriterion("updateBy is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatebyEqualTo(String value) {
            addCriterion("updateBy =", value, "updateby");
            return (Criteria) this;
        }

        public Criteria andUpdatebyNotEqualTo(String value) {
            addCriterion("updateBy <>", value, "updateby");
            return (Criteria) this;
        }

        public Criteria andUpdatebyGreaterThan(String value) {
            addCriterion("updateBy >", value, "updateby");
            return (Criteria) this;
        }

        public Criteria andUpdatebyGreaterThanOrEqualTo(String value) {
            addCriterion("updateBy >=", value, "updateby");
            return (Criteria) this;
        }

        public Criteria andUpdatebyLessThan(String value) {
            addCriterion("updateBy <", value, "updateby");
            return (Criteria) this;
        }

        public Criteria andUpdatebyLessThanOrEqualTo(String value) {
            addCriterion("updateBy <=", value, "updateby");
            return (Criteria) this;
        }

        public Criteria andUpdatebyLike(String value) {
            addCriterion("updateBy like", value, "updateby");
            return (Criteria) this;
        }

        public Criteria andUpdatebyNotLike(String value) {
            addCriterion("updateBy not like", value, "updateby");
            return (Criteria) this;
        }

        public Criteria andUpdatebyIn(List<String> values) {
            addCriterion("updateBy in", values, "updateby");
            return (Criteria) this;
        }

        public Criteria andUpdatebyNotIn(List<String> values) {
            addCriterion("updateBy not in", values, "updateby");
            return (Criteria) this;
        }

        public Criteria andUpdatebyBetween(String value1, String value2) {
            addCriterion("updateBy between", value1, value2, "updateby");
            return (Criteria) this;
        }

        public Criteria andUpdatebyNotBetween(String value1, String value2) {
            addCriterion("updateBy not between", value1, value2, "updateby");
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