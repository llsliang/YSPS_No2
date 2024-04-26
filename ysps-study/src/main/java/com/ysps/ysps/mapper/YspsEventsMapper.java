package com.ysps.ysps.mapper;

import java.util.List;
import com.ysps.ysps.domain.YspsEvents;
import com.ysps.ysps.domain.YspsParticipants;

/**
 * 活动信息Mapper接口
 * 
 * @author lins
 * @date 2024-04-04
 */
public interface YspsEventsMapper 
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
     * 删除活动信息
     * 
     * @param eventid 活动信息主键
     * @return 结果
     */
    public int deleteYspsEventsByEventid(Long eventid);

    /**
     * 批量删除活动信息
     * 
     * @param eventids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteYspsEventsByEventids(Long[] eventids);

    /**
     * 批量删除记录活动参与者的信息，包括报名时间和签到状态
     * 
     * @param eventids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteYspsParticipantsByEventids(Long[] eventids);
    
    /**
     * 批量新增记录活动参与者的信息，包括报名时间和签到状态
     * 
     * @param yspsParticipantsList 记录活动参与者的信息，包括报名时间和签到状态列表
     * @return 结果
     */
    public int batchYspsParticipants(List<YspsParticipants> yspsParticipantsList);
    

    /**
     * 通过活动信息主键删除记录活动参与者的信息，包括报名时间和签到状态信息
     * 
     * @param eventid 活动信息ID
     * @return 结果
     */
    public int deleteYspsParticipantsByEventid(Long eventid);
}
