package com.ysps.ysps.mapper;

import java.util.List;
import com.ysps.ysps.domain.YspsReplies;

/**
 * 存储用户对帖子的回复，包括回复内容和时间Mapper接口
 * 
 * @author lins
 * @date 2024-04-14
 */
public interface YspsRepliesMapper 
{
    /**
     * 查询存储用户对帖子的回复，包括回复内容和时间
     * 
     * @param replyid 存储用户对帖子的回复，包括回复内容和时间主键
     * @return 存储用户对帖子的回复，包括回复内容和时间
     */
    public YspsReplies selectYspsRepliesByReplyid(Long replyid);

    /**
     * 查询存储用户对帖子的回复，包括回复内容和时间列表
     * 
     * @param yspsReplies 存储用户对帖子的回复，包括回复内容和时间
     * @return 存储用户对帖子的回复，包括回复内容和时间集合
     */
    public List<YspsReplies> selectYspsRepliesList(YspsReplies yspsReplies);

    /**
     * 新增存储用户对帖子的回复，包括回复内容和时间
     * 
     * @param yspsReplies 存储用户对帖子的回复，包括回复内容和时间
     * @return 结果
     */
    public int insertYspsReplies(YspsReplies yspsReplies);

    /**
     * 修改存储用户对帖子的回复，包括回复内容和时间
     * 
     * @param yspsReplies 存储用户对帖子的回复，包括回复内容和时间
     * @return 结果
     */
    public int updateYspsReplies(YspsReplies yspsReplies);

    /**
     * 删除存储用户对帖子的回复，包括回复内容和时间
     * 
     * @param replyid 存储用户对帖子的回复，包括回复内容和时间主键
     * @return 结果
     */
    public int deleteYspsRepliesByReplyid(Long replyid);

    /**
     * 批量删除存储用户对帖子的回复，包括回复内容和时间
     * 
     * @param replyids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteYspsRepliesByReplyids(Long[] replyids);
}
