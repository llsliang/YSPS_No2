package com.ysps.ysps.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ysps.common.annotation.Excel;
import com.ysps.common.core.domain.BaseEntity;

/**
 * 包含课程信息，如课程名称、教师、时间和地点对象 ysps_courses
 * 
 * @author lins
 * @date 2024-04-12
 */
public class YspsCourses extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 课程id */
    private Long courseid;

    /** 课程名称 */
    @Excel(name = "课程名称")
    private String coursename;

    /** 授课教师 */
    @Excel(name = "授课教师")
    private String teacher;

    /** 课程时间 */
    @Excel(name = "课程时间")
    private String classtime;

    /** 课程地点 */
    @Excel(name = "课程地点")
    private String location;

    /** 课程类型 */
    @Excel(name = "课程类型")
    private String coursetype;

    /** 课程学分 */
    @Excel(name = "课程学分")
    private Long credits;

    /** 课程描述 */
    @Excel(name = "课程描述")
    private String description;

    public void setCourseid(Long courseid) 
    {
        this.courseid = courseid;
    }

    public Long getCourseid() 
    {
        return courseid;
    }
    public void setCoursename(String coursename) 
    {
        this.coursename = coursename;
    }

    public String getCoursename() 
    {
        return coursename;
    }
    public void setTeacher(String teacher) 
    {
        this.teacher = teacher;
    }

    public String getTeacher() 
    {
        return teacher;
    }
    public void setClasstime(String classtime) 
    {
        this.classtime = classtime;
    }

    public String getClasstime() 
    {
        return classtime;
    }
    public void setLocation(String location) 
    {
        this.location = location;
    }

    public String getLocation() 
    {
        return location;
    }
    public void setCoursetype(String coursetype) 
    {
        this.coursetype = coursetype;
    }

    public String getCoursetype() 
    {
        return coursetype;
    }
    public void setCredits(Long credits) 
    {
        this.credits = credits;
    }

    public Long getCredits() 
    {
        return credits;
    }
    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("courseid", getCourseid())
            .append("coursename", getCoursename())
            .append("teacher", getTeacher())
            .append("classtime", getClasstime())
            .append("location", getLocation())
            .append("coursetype", getCoursetype())
            .append("credits", getCredits())
            .append("description", getDescription())
            .toString();
    }
}
