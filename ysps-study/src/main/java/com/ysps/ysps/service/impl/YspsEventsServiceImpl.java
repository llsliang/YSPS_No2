package com.ysps.ysps.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ysps.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ysps.ysps.domain.YspsParticipants;
import com.ysps.ysps.mapper.YspsEventsMapper;
import com.ysps.ysps.domain.YspsEvents;
import com.ysps.ysps.service.IYspsEventsService;

/**
 * 活动信息Service业务层处理
 * 
 * @author lins
 * @date 2024-04-04
 */
@Service
public class YspsEventsServiceImpl implements IYspsEventsService 
{
    @Autowired
    private YspsEventsMapper yspsEventsMapper;

    /**
     * 查询活动信息
     * 
     * @param eventid 活动信息主键
     * @return 活动信息
     */
    @Override
    public YspsEvents selectYspsEventsByEventid(Long eventid)
    {
        return yspsEventsMapper.selectYspsEventsByEventid(eventid);
    }

    /**
     * 查询活动信息列表
     * 
     * @param yspsEvents 活动信息
     * @return 活动信息
     */
    @Override
    public List<YspsEvents> selectYspsEventsList(YspsEvents yspsEvents)
    {
        return yspsEventsMapper.selectYspsEventsList(yspsEvents);
    }

    /**
     * 新增活动信息
     * 
     * @param yspsEvents 活动信息
     * @return 结果
     */
    @Transactional
    @Override
    public int insertYspsEvents(YspsEvents yspsEvents)
    {
        int rows = yspsEventsMapper.insertYspsEvents(yspsEvents);
        insertYspsParticipants(yspsEvents);
        return rows;
    }

    /**
     * 修改活动信息
     * 
     * @param yspsEvents 活动信息
     * @return 结果
     */
    @Transactional
    @Override
    public int updateYspsEvents(YspsEvents yspsEvents)
    {
        yspsEventsMapper.deleteYspsParticipantsByEventid(yspsEvents.getEventid());
        insertYspsParticipants(yspsEvents);
        return yspsEventsMapper.updateYspsEvents(yspsEvents);
    }

    /**
     * 批量删除活动信息
     * 
     * @param eventids 需要删除的活动信息主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteYspsEventsByEventids(Long[] eventids)
    {
        yspsEventsMapper.deleteYspsParticipantsByEventids(eventids);
        return yspsEventsMapper.deleteYspsEventsByEventids(eventids);
    }

    /**
     * 删除活动信息信息
     * 
     * @param eventid 活动信息主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteYspsEventsByEventid(Long eventid)
    {
        yspsEventsMapper.deleteYspsParticipantsByEventid(eventid);
        return yspsEventsMapper.deleteYspsEventsByEventid(eventid);
    }

    /**
     * 新增记录活动参与者的信息，包括报名时间和签到状态信息
     * 
     * @param yspsEvents 活动信息对象
     */
    public void insertYspsParticipants(YspsEvents yspsEvents)
    {
        List<YspsParticipants> yspsParticipantsList = yspsEvents.getYspsParticipantsList();
        Long eventid = yspsEvents.getEventid();
        if (StringUtils.isNotNull(yspsParticipantsList))
        {
            List<YspsParticipants> list = new ArrayList<YspsParticipants>();
            for (YspsParticipants yspsParticipants : yspsParticipantsList)
            {
                yspsParticipants.setEventid(eventid);
                list.add(yspsParticipants);
            }
            if (list.size() > 0)
            {
                yspsEventsMapper.batchYspsParticipants(list);
            }
        }
    }
}
