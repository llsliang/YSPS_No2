package com.ysps.ysps.service;

import java.util.List;
import com.ysps.ysps.domain.YspsClassrooms;

/**
 * 教室状态Service接口
 * 
 * @author lins
 * @date 2024-04-22
 */
public interface IYspsClassroomsService 
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
     * 批量删除教室状态
     * 
     * @param classroomids 需要删除的教室状态主键集合
     * @return 结果
     */
    public int deleteYspsClassroomsByClassroomids(Long[] classroomids);

    /**
     * 删除教室状态信息
     * 
     * @param classroomid 教室状态主键
     * @return 结果
     */
    public int deleteYspsClassroomsByClassroomid(Long classroomid);

    int updateReservedSeats(YspsClassrooms classroom);

    int updateCurrentSeating(YspsClassrooms classroom);
}
