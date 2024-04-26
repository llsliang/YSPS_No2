package com.ysps.ysps.service;

import java.util.List;
import com.ysps.ysps.domain.YspsRecruitment;

/**
 * 招聘信息Service接口
 * 
 * @author lins
 * @date 2024-04-15
 */
public interface IYspsRecruitmentService 
{
    /**
     * 查询招聘信息
     * 
     * @param jobId 招聘信息主键
     * @return 招聘信息
     */
    public YspsRecruitment selectYspsRecruitmentByJobId(Long jobId);

    /**
     * 查询招聘信息列表
     * 
     * @param yspsRecruitment 招聘信息
     * @return 招聘信息集合
     */
    public List<YspsRecruitment> selectYspsRecruitmentList(YspsRecruitment yspsRecruitment);

    /**
     * 新增招聘信息
     * 
     * @param yspsRecruitment 招聘信息
     * @return 结果
     */
    public int insertYspsRecruitment(YspsRecruitment yspsRecruitment);

    /**
     * 修改招聘信息
     * 
     * @param yspsRecruitment 招聘信息
     * @return 结果
     */
    public int updateYspsRecruitment(YspsRecruitment yspsRecruitment);

    /**
     * 批量删除招聘信息
     * 
     * @param jobIds 需要删除的招聘信息主键集合
     * @return 结果
     */
    public int deleteYspsRecruitmentByJobIds(Long[] jobIds);

    /**
     * 删除招聘信息信息
     * 
     * @param jobId 招聘信息主键
     * @return 结果
     */
    public int deleteYspsRecruitmentByJobId(Long jobId);
}
