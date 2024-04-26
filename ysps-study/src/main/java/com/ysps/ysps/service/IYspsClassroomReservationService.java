package com.ysps.ysps.service;

import java.util.List;
import com.ysps.ysps.domain.YspsClassroomReservation;

/**
 * 教室预约信息Service接口
 * 
 * @author lins
 * @date 2024-04-23
 */
public interface IYspsClassroomReservationService 
{
    /**
     * 查询教室预约信息
     * 
     * @param reservationId 教室预约信息主键
     * @return 教室预约信息
     */
    public YspsClassroomReservation selectYspsClassroomReservationByReservationId(Long reservationId);

    /**
     * 查询教室预约信息列表
     * 
     * @param yspsClassroomReservation 教室预约信息
     * @return 教室预约信息集合
     */
    public List<YspsClassroomReservation> selectYspsClassroomReservationList(YspsClassroomReservation yspsClassroomReservation);

    /**
     * 新增教室预约信息
     * 
     * @param yspsClassroomReservation 教室预约信息
     * @return 结果
     */
    public int insertYspsClassroomReservation(YspsClassroomReservation yspsClassroomReservation);

    /**
     * 修改教室预约信息
     * 
     * @param yspsClassroomReservation 教室预约信息
     * @return 结果
     */
    public int updateYspsClassroomReservation(YspsClassroomReservation yspsClassroomReservation);

    /**
     * 批量删除教室预约信息
     * 
     * @param reservationIds 需要删除的教室预约信息主键集合
     * @return 结果
     */
    public int deleteYspsClassroomReservationByReservationIds(Long[] reservationIds);

    /**
     * 删除教室预约信息信息
     * 
     * @param reservationId 教室预约信息主键
     * @return 结果
     */
    public int deleteYspsClassroomReservationByReservationId(Long reservationId);

    /**
     * 根据预约用户名查询教室预约信息列表
     *
     * @param username 预约用户名
     * @return 教室预约信息列表
     */
    List<YspsClassroomReservation> selectYspsClassroomReservationByUsername(String username);
}
