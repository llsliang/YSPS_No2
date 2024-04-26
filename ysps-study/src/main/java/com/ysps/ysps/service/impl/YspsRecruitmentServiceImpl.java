package com.ysps.ysps.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ysps.ysps.mapper.YspsRecruitmentMapper;
import com.ysps.ysps.domain.YspsRecruitment;
import com.ysps.ysps.service.IYspsRecruitmentService;

/**
 * 招聘信息Service业务层处理
 * 
 * @author lins
 * @date 2024-04-15
 */
@Service
public class YspsRecruitmentServiceImpl implements IYspsRecruitmentService 
{
    @Autowired
    private YspsRecruitmentMapper yspsRecruitmentMapper;

    /**
     * 查询招聘信息
     * 
     * @param jobId 招聘信息主键
     * @return 招聘信息
     */
    @Override
    public YspsRecruitment selectYspsRecruitmentByJobId(Long jobId)
    {
        return yspsRecruitmentMapper.selectYspsRecruitmentByJobId(jobId);
    }

    /**
     * 查询招聘信息列表
     * 
     * @param yspsRecruitment 招聘信息
     * @return 招聘信息
     */
    @Override
    public List<YspsRecruitment> selectYspsRecruitmentList(YspsRecruitment yspsRecruitment)
    {
        return yspsRecruitmentMapper.selectYspsRecruitmentList(yspsRecruitment);
    }

    /**
     * 新增招聘信息
     * 
     * @param yspsRecruitment 招聘信息
     * @return 结果
     */
    @Override
    public int insertYspsRecruitment(YspsRecruitment yspsRecruitment)
    {
        return yspsRecruitmentMapper.insertYspsRecruitment(yspsRecruitment);
    }

    /**
     * 修改招聘信息
     * 
     * @param yspsRecruitment 招聘信息
     * @return 结果
     */
    @Override
    public int updateYspsRecruitment(YspsRecruitment yspsRecruitment)
    {
        return yspsRecruitmentMapper.updateYspsRecruitment(yspsRecruitment);
    }

    /**
     * 批量删除招聘信息
     * 
     * @param jobIds 需要删除的招聘信息主键
     * @return 结果
     */
    @Override
    public int deleteYspsRecruitmentByJobIds(Long[] jobIds)
    {
        return yspsRecruitmentMapper.deleteYspsRecruitmentByJobIds(jobIds);
    }

    /**
     * 删除招聘信息信息
     * 
     * @param jobId 招聘信息主键
     * @return 结果
     */
    @Override
    public int deleteYspsRecruitmentByJobId(Long jobId)
    {
        return yspsRecruitmentMapper.deleteYspsRecruitmentByJobId(jobId);
    }
}
