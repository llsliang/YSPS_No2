package com.ysps.ysps.domain;

import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ysps.common.annotation.Excel;
import com.ysps.common.core.domain.BaseEntity;

/**
 * 教室状态对象 ysps_classrooms
 * 
 * @author lins
 * @date 2024-04-22
 */
public class YspsClassrooms extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 教室唯一标识符 */
    private Long classroomid;

    /** 教室名称 */
    @Excel(name = "教室名称")
    private String classname;

    /** 教室容量 */
    @Excel(name = "教室容量")
    private Long capacity;

    /** 教室当前状态 */
    @Excel(name = "教室当前状态")
    private Long status;

    /** 当前落座人数 */
    @Excel(name = "当前落座人数")
    private Long currentSeating;

    /** 已预约座位数 */
    @Excel(name = "已预约座位数")
    private Long reservedSeats;

    /** 教室座位预约信息信息 */
    private List<YspsClassroomReservation> yspsClassroomReservationList;

    public void setClassroomid(Long classroomid) 
    {
        this.classroomid = classroomid;
    }

    public Long getClassroomid() 
    {
        return classroomid;
    }
    public void setClassname(String classname) 
    {
        this.classname = classname;
    }

    public String getClassname() 
    {
        return classname;
    }
    public void setCapacity(Long capacity) 
    {
        this.capacity = capacity;
    }

    public Long getCapacity() 
    {
        return capacity;
    }
    public void setStatus(Long status) 
    {
        this.status = status;
    }

    public Long getStatus() 
    {
        return status;
    }
    public void setCurrentSeating(Long currentSeating) 
    {
        this.currentSeating = currentSeating;
    }

    public Long getCurrentSeating() 
    {
        return currentSeating;
    }
    public void setReservedSeats(Long reservedSeats) 
    {
        this.reservedSeats = reservedSeats;
    }

    public Long getReservedSeats() 
    {
        return reservedSeats;
    }

    public List<YspsClassroomReservation> getYspsClassroomReservationList()
    {
        return yspsClassroomReservationList;
    }

    public void setYspsClassroomReservationList(List<YspsClassroomReservation> yspsClassroomReservationList)
    {
        this.yspsClassroomReservationList = yspsClassroomReservationList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("classroomid", getClassroomid())
            .append("classname", getClassname())
            .append("capacity", getCapacity())
            .append("status", getStatus())
            .append("currentSeating", getCurrentSeating())
            .append("reservedSeats", getReservedSeats())
            .append("yspsClassroomReservationList", getYspsClassroomReservationList())
            .toString();
    }
}
