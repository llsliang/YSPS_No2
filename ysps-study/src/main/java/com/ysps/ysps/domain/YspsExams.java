package com.ysps.ysps.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ysps.common.annotation.Excel;
import com.ysps.common.core.domain.BaseEntity;

/**
 * 考试信息对象 ysps_exams
 * 
 * @author lins
 * @date 2024-04-13
 */
public class YspsExams extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 考试id */
    private Long examid;

    /** 考试科目 */
    @Excel(name = "考试科目")
    private String subject;

//    /** 考试时间 */
//    @JsonFormat(pattern = "yyyy-MM-dd")
//    @Excel(name = "考试时间", width = 30, dateFormat = "yyyy-MM-dd")
//    private Date examtime;
    /** 考试时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "考试时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date examtime;


    /** 考试地点 */
    @Excel(name = "考试地点")
    private String examlocation;

    /** 考试类型 */
    @Excel(name = "考试类型")
    private String examtype;

    /** 考试形式 */
    @Excel(name = "考试形式")
    private String examformat;

    /** 考试成绩 */
    @Excel(name = "考试成绩")
    private BigDecimal grade;

    /** 参考人员 */
    @Excel(name = "参考人员")
    private String username;

    public void setExamid(Long examid) 
    {
        this.examid = examid;
    }

    public Long getExamid() 
    {
        return examid;
    }
    public void setSubject(String subject) 
    {
        this.subject = subject;
    }

    public String getSubject() 
    {
        return subject;
    }
    public void setExamtime(Date examtime) 
    {
        this.examtime = examtime;
    }

    public Date getExamtime() 
    {
        return examtime;
    }
    public void setExamlocation(String examlocation) 
    {
        this.examlocation = examlocation;
    }

    public String getExamlocation() 
    {
        return examlocation;
    }
    public void setExamtype(String examtype) 
    {
        this.examtype = examtype;
    }

    public String getExamtype() 
    {
        return examtype;
    }
    public void setExamformat(String examformat) 
    {
        this.examformat = examformat;
    }

    public String getExamformat() 
    {
        return examformat;
    }
    public void setGrade(BigDecimal grade) 
    {
        this.grade = grade;
    }

    public BigDecimal getGrade() 
    {
        return grade;
    }
    public void setUsername(String username) 
    {
        this.username = username;
    }

    public String getUsername() 
    {
        return username;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("examid", getExamid())
            .append("subject", getSubject())
            .append("examtime", getExamtime())
            .append("examlocation", getExamlocation())
            .append("examtype", getExamtype())
            .append("examformat", getExamformat())
            .append("grade", getGrade())
            .append("username", getUsername())
            .toString();
    }
}
