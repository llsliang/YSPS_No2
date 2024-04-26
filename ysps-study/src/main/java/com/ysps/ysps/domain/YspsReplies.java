package com.ysps.ysps.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ysps.common.annotation.Excel;
import com.ysps.common.core.domain.BaseEntity;

/**
 * 存储用户对帖子的回复，包括回复内容和时间对象 ysps_replies
 * 
 * @author lins
 * @date 2024-04-14
 */
public class YspsReplies extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 评论id */
    private Long replyid;

    /** 帖子ID */
    @Excel(name = "帖子ID")
    private Long postid;

    /** 回帖的用户 */
    @Excel(name = "回帖的用户")
    private String username;

    /** 回复内容 */
    @Excel(name = "回复内容")
    private String content;

    /** 回复时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "回复时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date replytime;

    public void setReplyid(Long replyid) 
    {
        this.replyid = replyid;
    }

    public Long getReplyid() 
    {
        return replyid;
    }
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
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }
    public void setReplytime(Date replytime)
    {
        this.replytime = replytime;
    }

    public Date getReplytime() 
    {
        return replytime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("replyid", getReplyid())
            .append("postid", getPostid())
            .append("username", getUsername())
            .append("content", getContent())
            .append("replytime", getReplytime())
            .toString();
    }
}
