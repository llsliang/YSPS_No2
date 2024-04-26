package com.ysps.ysps.service.impl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ysps.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ysps.ysps.domain.YspsReplies;
import com.ysps.ysps.mapper.YspsPostsMapper;
import com.ysps.ysps.domain.YspsPosts;
import com.ysps.ysps.service.IYspsPostsService;

/**
 * 论坛Service业务层处理
 * 
 * @author lins
 * @date 2024-04-14
 */
@Service
public class YspsPostsServiceImpl implements IYspsPostsService 
{
    @Autowired
    private YspsPostsMapper yspsPostsMapper;

    /**
     * 查询论坛
     * 
     * @param postid 论坛主键
     * @return 论坛
     */
    @Override
    public YspsPosts selectYspsPostsByPostid(Long postid)
    {
        // 首先，增加帖子的浏览量
        yspsPostsMapper.incrementPostViews(postid);
        return yspsPostsMapper.selectYspsPostsByPostid(postid);
    }

    /**
     * 查询论坛列表
     * 
     * @param yspsPosts 论坛
     * @return 论坛
     */
    @Override
    public List<YspsPosts> selectYspsPostsList(YspsPosts yspsPosts)
    {
        return yspsPostsMapper.selectYspsPostsList(yspsPosts);
    }

    /**
     * 新增论坛
     * 
     * @param yspsPosts 论坛
     * @return 结果
     */
    @Transactional
    @Override
    public int insertYspsPosts(YspsPosts yspsPosts)
    {
        int rows = yspsPostsMapper.insertYspsPosts(yspsPosts);
        insertYspsReplies(yspsPosts);
        return rows;
    }

    /**
     * 修改论坛
     * 
     * @param yspsPosts 论坛
     * @return 结果
     */
    @Transactional
    @Override
    public int updateYspsPosts(YspsPosts yspsPosts)
    {
        yspsPostsMapper.deleteYspsRepliesByPostid(yspsPosts.getPostid());
        insertYspsReplies(yspsPosts);
        // 设置最后更新时间为当前时间
        yspsPosts.setLastupdatetime(new Date());
        return yspsPostsMapper.updateYspsPosts(yspsPosts);
    }

    /**
     * 批量删除论坛
     * 
     * @param postids 需要删除的论坛主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteYspsPostsByPostids(Long[] postids)
    {
        yspsPostsMapper.deleteYspsRepliesByPostids(postids);
        return yspsPostsMapper.deleteYspsPostsByPostids(postids);
    }

    /**
     * 删除论坛信息
     * 
     * @param postid 论坛主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteYspsPostsByPostid(Long postid)
    {
        yspsPostsMapper.deleteYspsRepliesByPostid(postid);
        return yspsPostsMapper.deleteYspsPostsByPostid(postid);
    }

    /**
     * 新增存储用户对帖子的回复，包括回复内容和时间信息
     * 
     * @param yspsPosts 论坛对象
     */
    public void insertYspsReplies(YspsPosts yspsPosts)
    {
        List<YspsReplies> yspsRepliesList = yspsPosts.getYspsRepliesList();
        Long postid = yspsPosts.getPostid();
        if (StringUtils.isNotNull(yspsRepliesList))
        {
            List<YspsReplies> list = new ArrayList<YspsReplies>();
            for (YspsReplies yspsReplies : yspsRepliesList)
            {
                yspsReplies.setPostid(postid);
                list.add(yspsReplies);
            }
            if (list.size() > 0)
            {
                yspsPostsMapper.batchYspsReplies(list);
            }
        }
    }
}
