package com.ysps.ysps.service.impl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ysps.ysps.mapper.YspsRepliesMapper;
import com.ysps.ysps.domain.YspsReplies;
import com.ysps.ysps.service.IYspsRepliesService;

/**
 * 存储用户对帖子的回复，包括回复内容和时间Service业务层处理
 * 
 * @author lins
 * @date 2024-04-14
 */
@Service
public class YspsRepliesServiceImpl implements IYspsRepliesService 
{
    @Autowired
    private YspsRepliesMapper yspsRepliesMapper;

    /**
     * 查询存储用户对帖子的回复，包括回复内容和时间
     * 
     * @param replyid 存储用户对帖子的回复，包括回复内容和时间主键
     * @return 存储用户对帖子的回复，包括回复内容和时间
     */
    @Override
    public YspsReplies selectYspsRepliesByReplyid(Long replyid)
    {
        return yspsRepliesMapper.selectYspsRepliesByReplyid(replyid);
    }

    /**
     * 查询存储用户对帖子的回复，包括回复内容和时间列表
     * 
     * @param yspsReplies 存储用户对帖子的回复，包括回复内容和时间
     * @return 存储用户对帖子的回复，包括回复内容和时间
     */
    @Override
    public List<YspsReplies> selectYspsRepliesList(YspsReplies yspsReplies)
    {
        return yspsRepliesMapper.selectYspsRepliesList(yspsReplies);
    }

    /**
     * 新增存储用户对帖子的回复，包括回复内容和时间
     * 
     * @param yspsReplies 存储用户对帖子的回复，包括回复内容和时间
     * @return 结果
     */
    @Override
    public int insertYspsReplies(YspsReplies yspsReplies)
    {
        yspsReplies.setReplytime(new Date());
        return yspsRepliesMapper.insertYspsReplies(yspsReplies);
    }

    /**
     * 修改存储用户对帖子的回复，包括回复内容和时间
     * 
     * @param yspsReplies 存储用户对帖子的回复，包括回复内容和时间
     * @return 结果
     */
    @Override
    public int updateYspsReplies(YspsReplies yspsReplies)
    {
        return yspsRepliesMapper.updateYspsReplies(yspsReplies);
    }

    /**
     * 批量删除存储用户对帖子的回复，包括回复内容和时间
     * 
     * @param replyids 需要删除的存储用户对帖子的回复，包括回复内容和时间主键
     * @return 结果
     */
    @Override
    public int deleteYspsRepliesByReplyids(Long[] replyids)
    {
        return yspsRepliesMapper.deleteYspsRepliesByReplyids(replyids);
    }

    /**
     * 删除存储用户对帖子的回复，包括回复内容和时间信息
     * 
     * @param replyid 存储用户对帖子的回复，包括回复内容和时间主键
     * @return 结果
     */
    @Override
    public int deleteYspsRepliesByReplyid(Long replyid)
    {
        return yspsRepliesMapper.deleteYspsRepliesByReplyid(replyid);
    }
}
