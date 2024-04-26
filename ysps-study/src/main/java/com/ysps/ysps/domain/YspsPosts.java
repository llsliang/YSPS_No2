package com.ysps.ysps.domain;

import java.util.List;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ysps.common.annotation.Excel;
import com.ysps.common.core.domain.BaseEntity;

/**
 * 论坛对象 ysps_posts
 * 
 * @author lins
 * @date 2024-04-14
 */
public class YspsPosts extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 帖子id */
    private Long postid;

    /** 用户 */
    @Excel(name = "用户")
    private String username;

    /** 帖子标题 */
    @Excel(name = "帖子标题")
    private String title;

    /** 帖子内容 */
    @Excel(name = "帖子内容")
    private String content;

    /** 发布时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "发布时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date posttime;

    /** 最后更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "最后更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date lastupdatetime;

    /** 回复数量 */
    @Excel(name = "回复数量")
    private Long replycount;

    /** 外连接 */
    private String mediacontent;

    /** 浏览量 */
    @Excel(name = "浏览量")
    private Long views;

    /** 存储用户对帖子的回复，包括回复内容和时间信息 */
    private List<YspsReplies> yspsRepliesList;

    public void setPostid(Long postid) 
    {
        this.postid = postid;
    }

    public Long getPostid() 
    {
        return postid;
    }
    public void setUsername(String username) 
    {
        this.username = username;
    }

    public String getUsername() 
    {
        return username;
    }
    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }
    public void setPosttime(Date posttime) 
    {
        this.posttime = posttime;
    }

    public Date getPosttime() 
    {
        return posttime;
    }
    public void setLastupdatetime(Date lastupdatetime) 
    {
        this.lastupdatetime = lastupdatetime;
    }

    public Date getLastupdatetime() 
    {
        return lastupdatetime;
    }
    public void setReplycount(Long replycount) 
    {
        this.replycount = replycount;
    }

    public Long getReplycount() 
    {
        return replycount;
    }
    public void setMediacontent(String mediacontent) 
    {
        this.mediacontent = mediacontent;
    }

    public String getMediacontent() 
    {
        return mediacontent;
    }
    public void setViews(Long views) 
    {
        this.views = views;
    }

    public Long getViews() 
    {
        return views;
    }

    public List<YspsReplies> getYspsRepliesList()
    {
        return yspsRepliesList;
    }

    public void setYspsRepliesList(List<YspsReplies> yspsRepliesList)
    {
        this.yspsRepliesList = yspsRepliesList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("postid", getPostid())
            .append("username", getUsername())
            .append("title", getTitle())
            .append("content", getContent())
            .append("posttime", getPosttime())
            .append("lastupdatetime", getLastupdatetime())
            .append("replycount", getReplycount())
            .append("mediacontent", getMediacontent())
            .append("views", getViews())
            .append("yspsRepliesList", getYspsRepliesList())
            .toString();
    }
}
