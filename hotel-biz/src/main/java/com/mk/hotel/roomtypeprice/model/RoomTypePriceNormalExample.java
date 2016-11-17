package com.mk.hotel.roomtypeprice.model;

import com.mk.hotel.common.bean.PageBean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RoomTypePriceNormalExample extends PageBean {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public RoomTypePriceNormalExample() {
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

        public Criteria andMonMarketPriceIsNull() {
            addCriterion("mon_market_price is null");
            return (Criteria) this;
        }

        public Criteria andMonMarketPriceIsNotNull() {
            addCriterion("mon_market_price is not null");
            return (Criteria) this;
        }

        public Criteria andMonMarketPriceEqualTo(BigDecimal value) {
            addCriterion("mon_market_price =", value, "monMarketPrice");
            return (Criteria) this;
        }

        public Criteria andMonMarketPriceNotEqualTo(BigDecimal value) {
            addCriterion("mon_market_price <>", value, "monMarketPrice");
            return (Criteria) this;
        }

        public Criteria andMonMarketPriceGreaterThan(BigDecimal value) {
            addCriterion("mon_market_price >", value, "monMarketPrice");
            return (Criteria) this;
        }

        public Criteria andMonMarketPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("mon_market_price >=", value, "monMarketPrice");
            return (Criteria) this;
        }

        public Criteria andMonMarketPriceLessThan(BigDecimal value) {
            addCriterion("mon_market_price <", value, "monMarketPrice");
            return (Criteria) this;
        }

        public Criteria andMonMarketPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("mon_market_price <=", value, "monMarketPrice");
            return (Criteria) this;
        }

        public Criteria andMonMarketPriceIn(List<BigDecimal> values) {
            addCriterion("mon_market_price in", values, "monMarketPrice");
            return (Criteria) this;
        }

        public Criteria andMonMarketPriceNotIn(List<BigDecimal> values) {
            addCriterion("mon_market_price not in", values, "monMarketPrice");
            return (Criteria) this;
        }

        public Criteria andMonMarketPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("mon_market_price between", value1, value2, "monMarketPrice");
            return (Criteria) this;
        }

        public Criteria andMonMarketPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("mon_market_price not between", value1, value2, "monMarketPrice");
            return (Criteria) this;
        }

        public Criteria andMonSalePriceIsNull() {
            addCriterion("mon_sale_price is null");
            return (Criteria) this;
        }

        public Criteria andMonSalePriceIsNotNull() {
            addCriterion("mon_sale_price is not null");
            return (Criteria) this;
        }

        public Criteria andMonSalePriceEqualTo(BigDecimal value) {
            addCriterion("mon_sale_price =", value, "monSalePrice");
            return (Criteria) this;
        }

        public Criteria andMonSalePriceNotEqualTo(BigDecimal value) {
            addCriterion("mon_sale_price <>", value, "monSalePrice");
            return (Criteria) this;
        }

        public Criteria andMonSalePriceGreaterThan(BigDecimal value) {
            addCriterion("mon_sale_price >", value, "monSalePrice");
            return (Criteria) this;
        }

        public Criteria andMonSalePriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("mon_sale_price >=", value, "monSalePrice");
            return (Criteria) this;
        }

        public Criteria andMonSalePriceLessThan(BigDecimal value) {
            addCriterion("mon_sale_price <", value, "monSalePrice");
            return (Criteria) this;
        }

        public Criteria andMonSalePriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("mon_sale_price <=", value, "monSalePrice");
            return (Criteria) this;
        }

        public Criteria andMonSalePriceIn(List<BigDecimal> values) {
            addCriterion("mon_sale_price in", values, "monSalePrice");
            return (Criteria) this;
        }

        public Criteria andMonSalePriceNotIn(List<BigDecimal> values) {
            addCriterion("mon_sale_price not in", values, "monSalePrice");
            return (Criteria) this;
        }

        public Criteria andMonSalePriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("mon_sale_price between", value1, value2, "monSalePrice");
            return (Criteria) this;
        }

        public Criteria andMonSalePriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("mon_sale_price not between", value1, value2, "monSalePrice");
            return (Criteria) this;
        }

        public Criteria andMonSettlePriceIsNull() {
            addCriterion("mon_settle_price is null");
            return (Criteria) this;
        }

        public Criteria andMonSettlePriceIsNotNull() {
            addCriterion("mon_settle_price is not null");
            return (Criteria) this;
        }

        public Criteria andMonSettlePriceEqualTo(BigDecimal value) {
            addCriterion("mon_settle_price =", value, "monSettlePrice");
            return (Criteria) this;
        }

        public Criteria andMonSettlePriceNotEqualTo(BigDecimal value) {
            addCriterion("mon_settle_price <>", value, "monSettlePrice");
            return (Criteria) this;
        }

        public Criteria andMonSettlePriceGreaterThan(BigDecimal value) {
            addCriterion("mon_settle_price >", value, "monSettlePrice");
            return (Criteria) this;
        }

        public Criteria andMonSettlePriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("mon_settle_price >=", value, "monSettlePrice");
            return (Criteria) this;
        }

        public Criteria andMonSettlePriceLessThan(BigDecimal value) {
            addCriterion("mon_settle_price <", value, "monSettlePrice");
            return (Criteria) this;
        }

        public Criteria andMonSettlePriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("mon_settle_price <=", value, "monSettlePrice");
            return (Criteria) this;
        }

        public Criteria andMonSettlePriceIn(List<BigDecimal> values) {
            addCriterion("mon_settle_price in", values, "monSettlePrice");
            return (Criteria) this;
        }

        public Criteria andMonSettlePriceNotIn(List<BigDecimal> values) {
            addCriterion("mon_settle_price not in", values, "monSettlePrice");
            return (Criteria) this;
        }

        public Criteria andMonSettlePriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("mon_settle_price between", value1, value2, "monSettlePrice");
            return (Criteria) this;
        }

        public Criteria andMonSettlePriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("mon_settle_price not between", value1, value2, "monSettlePrice");
            return (Criteria) this;
        }

        public Criteria andTueMarketPriceIsNull() {
            addCriterion("tue_market_price is null");
            return (Criteria) this;
        }

        public Criteria andTueMarketPriceIsNotNull() {
            addCriterion("tue_market_price is not null");
            return (Criteria) this;
        }

        public Criteria andTueMarketPriceEqualTo(BigDecimal value) {
            addCriterion("tue_market_price =", value, "tueMarketPrice");
            return (Criteria) this;
        }

        public Criteria andTueMarketPriceNotEqualTo(BigDecimal value) {
            addCriterion("tue_market_price <>", value, "tueMarketPrice");
            return (Criteria) this;
        }

        public Criteria andTueMarketPriceGreaterThan(BigDecimal value) {
            addCriterion("tue_market_price >", value, "tueMarketPrice");
            return (Criteria) this;
        }

        public Criteria andTueMarketPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("tue_market_price >=", value, "tueMarketPrice");
            return (Criteria) this;
        }

        public Criteria andTueMarketPriceLessThan(BigDecimal value) {
            addCriterion("tue_market_price <", value, "tueMarketPrice");
            return (Criteria) this;
        }

        public Criteria andTueMarketPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("tue_market_price <=", value, "tueMarketPrice");
            return (Criteria) this;
        }

        public Criteria andTueMarketPriceIn(List<BigDecimal> values) {
            addCriterion("tue_market_price in", values, "tueMarketPrice");
            return (Criteria) this;
        }

        public Criteria andTueMarketPriceNotIn(List<BigDecimal> values) {
            addCriterion("tue_market_price not in", values, "tueMarketPrice");
            return (Criteria) this;
        }

        public Criteria andTueMarketPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("tue_market_price between", value1, value2, "tueMarketPrice");
            return (Criteria) this;
        }

        public Criteria andTueMarketPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("tue_market_price not between", value1, value2, "tueMarketPrice");
            return (Criteria) this;
        }

        public Criteria andTueSalePriceIsNull() {
            addCriterion("tue_sale_price is null");
            return (Criteria) this;
        }

        public Criteria andTueSalePriceIsNotNull() {
            addCriterion("tue_sale_price is not null");
            return (Criteria) this;
        }

        public Criteria andTueSalePriceEqualTo(BigDecimal value) {
            addCriterion("tue_sale_price =", value, "tueSalePrice");
            return (Criteria) this;
        }

        public Criteria andTueSalePriceNotEqualTo(BigDecimal value) {
            addCriterion("tue_sale_price <>", value, "tueSalePrice");
            return (Criteria) this;
        }

        public Criteria andTueSalePriceGreaterThan(BigDecimal value) {
            addCriterion("tue_sale_price >", value, "tueSalePrice");
            return (Criteria) this;
        }

        public Criteria andTueSalePriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("tue_sale_price >=", value, "tueSalePrice");
            return (Criteria) this;
        }

        public Criteria andTueSalePriceLessThan(BigDecimal value) {
            addCriterion("tue_sale_price <", value, "tueSalePrice");
            return (Criteria) this;
        }

        public Criteria andTueSalePriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("tue_sale_price <=", value, "tueSalePrice");
            return (Criteria) this;
        }

        public Criteria andTueSalePriceIn(List<BigDecimal> values) {
            addCriterion("tue_sale_price in", values, "tueSalePrice");
            return (Criteria) this;
        }

        public Criteria andTueSalePriceNotIn(List<BigDecimal> values) {
            addCriterion("tue_sale_price not in", values, "tueSalePrice");
            return (Criteria) this;
        }

        public Criteria andTueSalePriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("tue_sale_price between", value1, value2, "tueSalePrice");
            return (Criteria) this;
        }

        public Criteria andTueSalePriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("tue_sale_price not between", value1, value2, "tueSalePrice");
            return (Criteria) this;
        }

        public Criteria andTueSettlePriceIsNull() {
            addCriterion("tue_settle_price is null");
            return (Criteria) this;
        }

        public Criteria andTueSettlePriceIsNotNull() {
            addCriterion("tue_settle_price is not null");
            return (Criteria) this;
        }

        public Criteria andTueSettlePriceEqualTo(BigDecimal value) {
            addCriterion("tue_settle_price =", value, "tueSettlePrice");
            return (Criteria) this;
        }

        public Criteria andTueSettlePriceNotEqualTo(BigDecimal value) {
            addCriterion("tue_settle_price <>", value, "tueSettlePrice");
            return (Criteria) this;
        }

        public Criteria andTueSettlePriceGreaterThan(BigDecimal value) {
            addCriterion("tue_settle_price >", value, "tueSettlePrice");
            return (Criteria) this;
        }

        public Criteria andTueSettlePriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("tue_settle_price >=", value, "tueSettlePrice");
            return (Criteria) this;
        }

        public Criteria andTueSettlePriceLessThan(BigDecimal value) {
            addCriterion("tue_settle_price <", value, "tueSettlePrice");
            return (Criteria) this;
        }

        public Criteria andTueSettlePriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("tue_settle_price <=", value, "tueSettlePrice");
            return (Criteria) this;
        }

        public Criteria andTueSettlePriceIn(List<BigDecimal> values) {
            addCriterion("tue_settle_price in", values, "tueSettlePrice");
            return (Criteria) this;
        }

        public Criteria andTueSettlePriceNotIn(List<BigDecimal> values) {
            addCriterion("tue_settle_price not in", values, "tueSettlePrice");
            return (Criteria) this;
        }

        public Criteria andTueSettlePriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("tue_settle_price between", value1, value2, "tueSettlePrice");
            return (Criteria) this;
        }

        public Criteria andTueSettlePriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("tue_settle_price not between", value1, value2, "tueSettlePrice");
            return (Criteria) this;
        }

        public Criteria andWedMarketPriceIsNull() {
            addCriterion("wed_market_price is null");
            return (Criteria) this;
        }

        public Criteria andWedMarketPriceIsNotNull() {
            addCriterion("wed_market_price is not null");
            return (Criteria) this;
        }

        public Criteria andWedMarketPriceEqualTo(BigDecimal value) {
            addCriterion("wed_market_price =", value, "wedMarketPrice");
            return (Criteria) this;
        }

        public Criteria andWedMarketPriceNotEqualTo(BigDecimal value) {
            addCriterion("wed_market_price <>", value, "wedMarketPrice");
            return (Criteria) this;
        }

        public Criteria andWedMarketPriceGreaterThan(BigDecimal value) {
            addCriterion("wed_market_price >", value, "wedMarketPrice");
            return (Criteria) this;
        }

        public Criteria andWedMarketPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("wed_market_price >=", value, "wedMarketPrice");
            return (Criteria) this;
        }

        public Criteria andWedMarketPriceLessThan(BigDecimal value) {
            addCriterion("wed_market_price <", value, "wedMarketPrice");
            return (Criteria) this;
        }

        public Criteria andWedMarketPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("wed_market_price <=", value, "wedMarketPrice");
            return (Criteria) this;
        }

        public Criteria andWedMarketPriceIn(List<BigDecimal> values) {
            addCriterion("wed_market_price in", values, "wedMarketPrice");
            return (Criteria) this;
        }

        public Criteria andWedMarketPriceNotIn(List<BigDecimal> values) {
            addCriterion("wed_market_price not in", values, "wedMarketPrice");
            return (Criteria) this;
        }

        public Criteria andWedMarketPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("wed_market_price between", value1, value2, "wedMarketPrice");
            return (Criteria) this;
        }

        public Criteria andWedMarketPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("wed_market_price not between", value1, value2, "wedMarketPrice");
            return (Criteria) this;
        }

        public Criteria andWedSalePriceIsNull() {
            addCriterion("wed_sale_price is null");
            return (Criteria) this;
        }

        public Criteria andWedSalePriceIsNotNull() {
            addCriterion("wed_sale_price is not null");
            return (Criteria) this;
        }

        public Criteria andWedSalePriceEqualTo(BigDecimal value) {
            addCriterion("wed_sale_price =", value, "wedSalePrice");
            return (Criteria) this;
        }

        public Criteria andWedSalePriceNotEqualTo(BigDecimal value) {
            addCriterion("wed_sale_price <>", value, "wedSalePrice");
            return (Criteria) this;
        }

        public Criteria andWedSalePriceGreaterThan(BigDecimal value) {
            addCriterion("wed_sale_price >", value, "wedSalePrice");
            return (Criteria) this;
        }

        public Criteria andWedSalePriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("wed_sale_price >=", value, "wedSalePrice");
            return (Criteria) this;
        }

        public Criteria andWedSalePriceLessThan(BigDecimal value) {
            addCriterion("wed_sale_price <", value, "wedSalePrice");
            return (Criteria) this;
        }

        public Criteria andWedSalePriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("wed_sale_price <=", value, "wedSalePrice");
            return (Criteria) this;
        }

        public Criteria andWedSalePriceIn(List<BigDecimal> values) {
            addCriterion("wed_sale_price in", values, "wedSalePrice");
            return (Criteria) this;
        }

        public Criteria andWedSalePriceNotIn(List<BigDecimal> values) {
            addCriterion("wed_sale_price not in", values, "wedSalePrice");
            return (Criteria) this;
        }

        public Criteria andWedSalePriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("wed_sale_price between", value1, value2, "wedSalePrice");
            return (Criteria) this;
        }

        public Criteria andWedSalePriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("wed_sale_price not between", value1, value2, "wedSalePrice");
            return (Criteria) this;
        }

        public Criteria andWedSettlePriceIsNull() {
            addCriterion("wed_settle_price is null");
            return (Criteria) this;
        }

        public Criteria andWedSettlePriceIsNotNull() {
            addCriterion("wed_settle_price is not null");
            return (Criteria) this;
        }

        public Criteria andWedSettlePriceEqualTo(BigDecimal value) {
            addCriterion("wed_settle_price =", value, "wedSettlePrice");
            return (Criteria) this;
        }

        public Criteria andWedSettlePriceNotEqualTo(BigDecimal value) {
            addCriterion("wed_settle_price <>", value, "wedSettlePrice");
            return (Criteria) this;
        }

        public Criteria andWedSettlePriceGreaterThan(BigDecimal value) {
            addCriterion("wed_settle_price >", value, "wedSettlePrice");
            return (Criteria) this;
        }

        public Criteria andWedSettlePriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("wed_settle_price >=", value, "wedSettlePrice");
            return (Criteria) this;
        }

        public Criteria andWedSettlePriceLessThan(BigDecimal value) {
            addCriterion("wed_settle_price <", value, "wedSettlePrice");
            return (Criteria) this;
        }

        public Criteria andWedSettlePriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("wed_settle_price <=", value, "wedSettlePrice");
            return (Criteria) this;
        }

        public Criteria andWedSettlePriceIn(List<BigDecimal> values) {
            addCriterion("wed_settle_price in", values, "wedSettlePrice");
            return (Criteria) this;
        }

        public Criteria andWedSettlePriceNotIn(List<BigDecimal> values) {
            addCriterion("wed_settle_price not in", values, "wedSettlePrice");
            return (Criteria) this;
        }

        public Criteria andWedSettlePriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("wed_settle_price between", value1, value2, "wedSettlePrice");
            return (Criteria) this;
        }

        public Criteria andWedSettlePriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("wed_settle_price not between", value1, value2, "wedSettlePrice");
            return (Criteria) this;
        }

        public Criteria andThuMarketPriceIsNull() {
            addCriterion("thu_market_price is null");
            return (Criteria) this;
        }

        public Criteria andThuMarketPriceIsNotNull() {
            addCriterion("thu_market_price is not null");
            return (Criteria) this;
        }

        public Criteria andThuMarketPriceEqualTo(BigDecimal value) {
            addCriterion("thu_market_price =", value, "thuMarketPrice");
            return (Criteria) this;
        }

        public Criteria andThuMarketPriceNotEqualTo(BigDecimal value) {
            addCriterion("thu_market_price <>", value, "thuMarketPrice");
            return (Criteria) this;
        }

        public Criteria andThuMarketPriceGreaterThan(BigDecimal value) {
            addCriterion("thu_market_price >", value, "thuMarketPrice");
            return (Criteria) this;
        }

        public Criteria andThuMarketPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("thu_market_price >=", value, "thuMarketPrice");
            return (Criteria) this;
        }

        public Criteria andThuMarketPriceLessThan(BigDecimal value) {
            addCriterion("thu_market_price <", value, "thuMarketPrice");
            return (Criteria) this;
        }

        public Criteria andThuMarketPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("thu_market_price <=", value, "thuMarketPrice");
            return (Criteria) this;
        }

        public Criteria andThuMarketPriceIn(List<BigDecimal> values) {
            addCriterion("thu_market_price in", values, "thuMarketPrice");
            return (Criteria) this;
        }

        public Criteria andThuMarketPriceNotIn(List<BigDecimal> values) {
            addCriterion("thu_market_price not in", values, "thuMarketPrice");
            return (Criteria) this;
        }

        public Criteria andThuMarketPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("thu_market_price between", value1, value2, "thuMarketPrice");
            return (Criteria) this;
        }

        public Criteria andThuMarketPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("thu_market_price not between", value1, value2, "thuMarketPrice");
            return (Criteria) this;
        }

        public Criteria andThuSalePriceIsNull() {
            addCriterion("thu_sale_price is null");
            return (Criteria) this;
        }

        public Criteria andThuSalePriceIsNotNull() {
            addCriterion("thu_sale_price is not null");
            return (Criteria) this;
        }

        public Criteria andThuSalePriceEqualTo(BigDecimal value) {
            addCriterion("thu_sale_price =", value, "thuSalePrice");
            return (Criteria) this;
        }

        public Criteria andThuSalePriceNotEqualTo(BigDecimal value) {
            addCriterion("thu_sale_price <>", value, "thuSalePrice");
            return (Criteria) this;
        }

        public Criteria andThuSalePriceGreaterThan(BigDecimal value) {
            addCriterion("thu_sale_price >", value, "thuSalePrice");
            return (Criteria) this;
        }

        public Criteria andThuSalePriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("thu_sale_price >=", value, "thuSalePrice");
            return (Criteria) this;
        }

        public Criteria andThuSalePriceLessThan(BigDecimal value) {
            addCriterion("thu_sale_price <", value, "thuSalePrice");
            return (Criteria) this;
        }

        public Criteria andThuSalePriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("thu_sale_price <=", value, "thuSalePrice");
            return (Criteria) this;
        }

        public Criteria andThuSalePriceIn(List<BigDecimal> values) {
            addCriterion("thu_sale_price in", values, "thuSalePrice");
            return (Criteria) this;
        }

        public Criteria andThuSalePriceNotIn(List<BigDecimal> values) {
            addCriterion("thu_sale_price not in", values, "thuSalePrice");
            return (Criteria) this;
        }

        public Criteria andThuSalePriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("thu_sale_price between", value1, value2, "thuSalePrice");
            return (Criteria) this;
        }

        public Criteria andThuSalePriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("thu_sale_price not between", value1, value2, "thuSalePrice");
            return (Criteria) this;
        }

        public Criteria andThuSettlePriceIsNull() {
            addCriterion("thu_settle_price is null");
            return (Criteria) this;
        }

        public Criteria andThuSettlePriceIsNotNull() {
            addCriterion("thu_settle_price is not null");
            return (Criteria) this;
        }

        public Criteria andThuSettlePriceEqualTo(BigDecimal value) {
            addCriterion("thu_settle_price =", value, "thuSettlePrice");
            return (Criteria) this;
        }

        public Criteria andThuSettlePriceNotEqualTo(BigDecimal value) {
            addCriterion("thu_settle_price <>", value, "thuSettlePrice");
            return (Criteria) this;
        }

        public Criteria andThuSettlePriceGreaterThan(BigDecimal value) {
            addCriterion("thu_settle_price >", value, "thuSettlePrice");
            return (Criteria) this;
        }

        public Criteria andThuSettlePriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("thu_settle_price >=", value, "thuSettlePrice");
            return (Criteria) this;
        }

        public Criteria andThuSettlePriceLessThan(BigDecimal value) {
            addCriterion("thu_settle_price <", value, "thuSettlePrice");
            return (Criteria) this;
        }

        public Criteria andThuSettlePriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("thu_settle_price <=", value, "thuSettlePrice");
            return (Criteria) this;
        }

        public Criteria andThuSettlePriceIn(List<BigDecimal> values) {
            addCriterion("thu_settle_price in", values, "thuSettlePrice");
            return (Criteria) this;
        }

        public Criteria andThuSettlePriceNotIn(List<BigDecimal> values) {
            addCriterion("thu_settle_price not in", values, "thuSettlePrice");
            return (Criteria) this;
        }

        public Criteria andThuSettlePriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("thu_settle_price between", value1, value2, "thuSettlePrice");
            return (Criteria) this;
        }

        public Criteria andThuSettlePriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("thu_settle_price not between", value1, value2, "thuSettlePrice");
            return (Criteria) this;
        }

        public Criteria andFriMarketPriceIsNull() {
            addCriterion("fri_market_price is null");
            return (Criteria) this;
        }

        public Criteria andFriMarketPriceIsNotNull() {
            addCriterion("fri_market_price is not null");
            return (Criteria) this;
        }

        public Criteria andFriMarketPriceEqualTo(BigDecimal value) {
            addCriterion("fri_market_price =", value, "friMarketPrice");
            return (Criteria) this;
        }

        public Criteria andFriMarketPriceNotEqualTo(BigDecimal value) {
            addCriterion("fri_market_price <>", value, "friMarketPrice");
            return (Criteria) this;
        }

        public Criteria andFriMarketPriceGreaterThan(BigDecimal value) {
            addCriterion("fri_market_price >", value, "friMarketPrice");
            return (Criteria) this;
        }

        public Criteria andFriMarketPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("fri_market_price >=", value, "friMarketPrice");
            return (Criteria) this;
        }

        public Criteria andFriMarketPriceLessThan(BigDecimal value) {
            addCriterion("fri_market_price <", value, "friMarketPrice");
            return (Criteria) this;
        }

        public Criteria andFriMarketPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("fri_market_price <=", value, "friMarketPrice");
            return (Criteria) this;
        }

        public Criteria andFriMarketPriceIn(List<BigDecimal> values) {
            addCriterion("fri_market_price in", values, "friMarketPrice");
            return (Criteria) this;
        }

        public Criteria andFriMarketPriceNotIn(List<BigDecimal> values) {
            addCriterion("fri_market_price not in", values, "friMarketPrice");
            return (Criteria) this;
        }

        public Criteria andFriMarketPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("fri_market_price between", value1, value2, "friMarketPrice");
            return (Criteria) this;
        }

        public Criteria andFriMarketPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("fri_market_price not between", value1, value2, "friMarketPrice");
            return (Criteria) this;
        }

        public Criteria andFriSalePriceIsNull() {
            addCriterion("fri_sale_price is null");
            return (Criteria) this;
        }

        public Criteria andFriSalePriceIsNotNull() {
            addCriterion("fri_sale_price is not null");
            return (Criteria) this;
        }

        public Criteria andFriSalePriceEqualTo(BigDecimal value) {
            addCriterion("fri_sale_price =", value, "friSalePrice");
            return (Criteria) this;
        }

        public Criteria andFriSalePriceNotEqualTo(BigDecimal value) {
            addCriterion("fri_sale_price <>", value, "friSalePrice");
            return (Criteria) this;
        }

        public Criteria andFriSalePriceGreaterThan(BigDecimal value) {
            addCriterion("fri_sale_price >", value, "friSalePrice");
            return (Criteria) this;
        }

        public Criteria andFriSalePriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("fri_sale_price >=", value, "friSalePrice");
            return (Criteria) this;
        }

        public Criteria andFriSalePriceLessThan(BigDecimal value) {
            addCriterion("fri_sale_price <", value, "friSalePrice");
            return (Criteria) this;
        }

        public Criteria andFriSalePriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("fri_sale_price <=", value, "friSalePrice");
            return (Criteria) this;
        }

        public Criteria andFriSalePriceIn(List<BigDecimal> values) {
            addCriterion("fri_sale_price in", values, "friSalePrice");
            return (Criteria) this;
        }

        public Criteria andFriSalePriceNotIn(List<BigDecimal> values) {
            addCriterion("fri_sale_price not in", values, "friSalePrice");
            return (Criteria) this;
        }

        public Criteria andFriSalePriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("fri_sale_price between", value1, value2, "friSalePrice");
            return (Criteria) this;
        }

        public Criteria andFriSalePriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("fri_sale_price not between", value1, value2, "friSalePrice");
            return (Criteria) this;
        }

        public Criteria andFriSettlePriceIsNull() {
            addCriterion("fri_settle_price is null");
            return (Criteria) this;
        }

        public Criteria andFriSettlePriceIsNotNull() {
            addCriterion("fri_settle_price is not null");
            return (Criteria) this;
        }

        public Criteria andFriSettlePriceEqualTo(BigDecimal value) {
            addCriterion("fri_settle_price =", value, "friSettlePrice");
            return (Criteria) this;
        }

        public Criteria andFriSettlePriceNotEqualTo(BigDecimal value) {
            addCriterion("fri_settle_price <>", value, "friSettlePrice");
            return (Criteria) this;
        }

        public Criteria andFriSettlePriceGreaterThan(BigDecimal value) {
            addCriterion("fri_settle_price >", value, "friSettlePrice");
            return (Criteria) this;
        }

        public Criteria andFriSettlePriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("fri_settle_price >=", value, "friSettlePrice");
            return (Criteria) this;
        }

        public Criteria andFriSettlePriceLessThan(BigDecimal value) {
            addCriterion("fri_settle_price <", value, "friSettlePrice");
            return (Criteria) this;
        }

        public Criteria andFriSettlePriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("fri_settle_price <=", value, "friSettlePrice");
            return (Criteria) this;
        }

        public Criteria andFriSettlePriceIn(List<BigDecimal> values) {
            addCriterion("fri_settle_price in", values, "friSettlePrice");
            return (Criteria) this;
        }

        public Criteria andFriSettlePriceNotIn(List<BigDecimal> values) {
            addCriterion("fri_settle_price not in", values, "friSettlePrice");
            return (Criteria) this;
        }

        public Criteria andFriSettlePriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("fri_settle_price between", value1, value2, "friSettlePrice");
            return (Criteria) this;
        }

        public Criteria andFriSettlePriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("fri_settle_price not between", value1, value2, "friSettlePrice");
            return (Criteria) this;
        }

        public Criteria andSatMarketPriceIsNull() {
            addCriterion("sat_market_price is null");
            return (Criteria) this;
        }

        public Criteria andSatMarketPriceIsNotNull() {
            addCriterion("sat_market_price is not null");
            return (Criteria) this;
        }

        public Criteria andSatMarketPriceEqualTo(BigDecimal value) {
            addCriterion("sat_market_price =", value, "satMarketPrice");
            return (Criteria) this;
        }

        public Criteria andSatMarketPriceNotEqualTo(BigDecimal value) {
            addCriterion("sat_market_price <>", value, "satMarketPrice");
            return (Criteria) this;
        }

        public Criteria andSatMarketPriceGreaterThan(BigDecimal value) {
            addCriterion("sat_market_price >", value, "satMarketPrice");
            return (Criteria) this;
        }

        public Criteria andSatMarketPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("sat_market_price >=", value, "satMarketPrice");
            return (Criteria) this;
        }

        public Criteria andSatMarketPriceLessThan(BigDecimal value) {
            addCriterion("sat_market_price <", value, "satMarketPrice");
            return (Criteria) this;
        }

        public Criteria andSatMarketPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("sat_market_price <=", value, "satMarketPrice");
            return (Criteria) this;
        }

        public Criteria andSatMarketPriceIn(List<BigDecimal> values) {
            addCriterion("sat_market_price in", values, "satMarketPrice");
            return (Criteria) this;
        }

        public Criteria andSatMarketPriceNotIn(List<BigDecimal> values) {
            addCriterion("sat_market_price not in", values, "satMarketPrice");
            return (Criteria) this;
        }

        public Criteria andSatMarketPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("sat_market_price between", value1, value2, "satMarketPrice");
            return (Criteria) this;
        }

        public Criteria andSatMarketPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("sat_market_price not between", value1, value2, "satMarketPrice");
            return (Criteria) this;
        }

        public Criteria andSatSalePriceIsNull() {
            addCriterion("sat_sale_price is null");
            return (Criteria) this;
        }

        public Criteria andSatSalePriceIsNotNull() {
            addCriterion("sat_sale_price is not null");
            return (Criteria) this;
        }

        public Criteria andSatSalePriceEqualTo(BigDecimal value) {
            addCriterion("sat_sale_price =", value, "satSalePrice");
            return (Criteria) this;
        }

        public Criteria andSatSalePriceNotEqualTo(BigDecimal value) {
            addCriterion("sat_sale_price <>", value, "satSalePrice");
            return (Criteria) this;
        }

        public Criteria andSatSalePriceGreaterThan(BigDecimal value) {
            addCriterion("sat_sale_price >", value, "satSalePrice");
            return (Criteria) this;
        }

        public Criteria andSatSalePriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("sat_sale_price >=", value, "satSalePrice");
            return (Criteria) this;
        }

        public Criteria andSatSalePriceLessThan(BigDecimal value) {
            addCriterion("sat_sale_price <", value, "satSalePrice");
            return (Criteria) this;
        }

        public Criteria andSatSalePriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("sat_sale_price <=", value, "satSalePrice");
            return (Criteria) this;
        }

        public Criteria andSatSalePriceIn(List<BigDecimal> values) {
            addCriterion("sat_sale_price in", values, "satSalePrice");
            return (Criteria) this;
        }

        public Criteria andSatSalePriceNotIn(List<BigDecimal> values) {
            addCriterion("sat_sale_price not in", values, "satSalePrice");
            return (Criteria) this;
        }

        public Criteria andSatSalePriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("sat_sale_price between", value1, value2, "satSalePrice");
            return (Criteria) this;
        }

        public Criteria andSatSalePriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("sat_sale_price not between", value1, value2, "satSalePrice");
            return (Criteria) this;
        }

        public Criteria andSatSettlePriceIsNull() {
            addCriterion("sat_settle_price is null");
            return (Criteria) this;
        }

        public Criteria andSatSettlePriceIsNotNull() {
            addCriterion("sat_settle_price is not null");
            return (Criteria) this;
        }

        public Criteria andSatSettlePriceEqualTo(BigDecimal value) {
            addCriterion("sat_settle_price =", value, "satSettlePrice");
            return (Criteria) this;
        }

        public Criteria andSatSettlePriceNotEqualTo(BigDecimal value) {
            addCriterion("sat_settle_price <>", value, "satSettlePrice");
            return (Criteria) this;
        }

        public Criteria andSatSettlePriceGreaterThan(BigDecimal value) {
            addCriterion("sat_settle_price >", value, "satSettlePrice");
            return (Criteria) this;
        }

        public Criteria andSatSettlePriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("sat_settle_price >=", value, "satSettlePrice");
            return (Criteria) this;
        }

        public Criteria andSatSettlePriceLessThan(BigDecimal value) {
            addCriterion("sat_settle_price <", value, "satSettlePrice");
            return (Criteria) this;
        }

        public Criteria andSatSettlePriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("sat_settle_price <=", value, "satSettlePrice");
            return (Criteria) this;
        }

        public Criteria andSatSettlePriceIn(List<BigDecimal> values) {
            addCriterion("sat_settle_price in", values, "satSettlePrice");
            return (Criteria) this;
        }

        public Criteria andSatSettlePriceNotIn(List<BigDecimal> values) {
            addCriterion("sat_settle_price not in", values, "satSettlePrice");
            return (Criteria) this;
        }

        public Criteria andSatSettlePriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("sat_settle_price between", value1, value2, "satSettlePrice");
            return (Criteria) this;
        }

        public Criteria andSatSettlePriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("sat_settle_price not between", value1, value2, "satSettlePrice");
            return (Criteria) this;
        }

        public Criteria andSunMarketPriceIsNull() {
            addCriterion("sun_market_price is null");
            return (Criteria) this;
        }

        public Criteria andSunMarketPriceIsNotNull() {
            addCriterion("sun_market_price is not null");
            return (Criteria) this;
        }

        public Criteria andSunMarketPriceEqualTo(BigDecimal value) {
            addCriterion("sun_market_price =", value, "sunMarketPrice");
            return (Criteria) this;
        }

        public Criteria andSunMarketPriceNotEqualTo(BigDecimal value) {
            addCriterion("sun_market_price <>", value, "sunMarketPrice");
            return (Criteria) this;
        }

        public Criteria andSunMarketPriceGreaterThan(BigDecimal value) {
            addCriterion("sun_market_price >", value, "sunMarketPrice");
            return (Criteria) this;
        }

        public Criteria andSunMarketPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("sun_market_price >=", value, "sunMarketPrice");
            return (Criteria) this;
        }

        public Criteria andSunMarketPriceLessThan(BigDecimal value) {
            addCriterion("sun_market_price <", value, "sunMarketPrice");
            return (Criteria) this;
        }

        public Criteria andSunMarketPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("sun_market_price <=", value, "sunMarketPrice");
            return (Criteria) this;
        }

        public Criteria andSunMarketPriceIn(List<BigDecimal> values) {
            addCriterion("sun_market_price in", values, "sunMarketPrice");
            return (Criteria) this;
        }

        public Criteria andSunMarketPriceNotIn(List<BigDecimal> values) {
            addCriterion("sun_market_price not in", values, "sunMarketPrice");
            return (Criteria) this;
        }

        public Criteria andSunMarketPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("sun_market_price between", value1, value2, "sunMarketPrice");
            return (Criteria) this;
        }

        public Criteria andSunMarketPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("sun_market_price not between", value1, value2, "sunMarketPrice");
            return (Criteria) this;
        }

        public Criteria andSunSalePriceIsNull() {
            addCriterion("sun_sale_price is null");
            return (Criteria) this;
        }

        public Criteria andSunSalePriceIsNotNull() {
            addCriterion("sun_sale_price is not null");
            return (Criteria) this;
        }

        public Criteria andSunSalePriceEqualTo(BigDecimal value) {
            addCriterion("sun_sale_price =", value, "sunSalePrice");
            return (Criteria) this;
        }

        public Criteria andSunSalePriceNotEqualTo(BigDecimal value) {
            addCriterion("sun_sale_price <>", value, "sunSalePrice");
            return (Criteria) this;
        }

        public Criteria andSunSalePriceGreaterThan(BigDecimal value) {
            addCriterion("sun_sale_price >", value, "sunSalePrice");
            return (Criteria) this;
        }

        public Criteria andSunSalePriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("sun_sale_price >=", value, "sunSalePrice");
            return (Criteria) this;
        }

        public Criteria andSunSalePriceLessThan(BigDecimal value) {
            addCriterion("sun_sale_price <", value, "sunSalePrice");
            return (Criteria) this;
        }

        public Criteria andSunSalePriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("sun_sale_price <=", value, "sunSalePrice");
            return (Criteria) this;
        }

        public Criteria andSunSalePriceIn(List<BigDecimal> values) {
            addCriterion("sun_sale_price in", values, "sunSalePrice");
            return (Criteria) this;
        }

        public Criteria andSunSalePriceNotIn(List<BigDecimal> values) {
            addCriterion("sun_sale_price not in", values, "sunSalePrice");
            return (Criteria) this;
        }

        public Criteria andSunSalePriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("sun_sale_price between", value1, value2, "sunSalePrice");
            return (Criteria) this;
        }

        public Criteria andSunSalePriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("sun_sale_price not between", value1, value2, "sunSalePrice");
            return (Criteria) this;
        }

        public Criteria andSunSettlePriceIsNull() {
            addCriterion("sun_settle_price is null");
            return (Criteria) this;
        }

        public Criteria andSunSettlePriceIsNotNull() {
            addCriterion("sun_settle_price is not null");
            return (Criteria) this;
        }

        public Criteria andSunSettlePriceEqualTo(BigDecimal value) {
            addCriterion("sun_settle_price =", value, "sunSettlePrice");
            return (Criteria) this;
        }

        public Criteria andSunSettlePriceNotEqualTo(BigDecimal value) {
            addCriterion("sun_settle_price <>", value, "sunSettlePrice");
            return (Criteria) this;
        }

        public Criteria andSunSettlePriceGreaterThan(BigDecimal value) {
            addCriterion("sun_settle_price >", value, "sunSettlePrice");
            return (Criteria) this;
        }

        public Criteria andSunSettlePriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("sun_settle_price >=", value, "sunSettlePrice");
            return (Criteria) this;
        }

        public Criteria andSunSettlePriceLessThan(BigDecimal value) {
            addCriterion("sun_settle_price <", value, "sunSettlePrice");
            return (Criteria) this;
        }

        public Criteria andSunSettlePriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("sun_settle_price <=", value, "sunSettlePrice");
            return (Criteria) this;
        }

        public Criteria andSunSettlePriceIn(List<BigDecimal> values) {
            addCriterion("sun_settle_price in", values, "sunSettlePrice");
            return (Criteria) this;
        }

        public Criteria andSunSettlePriceNotIn(List<BigDecimal> values) {
            addCriterion("sun_settle_price not in", values, "sunSettlePrice");
            return (Criteria) this;
        }

        public Criteria andSunSettlePriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("sun_settle_price between", value1, value2, "sunSettlePrice");
            return (Criteria) this;
        }

        public Criteria andSunSettlePriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("sun_settle_price not between", value1, value2, "sunSettlePrice");
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