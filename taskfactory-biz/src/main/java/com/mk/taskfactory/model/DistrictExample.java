package com.mk.taskfactory.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class DistrictExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DistrictExample() {
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

        public Criteria andCodeIsNull() {
            addCriterion("Code is null");
            return (Criteria) this;
        }

        public Criteria andCodeIsNotNull() {
            addCriterion("Code is not null");
            return (Criteria) this;
        }

        public Criteria andCodeEqualTo(String value) {
            addCriterion("Code =", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotEqualTo(String value) {
            addCriterion("Code <>", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThan(String value) {
            addCriterion("Code >", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThanOrEqualTo(String value) {
            addCriterion("Code >=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThan(String value) {
            addCriterion("Code <", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThanOrEqualTo(String value) {
            addCriterion("Code <=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLike(String value) {
            addCriterion("Code like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotLike(String value) {
            addCriterion("Code not like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeIn(List<String> values) {
            addCriterion("Code in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotIn(List<String> values) {
            addCriterion("Code not in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeBetween(String value1, String value2) {
            addCriterion("Code between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotBetween(String value1, String value2) {
            addCriterion("Code not between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andDisnameIsNull() {
            addCriterion("DisName is null");
            return (Criteria) this;
        }

        public Criteria andDisnameIsNotNull() {
            addCriterion("DisName is not null");
            return (Criteria) this;
        }

        public Criteria andDisnameEqualTo(String value) {
            addCriterion("DisName =", value, "disname");
            return (Criteria) this;
        }

        public Criteria andDisnameNotEqualTo(String value) {
            addCriterion("DisName <>", value, "disname");
            return (Criteria) this;
        }

        public Criteria andDisnameGreaterThan(String value) {
            addCriterion("DisName >", value, "disname");
            return (Criteria) this;
        }

        public Criteria andDisnameGreaterThanOrEqualTo(String value) {
            addCriterion("DisName >=", value, "disname");
            return (Criteria) this;
        }

        public Criteria andDisnameLessThan(String value) {
            addCriterion("DisName <", value, "disname");
            return (Criteria) this;
        }

        public Criteria andDisnameLessThanOrEqualTo(String value) {
            addCriterion("DisName <=", value, "disname");
            return (Criteria) this;
        }

        public Criteria andDisnameLike(String value) {
            addCriterion("DisName like", value, "disname");
            return (Criteria) this;
        }

        public Criteria andDisnameNotLike(String value) {
            addCriterion("DisName not like", value, "disname");
            return (Criteria) this;
        }

        public Criteria andDisnameIn(List<String> values) {
            addCriterion("DisName in", values, "disname");
            return (Criteria) this;
        }

        public Criteria andDisnameNotIn(List<String> values) {
            addCriterion("DisName not in", values, "disname");
            return (Criteria) this;
        }

        public Criteria andDisnameBetween(String value1, String value2) {
            addCriterion("DisName between", value1, value2, "disname");
            return (Criteria) this;
        }

        public Criteria andDisnameNotBetween(String value1, String value2) {
            addCriterion("DisName not between", value1, value2, "disname");
            return (Criteria) this;
        }

        public Criteria andCityidIsNull() {
            addCriterion("CityID is null");
            return (Criteria) this;
        }

        public Criteria andCityidIsNotNull() {
            addCriterion("CityID is not null");
            return (Criteria) this;
        }

        public Criteria andCityidEqualTo(Integer value) {
            addCriterion("CityID =", value, "cityid");
            return (Criteria) this;
        }

        public Criteria andCityidNotEqualTo(Integer value) {
            addCriterion("CityID <>", value, "cityid");
            return (Criteria) this;
        }

        public Criteria andCityidGreaterThan(Integer value) {
            addCriterion("CityID >", value, "cityid");
            return (Criteria) this;
        }

        public Criteria andCityidGreaterThanOrEqualTo(Integer value) {
            addCriterion("CityID >=", value, "cityid");
            return (Criteria) this;
        }

        public Criteria andCityidLessThan(Integer value) {
            addCriterion("CityID <", value, "cityid");
            return (Criteria) this;
        }

        public Criteria andCityidLessThanOrEqualTo(Integer value) {
            addCriterion("CityID <=", value, "cityid");
            return (Criteria) this;
        }

        public Criteria andCityidIn(List<Integer> values) {
            addCriterion("CityID in", values, "cityid");
            return (Criteria) this;
        }

        public Criteria andCityidNotIn(List<Integer> values) {
            addCriterion("CityID not in", values, "cityid");
            return (Criteria) this;
        }

        public Criteria andCityidBetween(Integer value1, Integer value2) {
            addCriterion("CityID between", value1, value2, "cityid");
            return (Criteria) this;
        }

        public Criteria andCityidNotBetween(Integer value1, Integer value2) {
            addCriterion("CityID not between", value1, value2, "cityid");
            return (Criteria) this;
        }

        public Criteria andDissortIsNull() {
            addCriterion("DisSort is null");
            return (Criteria) this;
        }

        public Criteria andDissortIsNotNull() {
            addCriterion("DisSort is not null");
            return (Criteria) this;
        }

        public Criteria andDissortEqualTo(Integer value) {
            addCriterion("DisSort =", value, "dissort");
            return (Criteria) this;
        }

        public Criteria andDissortNotEqualTo(Integer value) {
            addCriterion("DisSort <>", value, "dissort");
            return (Criteria) this;
        }

        public Criteria andDissortGreaterThan(Integer value) {
            addCriterion("DisSort >", value, "dissort");
            return (Criteria) this;
        }

        public Criteria andDissortGreaterThanOrEqualTo(Integer value) {
            addCriterion("DisSort >=", value, "dissort");
            return (Criteria) this;
        }

        public Criteria andDissortLessThan(Integer value) {
            addCriterion("DisSort <", value, "dissort");
            return (Criteria) this;
        }

        public Criteria andDissortLessThanOrEqualTo(Integer value) {
            addCriterion("DisSort <=", value, "dissort");
            return (Criteria) this;
        }

        public Criteria andDissortIn(List<Integer> values) {
            addCriterion("DisSort in", values, "dissort");
            return (Criteria) this;
        }

        public Criteria andDissortNotIn(List<Integer> values) {
            addCriterion("DisSort not in", values, "dissort");
            return (Criteria) this;
        }

        public Criteria andDissortBetween(Integer value1, Integer value2) {
            addCriterion("DisSort between", value1, value2, "dissort");
            return (Criteria) this;
        }

        public Criteria andDissortNotBetween(Integer value1, Integer value2) {
            addCriterion("DisSort not between", value1, value2, "dissort");
            return (Criteria) this;
        }

        public Criteria andLatitudeIsNull() {
            addCriterion("latitude is null");
            return (Criteria) this;
        }

        public Criteria andLatitudeIsNotNull() {
            addCriterion("latitude is not null");
            return (Criteria) this;
        }

        public Criteria andLatitudeEqualTo(BigDecimal value) {
            addCriterion("latitude =", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeNotEqualTo(BigDecimal value) {
            addCriterion("latitude <>", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeGreaterThan(BigDecimal value) {
            addCriterion("latitude >", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("latitude >=", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeLessThan(BigDecimal value) {
            addCriterion("latitude <", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("latitude <=", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeIn(List<BigDecimal> values) {
            addCriterion("latitude in", values, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeNotIn(List<BigDecimal> values) {
            addCriterion("latitude not in", values, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("latitude between", value1, value2, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("latitude not between", value1, value2, "latitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeIsNull() {
            addCriterion("longitude is null");
            return (Criteria) this;
        }

        public Criteria andLongitudeIsNotNull() {
            addCriterion("longitude is not null");
            return (Criteria) this;
        }

        public Criteria andLongitudeEqualTo(BigDecimal value) {
            addCriterion("longitude =", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeNotEqualTo(BigDecimal value) {
            addCriterion("longitude <>", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeGreaterThan(BigDecimal value) {
            addCriterion("longitude >", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("longitude >=", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeLessThan(BigDecimal value) {
            addCriterion("longitude <", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("longitude <=", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeIn(List<BigDecimal> values) {
            addCriterion("longitude in", values, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeNotIn(List<BigDecimal> values) {
            addCriterion("longitude not in", values, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("longitude between", value1, value2, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("longitude not between", value1, value2, "longitude");
            return (Criteria) this;
        }

        public Criteria andUpdateFlagIsNull() {
            addCriterion("update_flag is null");
            return (Criteria) this;
        }

        public Criteria andUpdateFlagIsNotNull() {
            addCriterion("update_flag is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateFlagEqualTo(String value) {
            addCriterion("update_flag =", value, "updateFlag");
            return (Criteria) this;
        }

        public Criteria andUpdateFlagNotEqualTo(String value) {
            addCriterion("update_flag <>", value, "updateFlag");
            return (Criteria) this;
        }

        public Criteria andUpdateFlagGreaterThan(String value) {
            addCriterion("update_flag >", value, "updateFlag");
            return (Criteria) this;
        }

        public Criteria andUpdateFlagGreaterThanOrEqualTo(String value) {
            addCriterion("update_flag >=", value, "updateFlag");
            return (Criteria) this;
        }

        public Criteria andUpdateFlagLessThan(String value) {
            addCriterion("update_flag <", value, "updateFlag");
            return (Criteria) this;
        }

        public Criteria andUpdateFlagLessThanOrEqualTo(String value) {
            addCriterion("update_flag <=", value, "updateFlag");
            return (Criteria) this;
        }

        public Criteria andUpdateFlagLike(String value) {
            addCriterion("update_flag like", value, "updateFlag");
            return (Criteria) this;
        }

        public Criteria andUpdateFlagNotLike(String value) {
            addCriterion("update_flag not like", value, "updateFlag");
            return (Criteria) this;
        }

        public Criteria andUpdateFlagIn(List<String> values) {
            addCriterion("update_flag in", values, "updateFlag");
            return (Criteria) this;
        }

        public Criteria andUpdateFlagNotIn(List<String> values) {
            addCriterion("update_flag not in", values, "updateFlag");
            return (Criteria) this;
        }

        public Criteria andUpdateFlagBetween(String value1, String value2) {
            addCriterion("update_flag between", value1, value2, "updateFlag");
            return (Criteria) this;
        }

        public Criteria andUpdateFlagNotBetween(String value1, String value2) {
            addCriterion("update_flag not between", value1, value2, "updateFlag");
            return (Criteria) this;
        }

        public Criteria andAcronymIsNull() {
            addCriterion("acronym is null");
            return (Criteria) this;
        }

        public Criteria andAcronymIsNotNull() {
            addCriterion("acronym is not null");
            return (Criteria) this;
        }

        public Criteria andAcronymEqualTo(String value) {
            addCriterion("acronym =", value, "acronym");
            return (Criteria) this;
        }

        public Criteria andAcronymNotEqualTo(String value) {
            addCriterion("acronym <>", value, "acronym");
            return (Criteria) this;
        }

        public Criteria andAcronymGreaterThan(String value) {
            addCriterion("acronym >", value, "acronym");
            return (Criteria) this;
        }

        public Criteria andAcronymGreaterThanOrEqualTo(String value) {
            addCriterion("acronym >=", value, "acronym");
            return (Criteria) this;
        }

        public Criteria andAcronymLessThan(String value) {
            addCriterion("acronym <", value, "acronym");
            return (Criteria) this;
        }

        public Criteria andAcronymLessThanOrEqualTo(String value) {
            addCriterion("acronym <=", value, "acronym");
            return (Criteria) this;
        }

        public Criteria andAcronymLike(String value) {
            addCriterion("acronym like", value, "acronym");
            return (Criteria) this;
        }

        public Criteria andAcronymNotLike(String value) {
            addCriterion("acronym not like", value, "acronym");
            return (Criteria) this;
        }

        public Criteria andAcronymIn(List<String> values) {
            addCriterion("acronym in", values, "acronym");
            return (Criteria) this;
        }

        public Criteria andAcronymNotIn(List<String> values) {
            addCriterion("acronym not in", values, "acronym");
            return (Criteria) this;
        }

        public Criteria andAcronymBetween(String value1, String value2) {
            addCriterion("acronym between", value1, value2, "acronym");
            return (Criteria) this;
        }

        public Criteria andAcronymNotBetween(String value1, String value2) {
            addCriterion("acronym not between", value1, value2, "acronym");
            return (Criteria) this;
        }

        public Criteria andPinYinIsNull() {
            addCriterion("pin_yin is null");
            return (Criteria) this;
        }

        public Criteria andPinYinIsNotNull() {
            addCriterion("pin_yin is not null");
            return (Criteria) this;
        }

        public Criteria andPinYinEqualTo(String value) {
            addCriterion("pin_yin =", value, "pinYin");
            return (Criteria) this;
        }

        public Criteria andPinYinNotEqualTo(String value) {
            addCriterion("pin_yin <>", value, "pinYin");
            return (Criteria) this;
        }

        public Criteria andPinYinGreaterThan(String value) {
            addCriterion("pin_yin >", value, "pinYin");
            return (Criteria) this;
        }

        public Criteria andPinYinGreaterThanOrEqualTo(String value) {
            addCriterion("pin_yin >=", value, "pinYin");
            return (Criteria) this;
        }

        public Criteria andPinYinLessThan(String value) {
            addCriterion("pin_yin <", value, "pinYin");
            return (Criteria) this;
        }

        public Criteria andPinYinLessThanOrEqualTo(String value) {
            addCriterion("pin_yin <=", value, "pinYin");
            return (Criteria) this;
        }

        public Criteria andPinYinLike(String value) {
            addCriterion("pin_yin like", value, "pinYin");
            return (Criteria) this;
        }

        public Criteria andPinYinNotLike(String value) {
            addCriterion("pin_yin not like", value, "pinYin");
            return (Criteria) this;
        }

        public Criteria andPinYinIn(List<String> values) {
            addCriterion("pin_yin in", values, "pinYin");
            return (Criteria) this;
        }

        public Criteria andPinYinNotIn(List<String> values) {
            addCriterion("pin_yin not in", values, "pinYin");
            return (Criteria) this;
        }

        public Criteria andPinYinBetween(String value1, String value2) {
            addCriterion("pin_yin between", value1, value2, "pinYin");
            return (Criteria) this;
        }

        public Criteria andPinYinNotBetween(String value1, String value2) {
            addCriterion("pin_yin not between", value1, value2, "pinYin");
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