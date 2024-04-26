package com.ysps.ysps.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ysps.common.annotation.Excel;
import com.ysps.common.core.domain.BaseEntity;

/**
 * 教室预约信息对象 ysps_classroom_reservation
 * 
 * @author lins
 * @date 2024-04-23
 */
public class YspsClassroomReservation extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 预约ID */
    private Long reservationId;

    /** 教室ID */
    @Excel(name = "教室ID")
    private Long classroomId;

    /** 预约用户ID */
    @Excel(name = "预约用户ID")
    private Long userId;

    /** 预约用户名 */
    @Excel(name = "预约用户名")
    private String username;

    /** 预约座位号 */
    @Excel(name = "预约座位号")
    private Long seatNumber;

    /** 预约日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "预约日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date reservationDate;

//    /** 预约开始时间 */
//    @JsonFormat(pattern = "yyyy-MM-dd")
//    @Excel(name = "预约开始时间", width = 30, dateFormat = "yyyy-MM-dd")
//    private Date startTime;
//
//    /** 预约结束时间 */
//    @JsonFormat(pattern = "yyyy-MM-dd")
//    @Excel(name = "预约结束时间", width = 30, dateFormat = "yyyy-MM-dd")
//    private Date endTime;
    /** 预约开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "预约开始时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    /** 预约结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "预约结束时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    /** 预约状态 */
    @Excel(name = "预约状态")
    private Long status;

    /** 预约创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "预约创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdAt;

    public void setReservationId(Long reservationId) 
    {
        this.reservationId = reservationId;
    }

    public Long getReservationId() 
    {
        return reservationId;
    }
    public void setClassroomId(Long classroomId) 
    {
        this.classroomId = classroomId;
    }

    public Long getClassroomId() 
    {
        return classroomId;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setUsername(String username) 
    {
        this.username = username;
    }

    public String getUsername() 
    {
        return username;
    }
    public void setSeatNumber(Long seatNumber) 
    {
        this.seatNumber = seatNumber;
    }

    public Long getSeatNumber() 
    {
        return seatNumber;
    }
    public void setReservationDate(Date reservationDate) 
    {
        this.reservationDate = reservationDate;
    }

    public Date getReservationDate() 
    {
        return reservationDate;
    }
    public void setStartTime(Date startTime) 
    {
        this.startTime = startTime;
    }

    public Date getStartTime() 
    {
        return startTime;
    }
    public void setEndTime(Date endTime) 
    {
        this.endTime = endTime;
    }

    public Date getEndTime() 
    {
        return endTime;
    }
    public void setStatus(Long status) 
    {
        this.status = status;
    }

    public Long getStatus() 
    {
        return status;
    }
    public void setCreatedAt(Date createdAt) 
    {
        this.createdAt = createdAt;
    }

    public Date getCreatedAt() 
    {
        return createdAt;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("reservationId", getReservationId())
            .append("classroomId", getClassroomId())
            .append("userId", getUserId())
            .append("username", getUsername())
            .append("seatNumber", getSeatNumber())
            .append("reservationDate", getReservationDate())
            .append("startTime", getStartTime())
            .append("endTime", getEndTime())
            .append("status", getStatus())
            .append("createdAt", getCreatedAt())
            .toString();
    }
}
