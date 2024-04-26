package com.ysps.ysps.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ysps.ysps.mapper.YspsParticipantsMapper;
import com.ysps.ysps.domain.YspsParticipants;
import com.ysps.ysps.service.IYspsParticipantsService;

/**
 * 参与者信息Service业务层处理
 * 
 * @author lins
 * @date 2024-04-04
 */
@Service
public class YspsParticipantsServiceImpl implements IYspsParticipantsService 
{
    @Autowired
    private YspsParticipantsMapper yspsParticipantsMapper;

    /**
     * 查询参与者信息
     * 
     * @param participantid 参与者信息主键
     * @return 参与者信息
     */
    @Override
    public YspsParticipants selectYspsParticipantsByParticipantid(Long participantid)
    {
        return yspsParticipantsMapper.selectYspsParticipantsByParticipantid(participantid);
    }

    /**
     * 查询参与者信息列表
     * 
     * @param yspsParticipants 参与者信息
     * @return 参与者信息
     */
    @Override
    public List<YspsParticipants> selectYspsParticipantsList(YspsParticipants yspsParticipants)
    {
        return yspsParticipantsMapper.selectYspsParticipantsList(yspsParticipants);
    }

    /**
     * 新增参与者信息
     * 
     * @param yspsParticipants 参与者信息
     * @return 结果
     */
    @Override
    public int insertYspsParticipants(YspsParticipants yspsParticipants)
    {
        return yspsParticipantsMapper.insertYspsParticipants(yspsParticipants);
    }

    /**
     * 修改参与者信息
     * 
     * @param yspsParticipants 参与者信息
     * @return 结果
     */
    @Override
    public int updateYspsParticipants(YspsParticipants yspsParticipants)
    {
        return yspsParticipantsMapper.updateYspsParticipants(yspsParticipants);
    }

    /**
     * 批量删除参与者信息
     * 
     * @param participantids 需要删除的参与者信息主键
     * @return 结果
     */
    @Override
    public int deleteYspsParticipantsByParticipantids(Long[] participantids)
    {
        return yspsParticipantsMapper.deleteYspsParticipantsByParticipantids(participantids);
    }

    /**
     * 删除参与者信息信息
     * 
     * @param participantid 参与者信息主键
     * @return 结果
     */
    @Override
    public int deleteYspsParticipantsByParticipantid(Long participantid)
    {
        return yspsParticipantsMapper.deleteYspsParticipantsByParticipantid(participantid);
    }
}
