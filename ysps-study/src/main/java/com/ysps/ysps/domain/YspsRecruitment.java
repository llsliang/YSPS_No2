package com.ysps.ysps.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ysps.common.annotation.Excel;
import com.ysps.common.core.domain.BaseEntity;

/**
 * 招聘信息对象 ysps_recruitment
 * 
 * @author lins
 * @date 2024-04-15
 */
public class YspsRecruitment extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 职位id */
    private Long jobId;

    /** 职位名称 */
    @Excel(name = "职位名称")
    private String jobTitle;

    /** 工作地点 */
    @Excel(name = "工作地点")
    private String jobLocation;

    /** 职位类型 */
    @Excel(name = "职位类型")
    private String jobType;

    /** 发布日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "发布日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date postingDate;

    /** 截止日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "截止日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date closingDate;

    /** 工作职责 */
    @Excel(name = "工作职责")
    private String jobResponsibilities;

    /** 任职要求 */
    @Excel(name = "任职要求")
    private String jobRequirements;

    /** 薪资范围 */
    @Excel(name = "薪资范围")
    private String salaryRange;

    /** 福利待遇 */
    @Excel(name = "福利待遇")
    private String benefits;

    /** 联系人 */
    @Excel(name = "联系人")
    private String contactPerson;

    /** 电子邮箱 */
    @Excel(name = "电子邮箱")
    private String contactEmail;

    /** 联系电话 */
    @Excel(name = "联系电话")
    private String contactPhone;

    /** 提交材料 */
    @Excel(name = "提交材料")
    private String applicationMaterials;

    public void setJobId(Long jobId) 
    {
        this.jobId = jobId;
    }

    public Long getJobId() 
    {
        return jobId;
    }
    public void setJobTitle(String jobTitle) 
    {
        this.jobTitle = jobTitle;
    }

    public String getJobTitle() 
    {
        return jobTitle;
    }
    public void setJobLocation(String jobLocation) 
    {
        this.jobLocation = jobLocation;
    }

    public String getJobLocation() 
    {
        return jobLocation;
    }
    public void setJobType(String jobType) 
    {
        this.jobType = jobType;
    }

    public String getJobType() 
    {
        return jobType;
    }
    public void setPostingDate(Date postingDate) 
    {
        this.postingDate = postingDate;
    }

    public Date getPostingDate() 
    {
        return postingDate;
    }
    public void setClosingDate(Date closingDate) 
    {
        this.closingDate = closingDate;
    }

    public Date getClosingDate() 
    {
        return closingDate;
    }
    public void setJobResponsibilities(String jobResponsibilities) 
    {
        this.jobResponsibilities = jobResponsibilities;
    }

    public String getJobResponsibilities() 
    {
        return jobResponsibilities;
    }
    public void setJobRequirements(String jobRequirements) 
    {
        this.jobRequirements = jobRequirements;
    }

    public String getJobRequirements() 
    {
        return jobRequirements;
    }
    public void setSalaryRange(String salaryRange) 
    {
        this.salaryRange = salaryRange;
    }

    public String getSalaryRange() 
    {
        return salaryRange;
    }
    public void setBenefits(String benefits) 
    {
        this.benefits = benefits;
    }

    public String getBenefits() 
    {
        return benefits;
    }
    public void setContactPerson(String contactPerson) 
    {
        this.contactPerson = contactPerson;
    }

    public String getContactPerson() 
    {
        return contactPerson;
    }
    public void setContactEmail(String contactEmail) 
    {
        this.contactEmail = contactEmail;
    }

    public String getContactEmail() 
    {
        return contactEmail;
    }
    public void setContactPhone(String contactPhone) 
    {
        this.contactPhone = contactPhone;
    }

    public String getContactPhone() 
    {
        return contactPhone;
    }
    public void setApplicationMaterials(String applicationMaterials) 
    {
        this.applicationMaterials = applicationMaterials;
    }

    public String getApplicationMaterials() 
    {
        return applicationMaterials;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("jobId", getJobId())
            .append("jobTitle", getJobTitle())
            .append("jobLocation", getJobLocation())
            .append("jobType", getJobType())
            .append("postingDate", getPostingDate())
            .append("closingDate", getClosingDate())
            .append("jobResponsibilities", getJobResponsibilities())
            .append("jobRequirements", getJobRequirements())
            .append("salaryRange", getSalaryRange())
            .append("benefits", getBenefits())
            .append("contactPerson", getContactPerson())
            .append("contactEmail", getContactEmail())
            .append("contactPhone", getContactPhone())
            .append("applicationMaterials", getApplicationMaterials())
            .toString();
    }
}
