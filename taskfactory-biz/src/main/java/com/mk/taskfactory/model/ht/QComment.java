package com.mk.taskfactory.model.ht;

import java.math.BigDecimal;

public class QComment {
    private Long id;

    private Long commentId;

    private Long hotelId;

    private String hotelName;

    private String score;

    private BigDecimal grade;

    private Long goodTotal;

    private Long badTotal;

    private Long mediumTotal;

    private String createTime;

    private String updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName == null ? null : hotelName.trim();
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score == null ? null : score.trim();
    }

    public BigDecimal getGrade() {
        return grade;
    }

    public void setGrade(BigDecimal grade) {
        this.grade = grade;
    }

    public Long getGoodTotal() {
        return goodTotal;
    }

    public void setGoodTotal(Long goodTotal) {
        this.goodTotal = goodTotal;
    }

    public Long getBadTotal() {
        return badTotal;
    }

    public void setBadTotal(Long badTotal) {
        this.badTotal = badTotal;
    }

    public Long getMediumTotal() {
        return mediumTotal;
    }

    public void setMediumTotal(Long mediumTotal) {
        this.mediumTotal = mediumTotal;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}