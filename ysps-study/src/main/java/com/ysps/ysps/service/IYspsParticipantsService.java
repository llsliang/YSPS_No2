package com.ysps.ysps.service;

import java.util.List;
import com.ysps.ysps.domain.YspsParticipants;

/**
 * 参与者信息Service接口
 * 
 * @author lins
 * @date 2024-04-04
 */
public interface IYspsParticipantsService 
{
    /**
     * 查询参与者信息
     * 
     * @param participantid 参与者信息主键
     * @return 参与者信息
     */
    public YspsParticipants selectYspsParticipantsByParticipantid(Long participantid);

    /**
     * 查询参与者信息列表
     * 
     * @param yspsParticipants 参与者信息
     * @return 参与者信息集合
     */
    public List<YspsParticipants> selectYspsParticipantsList(YspsParticipants yspsParticipants);

    /**
     * 新增参与者信息
     * 
     * @param yspsParticipants 参与者信息
     * @return 结果
     */
    public int insertYspsParticipants(YspsParticipants yspsParticipants);

    /**
     * 修改参与者信息
     * 
     * @param yspsParticipants 参与者信息
     * @return 结果
     */
    public int updateYspsParticipants(YspsParticipants yspsParticipants);

    /**
     * 批量删除参与者信息
     * 
     * @param participantids 需要删除的参与者信息主键集合
     * @return 结果
     */
    public int deleteYspsParticipantsByParticipantids(Long[] participantids);

    /**
     * 删除参与者信息信息
     * 
     * @param participantid 参与者信息主键
     * @return 结果
     */
    public int deleteYspsParticipantsByParticipantid(Long participantid);
}