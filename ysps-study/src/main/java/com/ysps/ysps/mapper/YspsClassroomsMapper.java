package com.ysps.ysps.mapper;

import java.util.List;
import com.ysps.ysps.domain.YspsClassrooms;
import com.ysps.ysps.domain.YspsClassroomReservation;

/**
 * 教室状态Mapper接口
 * 
 * @author lins
 * @date 2024-04-22
 */
public interface YspsClassroomsMapper 
{
    /**
     * 查询教室状态
     * 
     * @param classroomid 教室状态主键
     * @return 教室状态
     */
    public YspsClassrooms selectYspsClassroomsByClassroomid(Long classroomid);

    /**
     * 查询教室状态列表
     * 
     * @param yspsClassrooms 教室状态
     * @return 教室状态集合
     */
    public List<YspsClassrooms> selectYspsClassroomsList(YspsClassrooms yspsClassrooms);

    /**
     * 新增教室状态
     * 
     * @param yspsClassrooms 教室状态
     * @return 结果
     */
    public int insertYspsClassrooms(YspsClassrooms yspsClassrooms);

    /**
     * 修改教室状态
     * 
     * @param yspsClassrooms 教室状态
     * @return 结果
     */
    public int updateYspsClassrooms(YspsClassrooms yspsClassrooms);

    /**
     * 删除教室状态
     * 
     * @param classroomid 教室状态主键
     * @return 结果
     */
    public int deleteYspsClassroomsByClassroomid(Long classroomid);

    /**
     * 批量删除教室状态
     * 
     * @param classroomids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteYspsClassroomsByClassroomids(Long[] classroomids);

    /**
     * 批量删除教室座位预约信息
     * 
     * @param classroomids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteYspsClassroomReservationByClassroomIds(Long[] classroomids);
    
    /**
     * 批量新增教室座位预约信息
     * 
     * @param yspsClassroomReservationList 教室座位预约信息列表
     * @return 结果
     */
    public int batchYspsClassroomReservation(List<YspsClassroomReservation> yspsClassroomReservationList);
    

    /**
     * 通过教室状态主键删除教室座位预约信息信息
     * 
     * @param classroomid 教室状态ID
     * @return 结果
     */
    public int deleteYspsClassroomReservationByClassroomId(Long classroomid);
    /**
     * 修改教室预约人数
     *
     * @param classroomid     教室ID
     * @param reservedSeats   预约人数
     * @return 结果
     */
    public int updateReservedSeats(Long classroomid,  Long reservedSeats);

    /**
     * 修改教室落座人数
     *
     * @param classroomid     教室ID
     * @param currentSeating  落座人数
     * @return 结果
     */
    public int updateCurrentSeating(Long classroomid,  Long currentSeating);



}
