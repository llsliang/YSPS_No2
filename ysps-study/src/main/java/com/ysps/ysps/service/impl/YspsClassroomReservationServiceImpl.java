package com.ysps.ysps.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ysps.ysps.mapper.YspsClassroomReservationMapper;
import com.ysps.ysps.domain.YspsClassroomReservation;
import com.ysps.ysps.service.IYspsClassroomReservationService;

/**
 * 教室预约信息Service业务层处理
 * 
 * @author lins
 * @date 2024-04-23
 */
@Service
public class YspsClassroomReservationServiceImpl implements IYspsClassroomReservationService 
{
    @Autowired
    private YspsClassroomReservationMapper yspsClassroomReservationMapper;

    /**
     * 查询教室预约信息
     * 
     * @param reservationId 教室预约信息主键
     * @return 教室预约信息
     */
    @Override
    public YspsClassroomReservation selectYspsClassroomReservationByReservationId(Long reservationId)
    {
        return yspsClassroomReservationMapper.selectYspsClassroomReservationByReservationId(reservationId);
    }

    /**
     * 查询教室预约信息列表
     * 
     * @param yspsClassroomReservation 教室预约信息
     * @return 教室预约信息
     */
    @Override
    public List<YspsClassroomReservation> selectYspsClassroomReservationList(YspsClassroomReservation yspsClassroomReservation)
    {
        return yspsClassroomReservationMapper.selectYspsClassroomReservationList(yspsClassroomReservation);
    }

    /**
     * 新增教室预约信息
     * 
     * @param yspsClassroomReservation 教室预约信息
     * @return 结果
     */
    @Override
    public int insertYspsClassroomReservation(YspsClassroomReservation yspsClassroomReservation)
    {
        return yspsClassroomReservationMapper.insertYspsClassroomReservation(yspsClassroomReservation);
    }

    /**
     * 修改教室预约信息
     * 
     * @param yspsClassroomReservation 教室预约信息
     * @return 结果
     */
    @Override
    public int updateYspsClassroomReservation(YspsClassroomReservation yspsClassroomReservation)
    {
        return yspsClassroomReservationMapper.updateYspsClassroomReservation(yspsClassroomReservation);
    }

    /**
     * 批量删除教室预约信息
     * 
     * @param reservationIds 需要删除的教室预约信息主键
     * @return 结果
     */
    @Override
    public int deleteYspsClassroomReservationByReservationIds(Long[] reservationIds)
    {
        return yspsClassroomReservationMapper.deleteYspsClassroomReservationByReservationIds(reservationIds);
    }

    /**
     * 删除教室预约信息信息
     * 
     * @param reservationId 教室预约信息主键
     * @return 结果
     */
    @Override
    public int deleteYspsClassroomReservationByReservationId(Long reservationId)
    {
        return yspsClassroomReservationMapper.deleteYspsClassroomReservationByReservationId(reservationId);
    }
    @Override
    public List<YspsClassroomReservation> selectYspsClassroomReservationByUsername(String username) {
        return yspsClassroomReservationMapper.selectYspsClassroomReservationByUsername(username);
    }
}
