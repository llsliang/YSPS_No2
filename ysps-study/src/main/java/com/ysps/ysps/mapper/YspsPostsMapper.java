package com.ysps.ysps.mapper;

import java.util.List;
import com.ysps.ysps.domain.YspsPosts;
import com.ysps.ysps.domain.YspsReplies;

/**
 * 论坛Mapper接口
 * 
 * @author lins
 * @date 2024-04-14
 */
public interface YspsPostsMapper 
{
    /**
     * 查询论坛
     * 
     * @param postid 论坛主键
     * @return 论坛
     */
    public YspsPosts selectYspsPostsByPostid(Long postid);

    /**
     * 查询论坛列表
     * 
     * @param yspsPosts 论坛
     * @return 论坛集合
     */
    public List<YspsPosts> selectYspsPostsList(YspsPosts yspsPosts);

    /**
     * 新增论坛
     * 
     * @param yspsPosts 论坛
     * @return 结果
     */
    public int insertYspsPosts(YspsPosts yspsPosts);

    /**
     * 修改论坛
     * 
     * @param yspsPosts 论坛
     * @return 结果
     */
    public int updateYspsPosts(YspsPosts yspsPosts);

    /**
     * 删除论坛
     * 
     * @param postid 论坛主键
     * @return 结果
     */
    public int deleteYspsPostsByPostid(Long postid);

    /**
     * 批量删除论坛
     * 
     * @param postids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteYspsPostsByPostids(Long[] postids);

    /**
     * 批量删除存储用户对帖子的回复，包括回复内容和时间
     * 
     * @param postids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteYspsRepliesByPostids(Long[] postids);
    
    /**
     * 批量新增存储用户对帖子的回复，包括回复内容和时间
     * 
     * @param yspsRepliesList 存储用户对帖子的回复，包括回复内容和时间列表
     * @return 结果
     */
    public int batchYspsReplies(List<YspsReplies> yspsRepliesList);
    

    /**
     * 通过论坛主键删除存储用户对帖子的回复，包括回复内容和时间信息
     * 
     * @param postid 论坛ID
     * @return 结果
     */
    public int deleteYspsRepliesByPostid(Long postid);

    // 在你的Mapper接口中添加一个方法来增加浏览量
    public int incrementPostViews(Long postid);
}
