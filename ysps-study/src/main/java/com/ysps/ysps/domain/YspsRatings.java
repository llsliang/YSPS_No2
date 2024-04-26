package com.ysps.ysps.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ysps.common.annotation.Excel;
import com.ysps.common.core.domain.BaseEntity;

/**
 * 活动评价对象 ysps_ratings
 * 
 * @author lins
 * @date 2024-04-04
 */
public class YspsRatings extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 评价id */
    private Long ratingid;

    /** 被评价的活动ID */
    @Excel(name = "被评价的活动ID")
    private Long eventid;

    /** 进行评价的用户ID */
    @Excel(name = "进行评价的用户ID")
    private Long userid;

    /** 评分 */
    @Excel(name = "评分")
    private BigDecimal rating;

    /** 评论内容 */
    @Excel(name = "评论内容")
    private String comment;

    /** 评价时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "评价时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date ratingtime;

    public void setRatingid(Long ratingid) 
    {
        this.ratingid = ratingid;
    }

    public Long getRatingid() 
    {
        return ratingid;
    }
    public void setEventid(Long eventid) 
    {
        this.eventid = eventid;
    }

    public Long getEventid() 
    {
        return eventid;
    }
    public void setUserid(Long userid) 
    {
        this.userid = userid;
    }

    public Long getUserid() 
    {
        return userid;
    }
    public void setRating(BigDecimal rating) 
    {
        this.rating = rating;
    }

    public BigDecimal getRating() 
    {
        return rating;
    }
    public void setComment(String comment) 
    {
        this.comment = comment;
    }

    public String getComment() 
    {
        return comment;
    }
    public void setRatingtime(Date ratingtime) 
    {
        this.ratingtime = ratingtime;
    }

    public Date getRatingtime() 
    {
        return ratingtime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("ratingid", getRatingid())
            .append("eventid", getEventid())
            .append("userid", getUserid())
            .append("rating", getRating())
            .append("comment", getComment())
            .append("ratingtime", getRatingtime())
            .toString();
    }
}
