package com.ysps.ysps.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ysps.ysps.mapper.YspsRatingsMapper;
import com.ysps.ysps.domain.YspsRatings;
import com.ysps.ysps.service.IYspsRatingsService;

/**
 * 活动评价Service业务层处理
 * 
 * @author lins
 * @date 2024-04-04
 */
@Service
public class YspsRatingsServiceImpl implements IYspsRatingsService 
{
    @Autowired
    private YspsRatingsMapper yspsRatingsMapper;

    /**
     * 查询活动评价
     * 
     * @param ratingid 活动评价主键
     * @return 活动评价
     */
    @Override
    public YspsRatings selectYspsRatingsByRatingid(Long ratingid)
    {
        return yspsRatingsMapper.selectYspsRatingsByRatingid(ratingid);
    }

    /**
     * 查询活动评价列表
     * 
     * @param yspsRatings 活动评价
     * @return 活动评价
     */
    @Override
    public List<YspsRatings> selectYspsRatingsList(YspsRatings yspsRatings)
    {
        return yspsRatingsMapper.selectYspsRatingsList(yspsRatings);
    }

    /**
     * 新增活动评价
     * 
     * @param yspsRatings 活动评价
     * @return 结果
     */
    @Override
    public int insertYspsRatings(YspsRatings yspsRatings)
    {
        return yspsRatingsMapper.insertYspsRatings(yspsRatings);
    }

    /**
     * 修改活动评价
     * 
     * @param yspsRatings 活动评价
     * @return 结果
     */
    @Override
    public int updateYspsRatings(YspsRatings yspsRatings)
    {
        return yspsRatingsMapper.updateYspsRatings(yspsRatings);
    }

    /**
     * 批量删除活动评价
     * 
     * @param ratingids 需要删除的活动评价主键
     * @return 结果
     */
    @Override
    public int deleteYspsRatingsByRatingids(Long[] ratingids)
    {
        return yspsRatingsMapper.deleteYspsRatingsByRatingids(ratingids);
    }

    /**
     * 删除活动评价信息
     * 
     * @param ratingid 活动评价主键
     * @return 结果
     */
    @Override
    public int deleteYspsRatingsByRatingid(Long ratingid)
    {
        return yspsRatingsMapper.deleteYspsRatingsByRatingid(ratingid);
    }
}
