package com.ysps.ysps.domain;

import java.util.List;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ysps.common.annotation.Excel;
import com.ysps.common.core.domain.BaseEntity;

/**
 * 活动信息对象 ysps_events
 * 
 * @author lins
 * @date 2024-04-04
 */
public class YspsEvents extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 活动id */
    private Long eventid;

    /** 发布者ID */
    @Excel(name = "发布者ID")
    private Long publisherid;

    /** 活动名称 */
    @Excel(name = "活动名称")
    private String eventname;

    /** 活动时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "活动时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date eventtime;

    /** 活动地点 */
    @Excel(name = "活动地点")
    private String eventlocation;

    /** 活动类型 */
    @Excel(name = "活动类型")
    private String eventtype;

    /** 活动描述 */
    @Excel(name = "活动描述")
    private String description;

    /** 记录活动参与者的信息，包括报名时间和签到状态信息 */
    private List<YspsParticipants> yspsParticipantsList;

    public void setEventid(Long eventid) 
    {
        this.eventid = eventid;
    }

    public Long getEventid() 
    {
        return eventid;
    }
    public void setPublisherid(Long publisherid) 
    {
        this.publisherid = publisherid;
    }

    public Long getPublisherid() 
    {
        return publisherid;
    }
    public void setEventname(String eventname) 
    {
        this.eventname = eventname;
    }

    public String getEventname() 
    {
        return eventname;
    }
    public void setEventtime(Date eventtime) 
    {
        this.eventtime = eventtime;
    }

    public Date getEventtime() 
    {
        return eventtime;
    }
    public void setEventlocation(String eventlocation) 
    {
        this.eventlocation = eventlocation;
    }

    public String getEventlocation() 
    {
        return eventlocation;
    }
    public void setEventtype(String eventtype) 
    {
        this.eventtype = eventtype;
    }

    public String getEventtype() 
    {
        return eventtype;
    }
    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }

    public List<YspsParticipants> getYspsParticipantsList()
    {
        return yspsParticipantsList;
    }

    public void setYspsParticipantsList(List<YspsParticipants> yspsParticipantsList)
    {
        this.yspsParticipantsList = yspsParticipantsList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("eventid", getEventid())
            .append("publisherid", getPublisherid())
            .append("eventname", getEventname())
            .append("eventtime", getEventtime())
            .append("eventlocation", getEventlocation())
            .append("eventtype", getEventtype())
            .append("description", getDescription())
            .append("yspsParticipantsList", getYspsParticipantsList())
            .toString();
    }
}
