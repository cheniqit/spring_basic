package com.mk.ots.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class LandMarkExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public LandMarkExample() {
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

        public Criteria andLandmarkidIsNull() {
            addCriterion("landmarkid is null");
            return (Criteria) this;
        }

        public Criteria andLandmarkidIsNotNull() {
            addCriterion("landmarkid is not null");
            return (Criteria) this;
        }

        public Criteria andLandmarkidEqualTo(Long value) {
            addCriterion("landmarkid =", value, "landmarkid");
            return (Criteria) this;
        }

        public Criteria andLandmarkidNotEqualTo(Long value) {
            addCriterion("landmarkid <>", value, "landmarkid");
            return (Criteria) this;
        }

        public Criteria andLandmarkidGreaterThan(Long value) {
            addCriterion("landmarkid >", value, "landmarkid");
            return (Criteria) this;
        }

        public Criteria andLandmarkidGreaterThanOrEqualTo(Long value) {
            addCriterion("landmarkid >=", value, "landmarkid");
            return (Criteria) this;
        }

        public Criteria andLandmarkidLessThan(Long value) {
            addCriterion("landmarkid <", value, "landmarkid");
            return (Criteria) this;
        }

        public Criteria andLandmarkidLessThanOrEqualTo(Long value) {
            addCriterion("landmarkid <=", value, "landmarkid");
            return (Criteria) this;
        }

        public Criteria andLandmarkidIn(List<Long> values) {
            addCriterion("landmarkid in", values, "landmarkid");
            return (Criteria) this;
        }

        public Criteria andLandmarkidNotIn(List<Long> values) {
            addCriterion("landmarkid not in", values, "landmarkid");
            return (Criteria) this;
        }

        public Criteria andLandmarkidBetween(Long value1, Long value2) {
            addCriterion("landmarkid between", value1, value2, "landmarkid");
            return (Criteria) this;
        }

        public Criteria andLandmarkidNotBetween(Long value1, Long value2) {
            addCriterion("landmarkid not between", value1, value2, "landmarkid");
            return (Criteria) this;
        }

        public Criteria andLandmarknameIsNull() {
            addCriterion("landmarkname is null");
            return (Criteria) this;
        }

        public Criteria andLandmarknameIsNotNull() {
            addCriterion("landmarkname is not null");
            return (Criteria) this;
        }

        public Criteria andLandmarknameEqualTo(String value) {
            addCriterion("landmarkname =", value, "landmarkname");
            return (Criteria) this;
        }

        public Criteria andLandmarknameNotEqualTo(String value) {
            addCriterion("landmarkname <>", value, "landmarkname");
            return (Criteria) this;
        }

        public Criteria andLandmarknameGreaterThan(String value) {
            addCriterion("landmarkname >", value, "landmarkname");
            return (Criteria) this;
        }

        public Criteria andLandmarknameGreaterThanOrEqualTo(String value) {
            addCriterion("landmarkname >=", value, "landmarkname");
            return (Criteria) this;
        }

        public Criteria andLandmarknameLessThan(String value) {
            addCriterion("landmarkname <", value, "landmarkname");
            return (Criteria) this;
        }

        public Criteria andLandmarknameLessThanOrEqualTo(String value) {
            addCriterion("landmarkname <=", value, "landmarkname");
            return (Criteria) this;
        }

        public Criteria andLandmarknameLike(String value) {
            addCriterion("landmarkname like", value, "landmarkname");
            return (Criteria) this;
        }

        public Criteria andLandmarknameNotLike(String value) {
            addCriterion("landmarkname not like", value, "landmarkname");
            return (Criteria) this;
        }

        public Criteria andLandmarknameIn(List<String> values) {
            addCriterion("landmarkname in", values, "landmarkname");
            return (Criteria) this;
        }

        public Criteria andLandmarknameNotIn(List<String> values) {
            addCriterion("landmarkname not in", values, "landmarkname");
            return (Criteria) this;
        }

        public Criteria andLandmarknameBetween(String value1, String value2) {
            addCriterion("landmarkname between", value1, value2, "landmarkname");
            return (Criteria) this;
        }

        public Criteria andLandmarknameNotBetween(String value1, String value2) {
            addCriterion("landmarkname not between", value1, value2, "landmarkname");
            return (Criteria) this;
        }

        public Criteria andPinyinIsNull() {
            addCriterion("pinyin is null");
            return (Criteria) this;
        }

        public Criteria andPinyinIsNotNull() {
            addCriterion("pinyin is not null");
            return (Criteria) this;
        }

        public Criteria andPinyinEqualTo(String value) {
            addCriterion("pinyin =", value, "pinyin");
            return (Criteria) this;
        }

        public Criteria andPinyinNotEqualTo(String value) {
            addCriterion("pinyin <>", value, "pinyin");
            return (Criteria) this;
        }

        public Criteria andPinyinGreaterThan(String value) {
            addCriterion("pinyin >", value, "pinyin");
            return (Criteria) this;
        }

        public Criteria andPinyinGreaterThanOrEqualTo(String value) {
            addCriterion("pinyin >=", value, "pinyin");
            return (Criteria) this;
        }

        public Criteria andPinyinLessThan(String value) {
            addCriterion("pinyin <", value, "pinyin");
            return (Criteria) this;
        }

        public Criteria andPinyinLessThanOrEqualTo(String value) {
            addCriterion("pinyin <=", value, "pinyin");
            return (Criteria) this;
        }

        public Criteria andPinyinLike(String value) {
            addCriterion("pinyin like", value, "pinyin");
            return (Criteria) this;
        }

        public Criteria andPinyinNotLike(String value) {
            addCriterion("pinyin not like", value, "pinyin");
            return (Criteria) this;
        }

        public Criteria andPinyinIn(List<String> values) {
            addCriterion("pinyin in", values, "pinyin");
            return (Criteria) this;
        }

        public Criteria andPinyinNotIn(List<String> values) {
            addCriterion("pinyin not in", values, "pinyin");
            return (Criteria) this;
        }

        public Criteria andPinyinBetween(String value1, String value2) {
            addCriterion("pinyin between", value1, value2, "pinyin");
            return (Criteria) this;
        }

        public Criteria andPinyinNotBetween(String value1, String value2) {
            addCriterion("pinyin not between", value1, value2, "pinyin");
            return (Criteria) this;
        }

        public Criteria andLtypeIsNull() {
            addCriterion("ltype is null");
            return (Criteria) this;
        }

        public Criteria andLtypeIsNotNull() {
            addCriterion("ltype is not null");
            return (Criteria) this;
        }

        public Criteria andLtypeEqualTo(Integer value) {
            addCriterion("ltype =", value, "ltype");
            return (Criteria) this;
        }

        public Criteria andLtypeNotEqualTo(Integer value) {
            addCriterion("ltype <>", value, "ltype");
            return (Criteria) this;
        }

        public Criteria andLtypeGreaterThan(Integer value) {
            addCriterion("ltype >", value, "ltype");
            return (Criteria) this;
        }

        public Criteria andLtypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("ltype >=", value, "ltype");
            return (Criteria) this;
        }

        public Criteria andLtypeLessThan(Integer value) {
            addCriterion("ltype <", value, "ltype");
            return (Criteria) this;
        }

        public Criteria andLtypeLessThanOrEqualTo(Integer value) {
            addCriterion("ltype <=", value, "ltype");
            return (Criteria) this;
        }

        public Criteria andLtypeIn(List<Integer> values) {
            addCriterion("ltype in", values, "ltype");
            return (Criteria) this;
        }

        public Criteria andLtypeNotIn(List<Integer> values) {
            addCriterion("ltype not in", values, "ltype");
            return (Criteria) this;
        }

        public Criteria andLtypeBetween(Integer value1, Integer value2) {
            addCriterion("ltype between", value1, value2, "ltype");
            return (Criteria) this;
        }

        public Criteria andLtypeNotBetween(Integer value1, Integer value2) {
            addCriterion("ltype not between", value1, value2, "ltype");
            return (Criteria) this;
        }

        public Criteria andLatIsNull() {
            addCriterion("lat is null");
            return (Criteria) this;
        }

        public Criteria andLatIsNotNull() {
            addCriterion("lat is not null");
            return (Criteria) this;
        }

        public Criteria andLatEqualTo(BigDecimal value) {
            addCriterion("lat =", value, "lat");
            return (Criteria) this;
        }

        public Criteria andLatNotEqualTo(BigDecimal value) {
            addCriterion("lat <>", value, "lat");
            return (Criteria) this;
        }

        public Criteria andLatGreaterThan(BigDecimal value) {
            addCriterion("lat >", value, "lat");
            return (Criteria) this;
        }

        public Criteria andLatGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("lat >=", value, "lat");
            return (Criteria) this;
        }

        public Criteria andLatLessThan(BigDecimal value) {
            addCriterion("lat <", value, "lat");
            return (Criteria) this;
        }

        public Criteria andLatLessThanOrEqualTo(BigDecimal value) {
            addCriterion("lat <=", value, "lat");
            return (Criteria) this;
        }

        public Criteria andLatIn(List<BigDecimal> values) {
            addCriterion("lat in", values, "lat");
            return (Criteria) this;
        }

        public Criteria andLatNotIn(List<BigDecimal> values) {
            addCriterion("lat not in", values, "lat");
            return (Criteria) this;
        }

        public Criteria andLatBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("lat between", value1, value2, "lat");
            return (Criteria) this;
        }

        public Criteria andLatNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("lat not between", value1, value2, "lat");
            return (Criteria) this;
        }

        public Criteria andLngIsNull() {
            addCriterion("lng is null");
            return (Criteria) this;
        }

        public Criteria andLngIsNotNull() {
            addCriterion("lng is not null");
            return (Criteria) this;
        }

        public Criteria andLngEqualTo(BigDecimal value) {
            addCriterion("lng =", value, "lng");
            return (Criteria) this;
        }

        public Criteria andLngNotEqualTo(BigDecimal value) {
            addCriterion("lng <>", value, "lng");
            return (Criteria) this;
        }

        public Criteria andLngGreaterThan(BigDecimal value) {
            addCriterion("lng >", value, "lng");
            return (Criteria) this;
        }

        public Criteria andLngGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("lng >=", value, "lng");
            return (Criteria) this;
        }

        public Criteria andLngLessThan(BigDecimal value) {
            addCriterion("lng <", value, "lng");
            return (Criteria) this;
        }

        public Criteria andLngLessThanOrEqualTo(BigDecimal value) {
            addCriterion("lng <=", value, "lng");
            return (Criteria) this;
        }

        public Criteria andLngIn(List<BigDecimal> values) {
            addCriterion("lng in", values, "lng");
            return (Criteria) this;
        }

        public Criteria andLngNotIn(List<BigDecimal> values) {
            addCriterion("lng not in", values, "lng");
            return (Criteria) this;
        }

        public Criteria andLngBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("lng between", value1, value2, "lng");
            return (Criteria) this;
        }

        public Criteria andLngNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("lng not between", value1, value2, "lng");
            return (Criteria) this;
        }

        public Criteria andCitycodeIsNull() {
            addCriterion("citycode is null");
            return (Criteria) this;
        }

        public Criteria andCitycodeIsNotNull() {
            addCriterion("citycode is not null");
            return (Criteria) this;
        }

        public Criteria andCitycodeEqualTo(String value) {
            addCriterion("citycode =", value, "citycode");
            return (Criteria) this;
        }

        public Criteria andCitycodeNotEqualTo(String value) {
            addCriterion("citycode <>", value, "citycode");
            return (Criteria) this;
        }

        public Criteria andCitycodeGreaterThan(String value) {
            addCriterion("citycode >", value, "citycode");
            return (Criteria) this;
        }

        public Criteria andCitycodeGreaterThanOrEqualTo(String value) {
            addCriterion("citycode >=", value, "citycode");
            return (Criteria) this;
        }

        public Criteria andCitycodeLessThan(String value) {
            addCriterion("citycode <", value, "citycode");
            return (Criteria) this;
        }

        public Criteria andCitycodeLessThanOrEqualTo(String value) {
            addCriterion("citycode <=", value, "citycode");
            return (Criteria) this;
        }

        public Criteria andCitycodeLike(String value) {
            addCriterion("citycode like", value, "citycode");
            return (Criteria) this;
        }

        public Criteria andCitycodeNotLike(String value) {
            addCriterion("citycode not like", value, "citycode");
            return (Criteria) this;
        }

        public Criteria andCitycodeIn(List<String> values) {
            addCriterion("citycode in", values, "citycode");
            return (Criteria) this;
        }

        public Criteria andCitycodeNotIn(List<String> values) {
            addCriterion("citycode not in", values, "citycode");
            return (Criteria) this;
        }

        public Criteria andCitycodeBetween(String value1, String value2) {
            addCriterion("citycode between", value1, value2, "citycode");
            return (Criteria) this;
        }

        public Criteria andCitycodeNotBetween(String value1, String value2) {
            addCriterion("citycode not between", value1, value2, "citycode");
            return (Criteria) this;
        }

        public Criteria andDiscodeIsNull() {
            addCriterion("discode is null");
            return (Criteria) this;
        }

        public Criteria andDiscodeIsNotNull() {
            addCriterion("discode is not null");
            return (Criteria) this;
        }

        public Criteria andDiscodeEqualTo(String value) {
            addCriterion("discode =", value, "discode");
            return (Criteria) this;
        }

        public Criteria andDiscodeNotEqualTo(String value) {
            addCriterion("discode <>", value, "discode");
            return (Criteria) this;
        }

        public Criteria andDiscodeGreaterThan(String value) {
            addCriterion("discode >", value, "discode");
            return (Criteria) this;
        }

        public Criteria andDiscodeGreaterThanOrEqualTo(String value) {
            addCriterion("discode >=", value, "discode");
            return (Criteria) this;
        }

        public Criteria andDiscodeLessThan(String value) {
            addCriterion("discode <", value, "discode");
            return (Criteria) this;
        }

        public Criteria andDiscodeLessThanOrEqualTo(String value) {
            addCriterion("discode <=", value, "discode");
            return (Criteria) this;
        }

        public Criteria andDiscodeLike(String value) {
            addCriterion("discode like", value, "discode");
            return (Criteria) this;
        }

        public Criteria andDiscodeNotLike(String value) {
            addCriterion("discode not like", value, "discode");
            return (Criteria) this;
        }

        public Criteria andDiscodeIn(List<String> values) {
            addCriterion("discode in", values, "discode");
            return (Criteria) this;
        }

        public Criteria andDiscodeNotIn(List<String> values) {
            addCriterion("discode not in", values, "discode");
            return (Criteria) this;
        }

        public Criteria andDiscodeBetween(String value1, String value2) {
            addCriterion("discode between", value1, value2, "discode");
            return (Criteria) this;
        }

        public Criteria andDiscodeNotBetween(String value1, String value2) {
            addCriterion("discode not between", value1, value2, "discode");
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

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
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