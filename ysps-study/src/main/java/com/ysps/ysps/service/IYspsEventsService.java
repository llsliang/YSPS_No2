package com.ysps.ysps.service;

import java.util.List;
import com.ysps.ysps.domain.YspsEvents;

/**
 * 活动信息Service接口
 * 
 * @author lins
 * @date 2024-04-04
 */
public interface IYspsEventsService 
{
    /**
     * 查询活动信息
     * 
     * @param eventid 活动信息主键
     * @return 活动信息
     */
    public YspsEvents selectYspsEventsByEventid(Long eventid);

    /**
     * 查询活动信息列表
     * 
     * @param yspsEvents 活动信息
     * @return 活动信息集合
     */
    public List<YspsEvents> selectYspsEventsList(YspsEvents yspsEvents);

    /**
     * 新增活动信息
     * 
     * @param yspsEvents 活动信息
     * @return 结果
     */
    public int insertYspsEvents(YspsEvents yspsEvents);

    /**
     * 修改活动信息
     * 
     * @param yspsEvents 活动信息
     * @return 结果
     */
    public int updateYspsEvents(YspsEvents yspsEvents);

    /**
     * 批量删除活动信息
     * 
     * @param eventids 需要删除的活动信息主键集合
     * @return 结果
     */
    public int deleteYspsEventsByEventids(Long[] eventids);

    /**
     * 删除活动信息信息
     * 
     * @param eventid 活动信息主键
     * @return 结果
     */
    public int deleteYspsEventsByEventid(Long eventid);
}
