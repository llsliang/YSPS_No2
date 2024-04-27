package com.ysps.ysps.service;

import java.util.List;
import com.ysps.ysps.domain.YspsRatings;

/**
 * 活动评价Service接口
 * 
 * @author lins
 * @date 2024-04-04
 */
public interface IYspsRatingsService 
{
    /**
     * 查询活动评价
     * 
     * @param ratingid 活动评价主键
     * @return 活动评价
     */
    public YspsRatings selectYspsRatingsByRatingid(Long ratingid);

    /**
     * 查询活动评价列表
     * 
     * @param yspsRatings 活动评价
     * @return 活动评价集合
     */
    public List<YspsRatings> selectYspsRatingsList(YspsRatings yspsRatings);

    /**
     * 新增活动评价
     * 
     * @param yspsRatings 活动评价
     * @return 结果
     */
    public int insertYspsRatings(YspsRatings yspsRatings);

    /**
     * 修改活动评价
     * 
     * @param yspsRatings 活动评价
     * @return 结果
     */
    public int updateYspsRatings(YspsRatings yspsRatings);

    /**
     * 批量删除活动评价
     * 
     * @param ratingids 需要删除的活动评价主键集合
     * @return 结果
     */
    public int deleteYspsRatingsByRatingids(Long[] ratingids);

    /**
     * 删除活动评价信息
     * 
     * @param ratingid 活动评价主键
     * @return 结果
     */
    public int deleteYspsRatingsByRatingid(Long ratingid);
}