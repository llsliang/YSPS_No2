package com.ysps.ysps.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ysps.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ysps.ysps.domain.YspsClassroomReservation;
import com.ysps.ysps.mapper.YspsClassroomsMapper;
import com.ysps.ysps.domain.YspsClassrooms;
import com.ysps.ysps.service.IYspsClassroomsService;

/**
 * 教室状态Service业务层处理
 * 
 * @author lins
 * @date 2024-04-22
 */
@Service
public class YspsClassroomsServiceImpl implements IYspsClassroomsService 
{
    @Autowired
    private YspsClassroomsMapper yspsClassroomsMapper;

    /**
     * 查询教室状态
     * 
     * @param classroomid 教室状态主键
     * @return 教室状态
     */
    @Override
    public YspsClassrooms selectYspsClassroomsByClassroomid(Long classroomid)
    {
        return yspsClassroomsMapper.selectYspsClassroomsByClassroomid(classroomid);
    }

    /**
     * 查询教室状态列表
     * 
     * @param yspsClassrooms 教室状态
     * @return 教室状态
     */
    @Override
    public List<YspsClassrooms> selectYspsClassroomsList(YspsClassrooms yspsClassrooms)
    {
        return yspsClassroomsMapper.selectYspsClassroomsList(yspsClassrooms);
    }

    /**
     * 新增教室状态
     * 
     * @param yspsClassrooms 教室状态
     * @return 结果
     */
    @Transactional
    @Override
    public int insertYspsClassrooms(YspsClassrooms yspsClassrooms)
    {
        int rows = yspsClassroomsMapper.insertYspsClassrooms(yspsClassrooms);
        insertYspsClassroomReservation(yspsClassrooms);
        return rows;
    }

    /**
     * 修改教室状态
     * 
     * @param yspsClassrooms 教室状态
     * @return 结果
     */
    @Transactional
    @Override
    public int updateYspsClassrooms(YspsClassrooms yspsClassrooms)
    {
        //yspsClassroomsMapper.deleteYspsClassroomReservationByClassroomId(yspsClassrooms.getClassroomid());
        //insertYspsClassroomReservation(yspsClassrooms);
        return yspsClassroomsMapper.updateYspsClassrooms(yspsClassrooms);
    }

    /**
     * 批量删除教室状态
     * 
     * @param classroomids 需要删除的教室状态主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteYspsClassroomsByClassroomids(Long[] classroomids)
    {
        yspsClassroomsMapper.deleteYspsClassroomReservationByClassroomIds(classroomids);
        return yspsClassroomsMapper.deleteYspsClassroomsByClassroomids(classroomids);
    }

    /**
     * 删除教室状态信息
     * 
     * @param classroomid 教室状态主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteYspsClassroomsByClassroomid(Long classroomid)
    {
        yspsClassroomsMapper.deleteYspsClassroomReservationByClassroomId(classroomid);
        return yspsClassroomsMapper.deleteYspsClassroomsByClassroomid(classroomid);
    }

    @Override
    public int updateReservedSeats(YspsClassrooms classroom) {
        return 0;
    }

    @Override
    public int updateCurrentSeating(YspsClassrooms classroom) {
        return 0;
    }

    /**
     * 新增教室座位预约信息信息
     * 
     * @param yspsClassrooms 教室状态对象
     */
    public void insertYspsClassroomReservation(YspsClassrooms yspsClassrooms)
    {
        List<YspsClassroomReservation> yspsClassroomReservationList = yspsClassrooms.getYspsClassroomReservationList();
        Long classroomid = yspsClassrooms.getClassroomid();
        if (StringUtils.isNotNull(yspsClassroomReservationList))
        {
            List<YspsClassroomReservation> list = new ArrayList<YspsClassroomReservation>();
            for (YspsClassroomReservation yspsClassroomReservation : yspsClassroomReservationList)
            {
                yspsClassroomReservation.setClassroomId(classroomid);
                list.add(yspsClassroomReservation);
            }
            if (list.size() > 0)
            {
                yspsClassroomsMapper.batchYspsClassroomReservation(list);
            }
        }
    }
}
