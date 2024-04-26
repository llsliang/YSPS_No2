package com.ysps.ysps.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ysps.common.annotation.Excel;
import com.ysps.common.core.domain.BaseEntity;

/**
 * 记录活动参与者的信息，包括报名时间和签到状态对象 ysps_participants
 * 
 * @author lins
 * @date 2024-04-04
 */
public class YspsParticipants extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 参与者唯一标识符 */
    private Long participantid;

    /** 参与的活动ID */
    @Excel(name = "参与的活动ID")
    private Long eventid;

    /** 参与者的用户ID */
    @Excel(name = "参与者的用户ID")
    private Long userid;

    /** 报名参加活动的时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "报名参加活动的时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date signuptime;

    /** 是否签到，是或否 */
    @Excel(name = "是否签到，是或否")
    private Integer checkin;

    public void setParticipantid(Long participantid) 
    {
        this.participantid = participantid;
    }

    public Long getParticipantid() 
    {
        return participantid;
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
    public void setSignuptime(Date signuptime) 
    {
        this.signuptime = signuptime;
    }

    public Date getSignuptime() 
    {
        return signuptime;
    }
    public void setCheckin(Integer checkin) 
    {
        this.checkin = checkin;
    }

    public Integer getCheckin() 
    {
        return checkin;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("participantid", getParticipantid())
            .append("eventid", getEventid())
            .append("userid", getUserid())
            .append("signuptime", getSignuptime())
            .append("checkin", getCheckin())
            .toString();
    }
}
