package com.ysps.ysps.service;

import java.util.List;
import com.ysps.ysps.domain.YspsPosts;

/**
 * 论坛Service接口
 * 
 * @author lins
 * @date 2024-04-14
 */
public interface IYspsPostsService 
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
     * 批量删除论坛
     * 
     * @param postids 需要删除的论坛主键集合
     * @return 结果
     */
    public int deleteYspsPostsByPostids(Long[] postids);

    /**
     * 删除论坛信息
     * 
     * @param postid 论坛主键
     * @return 结果
     */
    public int deleteYspsPostsByPostid(Long postid);
}
